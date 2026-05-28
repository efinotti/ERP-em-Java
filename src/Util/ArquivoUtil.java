/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import erpapp.Model.Pedido.*;

/**
 *
 * @author enzo
 *
 
 /*
 
public class ArquivoUtil {
    
}

    public void armazenar(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))){
            for(Pedido p: listaPedido){
                writer.println(p.getId() + ";" + p.getCliente() + ";" + p.getProduto() + ";" + p.getValor());
            }
        }catch(IOException e){
            System.out.println("Erro ao salvar arquivo:" + e.getMessage());
        }
    }
    
    public void recuperar(){
        File file = new File(ARQUIVO);
        if(!file.exists()){ return;
        }
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String linha;
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length == 4){
                    int numero = Integer.parseInt(dados[0]);
                    double valor = Double.parseDouble(dados[3]);
                    listaPedido.add(new Pedido(numero,dados[1],dados[2],valor));
                }
            }
        }catch(IOException | NumberFormaException e){
            System.out.println("Erro ao recuperar os dados:" + e.getMessage());
        }
    }

*/