package TableModel;

import Model.ItemPedidoModel;
import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;

public class ItemPedidoTableModel extends AbstractTableModel {
    private final DefaultListModel<ItemPedidoModel> listModel;
    private final String[] colunas = {"ID", "ID Pedido", "ID Produto", "Quantidade"};

    @SuppressWarnings("unchecked")
    public ItemPedidoTableModel(DefaultListModel<?> listModel) {
        this.listModel = (DefaultListModel<ItemPedidoModel>) listModel;
        
        this.listModel.addListDataListener(new javax.swing.event.ListDataListener() {
            @Override public void intervalAdded(javax.swing.event.ListDataEvent e) { fireTableDataChanged(); }
            @Override public void intervalRemoved(javax.swing.event.ListDataEvent e) { fireTableDataChanged(); }
            @Override public void contentsChanged(javax.swing.event.ListDataEvent e) { fireTableDataChanged(); }
        });
    }

    @Override
    public int getRowCount() {
        return listModel.getSize();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemPedidoModel item = listModel.getElementAt(rowIndex);
        
        return switch (columnIndex) {
            case 0 -> item.getId(); // Coluna 0: ID do item
            case 1 -> item.getProduto() != null ? item.getProduto().getNome() : "Produto ID: " + item.getIdProduto(); // Coluna 1: Nome do Produto (Ype, Vassoura)
            case 2 -> item.getQuantidade(); // Coluna 2: Quantidade do item
            case 3 -> String.format("R$ %.2f", item.getPrecoTotal()); // Coluna 3: Valor Total da linha (Ex: R$ 20,00)
            default -> null;
        };
    }
}