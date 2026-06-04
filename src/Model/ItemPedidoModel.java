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

    public ItemPedidoModel(PedidoModel pedido, int id, ProdutoModel produto, int quantidade) {
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
    
    public float getPrecoTotal(){
        return preco_total;
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
    
    @Override
    public String toString() {
        return String.format("%d;%d;%d;%d;%f\n", id, id_pedido, id_produto, quantidade, preco_total); 
    }
    
    
}
