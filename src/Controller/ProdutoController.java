package Controller;

import Model.ProdutoModel;
import Repository.ProdutoRepository;
import Util.ValidadorUtil;
import View.AdicionarAlterarProdutoView;
import View.ProdutoView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProdutoController {

    private final ProdutoRepository repository;
    private final ProdutoView view;

    public ProdutoController(ProdutoRepository repository, ProdutoView view) {
        this.repository = repository;
        this.view = view;

        initController();
    }

    private void initController() {
        view.getInserirBtn().addActionListener(e -> abrirTelaInserir());
        view.getAlterarBtn().addActionListener(e -> abrirTelaAlterar());
        view.getExcluirBtn().addActionListener(e -> excluirProduto());

        atualizarTabela();
    }

    private void atualizarTabela() {

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Preço", "Quantidade"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        var produtos = repository.listar();

        for (int i = 0; i < produtos.size(); i++) {

            ProdutoModel p = produtos.getElementAt(i);

            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getQuantidade()
            });
        }

        view.getTabela().setModel(model);
    }

    private void abrirTelaInserir() {

        AdicionarAlterarProdutoView dialog =
                new AdicionarAlterarProdutoView(view, true);

        dialog.setTitle("Cadastrar Produto");

        dialog.getInserirBtn().setText("Salvar");

        dialog.getInserirBtn().addActionListener(e -> {

            try {

                String nome =
                        dialog.getjTextField1()
                                .getText()
                                .trim();

                String precoTexto =
                        dialog.getjTextField2()
                                .getText()
                                .trim();

                String qtdTexto =
                        dialog.getjTextField4()
                                .getText()
                                .trim();

                if (nome.isEmpty()) {
                    throw new Exception("Informe o nome do produto.");
                }

                if (precoTexto.isEmpty()) {
                    throw new Exception("Informe o preço.");
                }

                if (qtdTexto.isEmpty()) {
                    throw new Exception("Informe a quantidade.");
                }

                float preco =
                        Float.parseFloat(
                                precoTexto.replace(",", ".")
                        );

                int quantidade =
                        Integer.parseInt(qtdTexto);

                ValidadorUtil.validarProduto(
                        nome,
                        preco,
                        quantidade
                );

                ProdutoModel novo =
                        new ProdutoModel(
                                repository.proximoId(),
                                nome,
                                preco,
                                quantidade
                        );

                repository.incluir(novo);

                atualizarTabela();

                JOptionPane.showMessageDialog(
                        dialog,
                        "Produto cadastrado com sucesso!"
                );

                dialog.dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Preço ou quantidade inválidos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );

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

    private void abrirTelaAlterar() {

        int linhaSelecionada =
                view.getTabela().getSelectedRow();

        if (linhaSelecionada == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Selecione um produto para alterar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int idProduto =
                (Integer) view.getTabela()
                              .getValueAt(
                                      linhaSelecionada,
                                      0
                              );

        ProdutoModel produto =
                repository.consultarPorId(idProduto);

        if (produto == null) {

            JOptionPane.showMessageDialog(
                    view,
                    "Produto não encontrado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        AdicionarAlterarProdutoView dialog =
                new AdicionarAlterarProdutoView(view, true);

        dialog.setTitle("Alterar Produto");

        dialog.getjTextField1().setText(
                produto.getNome()
        );

        dialog.getjTextField2().setText(
                String.valueOf(produto.getPreco())
        );

        dialog.getjTextField4().setText(
                String.valueOf(produto.getQuantidade())
        );

        dialog.getInserirBtn().setText("Atualizar");

        dialog.getInserirBtn().addActionListener(e -> {

            try {

                String nome =
                        dialog.getjTextField1()
                                .getText()
                                .trim();

                String precoTexto =
                        dialog.getjTextField2()
                                .getText()
                                .trim();

                String qtdTexto =
                        dialog.getjTextField4()
                                .getText()
                                .trim();

                if (nome.isEmpty()) {
                    throw new Exception("Informe o nome do produto.");
                }

                if (precoTexto.isEmpty()) {
                    throw new Exception("Informe o preço.");
                }

                if (qtdTexto.isEmpty()) {
                    throw new Exception("Informe a quantidade.");
                }

                float preco =
                        Float.parseFloat(
                                precoTexto.replace(",", ".")
                        );

                int quantidade =
                        Integer.parseInt(qtdTexto);

                ValidadorUtil.validarProduto(
                        nome,
                        preco,
                        quantidade
                );

                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setQuantidade(quantidade);

                repository.alterar(produto);

                atualizarTabela();

                JOptionPane.showMessageDialog(
                        dialog,
                        "Produto alterado com sucesso!"
                );

                dialog.dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Preço ou quantidade inválidos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );

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

    private void excluirProduto() {

        int linhaSelecionada =
                view.getTabela().getSelectedRow();

        if (linhaSelecionada == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Selecione um produto para excluir.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int idProduto =
                (Integer) view.getTabela()
                              .getValueAt(
                                      linhaSelecionada,
                                      0
                              );

        int resposta =
                JOptionPane.showConfirmDialog(
                        view,
                        "Deseja realmente excluir este produto?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );

        if (resposta == JOptionPane.YES_OPTION) {

            repository.remover(idProduto);

            atualizarTabela();

            JOptionPane.showMessageDialog(
                    view,
                    "Produto removido com sucesso!"
            );
        }
    }
}