package frame; //  school3

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frame.banji.BanJiFrame;
import frame.score.ScoreFrame;
import frame.student.StudentFrame;
import frame.subject.SubjectFrame;

public class Mainframe {
	public void init() {
		JFrame frame = new JFrame();
		frame.setTitle("主面板");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel1 = (JPanel) frame.getContentPane();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
		JButton stuBtn = new JButton("学生管理");
		stuBtn.setPreferredSize(new Dimension(200, 100));
		panel1.add(stuBtn);
		stuBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentFrame sf = new StudentFrame();
				sf.init();
			}
		});
		JButton bjBtn = new JButton("班级管理");
		bjBtn.setPreferredSize(new Dimension(200, 100));
		panel1.add(bjBtn);
		bjBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BanJiFrame sf = new BanJiFrame();
				sf.init();
			}
		});
		JButton subBtn = new JButton("科目管理");
		subBtn.setPreferredSize(new Dimension(200, 100));
		panel1.add(subBtn);
		subBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SubjectFrame sf = new SubjectFrame();
				sf.init();
			}
		});
		JButton scoreBtn = new JButton("成绩管理");
		scoreBtn.setPreferredSize(new Dimension(200, 100));
		panel1.add(scoreBtn);
		scoreBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScoreFrame sf = new ScoreFrame();
				sf.init();
			}
		});
		
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Mainframe().init();
	}
}
