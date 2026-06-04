package Repository;

import Model.ClienteModel;
import Model.PedidoModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class PedidoRepository {

    private DefaultListModel<PedidoModel> listaPedidos;
    private final String ARQUIVO = "pedidos.csv";

    public PedidoRepository() {
        DefaultListModel<?> listaGenerica = ArquivoUtil.ler(4);
        
        if (listaGenerica != null) {
            listaPedidos = (DefaultListModel<PedidoModel>) listaGenerica;
        } else {
            listaPedidos = new DefaultListModel<>();
        }
    }

    public void incluir(ClienteModel cliente) {
        int id = listaPedidos.isEmpty() ? 1 : listaPedidos.lastElement().getId() + 1;

        PedidoModel pedido = new PedidoModel(id, cliente);
        listaPedidos.addElement(pedido);

        salvarNoArquivo();
    }

    public PedidoModel consultarPorId(int idBusca) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            PedidoModel p = listaPedidos.getElementAt(i);

            if (p.getId() == idBusca) {
                return p;
            }
        }

        return null;
    }

    public void excluir(int id) {
        PedidoModel p = consultarPorId(id);

        if (p != null) {
            listaPedidos.removeElement(p);
            salvarNoArquivo();
        }
    }

    private void salvarNoArquivo() {
        ArquivoUtil.armazenar(ARQUIVO, listaPedidos);
    }

    public DefaultListModel<PedidoModel> getListaPedidos() {
        return listaPedidos;
    }
}