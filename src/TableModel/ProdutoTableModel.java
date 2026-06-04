package TableModel;

import Model.ItemPedidoModel;
import Model.ProdutoModel;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;


public class ProdutoTableModel extends AbstractTableModel{
    private final DefaultListModel<ProdutoModel> listModel;
    private final String[] colunas = {"ID", "Preço", "Estoque"};
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @SuppressWarnings("unchecked")
    public ProdutoTableModel(DefaultListModel<ProdutoModel> listModel) {
        this.listModel = listModel;
        
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
        ProdutoModel produto = listModel.getElementAt(rowIndex);
        
        return switch (columnIndex) {
            case 0 -> produto.getId();
            case 1 -> produto.getPreco();
            case 2 -> produto.getQuantidade();
            default -> null;
        };
    }
}
