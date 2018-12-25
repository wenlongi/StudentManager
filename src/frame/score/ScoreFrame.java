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
		// ȷ��һ��Ư������۷��
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ����һ������
		JFrame frame = new JFrame();
		frame.setTitle("�ɼ���Ϣ");
		// ���ô��ڴ�С
		frame.setSize(800, 580);
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

		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 5));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("����");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameLabel);
		panel1.add(nameText);

//		JLabel bjLabel = new JLabel();
//		bjLabel.setText("�༶");
//		panel1.add(bjLabel);
		JComboBox bjBox = new JComboBox();
		bjBox.setPreferredSize(new Dimension(100, 30));
		panel1.add(bjBox);

		// ����bjBox��ʾ�༶��
		Util.setBanJiItem(bjBox);

		JComboBox subBox = new JComboBox();
		subBox.setPreferredSize(new Dimension(100, 30));
		panel1.add(subBox);

		Util.setSubjectItem(subBox);
		
		//�ȼ�
		JComboBox graBox = new JComboBox();
		graBox.setPreferredSize(new Dimension(100, 30));
		panel1.add(graBox);

		Util.setGraItem(graBox);
		
		JButton searchButton = new JButton();
		searchButton.setPreferredSize(new Dimension(60, 30));
		searchButton.setText("��ѯ");
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
		// ������+table+tableModel
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(500, 300));
		// ����ScoreDao�е�searchAll����
		list = scDao.searchAll();
		// �������ģ��
		model = new ScoreTableModel(list);
		JTable table = new JTable(model);
		// swing��ʹ�ñ�������������
		scroll.setViewportView(table);
		panel2.add(scroll);

		JButton saveButton = new JButton();
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.setText("����");
		panel3.add(saveButton);
		// ����¼�
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