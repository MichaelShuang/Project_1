package view.dep;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DepartmentDao;
import entity.Department;
import util.CallBack;

public abstract class SuperDepartmentView {
	JFrame frame;
	CallBack cb;
	JTextField nameText;
	DepartmentDao depDao = new DepartmentDao();

	public SuperDepartmentView(CallBack cb) {
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

			mainPanel.add(panel1);
			mainPanel.add(panel2);
			mainPanel.add(panel3);
			mainPanel.add(panel4);

			JLabel nameLabel = new JLabel();
			nameLabel.setText("Ãû³Æ");
			panel1.add(nameLabel);
			nameText = new JTextField();
			nameText.setPreferredSize(new Dimension(120, 30));
			panel1.add(nameText);

			JButton saveBtn = new JButton();
			saveBtn.setText("±£´æ");
			saveBtn.setPreferredSize(new Dimension(60, 30));
			panel4.add(saveBtn);
			saveBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Department dep = new Department();
					dep.setName(nameText.getText());
					boolean flag = action(dep);
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

	public abstract boolean action(Department dep);

}
