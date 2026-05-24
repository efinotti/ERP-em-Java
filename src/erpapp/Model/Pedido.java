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
    private ArrayList<Pedido> listaPedido = new ArrayList<>();
    private final String ARQUIVO = "pedidos.csv";
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
        
        ItemPedido itemPedido = new ItemPedido(id, produto, quantidade);
        getItensPedido().add(itemPedido);
    }
    
    public void incluir(Pedido p){
        listaPedido.add(p);
        armazenar();
    }
    
    public void alterar(){
        
    }
    
    public void excluir(){
        
    }
    
    public void consultar(){
        
    }
    
    public void listar(){
        
    }
    
    public void armazenar(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(Arquivo))){
            for(Pedido p: listaPedido){
                writer.println(p.getId() + ";" + p.getCliente() + ";" + p.getProduto() + ";" + p.getValor());
            }
        }catch(IOException e){
            System.out.println("Erro ao salvar arquivo:" + e.getMessage());
        }
    }
    
    public void recuperar(){
        
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
    
    public List<Pedido> getListPedido(){
        return listaPedido;
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
