package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import entity.BanJi;
import entity.Score;
import entity.Student;
import entity.Subject;

public class ScoreDao extends BaseDao {

	public List<Score> searchAll() {
		List<Score> list = new ArrayList<Score>();
		try {
			getStatement();
			// 5.执行语句
			String sql = "select *from v_stu_sub_sc";
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("scId"));
				sc.setResult((Integer) rs.getObject("result"));
				sc.setGrade(rs.getString("grade"));
				Student stu = new Student();
				stu.setId(rs.getInt("stuId"));
				stu.setName(rs.getString("stuName"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bjId"));
				bj.setName(rs.getString("bjName"));
				stu.setBj(bj);
				Subject sub = new Subject();
				sub.setId(rs.getInt("subId"));
				sub.setName(rs.getString("subName"));
				sc.setStu(stu);
				sc.setSub(sub);
				list.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public List<String> searchAll2() {
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		String sc = new String();
		try {
			getStatement();
			// 5.执行语句
			String sql = "select grade from v_stu_sub_sc";
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				if (rs.getString("grade") != null) {
					sc = rs.getString("grade");
					list.add(sc);
				}
			}
			HashSet h = new HashSet(list);
			list.clear();
			list.addAll(h);
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("优秀")) {
					list2.add(list.get(i));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("良好")) {
					list2.add(list.get(i));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("一般")) {
					list2.add(list.get(i));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("及格")) {
					list2.add(list.get(i));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("不及格")) {
					list2.add(list.get(i));
				}
			}

			list=list2;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public boolean save(Set<Score> scores) {
		for (Score sc : scores) {
			if (sc.getId() > 0) {
				update(sc);

			} else {
				add(sc);
			}
		}
		return false;
	}

	private void update(Score sc) {
		getStatement();
		String sql = "update score set result=" + sc.getResult() + " where id=" + sc.getId();
		try {
			int rs = stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	private void add(Score sc) {
		// 新***
		getConnection();
		String sql = "insert into score(stu_id,sub_id,result) values(?,?,?)";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getStu().getId());
			pstat.setInt(2, sc.getSub().getId());
			pstat.setInt(3, sc.getResult());
			pstat.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			closeAll();
		}
	}

	public List<Score> searchbyCondition(Score condition) {
		List<Score> scos = new ArrayList<Score>();
		try {
			getStatement();
			// 5.执行语句
			String where = "where 1=1";

			if (!condition.getStu().getName().equals("")) {
				where += " and stuName='" + condition.getStu().getName() + "'";
			}
			if (condition.getStu().getBj().getId() != null) {
				where += " and bjId=" + condition.getStu().getBj().getId();
			}
			if (condition.getSub().getId() != null) {
				where += " and subId=" + condition.getSub().getId();
			}
			if (!condition.getGrade().equals("所有等级")) {
				where += " and grade='" + condition.getGrade() + "'";
			}

			String sql = "select sco.* from v_stu_sub_sc as sco " + where + " order by result desc";
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {

				Student stu = new Student();
				stu.setName(rs.getString("stuName"));

				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bjId"));
				bj.setName(rs.getString("bjName"));
				stu.setBj(bj);

				Subject sub = new Subject();
				sub.setId(rs.getInt("subId"));
				sub.setName(rs.getString("subName"));

				Score sco = new Score();
				sco.setStu(stu);
				sco.setSub(sub);
				sco.setId(rs.getInt("scId"));
				sco.setGrade(rs.getString("grade"));
				sco.setResult(rs.getInt("result"));

				scos.add(sco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return scos;
	}

}
