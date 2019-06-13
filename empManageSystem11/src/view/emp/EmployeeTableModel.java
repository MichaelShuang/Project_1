package view.emp;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Employee;

public class EmployeeTableModel extends AbstractTableModel {
	List<Employee> list;
	String[] columnNames = { "序号", "姓名", "性别", "年龄", "部门" };

	public EmployeeTableModel(List<Employee> list) {
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
			return list.get(rowIndex).getSex();
		} else if (columnIndex == 3) {
			return list.get(rowIndex).getAge();
		} else if (columnIndex == 4) {
			return list.get(rowIndex).getDep().getName();
		}

		return null;
	}

	public void setList(List<Employee> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

}
