package view.dep;

import entity.Department;
import util.CallBack;

public class ModifyDepartmentView extends SuperDepartmentView {
	int id;
	private static ModifyDepartmentView instance;

	public ModifyDepartmentView(CallBack cb) {
		super(cb);
	}

	public static ModifyDepartmentView getInstance(int id, CallBack cb) {
		if (instance == null) {
			instance = new ModifyDepartmentView(cb);
		}
		instance.id = id;
		return instance;
	}

	public void setData() {
		Department dep = depDao.search(id);
		nameText.setText(dep.getName());
	}

	public boolean action(Department dep) {
		dep.setId(id);
		boolean flag = depDao.update(dep);
		return flag;
	}
}
