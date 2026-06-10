package TableModel; 

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
        switch (columnIndex) {
            case 0:
                return pedido.getId();
            case 1:
                return pedido.getId_cliente();
            case 2:
                return pedido.getDt_pedido() != null ? sdf.format(pedido.getDt_pedido()) : ""; 
            case 3:
                return pedido.getDt_entrega() != null ? sdf.format(pedido.getDt_entrega()) : ""; 
            case 4:
                return String.format("R$ %.2f", pedido.getVlr_total()); 
            default:
                return null;
        }
    }
}