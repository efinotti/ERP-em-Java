package erpapp.Repository;

import erpapp.Entities.Pedido;
import erpapp.Entities.ItemPedido;
import erpapp.Entities.Produto;
import Util.ArquivoUtil;
import java.util.ArrayList;

public class PedidoRepository {
   
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public PedidoRepository() {
        this.listaPedidos = ArquivoUtil.recuperarPedidos();
    }

    public void incluir(Pedido p) {
        listaPedidos.add(p);
        ArquivoUtil.salvarDados(listaPedidos);
    }

    public ArrayList<Pedido> listar() {
        return this.listaPedidos;
    }
    
    public Pedido consultarPorId(int idBusca) {
        for (Pedido p : listaPedidos) {
            if (p.getId() == idBusca) {
                return p;
            }
        }
        return null;
    }

    public void alterarPedido(int idPedidoAlterar, Produto produtoNovo, int novaQuantidade) {
        Pedido pedidoOriginal = consultarPorId(idPedidoAlterar);

        if (pedidoOriginal == null) {
            System.err.println("Pedido não encontrado para alteração.");
            return;
        }

        boolean itemAtualizado = false;
        for (ItemPedido item : pedidoOriginal.getItensPedido()) {
            if (item.getProduto().getId() == produtoNovo.getId()) {
                item.setQuantidade(novaQuantidade);
                itemAtualizado = true;
                break;
            }
        }
        
        if (!itemAtualizado) {
            pedidoOriginal.adicionarItem(produtoNovo, novaQuantidade);
        } else {
            pedidoOriginal.recalcularTotal();
        }

        ArquivoUtil.salvarDados(listaPedidos);
    }

    public void excluir(int idPedidoExcluir) {
        listaPedidos.removeIf(pedido -> pedido.getId() == idPedidoExcluir);
        ArquivoUtil.salvarDados(listaPedidos);
    }
}