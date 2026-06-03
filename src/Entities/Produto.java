package erpapp.Entities;

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private int quantidade;

    public Produto(int id, String nome, float preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        setPreco(preco);
        this.quantidade = quantidade;
    }

    public void manterEstoque(int valor, boolean remover) {
        if (remover) {
            if (this.quantidade >= valor) {
                this.quantidade -= valor;
            } else {
                System.err.println("Erro: Stock insuficiente para o produto " + this.nome);
            }
        } else {
            this.quantidade += valor;
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public float getPreco() { return preco; }
    
    public void setPreco(float preco) {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            this.preco = 0.0f;
            System.err.println("Aviso: O preço não pode ser negativo. Definido como 0.0");
        }
    }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    
    @Override
    public String toString() {
        return this.nome + " - " + String.format("%.2f€", this.preco) + " (Stock: " + this.quantidade + ")";
    }
}