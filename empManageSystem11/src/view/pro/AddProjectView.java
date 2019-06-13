package view.pro;

import dao.ProjectDao;
import entity.Project;
import util.CallBack;

public class AddProjectView extends SuperProjectView {

	CallBack cb;
	ProjectDao proDao = new ProjectDao();
	private static AddProjectView instance;

	private AddProjectView(CallBack cb) {
		super(cb);
	}

	public static AddProjectView getInstance(CallBack cb) {
		if (instance == null) {
			instance = new AddProjectView(cb);
		}
		return instance;
	}

	public void setData() {
		nameText.setText("");
	}

	public boolean action(Project pro) {
		boolean flag = proDao.add(pro);
		return flag;
	}
}
