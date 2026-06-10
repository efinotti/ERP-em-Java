package Repository;

import Model.ClienteModel;
import Util.ArquivoUtil;
import Util.ValidadorUtil;
import javax.swing.DefaultListModel;

public class ClienteRepository {

    private DefaultListModel<ClienteModel> clientes;
    private final String ARQUIVO = "clientes.csv";
    private final ArquivoUtil arquivoUtil = new ArquivoUtil();

    @SuppressWarnings("unchecked")
    public ClienteRepository() {
        DefaultListModel<?> listaGenerica = ArquivoUtil.ler(1);

        if (listaGenerica != null) {
            clientes = (DefaultListModel<ClienteModel>) listaGenerica;
        } else {
            clientes = new DefaultListModel<>();
        }
    }

    public void criarCliente(String nome, String cpf) throws Exception {
        if (verificarClienteExiste(cpf) != null) {
            throw new Exception("Já existe um cliente cadastrado com este CPF.");
        }
        
        if (!ValidadorUtil.validadorCPF(cpf)) {
            return;
        }

        int id = clientes.isEmpty() ? 1 : clientes.lastElement().getId() + 1;
        ClienteModel cliente = new ClienteModel(id, nome, cpf);
        
        clientes.addElement(cliente);
        salvarNoArquivo();
    }

    public void alterarCliente(int id, String novoNome, String novoCpf) throws Exception {
        novoCpf = novoCpf.replaceAll("[^0-9]", "");

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
        throw new Exception("Cliente não encontrado.");
    }

    public void removerCliente(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.getElementAt(i).getId() == id) {
                clientes.remove(i);
                salvarNoArquivo();
                return;
            }
        }
    }

    public ClienteModel verificarClienteExiste(String cpf) {
        for (int i = 0; i < clientes.size(); i++) {
            ClienteModel cliente = clientes.getElementAt(i);
            if (cliente.getCPF().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public ClienteModel consultarPorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            ClienteModel cliente = clientes.getElementAt(i);
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    private void salvarNoArquivo() {
        ArquivoUtil.armazenar(ARQUIVO, clientes);
    }

    public DefaultListModel<ClienteModel> getClientes() {
        return clientes;
    }
}