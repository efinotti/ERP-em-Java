package erpapp;

import Controller.MenuController;
import javax.swing.SwingUtilities;
import View.MenuView;

public class ERPApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Instancia a tela do Menu Principal (MenuView)
            MenuView menuView = new MenuView();
            
            // 2. Inicializa o controlador do menu passando a view correspondente
            MenuController menuController = new MenuController(menuView);
            
            // 3. Torna a janela do Menu Principal visível para o usuário
            menuView.setVisible(true);
        });
    }
}