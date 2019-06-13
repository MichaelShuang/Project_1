package view.dep;

import dao.DepartmentDao;
import entity.Department;
import util.CallBack;

public class AddDepartmentView extends SuperDepartmentView {

	CallBack cb;
	DepartmentDao depDao = new DepartmentDao();
	private static AddDepartmentView instance;

	private AddDepartmentView(CallBack cb) {
		super(cb);
	}

	public static AddDepartmentView getInstance(CallBack cb) {
		if (instance == null) {
			instance = new AddDepartmentView(cb);
		}
		return instance;
	}

	public void setData() {
		nameText.setText("");
	}

	public boolean action(Department dep) {
		boolean flag = depDao.add(dep);
		return flag;
	}
}
