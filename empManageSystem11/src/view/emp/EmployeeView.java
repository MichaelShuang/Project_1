package view.emp;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import util.CallBack;

public class EmployeeView {
	EmployeeDao empDao = new EmployeeDao();
	EmployeeTableModel model;
	List<Employee> list;
	JComboBox depBox;
	DepartmentDao depDao = new DepartmentDao();

	public void init() {
		JFrame frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel mainPanel = (JPanel) frame.getContentPane();
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxLayout);

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 5));

		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("姓名");
		panel1.add(nameLabel);
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameText);

		JLabel sexLabel = new JLabel();
		sexLabel.setText("性别");
		panel1.add(sexLabel);
		JTextField sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(100, 30));
		panel1.add(sexText);

		JLabel ageLabel = new JLabel();
		ageLabel.setText("年龄");
		panel1.add(ageLabel);
		JTextField ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(100, 30));
		panel1.add(ageText);

		JLabel depLabel = new JLabel();
		depLabel.setText("部门");
		panel1.add(depLabel);
		depBox = new JComboBox();
		// 把查出的所有部门的list的名称填充到部门的下拉框中
		depBox.addItem("选择部门");
		List<Department> depList = depDao.search();
		for (int i = 0; i < depList.size(); i++) {
			depBox.addItem(depList.get(i).getName());
		}
		depBox.setPreferredSize(new Dimension(100, 30));
		panel1.add(depBox);

		JButton searchBtn = new JButton();
		searchBtn.setText("查询");
		searchBtn.setPreferredSize(new Dimension(60, 30));
		panel1.add(searchBtn);

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee searchEmp = new Employee();
				searchEmp.setName(nameText.getText());
				searchEmp.setSex(sexText.getText());
				Department dep = new Department();
				int depIndex = depBox.getSelectedIndex() - 1;
				if (depIndex != -1) {
					dep = depList.get(depIndex);
				}

				searchEmp.setDep(dep);
				int age = -1;
				try {
					age = Integer.parseInt(ageText.getText());
				} catch (Exception e1) {
				}
				searchEmp.setAge(age);
				list = empDao.search(searchEmp);
				refreshTable(list);
			}
		});

		list = empDao.search();
		model = new EmployeeTableModel(list);
		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(550, 300));

		panel2.add(scroll);

		JButton addBtn = new JButton();
		addBtn.setText("新增");
		addBtn.setPreferredSize(new Dimension(60, 30));
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddEmployeeView aev = AddEmployeeView.getInstance(new CallBack() {
					@Override
					public void call() {
						// TODO Auto-generated method stub
						refreshTable();
					}
				});
				aev.init();
			}
		});
		panel3.add(addBtn);

		JButton modifyBtn = new JButton();
		modifyBtn.setText("修改");
		modifyBtn.setPreferredSize(new Dimension(60, 30));
		panel3.add(modifyBtn);
		modifyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index != -1) {
					ModifyEmployeeView aev = ModifyEmployeeView.getInstance(list.get(index), new CallBack() {
						public void call() {
							refreshTable();
						}
					});
					aev.init();
				} else {
					JOptionPane.showMessageDialog(null, "Please choose one message!");
				}
			}
		});

		JButton deleteBtn = new JButton();
		deleteBtn.setText("删除");
		deleteBtn.setPreferredSize(new Dimension(60, 30));
		panel3.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = table.getSelectedRow();
				if (index != -1) {
					int type = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", " ",
							JOptionPane.YES_NO_OPTION);
					if (type == 0) {
						int id = list.get(index).getId();
						boolean flag = empDao.delete(id);
						refreshTable();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please choose one message!");
				}
			}
		});
		frame.setVisible(true);
	}

	public void refreshTable(List<Employee> list) {
		EmployeeDao empDao = new EmployeeDao();
		model.setList(list);
		model.fireTableDataChanged();
	}

	public void refreshTable() {
		EmployeeDao empDao = new EmployeeDao();
		list = empDao.search();
		model.setList(list);
		model.fireTableDataChanged();
	}
}
