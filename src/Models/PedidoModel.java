/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Entities.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/*
import Util.ArquivoUtil;

/**
 *
 * @author enzo
 */
public class PedidoModel {
    
    
    private DefaultListModel<Pedido> listaPedidos = new DefaultListModel<>();
    private final String ARQUIVO = "pedidos.csv";
    private Date dt_pedido = new Date();
    private Date dt_entrega = new Date();
    private float vlr_total;
    
    
    public void incluir(Cliente cliente, Produto produto){
        int id;
        
        if (listaPedidos.isEmpty()) {
            id = 0;
        } else {
            id = listaPedidos.lastElement().getId() - 1;
        }
        
        Pedido pedido = new Pedido(id, cliente, produto);
        
        
        listaPedidos.addElement(pedido);
    }
    
    public void alterar(){
    }
    
    public void excluir(int index){
        listaPedidos.remove(index);
    }
    
    /**
     * @return the id
     */
 

    /**
     * @return the dt_pedido
     */
    public Date getDt_pedido() {
        return dt_pedido;
    }

    /**
     * @param dt_pedido the dt_pedido to set
     */
    public void setDt_pedido(Date dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    /**
     * @return the dt_entrega
     */
    public Date getDt_entrega() {
        return dt_entrega;
    }

    /**
     * @param dt_entrega the dt_entrega to set
     */
    public void setDt_entrega(Date dt_entrega) {
        this.dt_entrega = dt_entrega;
    }

    /**
     * @return the vlr_total
     */
    public float getVlr_total() {
        return vlr_total;
    }

    /**
     * @param vlr_total the vlr_total to set
     */
    public void setVlr_total(float vlr_total) {
        this.vlr_total = vlr_total;
    }
    
    public DefaultListModel<Pedido> getListaPedidos() {
        return listaPedidos;
    }
}
