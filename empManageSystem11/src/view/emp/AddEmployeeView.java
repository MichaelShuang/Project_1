package view.emp;

import dao.EmployeeDao;
import entity.Employee;
import util.CallBack;

public class AddEmployeeView extends SuperEmployeeView {

	CallBack cb;
	EmployeeDao empDao = new EmployeeDao();
	private static AddEmployeeView instance;

	private AddEmployeeView(CallBack cb) {
		super(cb);
	}

	public static AddEmployeeView getInstance(CallBack cb) {
		if (instance == null) {
			instance = new AddEmployeeView(cb);
		}
		return instance;
	}

	public void setData() {
		nameText.setText("");
		sexBox.setSelectedItem("ÄÐ");
		ageText.setText(String.valueOf(""));
	}

	public boolean action(Employee emp) {
		boolean flag = empDao.add(emp);
		return flag;
	}
}
