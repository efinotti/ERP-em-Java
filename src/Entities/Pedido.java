/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.ArrayList;
import java.util.Date;

/*
import Util.ArquivoUtil;

/**
 *
 * @author enzo
 */
public class Pedido {
    
    private ArrayList<ItemPedido> itensPedido = new ArrayList<>();
    private ArrayList<Pedido> listaPedido = new ArrayList<>();
    private final String ARQUIVO = "pedidos.csv";
    private int id;
    private int id_cliente;
    private Date dt_pedido = new Date();
    private Date dt_entrega = new Date();
    private float vlr_total;

    public Pedido(int id, Cliente cliente, Produto produto) {
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
        
        // ItemPedido itemPedido = new ItemPedido(id, produto, quantidade);
        // getItensPedido().add(itemPedido);
    }
    public void incluir(Pedido p){
        listaPedido.add(p);
        // armazenar(); // Está função está lá no repositório de pedido
    }
    
    public void alterar(){
        
    }
    
    public void excluir(Pedido p){
        listaPedido.remove(p);
        /*armazenar(); /* Esta função está lá no Util Arquivo
                    visto que a opção de salvarDados se encontra naquele package
                    */       
    }
    
    public void consultar(){
       
    }
    
    public void listar(){
        if(listaPedido.isEmpty()){
            System.out.println("Não há pedidos.");
        }else{
            for(Pedido p: listaPedido){
                // listModel.addElement(p); // listModel está lá no controller
            }
        }
    }
        /* Pensando em POO, basicamente faço uma Composição.
        * Caso o pedido seja destruido, itemPedido eh destruido
        */


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
    
    
    /*public List<Pedido> getListPedido(){
        return listaPedido;
    } */

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
}
