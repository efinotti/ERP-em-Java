/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Repository.ItemPedidoRepository;
import Repository.PedidoRepository;
import View.PedidoView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
/**
 *
 * @author enzo
 */
public class PedidoController {
    private PedidoRepository repository;
    private ItemPedidoRepository itemPedidoRepository;
    private PedidoView view;

    public PedidoController(PedidoRepository repository, PedidoView view) {
        this.repository = repository;
        this.view = view;
        
        itemPedidoRepository = new ItemPedidoRepository();
        
        setarEventos();
        
        this.view.setVisible(true);
    }
    
    public void setarEventos() {
        inserir(); 
        salvar();
        alterar();
        remover();
    }
    
    public void inserir() {
        
    }
    
    public void alterar() {
    
    }
    
    public void remover() {
        view.getExcluirBtn().addActionListener(e -> {
            System.out.println("Botão Remover apertado");
            repository.listar();
        });
    }
    
    public void salvar() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Fechando a janela... Salvando dados.");
                repository.salvar(); 
                itemPedidoRepository.salvar();
            }
        });
    }
    
}
