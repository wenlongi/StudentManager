package frame.banji;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.BanJiDao;
import entity.BanJi;

public class AddBanJiFrame {
	BanJiDao bjDao = new BanJiDao();
	BanJiFrame sf;

	public AddBanJiFrame(BanJiFrame sf) {
		this.sf = sf;
	}

	public void init() {

		JFrame frame = new JFrame();
		frame.setTitle("添加班级");
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		// 设置主panel 是垂直布局
		JPanel panel = (JPanel) frame.getContentPane();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

		JPanel panel2 = new JPanel();
		panel.add(panel1);
		panel.add(panel2);
		JLabel nameLabel = new JLabel();
		nameLabel.setText("名称");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(120, 30));
		panel1.add(nameLabel);
		panel1.add(nameText);

		JButton saveButton = new JButton();
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.setText("保存");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BanJi bj = new BanJi();
				String name = nameText.getText();
				bj.setName(name);
				boolean flag = bjDao.add(bj);
				sf.refreshTable();
				// 窗口销毁
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
		panel2.add(saveButton);
		panel2.add(cancelButton);
		frame.setVisible(true);
	}
}