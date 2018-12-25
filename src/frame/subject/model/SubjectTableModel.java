package frame.subject.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entity.Subject;

public class SubjectTableModel extends AbstractTableModel {
	List<Subject> list = new ArrayList<Subject>();
	String columnNames[] = { "���", "��Ŀ" };

	public SubjectTableModel(List<Subject> list) {
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
		} 
		return null;
	}

	public void refreshTable(List<Subject> list) {
		this.list = list;
		fireTableDataChanged();
	}
}
