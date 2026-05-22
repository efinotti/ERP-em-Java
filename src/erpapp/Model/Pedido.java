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
public class Pedido {
    
    private ArrayList<ItemPedido> itensPedido = new ArrayList<>();
    private int id;
    private int id_cliente;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.id_cliente = cliente.getId();
    }
    
    public void adicionarItemPedido (Produto produto, int quantidade) {
        int id;
        if (getItensPedido().isEmpty()) {
            id = 1;
        } else {
            id = getItensPedido().getLast().getId() + 1;
        }
        
        /* Pensando em POO, basicamente faço uma Composição.
        * Caso o pedido seja destruido, itemPedido eh destruido
        */
        ItemPedido itemPedido = new ItemPedido(id, produto, quantidade);
        
        getItensPedido().add(itemPedido);
    }

    /**
     * @return the itensPedido
     */
    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @param itensPedido the itensPedido to set
     */
    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
