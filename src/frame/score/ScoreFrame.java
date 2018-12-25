package frame.score;

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
import dao.ScoreDao;
import entity.BanJi;
import entity.Score;
import entity.Student;
import entity.Subject;
import frame.score.model.ScoreTableModel;
import util.Util;

public class ScoreFrame {

	List<Score> list = new ArrayList<>();
	ScoreTableModel model;
	ScoreDao scDao = new ScoreDao();
	BanJiDao bjDao = new BanJiDao();

	public void init() {
		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建一个窗口
		JFrame frame = new JFrame();
		frame.setTitle("成绩信息");
		// 设置窗口大小
		frame.setSize(800, 580);
		// 设置居中
		frame.setLocationRelativeTo(null);
		// 设置关闭 ***
		// frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		// 设置主panel 是垂直布局
		JPanel panel = (JPanel) frame.getContentPane();

		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 5));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("姓名");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameLabel);
		panel1.add(nameText);

//		JLabel bjLabel = new JLabel();
//		bjLabel.setText("班级");
//		panel1.add(bjLabel);
		JComboBox bjBox = new JComboBox();
		bjBox.setPreferredSize(new Dimension(100, 30));
		panel1.add(bjBox);

		// 设置bjBox显示班级名
		Util.setBanJiItem(bjBox);

		JComboBox subBox = new JComboBox();
		subBox.setPreferredSize(new Dimension(100, 30));
		panel1.add(subBox);

		Util.setSubjectItem(subBox);
		
		//等级
		JComboBox graBox = new JComboBox();
		graBox.setPreferredSize(new Dimension(100, 30));
		panel1.add(graBox);

		Util.setGraItem(graBox);
		
		JButton searchButton = new JButton();
		searchButton.setPreferredSize(new Dimension(60, 30));
		searchButton.setText("查询");
		panel1.add(searchButton);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				    String name = nameText.getText();
					Student stu = new Student();
					stu.setName(name);
					
					int bjIndex = bjBox.getSelectedIndex();
					BanJi bj = Util.getSelectBanJiItem(bjIndex);
					stu.setBj(bj);
					
					int subIndex = subBox.getSelectedIndex();
					Subject sub = Util.getSelectSubjectItem(subIndex);

					int graIndex = graBox.getSelectedIndex();
					String gra = Util.getSelectGraItem(graIndex);

					Score sco = new Score();
					sco.setStu(stu);
					sco.setSub(sub);
					sco.setGrade(gra);
					
					List<Score> list = scDao.searchbyCondition(sco);
					model.refreshTable(list);
			}

		});
		// 滚动条+table+tableModel
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(500, 300));
		// 调用ScoreDao中的searchAll方法
		list = scDao.searchAll();
		// 创建表格模型
		model = new ScoreTableModel(list);
		JTable table = new JTable(model);
		// swing中使用表格必须搭配滚动条
		scroll.setViewportView(table);
		panel2.add(scroll);

		JButton saveButton = new JButton();
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.setText("保存");
		panel3.add(saveButton);
		// 添加事件
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				scDao.save(model.saveList);
				scDao.save(model.saveSet);
				refreshTable();
			}
		});
		frame.setVisible(true);
	}

	public void refreshTable() {
		list = scDao.searchAll();
		model.refreshTable(list);
	}

	public static void main(String[] args) {
		ScoreFrame sf = new ScoreFrame();
		sf.init();
	}
	
}