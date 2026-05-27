/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erpapp.Model;

import java.util.ArrayList;
import java.util.Date;
import ERPApp.Repository.PedidoRepository;

/**
 *
 * @author enzo
 */
public class Pedido {
    
    private ArrayList<ItemPedido> itensPedido = new ArrayList<>();
    private ArrayList<Pedido> listaPedido = new ArrayList<>();
    private final String arquivo = "pedidos.csv";
    private int id;
    private int id_cliente;
    private Date dt_pedido = new Date();
    private Date dt_entrega = new Date();
    private float vlr_total;

    public Pedido(int id, Cliente cliente,Date dt_pedido, Date dt_entrega, float vlr_total) {
        this.id = id;
        this.id_cliente = cliente.getId();
        this.dt_pedido = dt_pedido;
        this.dt_entrega = dt_entrega;
        this.vlr_total = vlr_total;
    }
    
    public void adicionarItemPedido (Produto produto, int quantidade) {
        int id;
        if (getItensPedido().isEmpty()) {
            id = 1;
        } else {
            id = getItensPedido().getLast().getId() + 1;
        }
        
        ItemPedido itemPedido = new ItemPedido(id, produto, quantidade);
        getItensPedido().add(itemPedido);
    }
    public void incluir(Pedido p){
        listaPedido.add(p);
        armazenar();
    }
    
    public void alterar(Produto produto){
        Pedido selecionado = view.getListPedido().getSelectedValue();
        if(selecionado != null){
            DetalhePedido dialog = new DetalhePedido(view, true);
            dialog.getTxt
        }
    }
    
    public void excluir(Pedido p){
        listaPedido.remove(p);
        armazenar();      
    }
    
    public void consultar(){
       String termo = JOptionPane.showInputDialog(view,"Digite o ID para buscar o pedido...");
       if(termo != null && !termo.trim().isEmpty()){
           try{
               int IdBusca = Integer.parseInt(termo.trim());
               listModel.clear();
               boolean encontrado = false;
               
               for(Pedido p : model.getListPedido()){
                 if(p.getId() == IdBusca){
                     listModel.addElement(p);
                     encontrado = true;
                     break;
                 }
           }
               if(!encontrado){
                   JOptionPane.showMessageDialog(view,"Pedido com o ID: " + IdBusca + " não foi encontrado.");
               }
           }catch(NumberFormatExcpetion e){
               JOptionPane.showMessageDialog(view, "Erro: Digite apenas números válidos para o ID." + "Erro de Digitação: " + JOptionPane.ERROR_MESSAGE);
           }
       }else{
           return;
       }
    }
    
    public void listar(){
        if(listaPedido.isEmpty()){
            System.out.println("Não há pedidos.");
        }else{
            for(Pedido p: listaPedido){
                listModel.addElement(p); // listModel está lá no controller
            }
        }
    }
        /* Pensando em POO, basicamente faço uma Composição.
        * Caso o pedido seja destruido, itemPedido eh destruido
        */


    /**
     * @return the itensPedido
     */
    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    
    public List<Pedido> getListPedido(){
        return listaPedido;
    }

    /**
     * @return the id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @param itensPedido the itensPedido to set
     */
    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * @return the dt_pedido
     */
    public Date getDt_pedido() {
        return dt_pedido;
    }

    /**
     * @param dt_pedido the dt_pedido to set
     */
    public void setDt_pedido(Date dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    /**
     * @return the dt_entrega
     */
    public Date getDt_entrega() {
        return dt_entrega;
    }

    /**
     * @param dt_entrega the dt_entrega to set
     */
    public void setDt_entrega(Date dt_entrega) {
        this.dt_entrega = dt_entrega;
    }

    /**
     * @return the vlr_total
     */
    public float getVlr_total() {
        return vlr_total;
    }

    /**
     * @param vlr_total the vlr_total to set
     */
    public void setVlr_total(float vlr_total) {
        this.vlr_total = vlr_total;
    }
}
