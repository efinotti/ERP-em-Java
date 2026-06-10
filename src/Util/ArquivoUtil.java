package Util;

import Model.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.DefaultListModel;
 
public class ArquivoUtil {
    
    public static void armazenar(String nomeArquivo, DefaultListModel<?> listModel) {
        File file = new File(nomeArquivo);
        
        try {
            if (file.createNewFile()) {
                System.out.println("Arquivo criado: " + nomeArquivo);
            }
            
            // O try-with-resources fecha o FileWriter automaticamente
            try (FileWriter fw = new FileWriter(file)) {
                for (int i = 0; i < listModel.size(); i++) {
                    fw.write(listModel.elementAt(i).toString() + "\n");
                }
            }
            
        } catch (IOException e) {
            System.err.println("Erro ao armazenar arquivo: " + e.getMessage());
        }
    }
        
    @SuppressWarnings("unchecked")
    public static DefaultListModel<?> ler(int identificador) {
        switch(identificador) {
            case 1 -> {
                File file = new File("clientes.csv");
                if (file.exists()) {
                    DefaultListModel<ClienteModel> listaClientes = new DefaultListModel<>();
                    try (Scanner scan = new Scanner(file)) {
                        while (scan.hasNextLine()) {
                            String data = scan.nextLine();
                            if (data.trim().isEmpty()) continue; 
                            
                            String[] textoSeparado = data.split(";");
                            int id = Integer.parseInt(textoSeparado[0]);
                            String nome = textoSeparado[1];
                            String cpf = textoSeparado[2];
                            
                            ClienteModel cliente = new ClienteModel(id, nome, cpf);
                            listaClientes.addElement(cliente);
                        }
                        return listaClientes;
                    } catch (IOException e) {
                        System.out.println("Erro na Leitura de Clientes: " + e.getMessage());
                    }
                }
                return null;
            }
            case 2 -> {
                File file = new File("produtos.csv");
                DefaultListModel<ProdutoModel> listaProdutos = new DefaultListModel<>();
                
                if (file.exists()) {
                    try (Scanner scan = new Scanner(file)) {
                        while (scan.hasNextLine()) {
                            String data = scan.nextLine();
                            if (data.trim().isEmpty()) continue; 

                            String[] textoSeparado = data.split(";");
                            if (textoSeparado.length >= 4) {
                                int id = Integer.parseInt(textoSeparado[0]);
                                String nome = textoSeparado[1];
                                float preco = Float.parseFloat(textoSeparado[2].replace(",", "."));
                                int quantidade = Integer.parseInt(textoSeparado[3]);

                                ProdutoModel produto = new ProdutoModel(id, nome, preco, quantidade);
                                listaProdutos.addElement(produto);
                            }
                        }
                        return listaProdutos;
                    } catch (Exception e) {
                        System.out.println("Erro na Leitura de Produtos: " + e.getMessage());
                    }
                }
                return listaProdutos;
            }
            case 3 -> {
                File file = new File("itemPedidos.csv");
                
                if (file.exists()) {
                    DefaultListModel<ItemPedidoModel> listaItemPedidos = new DefaultListModel<>();
                    DefaultListModel<ProdutoModel> produtosCadastrados = (DefaultListModel<ProdutoModel>) ler(2);
                            
                    try (Scanner scan = new Scanner(file)) {
                        while (scan.hasNextLine()) {
                            String data = scan.nextLine();
                            if (data.trim().isEmpty()) continue;
                            
                            String[] textoSeparado = data.split(";");
                            int id = Integer.parseInt(textoSeparado[0]);
                            int idPedido = Integer.parseInt(textoSeparado[1]);
                            int idProduto = Integer.parseInt(textoSeparado[2]);
                            int quantidade = Integer.parseInt(textoSeparado[3]);
                            
                            ProdutoModel produtoInstancia = null;
                            if (produtosCadastrados != null) {
                                for (int i = 0; i < produtosCadastrados.size(); i++) {
                                    if (produtosCadastrados.get(i).getId() == idProduto) {
                                        produtoInstancia = produtosCadastrados.get(i);
                                        break;
                                    }
                                }
                            }
                            
                            if (produtoInstancia == null) {
                                produtoInstancia = new ProdutoModel(idProduto, "Produto Não Localizado", 0.0f, 0);
                            }
                            
                            ItemPedidoModel itemPedido = new ItemPedidoModel(id, idPedido, produtoInstancia, quantidade);
                            listaItemPedidos.addElement(itemPedido);
                        }
                        return listaItemPedidos;
                    } catch (IOException e) {
                        System.out.println("Erro na Leitura de Itens de Pedido: " + e.getMessage());
                    }
                }
                return null;
            }
            case 4 -> {
                File file = new File("pedidos.csv");
                if (file.exists()) {
                    DefaultListModel<PedidoModel> listaPedidos = new DefaultListModel<>();
                    SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

                    try (Scanner scan = new Scanner(file)) {
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
                        return listaPedidos;
                    } catch (IOException e) {
                        System.out.println("Erro na Leitura de Pedidos: " + e.getMessage());
                    } catch (java.text.ParseException e) {
                        System.out.println("Erro ao converter a data do arquivo (formato esperado yyyy-MM-dd): " + e.getMessage());
                    }
                }
                return null;
            }
        }
        return null;
    }
}