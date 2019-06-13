package view.emp;

import entity.Employee;
import util.CallBack;

public class ModifyEmployeeView extends SuperEmployeeView {
	private Employee selectEmp;
	private static ModifyEmployeeView instance;

	public ModifyEmployeeView(CallBack cb) {
		super(cb);
	}

	public static ModifyEmployeeView getInstance(Employee selectEmp, CallBack cb) {
		if (instance == null) {
			instance = new ModifyEmployeeView(cb);
		}
		instance.selectEmp = selectEmp;
		return instance;
	}

	public void setData() {
		nameText.setText(selectEmp.getName());
		sexBox.setSelectedItem(selectEmp.getSex());
		ageText.setText(String.valueOf(selectEmp.getAge()));
		if (selectEmp.getDep().getName() != null) {
			depBox.setSelectedItem(selectEmp.getDep().getName());
		} else {
			depBox.setSelectedIndex(0);
		}

	}

	public boolean action(Employee emp) {
		emp.setId(selectEmp.getId());
		boolean flag = empDao.update(emp);
		return flag;
	}
}
