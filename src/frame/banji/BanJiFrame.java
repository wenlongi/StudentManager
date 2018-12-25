package frame.banji;

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
import dao.BanJiDao;
import entity.BanJi;
import frame.banji.model.BanJiTableModel;

public class BanJiFrame {

	List<BanJi> list = new ArrayList<>();
	BanJiTableModel model;
	BanJiDao bjDao = new BanJiDao();

	public void init() {
		// ȷ��һ��Ư������۷��
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ����һ������
		JFrame frame = new JFrame();
		frame.setTitle("�༶��Ϣ");
		// ���ô��ڴ�С
		frame.setSize(800, 580);
		// ���þ���
		frame.setLocationRelativeTo(null);
		// ���ùر� ***
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		// ������panel �Ǵ�ֱ����
		JPanel panel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("����");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(120, 30));
		panel1.add(nameLabel);
		panel1.add(nameText);

		JLabel stuNumsLabel = new JLabel();
		stuNumsLabel.setText("����");
		JTextField stuNumsText = new JTextField();
		stuNumsText.setPreferredSize(new Dimension(120, 30));
		panel1.add(stuNumsLabel);
		panel1.add(stuNumsText);

		JButton searchButton = new JButton();
		searchButton.setPreferredSize(new Dimension(60, 30));
		searchButton.setText("��ѯ");
		panel1.add(searchButton);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String name = nameText.getText();
				int stuNums = -1;
				if (!stuNumsText.getText().equals("")) {
					stuNums = Integer.parseInt(stuNumsText.getText());
				}
				BanJi bj = new BanJi();
				bj.setName(name);
				bj.setStuNums(stuNums);
				List<BanJi> list = bjDao.searchbyCondition(bj);
				model.refreshTable(list);
			}

		});
		// ������+table+tableModel
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(500, 300));
		// ����BanJiDao�е�searchAll����
		list = bjDao.searchAll();
		// �������ģ��
		model = new BanJiTableModel(list);
		JTable table = new JTable(model);
		// swing��ʹ�ñ�������������
		scroll.setViewportView(table);
		panel2.add(scroll);
//
//		JButton subButton = new JButton();
//		subButton.setPreferredSize(new Dimension(60, 30));
//		subButton.setText("�γ�");
//		panel3.add(subButton);
//		
//		subButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				SubjectFrame subf = new SubjectFrame();
//				subf.init();
//				
//				
//				refreshTable();
//			}
//		});
//		
		JButton addButton = new JButton();
		addButton.setPreferredSize(new Dimension(60, 30));
		addButton.setText("����");
		panel3.add(addButton);
		
		// ����¼�
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddBanJiFrame asf = new AddBanJiFrame(BanJiFrame.this);
				asf.init();
				refreshTable();
			}
		});

		JButton updateButton = new JButton();
		updateButton.setPreferredSize(new Dimension(60, 30));
		updateButton.setText("�޸�");
		panel3.add(updateButton);

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ����Ϣ", "����", JOptionPane.WARNING_MESSAGE);
				} else {
					BanJi bj = list.get(index);
					UpdateBanJiFrame usf = new UpdateBanJiFrame(BanJiFrame.this, bj);
					usf.init();
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
						int id = list.get(index).getId();
						boolean flag = bjDao.delete(id);
						list = bjDao.searchAll();
						model.refreshTable(list);
						
						if (flag) {
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						}
						else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
						}
					}
				}
			}
		});
//		
		JButton manageButton = new JButton();
		manageButton.setPreferredSize(new Dimension(90, 30));
		manageButton.setText("����γ�");
		panel3.add(manageButton);
		//
		manageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ����Ϣ", "����", JOptionPane.WARNING_MESSAGE);
				} else {
						BanJi bj = list.get(index);
						Subject2BanJiframe s2bjf=new Subject2BanJiframe(bj);
						s2bjf.init();
		
				}
			}
		});
//		
		frame.setVisible(true);
	}

	public void refreshTable() {
		list = bjDao.searchAll();
		model.refreshTable(list);
	}

	public static void main(String[] args) {
		BanJiFrame sf = new BanJiFrame();
		sf.init();
	}
}