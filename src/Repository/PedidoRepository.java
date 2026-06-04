package Repository;

import Model.PedidoModel;
import Model.ClienteModel;
import Util.ArquivoUtil;
import javax.swing.DefaultListModel;

public class PedidoRepository {
    
    private DefaultListModel<PedidoModel> listaPedidos = new DefaultListModel<>();
    private final String ARQUIVO = "pedidos.csv";
    private final ArquivoUtil arquivoUtil = new ArquivoUtil();
    
    public PedidoModel consultarPorId(int idBusca) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            PedidoModel p = listaPedidos.getElementAt(i);
            if (p.getId() == idBusca) {
                return p;
            }
        }
        return null;
    }
    
    public void incluir(ClienteModel cliente) {
        int id = listaPedidos.isEmpty() ? 1 : listaPedidos.lastElement().getId() + 1;
        
        PedidoModel pedido = new PedidoModel(id, cliente);
        listaPedidos.addElement(pedido);
        
        
    }
    
    public void alterar(int id, PedidoModel pedidoAtualizado) {
        PedidoModel p = consultarPorId(id);
        if (p != null) {
            p.setId_cliente(pedidoAtualizado.getId_cliente());
            p.setDt_pedido(pedidoAtualizado.getDt_pedido());
            p.setDt_entrega(pedidoAtualizado.getDt_entrega());
            p.setItensPedido(pedidoAtualizado.getItensPedido());
            
            
        }
    }
    
    public void excluir(int id) {
        PedidoModel p = consultarPorId(id);
        if (p != null) {
            listaPedidos.removeElement(p);
        }
    }
    
    private void salvarNoArquivo() {
        arquivoUtil.armazenar(ARQUIVO, listaPedidos);
    }
    
    public DefaultListModel<PedidoModel> getListaPedidos() {
        return listaPedidos;
    }
}