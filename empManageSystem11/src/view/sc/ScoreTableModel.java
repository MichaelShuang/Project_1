package view.sc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import entity.Score;

public class ScoreTableModel extends AbstractTableModel {
	List<Score> list;
	private Set<Score> saveScores = new HashSet<>();
	String[] columnNames = { "id", "姓名", "部门", "项目", "成绩", "等级" };

	public Set<Score> getSaveScores() {
		return saveScores;
	}

	public ScoreTableModel(List<Score> list) {
		this.list = list;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 4) {
			return true;
		}
		return false;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		list.get(rowIndex).setValue(Double.parseDouble((String) aValue));
		boolean flag = true;
//		for (Score sc : saveScores) {
//			if (list.get(rowIndex) == sc) {
//				flag = false;
//				break;
//			}
//		}
//		if (flag) {
		saveScores.add(list.get(rowIndex));
//		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

		if (columnIndex == 0) {
			return rowIndex + 1;
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getEmp().getName();
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getEmp().getDep().getName();
		} else if (columnIndex == 3) {
			return list.get(rowIndex).getPro().getName();
		} else if (columnIndex == 4) {
			return list.get(rowIndex).getValue();
		} else if (columnIndex == 5) {
			return list.get(rowIndex).getGrade();
		}

		return null;
	}

	public void setList(List<Score> list) {
		this.list = list;
	}
}
