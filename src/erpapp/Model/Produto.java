package erpapp.Model;

public class Produto {
    private int id;
    private String nome;
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

    /**
     * @param quantidade the quantidade to set
     */
    public void manterEstoque(int valor, boolean remover) {
        if (remover) {
            
        } else {
            
        }
    }
    
}
