package Repository;

import Model.ProdutoModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class ProdutoRepository {

    private DefaultListModel<ProdutoModel> listaProdutos;

    public ProdutoRepository() {
        // listaProdutos = new
    }

    public void incluir(ProdutoModel produto) {
        listaProdutos.addElement(produto);
    }

    public DefaultListModel<ProdutoModel> listar() {
        return listaProdutos;
    }

    public DefaultListModel<ProdutoModel> consultarPorNome(String termo) {
        DefaultListModel<ProdutoModel> filtrados = new DefaultListModel<>();

        if (termo == null || termo.trim().isEmpty()) {
            return listaProdutos;
        }

        for (int i = 0; i < listaProdutos.size(); i++) {
            ProdutoModel p = listaProdutos.getElementAt(i);

            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                filtrados.addElement(p);
            }
        }

        return filtrados;
    }

    public ProdutoModel consultarPorId(int idBusca) {
        for (int i = 0; i < listaProdutos.size(); i++) {
            ProdutoModel p = listaProdutos.getElementAt(i);

            if (p.getId() == idBusca) {
                return p;
            }
        }

        return null;
    }

    public void alterar(ProdutoModel produto) {
        ProdutoModel original = consultarPorId(produto.getId());

        if (original != null) {
            original.setNome(produto.getNome());
            original.setPreco(produto.getPreco());
            original.setQuantidade(produto.getQuantidade());

            // ArquivoUtil.salvarDados(listaProdutos);
        }
    }

    public void remover(int idProdutoExcluir) {
        ProdutoModel produto = consultarPorId(idProdutoExcluir);

        if (produto != null) {
            listaProdutos.removeElement(produto);
        }
    }
}