package erpapp.Model;

import java.util.ArrayList;

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private int quantidade;
    private ArrayList<Produto> listaProduto = new ArrayList<>();
    private final String arquivo = "produtos.csv";
   
    public Produto(){
        ArquivoUtil.recuperar(listaProduto);
    }
    
    public Produto(int id, String nome, float preco, int quantidade){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public void incluir(Produto p){
        listaProduto.add(p);
        ArquivoUtil.armazenar(listaProduto);
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
        if(listaProduto.isEmpty()){
            System.out.println("Não há produtos.");
        }else{
            for(Pedido p: listaProduto){
                listModel.addElement(p); // listModel está lá no controller
            }
        }
    }
    
    public void alterar(Produto produtoAtualizado){
        for (Produto pOriginal : listaProduto) {
            if (pOriginal.getId() == produtoAtualizado.getId()) {
                pOriginal.setNome(produtoAtualizado.getNome());
                pOriginal.setPreco(produtoAtualizado.getPreco());
                pOriginal.setQuantia(produtoAtualizado.getQuantidade());
                
                ArquivoUtil.armazenar(listaProduto);
                return;
            }
        }
    }
    
    public void remover(Produto p){
        listaProduto.removeIf(produto -> produto.getId() == p.getId());
        ArquivoUtil.armazenar(listaProduto);
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
    
}
