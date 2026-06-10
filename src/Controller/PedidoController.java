package Controller;

import Model.ItemPedidoModel;
import Model.PedidoModel;
import Model.ProdutoModel;
import Model.ClienteModel;
import Repository.ItemPedidoRepository;
import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import Repository.ClienteRepository;
import TableModel.ItemPedidoTableModel;
import TableModel.PedidoTableModel;
import TableModel.ProdutoTableModel;
import View.AdicionarItemPedidoView;
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
    private AdicionarItemPedidoView adicionarItemPedidoView;
    private ProdutoRepository produtoRepository;
    private ClienteRepository clienteRepository;
    private DefaultListModel<ItemPedidoModel> carrinhoTemporario;
    
    private PedidoModel pedidoEmEdicao = null;

    public PedidoController(PedidoRepository repository, PedidoView view, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.view = view;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.itemPedidoRepository = new ItemPedidoRepository();
        this.detalhePedidoView = new DetalhePedidoView(view, true);
        this.adicionarItemPedidoView = new AdicionarItemPedidoView(view, true);
        this.carrinhoTemporario = new DefaultListModel<>();

        ajustarTela();
        setarEventos();

        this.view.setVisible(true);
    }

    public void ajustarTela() {
        DefaultListModel<?> originalRepository = repository.getLista();
        PedidoTableModel dataModel = new PedidoTableModel(originalRepository);
        view.getTabela().setModel(dataModel);

        ItemPedidoTableModel itemTable = new ItemPedidoTableModel(carrinhoTemporario);
        detalhePedidoView.getjTable1().setModel(itemTable);
        detalhePedidoView.getjTable1().createDefaultColumnsFromModel();

        detalhePedidoView.getjTable1().getTableHeader().setReorderingAllowed(false);

        String[] titulosCorretos = {"ID", "Nome", "Quantidade", "Valor Total"};
        for (int i = 0; i < detalhePedidoView.getjTable1().getColumnCount(); i++) {
            if (i < titulosCorretos.length) {
                detalhePedidoView.getjTable1().getColumnModel().getColumn(i).setHeaderValue(titulosCorretos[i]);
            }
        }
        detalhePedidoView.getjTable1().getTableHeader().repaint();

        DefaultListModel<ProdutoModel> produtoList = produtoRepository.getListaProdutos();
        ProdutoTableModel produtoTable = new ProdutoTableModel(produtoList);
        adicionarItemPedidoView.getjTable2().setModel(produtoTable);
    }

    public void setarEventos() {
        inserir();
        salvar();
        alterar();
        remover();
        configurarRemocaoItemCarrinho();
    }

    public void inserir() {
        view.getInserirBtn().addActionListener(e -> {
            pedidoEmEdicao = null; 
            carrinhoTemporario.clear();
            detalhePedidoView.getCPFtxt().setText("");
            detalhePedidoView.setVisible(true);
        });

        detalhePedidoView.getInserirBtn1().addActionListener(l -> {
            adicionarItemPedidoView.setVisible(true);
        });

        adicionarItemPedidoView.getInserirBttn().addActionListener(e -> {
            int linhaSelecionada = adicionarItemPedidoView.getjTable2().getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(adicionarItemPedidoView, "Selecione um produto para adicionar!");
                return;
            }

            String quantidadeTexto = adicionarItemPedidoView.getQuantidadeField().getText();
            int quantidade;
            try {
                quantidade = Integer.parseInt(quantidadeTexto);
                if (quantidade <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adicionarItemPedidoView, "Quantidade inválida!");
                return;
            }

            ProdutoModel produto = produtoRepository.getListaProdutos().getElementAt(linhaSelecionada);

            if (quantidade > produto.getQuantidade()) {
                JOptionPane.showMessageDialog(adicionarItemPedidoView, "Quantidade maior do que o estoque disponível!");
                return;
            }

            int idPedidoAtual = (pedidoEmEdicao != null) ? pedidoEmEdicao.getId() : 
                                (repository.getLista().isEmpty() ? 1 : repository.getLista().lastElement().getId() + 1);
            
            int novoIdItem = itemPedidoRepository.getList().isEmpty() ? 1 : itemPedidoRepository.getList().lastElement().getId() + 1;
            novoIdItem += carrinhoTemporario.size();

            ItemPedidoModel novoItem = new ItemPedidoModel(novoIdItem, idPedidoAtual, produto, quantidade);
            carrinhoTemporario.addElement(novoItem);

            produto.setQuantidade(produto.getQuantidade() - quantidade);

            adicionarItemPedidoView.getQuantidadeField().setText("");
            adicionarItemPedidoView.setVisible(false);
            JOptionPane.showMessageDialog(detalhePedidoView, "Item adicionado com sucesso!");
        });

        detalhePedidoView.getRealizarBtn().addActionListener(e -> {
            if (carrinhoTemporario.isEmpty()) {
                JOptionPane.showMessageDialog(detalhePedidoView, "Adicione pelo menos um item antes de realizar o pedido!");
                return;
            }

            String cpfDigitado = detalhePedidoView.getCPFtxt().getText().trim();
            if (cpfDigitado.isEmpty()) {
                JOptionPane.showMessageDialog(detalhePedidoView, "Por favor, insira o CPF do cliente para finalizar!");
                return;
            }

            String cpfLimpo = cpfDigitado.replaceAll("[^0-9]", "");
            int idClienteEncontrado = -1;

            if (clienteRepository != null && clienteRepository.getClientes() != null) {
                for (int i = 0; i < clienteRepository.getClientes().size(); i++) {
                    Object obj = clienteRepository.getClientes().get(i);
                    if (obj instanceof ClienteModel) {
                        ClienteModel c = (ClienteModel) obj;
                        if (c.getCPF().replaceAll("[^0-9]", "").equals(cpfLimpo)) {
                            idClienteEncontrado = c.getId();
                            break;
                        }
                    }
                }
            }

            if (idClienteEncontrado == -1) {
                JOptionPane.showMessageDialog(detalhePedidoView, "Erro: O CPF '" + cpfDigitado + "' não corresponde a nenhum cliente cadastrado!");
                return;
            }

            ArrayList<ItemPedidoModel> itensDoPedido = new ArrayList<>();
            float valorTotalCalculado = 0.0f;

            if (pedidoEmEdicao == null) {
                int novoIdPedido = repository.getLista().isEmpty() ? 1 : repository.getLista().lastElement().getId() + 1;

                for (int i = 0; i < carrinhoTemporario.size(); i++) {
                    ItemPedidoModel item = carrinhoTemporario.get(i);
                    item.setIdPedido(novoIdPedido);
                    itensDoPedido.add(item);
                    valorTotalCalculado += item.getPrecoTotal();
                    itemPedidoRepository.getList().addElement(item);
                }

                java.util.Date dataPedido = new java.util.Date();
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(dataPedido);
                cal.add(java.util.Calendar.DAY_OF_MONTH, 7);
                java.util.Date dataEntrega = cal.getTime();

                PedidoModel novoPedido = new PedidoModel(novoIdPedido, idClienteEncontrado, dataPedido, dataEntrega, valorTotalCalculado);
                novoPedido.setItensPedido(itensDoPedido);

                repository.getLista().addElement(novoPedido);
            } else {
                DefaultListModel<ItemPedidoModel> listaItensRepo = itemPedidoRepository.getList();
                for (int i = listaItensRepo.size() - 1; i >= 0; i--) {
                    if (listaItensRepo.getElementAt(i).getIdPedido() == pedidoEmEdicao.getId()) {
                        listaItensRepo.removeElementAt(i);
                    }
                }

                for (int i = 0; i < carrinhoTemporario.size(); i++) {
                    ItemPedidoModel item = carrinhoTemporario.get(i);
                    itensDoPedido.add(item);
                    valorTotalCalculado += item.getPrecoTotal();
                    listaItensRepo.addElement(item);
                }

                pedidoEmEdicao.setId_cliente(idClienteEncontrado);
                pedidoEmEdicao.setItensPedido(itensDoPedido);
            }

            repository.salvar();
            itemPedidoRepository.salvar();
            produtoRepository.salvar();

            carrinhoTemporario.clear();
            detalhePedidoView.getCPFtxt().setText("");
            
            ajustarTela();
            detalhePedidoView.setVisible(false);
            
            String msg = (pedidoEmEdicao == null) ? "Pedido gerado com sucesso!" : "Pedido #" + pedidoEmEdicao.getId() + " atualizado com sucesso!";
            JOptionPane.showMessageDialog(view, msg);
            pedidoEmEdicao = null;
        });
    }

    public void alterar() {
        view.getAlterarBtn().addActionListener(e -> {
            int linhaSelecionada = view.getTabela().getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(view, "Selecione um pedido para alterar!");
                return;
            }
            
            pedidoEmEdicao = repository.getLista().getElementAt(linhaSelecionada);

            carrinhoTemporario.clear();
            ArrayList<ItemPedidoModel> itensDoPedido = new ArrayList<>();
            DefaultListModel<ItemPedidoModel> todosOsItens = itemPedidoRepository.getList();

            for (int i = 0; i < todosOsItens.size(); i++) {
                ItemPedidoModel item = todosOsItens.getElementAt(i);
                
                if (item.getIdPedido() == pedidoEmEdicao.getId()) {
                    if (item.getProduto() == null) {
                        ProdutoModel prod = produtoRepository.consultarPorId(item.getIdProduto());
                        try {
                            java.lang.reflect.Method setter = item.getClass().getMethod("setProduto", ProdutoModel.class);
                            setter.invoke(item, prod);
                        } catch (Exception ex) {
                        }
                    }
                    carrinhoTemporario.addElement(item);
                    itensDoPedido.add(item);
                }
            }
            
            pedidoEmEdicao.setItensPedido(itensDoPedido);

            String cpfCliente = "";
            if (clienteRepository != null && clienteRepository.getClientes() != null) {
                for (int i = 0; i < clienteRepository.getClientes().size(); i++) {
                    Object obj = clienteRepository.getClientes().get(i);
                    if (obj instanceof ClienteModel) {
                        ClienteModel c = (ClienteModel) obj;
                        if (c.getId() == pedidoEmEdicao.getId_cliente()) {
                            cpfCliente = c.getCPF();
                            break;
                        }
                    }
                }
            }
            detalhePedidoView.getCPFtxt().setText(cpfCliente);
            
            detalhePedidoView.setVisible(true);
        });
    }

    private void configurarRemocaoItemCarrinho() {
        detalhePedidoView.getExcluirBtn().addActionListener(e -> {
            int linhaSelecionada = detalhePedidoView.getjTable1().getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(detalhePedidoView, "Selecione um produto da lista para remover!");
                return;
            }

            ItemPedidoModel itemRemovido = carrinhoTemporario.getElementAt(linhaSelecionada);

            ProdutoModel produto = produtoRepository.consultarPorId(itemRemovido.getIdProduto());
            if (produto != null) {
                produto.setQuantidade(produto.getQuantidade() + itemRemovido.getQuantidade());
            }

            carrinhoTemporario.removeElementAt(linhaSelecionada);
            adicionarItemPedidoView.getjTable2().repaint();
            JOptionPane.showMessageDialog(detalhePedidoView, "Item removido do carrinho e estoque retornado!");
        });
    }

    public void remover() {
        view.getExcluirBtn().addActionListener(e -> {
            int líneaSelecionada = view.getTabela().getSelectedRow();
            if (líneaSelecionada == -1) {
                JOptionPane.showMessageDialog(view, "Selecione um pedido para excluir!");
                return;
            }

            int confirma = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir este pedido?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                PedidoModel pedido = repository.getLista().getElementAt(líneaSelecionada);

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
                ajustarTela();
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