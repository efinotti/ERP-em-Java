package Repository;

import Model.ItemPedidoModel;
import Model.ProdutoModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class ItemPedidoRepository {

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
    private DefaultListModel<ItemPedidoModel> listaItemPedido;
    private String ARQUIVO = "itemPedidos.csv";

    public ItemPedidoRepository() {
        DefaultListModel<?> listaGenerica = ArquivoUtil.ler(3);
        
        if (listaGenerica != null) {
            listaItemPedido = (DefaultListModel<ItemPedidoModel>) listaGenerica;
        } else {
            listaItemPedido = new DefaultListModel<>();
        }
    }
    
    public void criarItemPedido(int id, int id_pedido, int id_produto, int quantidade) {
        
        ItemPedidoModel itemPedido = new ItemPedidoModel(id, id_pedido, id_produto, quantidade);
        
        getListaItemPedido().addElement(itemPedido);
    }
    
    public void salvar() {
        ArquivoUtil.armazenar(getARQUIVO(), getListaItemPedido());
    }
    
    public DefaultListModel<ItemPedidoModel> getList() {
        return getListaItemPedido();
    }
    
    
}
