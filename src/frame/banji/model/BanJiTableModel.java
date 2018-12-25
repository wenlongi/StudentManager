package frame.banji.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entity.BanJi;

public class BanJiTableModel extends AbstractTableModel {
	List<BanJi> list = new ArrayList<BanJi>();
	String columnNames[] = { "序号", "名称", "人数" };

	public BanJiTableModel(List<BanJi> list) {
		this.list = list;
	}

	@Override
	public int getRowCount() { // getRowCount 行
		return list.size();
	}

	public String getColumnName(int column) {// getColumnName 列
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			// return rowIndex+1; //序号连续
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
