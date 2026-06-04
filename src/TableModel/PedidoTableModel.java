package Controller; // Ou crie um pacote específico como 'Util' ou 'TableModel'

import Model.PedidoModel;
import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;

public class PedidoTableModel extends AbstractTableModel {

    private final DefaultListModel<PedidoModel> listModel;
    private final String[] colunas = {"ID", "ID Cliente", "Data Pedido", "Data Entrega", "Valor Total"};
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @SuppressWarnings("unchecked")
    public PedidoTableModel(DefaultListModel<?> listModel) {
        this.listModel = (DefaultListModel<PedidoModel>) listModel;
        
        this.listModel.addListDataListener(new javax.swing.event.ListDataListener() {
            @Override public void intervalAdded(javax.swing.event.ListDataEvent e) { fireTableDataChanged(); }
            @Override public void intervalRemoved(javax.swing.event.ListDataEvent e) { fireTableDataChanged(); }
            @Override public void contentsChanged(javax.swing.event.ListDataEvent e) { fireTableDataChanged(); }
        });
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
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
        PedidoModel pedido = listModel.getElementAt(rowIndex);
        
        return pedido.getId();
    }
}