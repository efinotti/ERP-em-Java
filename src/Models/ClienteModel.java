/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Entities.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import Util.ValiadorUtil;
import javax.swing.DefaultListModel;


/**
 *
 * @author enzo
 */
public class ClienteModel {
    
    DefaultListModel<Cliente> clientes;
    
    public void criarCliente(String nome, String cpf) {
        int id;
        
        if (clientes.isEmpty()) {
            id = 0;
        } else {
            id = clientes.lastElement().getId() - 1;
        }
        
        Cliente novoCliente = new Cliente(id, nome, cpf);
        
        clientes.addElement(novoCliente);
    }
    
    public void removerCliente(int id) {
        clientes.remove(id);
    }
    
    public Cliente verificarClienteExiste(DefaultListModel<Cliente> clientes, String cpf) throws NullPointerException {
        for (int i = 0; i < clientes.getSize(); i++){
            Cliente cliente = clientes.getElementAt(i);
            if (cliente.getCPF().equals(cpf)){
                return cliente;
            }
        }
        
        throw new NullPointerException("CPF nao encontrado!");
    }
}
