package Model;

public class ItemPedidoModel {

    private PedidoModel pedido;
    private int id_pedido;
    private int id;
    private ProdutoModel produto;
    private int id_produto;
    private int quantidade;
    private float preco_uni;
    private float preco_total;

    public ItemPedidoModel(int id, int id_pedido, ProdutoModel produto, int quantidade) {
        this.id = id;
        this.id_pedido = id_pedido;
        this.produto = produto;
        
        if (produto != null) {
            this.id_produto = produto.getId();
            this.preco_uni = produto.getPreco();
        }
        
        this.quantidade = quantidade;
        
        calcularPrecoTotal();
    }

    private void calcularPrecoTotal() {
        this.preco_total = this.preco_uni * this.quantidade;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
        if (pedido != null) {
            this.id_pedido = pedido.getId();
        }
    }

    public int getIdPedido() {
        return id_pedido;
    }

    public void setIdPedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
        if (produto != null) {
            this.id_produto = produto.getId();
            this.preco_uni = produto.getPreco();
            calcularPrecoTotal();
        }
    }

    public int getIdProduto() {
        return id_produto;
    }

    public void setIdProduto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularPrecoTotal();
    }

    public float getPreco_uni() {
        return preco_uni;
    }

    public void setPreco_uni(float preco_uni) {
        this.preco_uni = preco_uni;
        calcularPrecoTotal();
    }

    public float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }
    
    public int getId() {
        return this.id;
    }
        
    public void setId(int id) {
        this.id = id;
    }
    
    public float getPrecoTotal(){
        return getPreco_total();
    }
    
    @Override
    public String toString() {
        return String.format("%d;%d;%d;%d", getId(), getIdPedido(), getIdProduto(), getQuantidade()); 
    }
}