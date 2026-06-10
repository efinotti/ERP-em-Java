package Controller;

import View.MenuView;
import View.PedidoView;
import View.ProdutoView;
import View.ClienteView;

import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import Repository.ClienteRepository;

public class MenuController {
    private final MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        initListeners();
    }

    private void initListeners() {
        this.view.getGerirClientesBtn().addActionListener(e -> abrirModuloClientes());
        this.view.getGerirPedidosBtn().addActionListener(e -> abrirModuloPedidos());
        this.view.getGerirProdutosBtn().addActionListener(e -> abrirModuloProdutos());
    }

    private void abrirModuloClientes() {
        ClienteView clienteView = new ClienteView();
        ClienteRepository clienteRepo = new ClienteRepository();
        new ClienteController(clienteView, clienteRepo); 
        
        clienteView.setVisible(true);
    }

    private void abrirModuloPedidos() {
        PedidoView pedidoView = new PedidoView();
        PedidoRepository pedidoRepo = new PedidoRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        ClienteRepository clienteRepo = new ClienteRepository();
        
        new PedidoController(pedidoRepo, pedidoView, produtoRepo, clienteRepo);
        pedidoView.setVisible(true);
    }

    private void abrirModuloProdutos() {
        ProdutoView produtoView = new ProdutoView();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        
        new ProdutoController(produtoRepo, produtoView);
        produtoView.setVisible(true);
    }
}