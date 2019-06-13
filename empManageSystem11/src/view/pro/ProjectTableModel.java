package view.pro;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Project;

public class ProjectTableModel extends AbstractTableModel {
	List<Project> list;
	String[] columnNames = { "ÐòºÅ", "Ãû³Æ" };

	public ProjectTableModel(List<Project> list) {
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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

		if (columnIndex == 0) {
			return rowIndex + 1;
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getName();
		}

		return null;
	}

	public void setList(List<Project> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

}
