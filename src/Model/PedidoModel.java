package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PedidoModel {
    private ArrayList<ItemPedidoModel> itensPedido = new ArrayList<>();
    private int id;
    private int id_cliente;
    private Date dt_pedido;
    private Date dt_entrega;
    private float vlr_total;

    public PedidoModel(int id, ClienteModel cliente) {
        this.id = id;
        if (cliente != null) {
            this.id_cliente = cliente.getId();
        }
        this.dt_pedido = new Date();
        this.dt_entrega = new Date();
        this.vlr_total = 0.0f;
    }

    public PedidoModel(int id, int id_cliente, Date dt_pedido, Date dt_entrega, float vlr_total) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.dt_pedido = dt_pedido;
        this.dt_entrega = dt_entrega;
        this.vlr_total = vlr_total;
    }

    public void adicionarItemPedido(ProdutoModel produto, int quantidade) {
        int novoId = itensPedido.isEmpty() ? 1 : itensPedido.get(itensPedido.size() - 1).getId() + 1;
        int id_produto = produto.getId();
        ItemPedidoModel novoItem = new ItemPedidoModel(novoId, this.id, id_produto, quantidade);
        itensPedido.add(novoItem);
        calcularValorTotal();
    }

    public void removerItemPedido(ItemPedidoModel item) {
        itensPedido.remove(item);
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        this.vlr_total = 0.0f;
        for (ItemPedidoModel item : itensPedido) {
            this.vlr_total += item.getPrecoTotal();
        }
    }

    public ArrayList<ItemPedidoModel> getItensPedido() {
        return itensPedido;
    }

    public int getId() {
        return id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setItensPedido(ArrayList<ItemPedidoModel> itensPedido) {
        this.itensPedido = itensPedido;
        calcularValorTotal();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getDt_pedido() {
        return dt_pedido;
    }

    public void setDt_pedido(Date dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    public Date getDt_entrega() {
        return dt_entrega;
    }

    public void setDt_entrega(Date dt_entrega) {
        this.dt_entrega = dt_entrega;
    }

    public float getVlr_total() {
        return vlr_total;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%d;%d;%s;%s;%.2f", id, id_cliente,
                sdf.format(dt_pedido), sdf.format(dt_entrega), vlr_total);
    }
}