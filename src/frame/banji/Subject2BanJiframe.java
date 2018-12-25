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
		// ȷ��һ��Ư������۷��
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ����һ������
		JFrame frame = new JFrame();
		frame.setTitle("�༶��Ŀ����");
		// ���ô��ڴ�С
		frame.setSize(500, 380);
		// ���þ���
		frame.setLocationRelativeTo(null);
		// ���ùر� ***
		// frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		// ������panel �Ǵ�ֱ����
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

		// ������+table+tableModel
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(400, 230));
		// ����SubjectDao�е�searchAll����
		list = s2bDao.searchSubjectByBanJi(bj.getId());
		// �������ģ��
		model = new SubjectTableModel(list);
		JTable table = new JTable(model);
		// swing��ʹ�ñ�������������
		scroll.setViewportView(table);
		panel2.add(scroll);

		// panl3������ʾ ������ ���� ɾ��������ť
		subBox = new JComboBox();
		subBox.setPreferredSize(new Dimension(90, 30));
		panel3.add(subBox);
		// subNotList = s2bDao.searchSubjectNotByBanJi(bj.getId());
		refreshComboBox();

		JButton addButton = new JButton();
		addButton.setPreferredSize(new Dimension(60, 30));
		addButton.setText("����");
		panel3.add(addButton);
		// ����¼�
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int bjId = bj.getId();
				int subId = subNotList.get(subBox.getSelectedIndex()).getId();
				Boolean flag = s2bDao.add(bjId, subId);
				refreshTable();
				refreshComboBox();
				if (flag) {
					JOptionPane.showMessageDialog(null, "����ɹ���");
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				}

			}
		});

		JButton deleteButton = new JButton();
		deleteButton.setPreferredSize(new Dimension(60, 30));
		deleteButton.setText("ɾ��");
		panel3.add(deleteButton);
		//
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ����Ϣ", "����", JOptionPane.WARNING_MESSAGE);
				} else {
					int type = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "����", JOptionPane.YES_NO_OPTION);
					if (type == 0) {
						int bjId = bj.getId();
						int subId = list.get(index).getId();
						boolean flag = s2bDao.delete(bjId, subId);
						refreshTable();
						refreshComboBox();
						if (flag) {
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						} else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
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