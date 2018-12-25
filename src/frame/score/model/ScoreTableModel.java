package frame.score.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;
import javax.swing.table.AbstractTableModel;
import entity.Score;

public class ScoreTableModel extends AbstractTableModel {
	List<Score> list = new ArrayList<Score>();
	//ʹ��listʱ
	// public List<Score> saveList = new ArrayList<Score>();
	public Set<Score> saveSet = new HashSet<>();

	String columnNames[] = { "���", "����", "�༶", "��Ŀ", "�ɼ�", "�ȼ�" };

	public ScoreTableModel(List<Score> list) {
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

	// �����ĸ���Ԫ����Ա༭
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 4) {
			return true;
		} else {
			return false;
		}
	}

	// ���õ�Ԫ���ֵ
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		list.get(rowIndex).setResult(Integer.parseInt((String) aValue));
		saveSet.add(list.get(rowIndex));
		//ʹ��listʱ
		// boolean flag = true;
		// for (int i = 0; i < saveList.size(); i++) {
		// if (saveList.get(i) == list.get(rowIndex)) {
		// flag = false;
		// break;
		// }
		// }
		// if (flag) {
		// saveList.add(list.get(rowIndex));
		// }
	}

	// ÿ����Ԫ����ʾʲô
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return rowIndex + 1; // �������
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getStu().getName();
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getStu().getBj().getName();
		} else if (columnIndex == 3) {
			return list.get(rowIndex).getSub().getName();
		} else if (columnIndex == 4) {
			return list.get(rowIndex).getResult();
		} else if (columnIndex == 5) {
			return list.get(rowIndex).getGrade();
		}
		return null;
	}

	public void refreshTable(List<Score> list) {
		this.list = list;
		fireTableDataChanged();
	}
}
