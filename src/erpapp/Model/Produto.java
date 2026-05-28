package erpapp.Model;

import java.util.ArrayList;

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private int quantidade;
    private ArrayList<Produto> listaProduto = new ArrayList<>();
    
    
    public void incluir(Produto p){
        listaProduto.add(p);
        armazenar();
    }
    
    public void consultar(){
        String termo = JOptionPane.showInputDialog(view,"Digite o nome do produto para buscar...");
        if(termo != null && !termo.trim().isEmpty()){
            listModel.clear();
            for(Produto p : model.listaProduto){
                if(p.getNome().toLowerCase().contains(termo.toLowerCase())){
                    listModel.addElement(p);
                }
            }
        if(listModel.isEmpty()){
            JOptionPane.showMessageDialog(view,"Nenhum produto encontrado.");
        }
        }else{
            return;
        }
    }
    
    public void listar(){
        
    }
    
    public void alterar(){
        
    }
    
    public void remover(Produto p){
        listaProduto.remove(p);
        armazenar();
    }
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
    
}
