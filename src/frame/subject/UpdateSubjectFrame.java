package frame.subject;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.SubjectDao;
import entity.Subject;

public class UpdateSubjectFrame {
	Subject sub;
	SubjectDao subDao = new SubjectDao();
	SubjectFrame sf;

	public UpdateSubjectFrame(SubjectFrame sf, Subject sub) {
		this.sf = sf;
		this.sub = sub;
	}

	public void init() {

		JFrame frame = new JFrame();
		frame.setTitle("�޸Ŀ�Ŀ");
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		// ������panel �Ǵ�ֱ����
		JPanel panel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		panel.add(panel1);
		panel.add(panel2);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("����");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(120, 30));
		nameText.setText(sub.getName());
		panel1.add(nameLabel);
		panel1.add(nameText);

		JButton saveButton = new JButton();
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.setText("����");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				sub.setName(name);
				boolean flag = subDao.update(sub);
				sf.refreshTable();
				frame.dispose();
				if (flag) {
					JOptionPane.showMessageDialog(null, "����ɹ���");
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				}

			}
		});
		JButton cancelButton = new JButton();
		cancelButton.setPreferredSize(new Dimension(60, 30));
		cancelButton.setText("ȡ��");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel2.add(saveButton);
		panel2.add(cancelButton);
		frame.setVisible(true);
	}
}