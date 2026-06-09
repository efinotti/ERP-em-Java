package Repository;

import Model.ProdutoModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class ProdutoRepository {

    private DefaultListModel<ProdutoModel> listaProdutos;
    private String ARQUIVO = "produtos.csv";

    public DefaultListModel<ProdutoModel> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(DefaultListModel<ProdutoModel> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String getARQUIVO() {
        return ARQUIVO;
    }

    public void setARQUIVO(String ARQUIVO) {
        this.ARQUIVO = ARQUIVO;
    }

    @SuppressWarnings("unchecked")
    public ProdutoRepository() {
        Object listaGenerica = ArquivoUtil.ler(2);
        
        if (listaGenerica != null) {
            listaProdutos = (DefaultListModel<ProdutoModel>) listaGenerica;
        } else {
            listaProdutos = new DefaultListModel<>();
        }
    }

    public void incluir(ProdutoModel produto) {
        getListaProdutos().addElement(produto);
        salvar();
    }

    public DefaultListModel<ProdutoModel> listar() {
        return getListaProdutos();
    }

    public DefaultListModel<ProdutoModel> consultarPorNome(String termo) {
        DefaultListModel<ProdutoModel> filtrados = new DefaultListModel<>();

        if (termo == null || termo.trim().isEmpty()) {
            return getListaProdutos();
        }

        for (int i = 0; i < getListaProdutos().size(); i++) {
            ProdutoModel p = getListaProdutos().getElementAt(i);

            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                filtrados.addElement(p);
            }
        }

        return filtrados;
    }

    public ProdutoModel consultarPorId(int idBusca) {
        for (int i = 0; i < getListaProdutos().size(); i++) {
            ProdutoModel p = getListaProdutos().getElementAt(i);

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
            salvar();
        }
    }

    public void remover(int idProdutoExcluir) {
        ProdutoModel produto = consultarPorId(idProdutoExcluir);

        if (produto != null) {
            getListaProdutos().removeElement(produto);
            salvar();
        }
    }

    public void salvar() {
        ArquivoUtil.armazenar(ARQUIVO, listaProdutos);
    }
}