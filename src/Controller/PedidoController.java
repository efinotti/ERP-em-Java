package Controller;

import Model.ItemPedidoModel;
import Model.PedidoModel;
import Model.ProdutoModel;
import Repository.ItemPedidoRepository;
import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import TableModel.ItemPedidoTableModel;
import TableModel.PedidoTableModel;
import TableModel.ProdutoTableModel;
import View.ConfirmarPedidoView;
import View.DetalhePedidoView;
import View.PedidoView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class PedidoController {
    private PedidoRepository repository;
    private PedidoView view;
    private ItemPedidoRepository itemPedidoRepository;
    private DetalhePedidoView detalhePedidoView;
    private ConfirmarPedidoView confirmarPedidoView;
    
    ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository repository, PedidoView view, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.view = view;
        this.produtoRepository = produtoRepository;
        
        this.itemPedidoRepository = new ItemPedidoRepository();
        
        this.detalhePedidoView = new DetalhePedidoView(view, true);
        
        this.confirmarPedidoView = new ConfirmarPedidoView(view, true);
        
        ajustarTela();
        setarEventos();
        
        this.view.setVisible(true);
    }
    
    public void ajustarTela() {
        DefaultListModel<?> originalRepository = repository.getLista(); 
        PedidoTableModel dataModel = new PedidoTableModel(originalRepository);
        view.getTabela().setModel(dataModel);
        
        DefaultListModel<ItemPedidoModel> itemPedidoList = itemPedidoRepository.getList();
        ItemPedidoTableModel itemTable = new ItemPedidoTableModel(itemPedidoList);
        detalhePedidoView.getjTable1().setModel(itemTable);
        
        DefaultListModel<ProdutoModel> produtoList = produtoRepository.getListaProdutos();
        ProdutoTableModel produtoTable = new ProdutoTableModel(produtoList);
        confirmarPedidoView.getTableProdutos().setModel(produtoTable);
    }
    
    public void setarEventos() {
        inserir(); 
        salvar();
        alterar();
        remover();
    }
    
    public void inserir() {
        view.getInserirBtn().addActionListener(e -> {
            detalhePedidoView.setVisible(true);
        });

        detalhePedidoView.getInserirBtn1().addActionListener(l -> {
            confirmarPedidoView.setVisible(true);
        });         
        
        confirmarPedidoView.getConfirmarBttn().addActionListener(e -> {
            int linhaSelecionada = confirmarPedidoView.getTableProdutos().getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(confirmarPedidoView, "Selecione um produto para adicionar!");
                return;
            }

            String quantidadeTexto = confirmarPedidoView.getjTextField1().getText();
            int quantidade;
            try {
                quantidade = Integer.parseInt(quantidadeTexto);
                if (quantidade <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(confirmarPedidoView, "Quantidade inválida!");
                return;
            }

            ProdutoModel produto = produtoRepository.getListaProdutos().getElementAt(linhaSelecionada);
            
            if (quantidade > produto.getQuantidade()) {
                JOptionPane.showMessageDialog(confirmarPedidoView, "Quantidade maior do que o estoque disponível!");
                return;
            }

            int idPedidoAtual = repository.getLista().isEmpty() ? 1 : repository.getLista().lastElement().getId() + 1;
            int novoIdItem = itemPedidoRepository.getList().isEmpty() ? 1 : itemPedidoRepository.getList().lastElement().getId() + 1;

            ItemPedidoModel novoItem = new ItemPedidoModel(novoIdItem, idPedidoAtual, produto.getId(), quantidade);
            itemPedidoRepository.getList().addElement(novoItem);
            
            produto.setQuantidade(produto.getQuantidade()- quantidade);
            
            confirmarPedidoView.setVisible(false);
            JOptionPane.showMessageDialog(detalhePedidoView, "Item adicionado com sucesso!");
        });

        confirmarPedidoView.getCancelarBttn().addActionListener(e -> {
            confirmarPedidoView.setVisible(false);
        });
    }
    
    public void alterar() {
        view.getAlterarBtn().addActionListener(e -> {
            int linhaSelecionada = view.getTabela().getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(view, "Selecione um pedido para alterar!");
                return;
            }
            PedidoModel pedido = repository.getLista().getElementAt(linhaSelecionada);
            
            String novoIdClienteStr = JOptionPane.showInputDialog(view, "ID do Cliente Atual: " + pedido.getId_cliente() + "\nDigite o novo ID do Cliente:");
            if (novoIdClienteStr != null && !novoIdClienteStr.trim().isEmpty()) {
                try {
                    int novoIdCliente = Integer.parseInt(novoIdClienteStr);
                    pedido.setId_cliente(novoIdCliente);
                    repository.salvar();
                    view.getTabela().repaint();
                    JOptionPane.showMessageDialog(view, "Pedido alterado com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "ID do Cliente inválido!");
                }
            }
        });
    }
    
    public void remover() {
        view.getExcluirBtn().addActionListener(e -> {
            int linhaSelecionada = view.getTabela().getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(view, "Selecione um pedido para excluir!");
                return;
            }

            int confirma = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir este pedido?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                PedidoModel pedido = repository.getLista().getElementAt(linhaSelecionada);
                
                ArrayList<ItemPedidoModel> itensParaRemover = new ArrayList<>();
                DefaultListModel<ItemPedidoModel> listaItens = itemPedidoRepository.getList();
                
                for (int i = 0; i < listaItens.size(); i++) {
                    ItemPedidoModel item = listaItens.getElementAt(i);
                    if (item.getIdPedido() == pedido.getId()) {
                        itensParaRemover.add(item);
                        
                        ProdutoModel produto = produtoRepository.consultarPorId(item.getIdProduto());
                        if (produto != null) {
                            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
                        }
                    }
                }
                
                for (ItemPedidoModel item : itensParaRemover) {
                    listaItens.removeElement(item);
                }
                
                repository.getLista().removeElement(pedido);
                
                repository.salvar();
                itemPedidoRepository.salvar();
                produtoRepository.salvar();
                
                JOptionPane.showMessageDialog(view, "Pedido excluído e estoque restaurado!");
            }
        });
    }
    
    public void salvar() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Fechando a janela... Salvando dados.");
                repository.salvar(); 
                itemPedidoRepository.salvar();
                produtoRepository.salvar();
            }
        });
    }
}