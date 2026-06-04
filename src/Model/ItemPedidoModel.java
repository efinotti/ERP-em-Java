package Model;


public class ItemPedidoModel {

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
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

    public float getPreco_uni() {
        return preco_uni;
    }

    public void setPreco_uni(float preco_uni) {
        this.preco_uni = preco_uni;
    }

    public float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }
    

    private PedidoModel pedido;
    private int id_pedido;
    private int id;
    private ProdutoModel produto;
    private int id_produto;
    private int quantidade;
    private float preco_uni;
    private float preco_total;

    public ItemPedidoModel(int id, int id_pedido, int id_produto, int quantidade) {
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
        
    
    public void setId(int id) {
        this.id = id;
    }
    
    public float getPrecoTotal(){
        return getPreco_total();
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setPrecoUni(float preco_uni) throws ArithmeticException{
        if (getPreco_total() <= 0) {
            throw new ArithmeticException("Preço menor ou igual a zero");
        }
        this.setPreco_uni(preco_uni);
    }
    
    public void setPrecoTotal(float preco_total) throws ArithmeticException{
        
        if (getQuantidade() <= 0) {
            this.setPreco_total(this.getPreco_uni() * getQuantidade());
        } else {
            throw new ArithmeticException("Quantidade deve ser igual ou maior que um");
        }
        
    }
    
    @Override
    public String toString() {
        
        return String.format("%d;%d;%d;%d", getId(), getIdPedido(), getIdProduto(), getQuantidade()); 
    }
    
    
}
