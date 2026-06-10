package Controller;

import Model.ClienteModel;
import Repository.ClienteRepository;
import View.ClienteView;
import View.AdicionarAlterarClientesView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteController {

    private final ClienteView view;
    private final ClienteRepository repository;

    public ClienteController(ClienteView view, ClienteRepository repository) {
        this.view = view;
        this.repository = repository;
        atualizarTabela();
    }

    public final void atualizarTabela() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTabela().getModel();
        
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Nome do Cliente", "CPF"});
        tableModel.setRowCount(0);

        var listaClientes = repository.getClientes();
        for (int i = 0; i < listaClientes.getSize(); i++) {
            ClienteModel c = listaClientes.getElementAt(i);
            tableModel.addRow(new Object[]{c.getId(), c.getNome(), c.getCPF()});
        }
    }

    public void acaoInserir() {
        AdicionarAlterarClientesView dialog = new AdicionarAlterarClientesView(view, true);
       
        dialog.getTxtId().setEditable(false);
        dialog.getTxtId().setText("Auto");
        dialog.getBtnConfirmar().addActionListener(e -> {
            String nome = dialog.getTxtNome().getText().trim();
            String cpf = dialog.getTxtCpf().getText().trim();

            if (nome.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Todos os campos são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            repository.criarCliente(nome, cpf);
            atualizarTabela();
            dialog.dispose();
        });

        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    public void acaoAlterar() {
        int linhaSelecionada = view.getTabela().getSelectedRow();
        
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na tabela para alterar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) view.getTabela().getValueAt(linhaSelecionada, 0);
        String nomeAtual = (String) view.getTabela().getValueAt(linhaSelecionada, 1);
        String cpfAtual = (String) view.getTabela().getValueAt(linhaSelecionada, 2);

        AdicionarAlterarClientesView dialog = new AdicionarAlterarClientesView(view, true);
        
        dialog.getTxtId().setEditable(false);
        dialog.getTxtId().setText(String.valueOf(id));
        dialog.getTxtNome().setText(nomeAtual);
        dialog.getTxtCpf().setText(cpfAtual);
        dialog.getBtnConfirmar().addActionListener(e -> {
            String novoNome = dialog.getTxtNome().getText().trim();
            String novoCpf = dialog.getTxtCpf().getText().trim();

            if (novoNome.isEmpty() || novoCpf.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Os campos não podem ficar vazios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                    repository.alterarCliente(id, novoNome, novoCpf);
                    atualizarTabela();
                       dialog.dispose();
                } catch (Exception ex) {
        JOptionPane.showMessageDialog(dialog, "Erro ao alterar o cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        });

        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    public void acaoExcluir() {
        int linhaSelecionada = view.getTabela().getSelectedRow();
        
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na tabela para excluir!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) view.getTabela().getValueAt(linhaSelecionada, 0);
        String nome = (String) view.getTabela().getValueAt(linhaSelecionada, 1);

        int resposta = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir o cliente " + nome + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            repository.removerCliente(id);
            atualizarTabela();
            JOptionPane.showMessageDialog(view, "Cliente removido com sucesso!");
        }
    }
}