package frame.banji.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entity.BanJi;

public class BanJiTableModel extends AbstractTableModel {
	List<BanJi> list = new ArrayList<BanJi>();
	String columnNames[] = { "���", "����", "����" };

	public BanJiTableModel(List<BanJi> list) {
		this.list = list;
	}

	@Override
	public int getRowCount() { // getRowCount ��
		return list.size();
	}

	public String getColumnName(int column) {// getColumnName ��
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			// return rowIndex+1; //�������
			return list.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getName();
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getStuNums();
		}
		return null;
	}

	public void refreshTable(List<BanJi> list) {
		this.list = list;
		fireTableDataChanged();
	}
}
