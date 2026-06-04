package erpapp;

import Controller.MenuController;
import Controller.PedidoController;
import javax.swing.SwingUtilities;

import Repository.EPRRepository;
import Repository.PedidoRepository;
import View.MenuView;
import View.PedidoView;

public class ERPApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            PedidoView view = new PedidoView();
            PedidoRepository repository = new PedidoRepository();
            
            PedidoController controller = new PedidoController(repository, view);
            
        });
        /* Aqui eu to achando que vamos ter que criar uma View de um Menu
        Principal, e que ao escolher uma opção (ex: Pedido) ele invoka a View 
        correspondente.
        Coloquei um rascunho bem mal feito lá no View do Menu, só pra tentar 
        exemplificar o que to falando
        */
    }
}
   
