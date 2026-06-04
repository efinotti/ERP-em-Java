package Model;

public class ProdutoModel {

    float getPreco() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private int quantidade;    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    
    public float getPreco(){
        return preco;
    }
    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setPreco(float preco){
        this.preco = preco;
    }
    
    public void setQuantia(int quantidade){
        this.quantidade = quantidade;
    }
    
    /**
     * @param quantidade the quantidade to set
     */
    public void manterEstoque(int valor, boolean remover) {
        if (remover) {
            
        } else {
            
        }
    }
    
        @Override
    public String toString() {
        return String.format("%d;%s;%f;%d", id, nome, preco, quantidade); 
    }
  }
    
}
