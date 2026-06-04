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
            case 0 -> item.getId();
            case 1 -> item.getIdPedido();
            case 2 -> item.getIdProduto();
            case 3 -> item.getQuantidade();
            default -> null;
        };
    }
}