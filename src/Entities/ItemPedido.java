/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author enzo
 */
public class ItemPedido {

    private Pedido pedido;
    private int id_pedido;
    private int id;
    private Produto produto;
    private int id_produto;
    private int quantidade;
    private float preco_uni;
    private float preco_total;

    public ItemPedido(Pedido pedido, int id, Produto produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.id = id;
        this.quantidade = quantidade;
        try{
            setPrecoUni(produto.getPreco());
            setPrecoTotal(preco_total);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
        
    }
    
    public int getId() {
        return this.id;
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
    
    public void setPrecoUni(float preco_uni) throws ArithmeticException{
        if (preco_total <= 0) {
            throw new ArithmeticException("Preço menor ou igual a zero");
        }
        this.preco_uni = preco_uni;
    }
    
    public void setPrecoTotal(float preco_total) throws ArithmeticException{
        
        if (quantidade <= 0) {
            this.preco_total = this.preco_uni * quantidade;
        } else {
            throw new ArithmeticException("Quantidade deve ser igual ou maior que um");
        }
        
    }
    
    
}
