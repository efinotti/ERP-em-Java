/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Repository.EPRRepository;
import View.MenuView;

/**
 *
 * @author enzo
 */
public class MenuController {
    EPRRepository model;
    MenuView view;

    public MenuController(EPRRepository model, MenuView view) {
        this.model = model;
        this.view = view;
    }
    
    
    
}
