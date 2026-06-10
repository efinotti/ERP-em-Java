/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        // Liga os ouvidores de eventos aos botões da View principal
        view.getInserirBtn().addActionListener(e -> abrirTelaInserir());
        view.getAlterarBtn().addActionListener(e -> abrirTelaAlterar());
        view.getExcluirBtn().addActionListener(e -> excluirProduto());

        // Alimenta a tabela de produtos na inicialização
        atualizarTabela();
    }

    private void atualizarTabela() {
        // Define colunas personalizadas impossibilitando a edição direta nas células
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nome", "Preço", "Estoque Atual"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        var produtos = repository.listar();
        for (int i = 0; i < produtos.size(); i++) {
            ProdutoModel p = produtos.getElementAt(i);
            model.addRow(new Object[] {
                p.getId(),
                p.getNome(),
                String.format("R$ %.2f", p.getPreco()),
                p.getQuantidade()
            });
        }
        
        view.getTabela().setModel(model);
    }

    private void abrirTelaInserir() {
        AdicionarAlterarProdutoView dialog = new AdicionarAlterarProdutoView(view, true);
        dialog.setTitle("Cadastrar Novo Produto");
        
        // Bloqueia o ID sugerido e atribui o ID sequencial correto
        dialog.getjTextField1().setText(String.valueOf(repository.proximoId()));
        dialog.getjTextField1().setEditable(false);
        
        dialog.getInserirBtn().setText("Salvar");
        dialog.getInserirBtn().addActionListener(e -> {
            try {
                String nome = dialog.getjTextField2().getText();
                float preco = Float.parseFloat(dialog.getjTextField3().getText().replace(",", "."));
                int qtd = Integer.parseInt(dialog.getjTextField4().getText());

                // Aplica regras de validação da Util
                ValidadorUtil.validarProduto(nome, preco, qtd);

                int id = Integer.parseInt(dialog.getjTextField1().getText());
                ProdutoModel novo = new ProdutoModel(id, nome, preco, qtd);
                
                repository.incluir(novo);
                JOptionPane.showMessageDialog(dialog, "Produto cadastrado com sucesso!");
                dialog.dispose();
                atualizarTabela();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Formato numérico inválido para Preço ou Estoque.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
            }
        });

        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    private void abrirTelaAlterar() {
        int linhaSelecionada = view.getTabela().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto na lista para alterá-lo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idProduto = (int) view.getTabela().getValueAt(linhaSelecionada, 0);
        ProdutoModel produto = repository.consultarPorId(idProduto);

        if (produto == null) {
            JOptionPane.showMessageDialog(view, "Produto não pôde ser localizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AdicionarAlterarProdutoView dialog = new AdicionarAlterarProdutoView(view, true);
        dialog.setTitle("Editar Dados do Produto");
        
        // Carrega dados originais nos formulários da janela modal
        dialog.getjTextField1().setText(String.valueOf(produto.getId()));
        dialog.getjTextField1().setEditable(false);
        dialog.getjTextField2().setText(produto.getNome());
        dialog.getjTextField3().setText(String.valueOf(produto.getPreco()));
        dialog.getjTextField4().setText(String.valueOf(produto.getQuantidade()));

        dialog.getInserirBtn().setText("Atualizar");
        dialog.getInserirBtn().addActionListener(e -> {
            try {
                String nome = dialog.getjTextField2().getText();
                float preco = Float.parseFloat(dialog.getjTextField3().getText().replace(",", "."));
                int qtd = Integer.parseInt(dialog.getjTextField4().getText());

                ValidadorUtil.validarProduto(nome, preco, qtd);

                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setQuantidade(qtd);

                repository.alterar(produto);
                JOptionPane.showMessageDialog(dialog, "Produto modificado com sucesso!");
                dialog.dispose();
                atualizarTabela();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Formato inválido inserido nos campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
            }
        });

        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    private void excluirProduto() {
        int linhaSelecionada = view.getTabela().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um item da tabela para efetuar a exclusão.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idProduto = (int) view.getTabela().getValueAt(linhaSelecionada, 0);
        
        int resposta = JOptionPane.showConfirmDialog(
            view, 
            "Deseja realmente remover o produto com ID: " + idProduto + "?", 
            "Confirmação de Exclusão", 
            JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            repository.remover(idProduto);
            JOptionPane.showMessageDialog(view, "Produto excluído do sistema.");
            atualizarTabela();
        }
    }
}