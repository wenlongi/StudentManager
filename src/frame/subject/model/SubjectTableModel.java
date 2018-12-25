package frame.subject.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entity.Subject;

public class SubjectTableModel extends AbstractTableModel {
	List<Subject> list = new ArrayList<Subject>();
	String columnNames[] = { "序号", "科目" };

	public SubjectTableModel(List<Subject> list) {
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
		} 
		return null;
	}

	public void refreshTable(List<Subject> list) {
		this.list = list;
		fireTableDataChanged();
	}
}
