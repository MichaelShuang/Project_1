package view.dep.pro;

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

import dao.DepartmentProjectDao;
import dao.ProjectDao;
import entity.Department;
import entity.Project;

public class DepartmentProjectView {

	DepartmentProjectTableModel model;
	JComboBox box;
	List<Project> list;
	List<Project> noList;
	Department dep;
	DepartmentProjectDao dpDao = new DepartmentProjectDao();
	JButton addBtn;
	JTable table;
	JButton deleteBtn;

	public DepartmentProjectView(Department dep) {
		this.dep = dep;
	}

	public void init() {

		JFrame frame = new JFrame();
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel mainPanel = (JPanel) frame.getContentPane();
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxLayout);

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5));

		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		JLabel depLabel = new JLabel();
		depLabel.setText(dep.getName());
		panel1.add(depLabel);

		list = dpDao.searchByDep(dep.getId());
		model = new DepartmentProjectTableModel();
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(450, 200));
		panel2.add(scroll);

		box = new JComboBox<>();

		box.setPreferredSize(new Dimension(120, 30));
		panel3.add(box);

		addBtn = new JButton();
		addBtn.setText("Ôö¼Ó");
		addBtn.setPreferredSize(new Dimension(60, 30));
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int depId = dep.getId();
				int index = box.getSelectedIndex();

				if (index != -1) {
					int proId = noList.get(index).getId();
					boolean flag = dpDao.add(depId, proId);
					refresh();
				}
			}
		});
		panel3.add(addBtn);

		deleteBtn = new JButton();
		deleteBtn.setText("É¾³ý");
		deleteBtn.setPreferredSize(new Dimension(60, 30));
		panel3.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int depId = dep.getId();
				int index = table.getSelectedRow();

				if (index != -1) {
					int proId = list.get(index).getId();
					boolean flag = dpDao.delete(depId, proId);
					refresh();
				}
			}
		});
		refresh();
		frame.setVisible(true);
	}

	public void refresh() {
		list = dpDao.searchByDep(dep.getId());
		model.setList(list);
		model.fireTableDataChanged();
		noList = dpDao.searchByNoDep(dep.getId());
		box.removeAllItems();
		for (int i = 0; i < noList.size(); i++) {
			box.addItem(noList.get(i).getName());
		}
		if (box.getItemCount() == 0) {
			box.setEnabled(false);
			addBtn.setEnabled(false);
		} else {
			box.setEnabled(true);
			addBtn.setEnabled(true);
		}
		if (table.getRowCount() == 0) {
			deleteBtn.setEnabled(false);
		} else {
			deleteBtn.setEnabled(true);
		}
	}
}
