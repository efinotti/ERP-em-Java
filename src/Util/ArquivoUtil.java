package Util;

import Model.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
 
public class ArquivoUtil {
    
    // SO SERA CHAMADO NA HORA QUE O SISTEMA FECHAR
    public static void armazenar(String nomeArquivo, DefaultListModel<?> listModel) {
        File file = new File(nomeArquivo);
        try {
            if (file.createNewFile()){
                System.out.println("Arquivo criado");
            } else {
                System.out.println("Arquivo ja existe");
            }
            
            FileWriter fw = new FileWriter(file);
            
            for (int i = 0; i < listModel.size(); i++) {
                fw.write(listModel.elementAt(i).toString() + "\n");
            }
            
            fw.close();
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
        
    // 1 - Cliente | 2 - Produto | 3 - ItemPedido | 4 - Pedido
    public static DefaultListModel<?> ler(int identificador) {
        switch(identificador){
            case 1 -> {
                
                File file = new File("clientes.csv");
                
                if (file.exists()){
                    DefaultListModel<ClienteModel> listaClientes = new DefaultListModel<>();
                    try {
                        Scanner scan = new Scanner(file);
                        
                        while (scan.hasNextLine()) {
                            String data = scan.nextLine();
                            
                            String[] textoSeparado = data.split(";");
                            
                            int id = Integer.parseInt(textoSeparado[0]);
                            String nome = textoSeparado[1];
                            String cpf = textoSeparado [2];
                            
                            ClienteModel cliente = new ClienteModel(id, nome, cpf);
                            
                            listaClientes.addElement(cliente);
                        }
                        scan.close();
                        return listaClientes;
                        
                    } catch (IOException e) {
                        System.out.println("Erro na Criação do FileReader" + e);
                    }
                    
                } else {
                    return null;
                }
                
            }
            case 2 -> {
                File file = new File("produtos.csv");
                
                if (file.exists()){
                    DefaultListModel<ProdutoModel> listaProdutos = new DefaultListModel<>();
                    
                    try {
                        Scanner scan = new Scanner(file);
                        
                        while (scan.hasNextLine()) {
                            String data = scan.nextLine();
                            String[] textoSeparado = data.split(";");
                            
                            int id = Integer.parseInt(textoSeparado[0]);
                            String nome = textoSeparado[1];
                            float preco = Float.parseFloat(textoSeparado[2]);
                            int quantidade = Integer.parseInt(textoSeparado[3]);
                            
                            ProdutoModel produto = new ProdutoModel(id, nome, preco, quantidade);
                            
                            listaProdutos.addElement(produto);
                            
                        }
                        
                        scan.close();
                        return listaProdutos;
                        
                    } catch (IOException e) {
                        System.out.println("Erro na Criação do FileReader" + e);
                    }
                } else {
                    return null;
                }
                
            }
            case 3 -> {
                File file = new File("itemPedidos.csv.csv");
                
                
                if (file.exists()){
                    DefaultListModel<ItemPedidoModel> listaItemPedidos = new DefaultListModel<>();
                            
                    try {
                        Scanner scan = new Scanner(file);
                        
                        while (scan.hasNextLine()) {
                            String data = scan.nextLine();
                            
                            String[] textoSeparado = data.split(";");
                            
                            int id = Integer.parseInt(textoSeparado[0]);
                            int idPedido = Integer.parseInt(textoSeparado[1]);
                            int idProduto = Integer.parseInt(textoSeparado[2]);
                            int quantidade = Integer.parseInt(textoSeparado[3]);
                            
                            ItemPedidoModel itemPedido = new ItemPedidoModel(id, idPedido, idProduto, quantidade);
                            
                            listaItemPedidos.addElement(itemPedido);
                        }
                        
                        scan.close();
                        return listaItemPedidos;
                        
                    } catch (IOException e) {
                        System.out.println("Erro na Criação do FileReader" + e);
                    }
                } else {
                    return null;
                }
                
            }
           case 4 -> {
                File file = new File("pedidos.csv");

                if (file.exists()) {
                    DefaultListModel<PedidoModel> listaPedidos = new DefaultListModel<>();

                    SimpleDateFormat formatador = new java.text.SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Scanner scan = new Scanner(file);

                        while (scan.hasNextLine()) {
                            String data = scan.nextLine();
                            if (data.trim().isEmpty()) continue; 

                            String[] textoSeparado = data.split(";");

                            int id = Integer.parseInt(textoSeparado[0]);
                            int id_cliente = Integer.parseInt(textoSeparado[1]);

                            Date dt_pedido = formatador.parse(textoSeparado[2]);
                            Date dt_entrega = formatador.parse(textoSeparado[3]);

                            float vlr_total = Float.parseFloat(textoSeparado[4].replace(",", "."));

                            PedidoModel pedido = new PedidoModel(id, id_cliente, dt_pedido, dt_entrega, vlr_total);

                            listaPedidos.addElement(pedido);
                        }

                        scan.close();
                        return listaPedidos;

                    } catch (IOException e) {
                        System.out.println("Erro na Leitura do Arquivo: " + e);
                    } catch (java.text.ParseException e) {
                        System.out.println("Erro ao converter a data do arquivo (formato esperado yyyy-MM-dd): " + e.getMessage());
                    }
                } else {
                    return null;
                }
            }
        }
    return null;
    }
}