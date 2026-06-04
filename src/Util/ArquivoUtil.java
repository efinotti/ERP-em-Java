package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
 
public class ArquivoUtil {
    
    // SO SERA CHAMADO NA HORA QUE O SISTEMA FECHAR
    public void armazenar(String nomeArquivo, DefaultListModel<?> listModel) {
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
    
        
    // 1 - Cliente | 2 - Produto | 3 - ItemProduto | 4 - Pedido
    public void ler(String nomeArquivo, int identificador, ListModel<?> list) {
        switch(identificador){
            case 1 -> {
                
            }
            case 2 -> {
                
            }
            case 3 -> {
                
            }
            case 4 -> {
                
            }
}
    }
}