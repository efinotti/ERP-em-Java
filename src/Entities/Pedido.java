package erpapp.Entities;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private int id_cliente;
    private Date dt_pedido = new Date();
    private Date dt_entrega = new Date();
    private float vlr_total;
    
    private ArrayList<ItemPedido> itensPedido = new ArrayList<>();

    public Pedido(int id, int id_cliente) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.vlr_total = 0.0f;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        int novoIdItem = itensPedido.isEmpty() ? 1 : itensPedido.getLast().getId() + 1;
        
        ItemPedido novoItem = new ItemPedido(novoIdItem, produto, quantidade);
        this.itensPedido.add(novoItem);
        
        recalcularTotal();
    }

    public void recalcularTotal() {
        float soma = 0;
        for (ItemPedido item : itensPedido) {
            soma += item.getProduto().getPreco() * item.getQuantidade();
        }
        this.vlr_total = soma;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getId_cliente() { return id_cliente; }
    public void setId_cliente(int id_cliente) { this.id_cliente = id_cliente; }

    public Date getDt_pedido() { return dt_pedido; }
    public void setDt_pedido(Date dt_pedido) { this.dt_pedido = dt_pedido; }

    public Date getDt_entrega() { return dt_entrega; }
    public void setDt_entrega(Date dt_entrega) { this.dt_entrega = dt_entrega; }

    public float getVlr_total() { return vlr_total; }
    public void setVlr_total(float vlr_total) { this.vlr_total = vlr_total; }

    public ArrayList<ItemPedido> getItensPedido() { return itensPedido; }
    public void setItensPedido(ArrayList<ItemPedido> itensPedido) { this.itensPedido = itensPedido; }
}