/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Repository.PedidoRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import Util.ValidadorUtil;
import Repository.ClienteRepository.*;


/**
 *
 * @author enzo
 */
public class ClienteModel {
    private int id;
    private String nome;
    private String cpf;

    public ClienteModel(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        try{
           setCPF(cpf);
        } catch (Exception e) {
            System.out.println(e);
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
        if (ValidadorUtil.validadorCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new Exception("CPF inválido! Impossivel criar o Cliente");
        }
    }

    public void atualizarCPF(String cpf) {
        if (ValidadorUtil.validadorCPF(cpf)) {
            this.cpf = cpf;
        } else {
            System.err.println("Aviso: Falha ao alterar. CPF " + cpf + " é inválido!");
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return String.format("%d;%s;%s", id, nome, cpf);
    }
}