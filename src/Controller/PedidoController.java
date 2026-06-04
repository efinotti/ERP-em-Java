package Controller;

import Model.ItemPedidoModel;
import Model.ProdutoModel;
import Repository.ItemPedidoRepository;
import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import TableModel.ItemPedidoTableModel;
import TableModel.ProdutoTableModel;
import View.ConfirmarPedidoView;
import View.DetalhePedidoView;
import View.PedidoView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;

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
    }
    
    public void alterar() {
        
    }
    
    public void remover() {
        view.getExcluirBtn().addActionListener(e -> {
            
        });
    }
    
    public void salvar() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Fechando a janela... Salvando dados.");
                repository.salvar(); 
                itemPedidoRepository.salvar();
            }
        });
    }
}