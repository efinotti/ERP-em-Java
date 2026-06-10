package Repository;

import Model.ClienteModel;
import Model.PedidoModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class PedidoRepository {
    private DefaultListModel<PedidoModel> listaPedidos;
    private final String ARQUIVO = "pedidos.csv";

    @SuppressWarnings("unchecked")
    public PedidoRepository() {
        Object listaGenerica = ArquivoUtil.ler(4);
        if (listaGenerica != null) {
            listaPedidos = (DefaultListModel<PedidoModel>) listaGenerica;
        } else {
            listaPedidos = new DefaultListModel<>();
        }
    }

    public DefaultListModel<PedidoModel> getLista() {
        return listaPedidos;
    }

    public void incluir(ClienteModel cliente) {
        int id = listaPedidos.isEmpty() ? 1 : listaPedidos.lastElement().getId() + 1;
        PedidoModel pedido = new PedidoModel(id, cliente);
        listaPedidos.addElement(pedido);
        salvarNoArquivo();
    }

    public void listar() {
        if (listaPedidos.getSize() <= 0) {
            System.out.println("Lista de pedidos está vazia");
            return;
        }
        for (int i = 0; i < listaPedidos.getSize(); i++) {
            System.out.println(listaPedidos.get(i).toString() + "\n");
        }
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

    public void salvar() {
        ArquivoUtil.armazenar(ARQUIVO, listaPedidos);
    }
}