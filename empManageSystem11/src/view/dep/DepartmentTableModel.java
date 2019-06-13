package view.dep;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Department;

public class DepartmentTableModel extends AbstractTableModel {
	List<Department> list;
	String[] columnNames = { "序号", "名称", "员工数目" };

	public DepartmentTableModel(List<Department> list) {
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
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getEmpNum();
		}

		return null;
	}

	public void setList(List<Department> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

}
