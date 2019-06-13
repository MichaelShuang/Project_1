package view.sc;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DepartmentDao;
import dao.ScoreDao;
import entity.Department;
import entity.Score;

public class ScoreView {

	ScoreTableModel model;
	List<Score> list;
	JComboBox depBox;
	DepartmentDao depDao = new DepartmentDao();
	ScoreDao scDao = new ScoreDao();

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
		nameLabel.setText("姓名");
		panel1.add(nameLabel);
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(80, 30));
		panel1.add(nameText);

		JLabel sexLabel = new JLabel();
		sexLabel.setText("性别");
		panel1.add(sexLabel);
		JTextField sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(80, 30));
		panel1.add(sexText);

		JLabel ageLabel = new JLabel();
		ageLabel.setText("年龄");
		panel1.add(ageLabel);
		JTextField ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(80, 30));
		panel1.add(ageText);

		JLabel depLabel = new JLabel();
		depLabel.setText("部门");
		panel1.add(depLabel);
		depBox = new JComboBox();
		depBox.addItem("选择部门");
		List<Department> depList = depDao.search();
		for (int i = 0; i < depList.size(); i++) {
			depBox.addItem(depList.get(i).getName());
		}
		depBox.setPreferredSize(new Dimension(80, 30));
		panel1.add(depBox);

		JButton searchBtn = new JButton();
		searchBtn.setText("查询");
		searchBtn.setPreferredSize(new Dimension(60, 30));
		panel1.add(searchBtn);

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Score searchSc = new Score();
				searchSc.setGrade(nameText.getText());
				list = scDao.search();
				refreshTable(list);
			}
		});

		list = scDao.search();
		model = new ScoreTableModel(list);
		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(550, 300));
		panel2.add(scroll);

		JButton saveBtn = new JButton();
		saveBtn.setText("保存");
		saveBtn.setPreferredSize(new Dimension(60, 30));
		panel3.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scDao.save(model.getSaveScores());
				refreshTable();
			}
		});
		frame.setVisible(true);
	}

	public void refreshTable(List<Score> list) {
		ScoreDao scDao = new ScoreDao();
		model.setList(list);
		model.fireTableDataChanged();
	}

	public void refreshTable() {
		list = scDao.search();
		model.setList(list);
		model.getSaveScores().clear();
		model.fireTableDataChanged();
	}
}
