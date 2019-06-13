package view.emp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import util.CallBack;

public abstract class SuperEmployeeView {
	JFrame frame;
	CallBack cb;
	JTextField nameText;
	JComboBox sexBox;
	JTextField ageText;
	JComboBox depBox;
	EmployeeDao empDao = new EmployeeDao();
	DepartmentDao depDao = new DepartmentDao();

	public SuperEmployeeView(CallBack cb) {
		this.cb = cb;
	}

	public void init() {
		if (frame == null) {
			frame = new JFrame();
			frame.setSize(300, 400);
			frame.setLocationRelativeTo(null);
			JPanel mainPanel = (JPanel) frame.getContentPane();
			BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
			mainPanel.setLayout(boxLayout);

			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JPanel panel3 = new JPanel();
			JPanel panel4 = new JPanel();
			JPanel panel5 = new JPanel();

			mainPanel.add(panel1);
			mainPanel.add(panel2);
			mainPanel.add(panel3);
			mainPanel.add(panel4);
			mainPanel.add(panel5);

			JLabel nameLabel = new JLabel();
			nameLabel.setText("姓名");
			panel1.add(nameLabel);
			nameText = new JTextField();
			nameText.setPreferredSize(new Dimension(120, 30));
			panel1.add(nameText);

			JLabel sexLabel = new JLabel();
			sexLabel.setText("性别");
			panel2.add(sexLabel);
			sexBox = new JComboBox();
			sexBox.setPreferredSize(new Dimension(120, 30));
			sexBox.addItem("男");
			sexBox.addItem("女");
			panel2.add(sexBox);

			JLabel ageLabel = new JLabel();
			ageLabel.setText("年龄");
			panel3.add(ageLabel);
			ageText = new JTextField();
			ageText.setPreferredSize(new Dimension(120, 30));
			panel3.add(ageText);

			JLabel depLabel = new JLabel();
			depLabel.setText("部门");
			panel4.add(depLabel);
			depBox = new JComboBox();
			// 把查出的所有部门的list的名称填充到部门的下拉框中
			depBox.addItem("选择部门");
			List<Department> depList = depDao.search();
			for (int i = 0; i < depList.size(); i++) {
				depBox.addItem(depList.get(i).getName());
			}
			depBox.setPreferredSize(new Dimension(120, 30));
			panel4.add(depBox);

			JButton saveBtn = new JButton();
			saveBtn.setText("保存");
			saveBtn.setPreferredSize(new Dimension(60, 30));
			panel5.add(saveBtn);
			saveBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Employee emp = new Employee();
					emp.setName(nameText.getText());
					emp.setSex((String) sexBox.getSelectedItem());
					emp.setAge(Integer.parseInt(ageText.getText()));
					// 从下拉框中取索引
					Department dep = new Department();
					int depIndex = depBox.getSelectedIndex() - 1;
					if (depIndex != -1) {
						dep = depList.get(depIndex);
					}

					emp.setDep(dep);
					boolean flag = action(emp);
					if (flag) {
						JOptionPane.showMessageDialog(null, "SAVE SUCCESSFUL!");
					} else {
						JOptionPane.showMessageDialog(null, "SAVE UNSUCCESSFUL!");
					}
					frame.dispose();
					cb.call();
				}
			});
		}
		setData();
		frame.setVisible(true);
	}

	public abstract void setData();

	public abstract boolean action(Employee emp);

}
