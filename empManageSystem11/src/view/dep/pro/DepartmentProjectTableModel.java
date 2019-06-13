package view.dep.pro;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Project;

public class DepartmentProjectTableModel extends AbstractTableModel {
	List<Project> list;
	// Project[] pros;
	String[] columnNames = { "ÐòºÅ", "Ãû³Æ" };

	public DepartmentProjectTableModel() {

	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
		// return pros.length;
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
			// return pros[rowsIndex].getName();
		}

		return null;
	}

	public void setList(List<Project> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}
}
