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
	//使用list时
	// public List<Score> saveList = new ArrayList<Score>();
	public Set<Score> saveSet = new HashSet<>();

	String columnNames[] = { "序号", "姓名", "班级", "科目", "成绩", "等级" };

	public ScoreTableModel(List<Score> list) {
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

	// 设置哪个单元格可以编辑
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 4) {
			return true;
		} else {
			return false;
		}
	}

	// 设置单元格的值
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		list.get(rowIndex).setResult(Integer.parseInt((String) aValue));
		saveSet.add(list.get(rowIndex));
		//使用list时
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

	// 每个单元格显示什么
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return rowIndex + 1; // 序号连续
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
