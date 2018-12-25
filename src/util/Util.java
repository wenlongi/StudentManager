package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import dao.BanJiDao;
import dao.ScoreDao;
import entity.BanJi;
import entity.Score;
//
import dao.SubjectDao;
import entity.Subject;

public class Util {

	public static void setBanJiItem(JComboBox bjBox) {
		BanJiDao bjDao = new BanJiDao();
		List<BanJi> bjlist = bjDao.searchAll();
		bjBox.addItem("请选择班级");
		for (int i = 0; i < bjlist.size(); i++) {
			bjBox.addItem(bjlist.get(i).getName());
		}
	}

	public static BanJi getSelectBanJiItem(int index) {
		BanJiDao bjDao = new BanJiDao();
		List<BanJi> bjlist = bjDao.searchAll();
		BanJi bj = null;
		if (index > 0) {
			index = index - 1;
			bj = bjlist.get(index);
		} else {
			bj = new BanJi();
		}
		return bj;
	}

	// **

	public static void setSubjectItem(JComboBox subBox) {
		SubjectDao subDao = new SubjectDao();
		List<Subject> sublist = subDao.searchAll();
		subBox.addItem("请选择科目");
		for (int i = 0; i < sublist.size(); i++) {
			subBox.addItem(sublist.get(i).getName());
		}
	}

	public static Subject getSelectSubjectItem(int index) {
		SubjectDao subDao = new SubjectDao();
		List<Subject> sublist = subDao.searchAll();
		Subject sub = null;
		if (index > 0) {
			index = index - 1;
			sub = sublist.get(index);
		} else {
			sub = new Subject();
		}
		return sub;
	}

	// 等级1
	// public static void setGrajectItem(JComboBox graBox) {
	// ScoreDao scoDao = new ScoreDao();
	//
	// Set<String> graSet =scoDao.searchByGrade();
	// graBox.addItem("请选择等级");
	// for (Score sc : graSet) {
	// if(sc.getGrade()!=null) {
	// graBox.addItem(sc.getGrade());
	// }
	// }
	// }
	//
	// public static Score getSelectScoreItem(int graIndex) {
	//
	// ScoreDao scoDao = new ScoreDao();
	// Set<String> graSet = scoDao.searchByGrade();
	// List<String> setList= new ArrayList<>(graSet);
	//
	//
	// return sco;
	// }

	// 等级2
	public static void setGraItem(JComboBox graBox) {
		ScoreDao scoDao = new ScoreDao();
		List<String> scolist = scoDao.searchAll2();
		graBox.addItem("请选择等级");
		for (int i = 0; i < scolist.size(); i++) {
			graBox.addItem(scolist.get(i));
		}
		
//		graBox.addItem("请选择等级");
//		graBox.addItem("优秀");
//		graBox.addItem("良好");
//		graBox.addItem("一般");
//		graBox.addItem("及格");
//		graBox.addItem("不及格");
		
	}

	public static String getSelectGraItem(int index) {
		ScoreDao scoDao = new ScoreDao();
		List<String> scolist = scoDao.searchAll2();

		String sco = null;
		if (index > 0) {
			index = index - 1;
			sco = scolist.get(index);
		} else {
			sco = "所有等级";
		}
		return sco;
	}

}
