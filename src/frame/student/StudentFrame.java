package frame.student;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import dao.BanJiDao;
import dao.StudentDao;
import entity.BanJi;
import entity.Student;
import frame.student.model.StudentTableModel;
import util.Util;

public class StudentFrame {

	List<Student> list = new ArrayList<>();
	StudentTableModel model;
	StudentDao sd = new StudentDao();
	BanJiDao bjDao = new BanJiDao();

	public void init() {
		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建一个窗口
		JFrame frame = new JFrame();
		frame.setTitle("学生信息");
		// 设置窗口大小
		frame.setSize(800, 580);
		// 设置居中
		frame.setLocationRelativeTo(null);
		// 设置关闭 ***
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		// 设置主panel 是垂直布局
		JPanel panel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 5));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("姓名");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(80, 30));
		panel1.add(nameLabel);
		panel1.add(nameText);

		JLabel sexLabel = new JLabel();
		sexLabel.setText("性别");
		JTextField sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(80, 30));
		panel1.add(sexLabel);
		panel1.add(sexText);

		JLabel ageLabel = new JLabel();
		ageLabel.setText("年龄");
		JTextField ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(80, 30));
		panel1.add(ageLabel);
		panel1.add(ageText);
		
		JLabel bjLabel = new JLabel();
		bjLabel.setText("班级");
		panel1.add(bjLabel);
		JComboBox bjBox = new JComboBox();
		bjBox.setPreferredSize(new Dimension(80, 30));
		panel1.add(bjBox);

		// 设置bjBox显示班级名
		Util.setBanJiItem(bjBox);

		JButton searchButton = new JButton();
		searchButton.setPreferredSize(new Dimension(60, 30));
		searchButton.setText("查询");
		panel1.add(searchButton);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String name = nameText.getText();
				String sex = sexText.getText();
				int age = -1;
				if (!ageText.getText().equals("")) {
					age = Integer.parseInt(ageText.getText());
				}
				Student stu = new Student();
				stu.setName(name);
				stu.setSex(sex);
				stu.setAge(age);

				int index = bjBox.getSelectedIndex();
				BanJi bj = Util.getSelectBanJiItem(index);
				stu.setBj(bj);

				List<Student> list = sd.searchbyCondition(stu);
				model.refreshTable(list);
			}

		});
		// 滚动条+table+tableModel
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(500, 300));
		// 调用StudentDao中的searchAll方法
		list = sd.searchAll();
		// 创建表格模型
		model = new StudentTableModel(list);
		JTable table = new JTable(model);
		// swing中使用表格必须搭配滚动条
		scroll.setViewportView(table);
		panel2.add(scroll);
		//
		JButton addButton = new JButton();
		addButton.setPreferredSize(new Dimension(60, 30));
		addButton.setText("新增");
		panel3.add(addButton);
		// 添加事件
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AddStudentFrame asf = new AddStudentFrame(StudentFrame.this);
				asf.init();
				refreshTable();
			}
		});

		JButton updateButton = new JButton();
		updateButton.setPreferredSize(new Dimension(60, 30));
		updateButton.setText("修改");
		panel3.add(updateButton);

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "请选中一条信息", "警告", JOptionPane.WARNING_MESSAGE);
				} else {
					Student stu = list.get(index);
					UpdateStudentFrame usf = new UpdateStudentFrame(StudentFrame.this, stu);
					usf.init();
				}
			}
		});

		JButton deleteButton = new JButton();
		deleteButton.setPreferredSize(new Dimension(60, 30));
		deleteButton.setText("删除");
		panel3.add(deleteButton);
		//
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "请选中一条信息", "警告", JOptionPane.WARNING_MESSAGE);
				} else {
					int type = JOptionPane.showConfirmDialog(null, "确认删除？", "警告", JOptionPane.YES_NO_OPTION);
					if (type == 0) {
						int id = list.get(index).getId();
						boolean flag = sd.delete(id);
						list = sd.searchAll();
						model.refreshTable(list);
						if (flag) {
							JOptionPane.showMessageDialog(null, "删除成功！");
						} else {
							JOptionPane.showMessageDialog(null, "删除失败！");
						}
					}
				}
			}
		});
		frame.setVisible(true);
	}

	public void refreshTable() {
		list = sd.searchAll();
		model.refreshTable(list);
	}

	public static void main(String[] args) {
		StudentFrame sf = new StudentFrame();
		sf.init();
	}
}