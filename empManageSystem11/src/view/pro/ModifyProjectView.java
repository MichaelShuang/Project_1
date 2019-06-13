package view.pro;

import entity.Project;
import util.CallBack;

public class ModifyProjectView extends SuperProjectView {
	int id;
	private static ModifyProjectView instance;

	public ModifyProjectView(CallBack cb) {
		super(cb);
	}

	public static ModifyProjectView getInstance(int id, CallBack cb) {
		if (instance == null) {
			instance = new ModifyProjectView(cb);
		}
		instance.id = id;
		return instance;
	}

	public void setData() {
		Project pro = proDao.search(id);
		nameText.setText(pro.getName());
	}

	public boolean action(Project pro) {
		pro.setId(id);
		boolean flag = proDao.update(pro);
		return flag;
	}
}
