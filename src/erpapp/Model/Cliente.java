/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erpapp.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import Util.ValiadorUtil;


/**
 *
 * @author enzo
 */
public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Cliente(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        try{
           setCPF(cpf);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    public void adicionarPedidos() {
        int id_pedido;
        
        if (pedidos.isEmpty()) {
            id_pedido = 1;
        } else {
            id_pedido = pedidos.getLast().getId() + 1;
        } // Adiciona PRIMARY KEY (ID) PARA O PEDIDO
        
        Pedido pedido = new Pedido(id_pedido, this); // Instancia o PEDIDO com o ID dele
        pedidos.add(pedido);
        
    }
    
   /* 
    
    public Cliente verificarClienteExiste(ArrayList<Cliente> clientes, String cpf) throws NullPointerException {
        for (Cliente cliente: clientes){
            if (cliente.getCPF().equals(cpf)){
                return cliente;
            }
        }
        
        throw new NullPointerException("CPF nao encontrado!");
    }
    */

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
    
    public String getCPF() {
        return cpf;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public void setCPF (String cpf) throws Exception {
        if (ValiadorUtil.validadorCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new Exception("CPF inválido! Impossivel criar o Cliente");
        }
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
