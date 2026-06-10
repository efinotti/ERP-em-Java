package Repository;

import Model.ItemPedidoModel;
import Model.ProdutoModel; // Importado corretamente
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class ItemPedidoRepository {

    private DefaultListModel<ItemPedidoModel> listaItemPedido;
    private String ARQUIVO = "itemPedidos.csv";

    @SuppressWarnings("unchecked")
    public ItemPedidoRepository() {
        DefaultListModel<?> listaGenerica = ArquivoUtil.ler(3);
        
        if (listaGenerica != null) {
            listaItemPedido = (DefaultListModel<ItemPedidoModel>) listaGenerica;
        } else {
            listaItemPedido = new DefaultListModel<>();
        }
    }
    
    // CORRIGIDO: Alterado de 'int id_produto' para 'ProdutoModel produto'
    public void criarItemPedido(int id, int id_pedido, ProdutoModel produto, int quantidade) {
        if (produto == null) return; // Segurança contra valores nulos

        // Passa o objeto 'produto' para o novo construtor do ItemPedidoModel
        ItemPedidoModel itemPedido = new ItemPedidoModel(id, id_pedido, produto, quantidade);
        
        getListaItemPedido().addElement(itemPedido);
    }
    
    public void salvar() {
        ArquivoUtil.armazenar(getARQUIVO(), getListaItemPedido());
    }
    
    public DefaultListModel<ItemPedidoModel> getList() {
        return getListaItemPedido();
    }

    public DefaultListModel<ItemPedidoModel> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(DefaultListModel<ItemPedidoModel> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }

    public String getARQUIVO() {
        return ARQUIVO;
    }

    public void setARQUIVO(String ARQUIVO) {
        this.ARQUIVO = ARQUIVO;
    }
}