/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erpapp.Model;

/**
 *
 * @author enzo
 */
public class ItemPedido {
    private int id_pedido;
    private int id;
    private int id_produto;
    private int quantidade;
    private float preco_uni;
    private float preco_total;

    public ItemPedido(Pedido pedido, int id, Produto produto, int quantidade,float preco_uni, float preco_total) {
        this.id_pedido = pedido.getId();
        this.id = id;
        this.id_produto = produto.getId();
        this.quantidade = quantidade;
        this.preco_uni = preco_uni;
        this.preco_total = preco_total;
    }
    
    /**
     * @return the id
     */
    public int getIdPedido(){
        return id_pedido;
    }
    
    public int getId() {
        return id;
    }
    
    /**
     * @return the id of the product
     */
    public int getIdProduto() {
        return id_produto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }
    
    public float getPrecoUni(){
        return preco_uni;
    }
    
    public float getPrecoTotal(){
        return preco_total;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setPrecoUni(float preco_uni){
        this.preco_uni = preco_uni;
    }
    
    public void setPrecoTotal(float preco_total){
        this.preco_total = preco_total;
    }
    
}
