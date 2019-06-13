package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.dep.DepartmentView;
import view.emp.EmployeeView;
import view.pro.ProjectView;
import view.sc.ScoreView;

public class MainView {
	public void init() {
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel mainPanel = (JPanel) frame.getContentPane();
		mainPanel.setLayout(new FlowLayout());
		JButton empBtn = new JButton();
		empBtn.setText("Ա������");
		empBtn.setPreferredSize(new Dimension(180, 120));
		mainPanel.add(empBtn);

		empBtn.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new EmployeeView().init();
			}
		});

		JButton depBtn = new JButton();
		depBtn.setText("���Ź���");
		depBtn.setPreferredSize(new Dimension(180, 120));
		mainPanel.add(depBtn);
	
		depBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DepartmentView().init();
			}
		});

		JButton proBtn = new JButton();
		proBtn.setText("��Ŀ����");
		proBtn.setPreferredSize(new Dimension(180, 120));
		mainPanel.add(proBtn);

		proBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ProjectView().init();
			}
		});

		JButton scBtn = new JButton();
		scBtn.setText("��Ч����");
		scBtn.setPreferredSize(new Dimension(180, 120));
		mainPanel.add(scBtn);

		scBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ScoreView().init();
			}
		});

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new MainView().init();
	}
}
