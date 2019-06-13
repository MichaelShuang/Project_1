package view.pro;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.ProjectDao;
import entity.Project;
import util.CallBack;

public class ProjectView {

	ProjectTableModel model;
	List<Project> list;
	ProjectDao proDao = new ProjectDao();

	public void init() {

		JFrame frame = new JFrame();
		frame.setSize(600, 500);
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
		nameLabel.setText("名称");
		panel1.add(nameLabel);
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(120, 30));
		panel1.add(nameText);

		JButton searchBtn = new JButton();
		searchBtn.setText("查询");
		searchBtn.setPreferredSize(new Dimension(60, 30));
		panel1.add(searchBtn);

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Project searchEmp = new Project();
				searchEmp.setName(nameText.getText());
				list = proDao.search(searchEmp);
				refreshTable(list);
			}
		});

		list = proDao.search();
		model = new ProjectTableModel(list);
		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(550, 300));

		panel2.add(scroll);

		JButton addBtn = new JButton();
		addBtn.setText("增加");
		addBtn.setPreferredSize(new Dimension(60, 30));
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddProjectView aev = AddProjectView.getInstance(new CallBack() {
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
					int id = list.get(index).getId();
					ModifyProjectView aev = ModifyProjectView.getInstance(id, new CallBack() {
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
						boolean flag = proDao.delete(id);
						refreshTable();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please choose one message!");
				}
			}
		});
		frame.setVisible(true);
	}

	public void refreshTable(List<Project> list) {
		ProjectDao proDao = new ProjectDao();
		model.setList(list);
		model.fireTableDataChanged();
	}

	public void refreshTable() {
		ProjectDao proDao = new ProjectDao();
		list = proDao.search();
		model.setList(list);
		model.fireTableDataChanged();
	}
}
