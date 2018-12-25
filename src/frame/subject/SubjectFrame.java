package frame.subject;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import dao.SubjectDao;
import entity.Subject;
import frame.subject.model.SubjectTableModel;

public class SubjectFrame {

	List<Subject> list = new ArrayList<>();
	SubjectTableModel model;
	SubjectDao subDao = new SubjectDao();

	public void init() {
		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建一个窗口
		JFrame frame = new JFrame();
		frame.setTitle("科目信息");
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
		nameLabel.setText("名称");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(120, 30));
		panel1.add(nameLabel);
		panel1.add(nameText);

		JButton searchButton = new JButton();
		searchButton.setPreferredSize(new Dimension(60, 30));
		searchButton.setText("查询");
		panel1.add(searchButton);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String name = nameText.getText();
				Subject sub = new Subject();
				sub.setName(name);
				List<Subject> list = subDao.searchbyCondition(sub);
				model.refreshTable(list);
			}

		});
		// 滚动条+table+tableModel
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(500, 300));
		// 调用SubjectDao中的searchAll方法
		list = subDao.searchAll();
		// 创建表格模型
		model = new SubjectTableModel(list);
		JTable table = new JTable(model);
		// swing中使用表格必须搭配滚动条
		scroll.setViewportView(table);
		panel2.add(scroll);

		JButton addButton = new JButton();
		addButton.setPreferredSize(new Dimension(60, 30));
		addButton.setText("新增");
		panel3.add(addButton);
		// 添加事件
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddSubjectFrame asf = new AddSubjectFrame(SubjectFrame.this);
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
					Subject sub = list.get(index);
					UpdateSubjectFrame usf = new UpdateSubjectFrame(SubjectFrame.this, sub);
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
						boolean flag = subDao.delete(id);
						list = subDao.searchAll();
						model.refreshTable(list);
						
						if (flag) {
							JOptionPane.showMessageDialog(null, "删除成功！");
						}
						else {
							JOptionPane.showMessageDialog(null, "删除失败！");
						}
					}
				}
			}
		});
		frame.setVisible(true);
	}

	public void refreshTable() {
		list = subDao.searchAll();
		model.refreshTable(list);
	}

	public static void main(String[] args) {
		SubjectFrame sf = new SubjectFrame();
		sf.init();
	}
}