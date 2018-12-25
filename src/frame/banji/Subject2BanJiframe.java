package frame.banji;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import dao.Subject2BanJiDao;
import dao.SubjectDao;
import entity.BanJi;
import entity.Subject;
import frame.subject.model.SubjectTableModel;

public class Subject2BanJiframe {

	List<Subject> subNotList;
	List<Subject> list = new ArrayList<>();
	SubjectTableModel model;
	SubjectDao subDao = new SubjectDao();
	Subject2BanJiDao s2bDao = new Subject2BanJiDao();
	BanJi bj;
	JComboBox subBox;

	public Subject2BanJiframe(BanJi bj) {
		this.bj = bj;
	}

	public void init() {
		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建一个窗口
		JFrame frame = new JFrame();
		frame.setTitle("班级科目管理");
		// 设置窗口大小
		frame.setSize(500, 380);
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

		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 5));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		JLabel Label = new JLabel();
		Label.setText(bj.getName());
		Label.setFont(new Font(null, Font.BOLD, 20));
		panel1.add(Label);

		// 滚动条+table+tableModel
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(400, 230));
		// 调用SubjectDao中的searchAll方法
		list = s2bDao.searchSubjectByBanJi(bj.getId());
		// 创建表格模型
		model = new SubjectTableModel(list);
		JTable table = new JTable(model);
		// swing中使用表格必须搭配滚动条
		scroll.setViewportView(table);
		panel2.add(scroll);

		// panl3负责显示 下拉框 新增 删除三个按钮
		subBox = new JComboBox();
		subBox.setPreferredSize(new Dimension(90, 30));
		panel3.add(subBox);
		// subNotList = s2bDao.searchSubjectNotByBanJi(bj.getId());
		refreshComboBox();

		JButton addButton = new JButton();
		addButton.setPreferredSize(new Dimension(60, 30));
		addButton.setText("新增");
		panel3.add(addButton);
		// 添加事件
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int bjId = bj.getId();
				int subId = subNotList.get(subBox.getSelectedIndex()).getId();
				Boolean flag = s2bDao.add(bjId, subId);
				refreshTable();
				refreshComboBox();
				if (flag) {
					JOptionPane.showMessageDialog(null, "保存成功！");
				} else {
					JOptionPane.showMessageDialog(null, "保存失败！");
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
						int bjId = bj.getId();
						int subId = list.get(index).getId();
						boolean flag = s2bDao.delete(bjId, subId);
						refreshTable();
						refreshComboBox();
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
		list = s2bDao.searchSubjectByBanJi(bj.getId());
		model.refreshTable(list);
	}

	public void refreshComboBox() {
		subNotList = s2bDao.searchSubjectNotByBanJi(bj.getId());
		subBox.removeAllItems();
		for (Subject sub : subNotList) {
			subBox.addItem(sub.getName());
		}
	}

	public void refreshTable(List<Subject> list) {
		model.refreshTable(list);
	}

}