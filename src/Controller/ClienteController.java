package Controller;

import Model.ClienteModel;
import Repository.ClienteRepository;
import View.AdicionarAlterarClientesView;
import View.ClienteView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteController {

    private final ClienteView view;
    private final ClienteRepository repository;

    public ClienteController(
            ClienteView view,
            ClienteRepository repository) {

        this.view = view;
        this.repository = repository;

        configurarEventos();
        atualizarTabela();
    }

    private void configurarEventos() {

        view.getInserirBtn().addActionListener(
                e -> acaoInserir()
        );

        view.getAlterarBtn().addActionListener(
                e -> acaoAlterar()
        );

        view.getExcluirBtn().addActionListener(
                e -> acaoExcluir()
        );
    }

    public final void atualizarTabela() {

        DefaultTableModel model =
                (DefaultTableModel) view.getTabela().getModel();

        model.setColumnIdentifiers(
                new Object[]{
                    "ID",
                    "Nome do Cliente",
                    "CPF"
                }
        );

        model.setRowCount(0);

        for (int i = 0; i < repository.getClientes().size(); i++) {

            ClienteModel cliente =
                    repository.getClientes().getElementAt(i);

            model.addRow(
                    new Object[]{
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCPF()
                    }
            );
        }
    }

    private void acaoInserir() {

        AdicionarAlterarClientesView dialog =
                new AdicionarAlterarClientesView(view, true);

        dialog.getBtnConfirmar().addActionListener(e -> {

            String nome =
                    dialog.getTxtNome()
                            .getText()
                            .trim();

            String cpf =
                    dialog.getTxtCpf()
                            .getText()
                            .trim();

            if (nome.isEmpty() || cpf.isEmpty()) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Todos os campos são obrigatórios!"
                );

                return;
            }

            try {

                repository.criarCliente(
                        nome,
                        cpf
                );

                atualizarTabela();

                JOptionPane.showMessageDialog(
                        dialog,
                        "Cliente cadastrado com sucesso!"
                );

                dialog.dispose();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        dialog,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    private void acaoAlterar() {

        int linhaSelecionada =
                view.getTabela().getSelectedRow();

        if (linhaSelecionada == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Selecione um cliente para alterar!"
            );

            return;
        }

        int id = (Integer)
                view.getTabela()
                        .getValueAt(
                                linhaSelecionada,
                                0
                        );

        String nomeAtual = (String)
                view.getTabela()
                        .getValueAt(
                                linhaSelecionada,
                                1
                        );

        String cpfAtual = (String)
                view.getTabela()
                        .getValueAt(
                                linhaSelecionada,
                                2
                        );

        AdicionarAlterarClientesView dialog =
                new AdicionarAlterarClientesView(view, true);

        dialog.getTxtNome().setText(
                nomeAtual
        );

        dialog.getTxtCpf().setText(
                cpfAtual
        );

        dialog.getBtnConfirmar().addActionListener(e -> {

            String novoNome =
                    dialog.getTxtNome()
                            .getText()
                            .trim();

            String novoCpf =
            dialog.getTxtCpf()
              .getText()
              .replaceAll("[^0-9]", "")
              .trim();

            if (novoNome.isEmpty()
                    || novoCpf.isEmpty()) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Todos os campos são obrigatórios!"
                );

                return;
            }

            try {

                repository.alterarCliente(
                        id,
                        novoNome,
                        novoCpf
                );

                atualizarTabela();

                JOptionPane.showMessageDialog(
                        dialog,
                        "Cliente alterado com sucesso!"
                );

                dialog.dispose();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        dialog,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    private void acaoExcluir() {

        int linhaSelecionada =
                view.getTabela().getSelectedRow();

        if (linhaSelecionada == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Selecione um cliente para excluir!"
            );

            return;
        }

        int id = (Integer)
                view.getTabela()
                        .getValueAt(
                                linhaSelecionada,
                                0
                        );

        String nome = (String)
                view.getTabela()
                        .getValueAt(
                                linhaSelecionada,
                                1
                        );

        int resposta =
                JOptionPane.showConfirmDialog(
                        view,
                        "Deseja excluir o cliente "
                        + nome
                        + "?",
                        "Confirmar Exclusão",
                        JOptionPane.YES_NO_OPTION
                );

        if (resposta
                == JOptionPane.YES_OPTION) {

            repository.removerCliente(id);

            atualizarTabela();

            JOptionPane.showMessageDialog(
                    view,
                    "Cliente removido com sucesso!"
            );
        }
    }
}