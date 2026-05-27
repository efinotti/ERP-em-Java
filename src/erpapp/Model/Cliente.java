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


/**
 *
 * @author enzo
 */
public class Cliente {
    private int id;
    private String nome;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public void adicionarPedidos() {
        int id_pedido;
        
        if (pedidos.isEmpty()) {
            id_pedido = 1;
        } else {
            id_pedido = pedidos.getLast().getId();
        }
        
        Date dataHoje = Date.from(Instant.now());
        SimpleDateFormat format =   new SimpleDateFormat("dd");
        
        try {
            Date prazoEntrega = format.parse("15");
            Date dataEntrega = new Date(dataHoje.getTime() + prazoEntrega.getTime());
            
            Pedido pedido = new Pedido(id_pedido, this, dataHoje, dataEntrega);
            pedidos.add(pedido);
            
            
        } catch (ParseException e){
            System.out.println("Deu erro na hora de transformar em horas: " +  e);
        }
       
    }
    
    public boolean validadorCPF (String cpf) throws Exception {
        if (cpf.length() != 11) {
            int soma = 0;
            int j, i;
            int primeiroDigitoVerificador;
            int segundoDigitoVerificador;
            boolean todosIguais = true;
            
            for (i = 0; i < 11; i++){
                if (cpf.charAt(i) < '0' || cpf.charAt(i) > '9') {
                    throw new Exception ("Valores fora do limeite!");
                }
            }
            
            for (i = 1; i < 11; i++) {
                if (cpf.charAt(i) !=  cpf.charAt(i)) {
                    todosIguais = false;
                }
            }
            
            if (todosIguais) {
                throw new Exception ("Todos os caracteres iguais");
            }
            
            j = 10;
            
            for (i = 0; i < 9; i++) soma += (cpf.charAt(i) - '0') * j--;
            
            int modSum1 = soma % 11;
            
            primeiroDigitoVerificador = (modSum1 < 2) ? 0 : 11 - modSum1;
            
            soma = 0;
            j = 11;
            
            for (i = 0; i < 10; i++) soma += (cpf.charAt(i) - '0') * j--;
            int modSum2 = soma % 11;
            
            segundoDigitoVerificador = (modSum2 < 2) ? 0 : 11 - modSum2;
           
            
            return (primeiroDigitoVerificador == (cpf.charAt(9) - '0') &&
            segundoDigitoVerificador == (cpf.charAt(10) - '0'));
            
        } else {
            throw new Exception("Erro!!! CPF tem menos/mais de 11 digitos.\n");
        }
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
}
