/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erpapp.Model;

import java.util.ArrayList;

/**
 *
 * @author enzo
 */
public class Cliente {
    private int id;
    private String nome;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public void adicionarPedidos() {
        int id_pedido;
        
        if (pedidos.isEmpty()) {
            id_pedido = 1;
        } else {
            id_pedido = pedidos.getLast().getId();
        }
        
        Pedido pedido = new Pedido(id_pedido, this);
        pedidos.add(pedido);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
