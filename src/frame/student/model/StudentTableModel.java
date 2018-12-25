package frame.student.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entity.Student;

public class StudentTableModel extends AbstractTableModel {
	List<Student> list = new ArrayList<Student>();
	String columnNames[] = { "���", "����", "�Ա�", "����", "�༶" };

	public StudentTableModel(List<Student> list) {
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
			return list.get(rowIndex).getSex();
		} else if (columnIndex == 3) {
			return list.get(rowIndex).getAge();
		} else if (columnIndex == 4) {
			return list.get(rowIndex).getBj().getName();
		}
		return null;
	}

	public void refreshTable(List<Student> list) {
		this.list = list;
		fireTableDataChanged();
	}
}
