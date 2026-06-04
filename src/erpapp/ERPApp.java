package erpapp;

import Controller.MenuController;
import javax.swing.SwingUtilities;

import Repository.EPRRepository;
import View.MenuView;

public class ERPApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuView view = new MenuView();
            EPRRepository model = new EPRRepository();
            MenuController controller = new MenuController(model, view);
        });
        /* Aqui eu to achando que vamos ter que criar uma View de um Menu
        Principal, e que ao escolher uma opção (ex: Pedido) ele invoka a View 
        correspondente.
        Coloquei um rascunho bem mal feito lá no View do Menu, só pra tentar 
        exemplificar o que to falando
        */
    }
}
   
