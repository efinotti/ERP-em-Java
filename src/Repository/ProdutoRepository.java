package Repository;

import Model.ProdutoModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class ProdutoRepository {

    private DefaultListModel<ProdutoModel> listaProdutos;
    private final String ARQUIVO = "produtos.csv";

    @SuppressWarnings("unchecked")
    public ProdutoRepository() {
        // Identificador '2' mapeia a leitura de produtos em ArquivoUtil
        Object listaGenerica = ArquivoUtil.ler(2);
        
        if (listaGenerica != null) {
            listaProdutos = (DefaultListModel<ProdutoModel>) listaGenerica;
        } else {
            listaProdutos = new DefaultListModel<>();
        }
    }

    public DefaultListModel<ProdutoModel> getListaProdutos() {
        return listaProdutos;
    }

    public void incluir(ProdutoModel produto) {
        listaProdutos.addElement(produto);
        salvar();
    }

    public DefaultListModel<ProdutoModel> listar() {
        return listaProdutos;
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

    public void alterar(ProdutoModel produto) {
        ProdutoModel original = consultarPorId(produto.getId());
        if (original != null) {
            original.setNome(produto.getNome());
            original.setPreco(produto.getPreco());
            original.setQuantidade(produto.getQuantidade());
            salvar();
        }
    }

    public void remover(int idProdutoExcluir) {
        ProdutoModel produto = consultarPorId(idProdutoExcluir);
        if (produto != null) {
            listaProdutos.removeElement(produto);
            salvar();
        }
    }

    public void salvar() {
        ArquivoUtil.armazenar(ARQUIVO, listaProdutos);
    }
    
    public int proximoId() {
        int maxId = 0;
        for (int i = 0; i < listaProdutos.size(); i++) {
            ProdutoModel p = listaProdutos.getElementAt(i);
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }
}