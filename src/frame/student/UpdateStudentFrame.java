package frame.student;

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
import javax.swing.JTextField;

import dao.BanJiDao;
import dao.StudentDao;
import entity.BanJi;
import entity.Student;
import util.Util;

public class UpdateStudentFrame {
	Student stu;
	StudentDao stuDao = new StudentDao();
	StudentFrame sf;
	BanJiDao bjDao = new BanJiDao();

	public UpdateStudentFrame(StudentFrame sf, Student stu) {
		this.sf = sf;
		this.stu = stu;
	}

	public void init() {

		JFrame frame = new JFrame();
		frame.setTitle("修改学生");
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		// 设置主panel 是垂直布局
		JPanel panel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("输入新的姓名");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(120, 30));
		nameText.setText(stu.getName());
		panel1.add(nameLabel);
		panel1.add(nameText);

		JLabel sexLabel = new JLabel();
		sexLabel.setText("输入新的性别");
		JTextField sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(120, 30));
		sexText.setText(stu.getSex());
		panel2.add(sexLabel);
		panel2.add(sexText);

		JLabel ageLabel = new JLabel();
		ageLabel.setText("输入新的年龄");
		JTextField ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(120, 30));
		ageText.setText(Integer.toString(stu.getAge()));

		panel3.add(ageLabel);
		panel3.add(ageText);
		
		JLabel bjLabel = new JLabel();
		bjLabel.setText("班级");
		panel4.add(bjLabel);
		JComboBox bjBox = new JComboBox();
		List<BanJi> bjlist = bjDao.searchAll();
		BanJi bj=stu.getBj();
		bjBox.addItem("请选择班级");
		for (int i = 0; i < bjlist.size(); i++) {
			bjBox.addItem(bjlist.get(i).getName());
			if(bjlist.get(i).getId()==bj.getId()) {
				bjBox.setSelectedIndex(i+1);		//定位班级
			}
		}
		
		bjBox.setPreferredSize(new Dimension(120, 30));
		panel4.add(bjBox);

		JButton saveButton = new JButton();
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.setText("保存");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Student stu = new Student();
				String name = nameText.getText();
				String sex = sexText.getText();
				int age = Integer.parseInt(ageText.getText());

				stu.setName(name);
				stu.setSex(sex);
				stu.setAge(age);

				int index = bjBox.getSelectedIndex();
				//通过工具类，传入索引，拿到选中的班级
				BanJi bj=Util.getSelectBanJiItem(index);
				stu.setBj(bj);
				
				boolean flag = stuDao.update(stu);
				sf.refreshTable();
				frame.dispose();
				if (flag) {
					JOptionPane.showMessageDialog(null, "保存成功！");
				} else {
					JOptionPane.showMessageDialog(null, "保存失败！");
				}

			}
		});
		JButton cancelButton = new JButton();
		cancelButton.setPreferredSize(new Dimension(60, 30));
		cancelButton.setText("取消");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel5.add(saveButton);
		panel5.add(cancelButton);
		frame.setVisible(true);
	}
}