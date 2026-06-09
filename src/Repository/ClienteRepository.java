package Repository;

import Model.ClienteModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class ClienteRepository {
    
    private DefaultListModel<ClienteModel> clientes;
    private final String ARQUIVO = "clientes.csv";
    
    private final ArquivoUtil arquivoUtil = new ArquivoUtil();

    public ClienteRepository() {
        DefaultListModel<?> listaGenerica = ArquivoUtil.ler(1);
        
        if (listaGenerica != null) {
            this.clientes = (DefaultListModel<ClienteModel>) listaGenerica;
        } else {
            this.clientes = new DefaultListModel<>();
        }
    }
    
    public void criarCliente(String nome, String cpf) {
        try {
            if (verificarClienteExiste(cpf) != null) {
                System.out.println("CLIENTE EXISTE!");
                return;
            }
        } catch (NullPointerException e) {
            int id = clientes.isEmpty() ? 1 : clientes.lastElement().getId() + 1;
            
            ClienteModel novoCliente = new ClienteModel(id, nome, cpf);
            clientes.addElement(novoCliente);
            salvarNoArquivo();
        }
    }
    
    public void alterarCliente(int id, String novoNome, String novoCpf) {
        for (int i = 0; i < clientes.size(); i++) {
            ClienteModel cliente = clientes.getElementAt(i);
            if (cliente.getId() == id) {
                cliente.setNome(novoNome);
                cliente.atualizarCPF(novoCpf);
                
                clientes.setElementAt(cliente, i); 
                salvarNoArquivo(); 
                return;
            }
        }
        System.err.println("Erro: Cliente com o ID " + id + " não foi encontrado para alteração.");
    }
    
    public void removerCliente(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.getElementAt(i).getId() == id) {
                clientes.remove(i);
                salvarNoArquivo();
                break;
            }
        }
    }
    
    public ClienteModel consultarPorId(int id) {
        for (int i = 0; i < clientes.getSize(); i++) {
            ClienteModel cliente = clientes.getElementAt(i);
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
    
    public ClienteModel verificarClienteExiste(String cpf) throws NullPointerException {
        for (int i = 0; i < clientes.getSize(); i++){
            ClienteModel cliente = clientes.getElementAt(i);
            if (cliente.getCPF().equals(cpf)){
                return cliente;
            }
        }
        throw new NullPointerException("CPF nao encontrado!");
    }
    
    private void salvarNoArquivo() {
        arquivoUtil.armazenar(ARQUIVO, clientes);
    }
    
    public DefaultListModel<ClienteModel> getClientes() {
        return clientes;
    }
}