package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BanJi;
import entity.Student;

public class StudentDao extends BaseDao{

	public List<Student> searchAll() {
		List<Student> list = new ArrayList<Student>();
		try {
			getStatement();
			// 5.执行语句
			String sql = "select stu.*,bj.name as bjName from student as stu left join banji as bj on stu.bj_id=bj.id";
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bj_id"));
				bj.setName(rs.getString("bjName"));
				stu.setBj(bj);
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public boolean add(Student stu) {
		int rs = 0;
		try {
			getStatement();
			// 5.执行语句
			String sql = "insert into student(name,sex,age,bj_id) values('" + stu.getName() + "','" + stu.getSex()
					+ "','" + stu.getAge() + "'," + stu.getBj().getId() + ")";
			rs = stat.executeUpdate(sql);
			// 6.对结果集的处理

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return rs > 0;
	}

	public boolean update(Student stu) {
		int rs = 0;
		try {
			getStatement();
			// 5.执行语句
			String sql = "update student set name='" + stu.getName() + "',sex='" + stu.getSex() + "',age='"
					+ stu.getAge() + "',bj_id=" + stu.getBj().getId() + " where id=" + stu.getId() + "";
			rs = stat.executeUpdate(sql);
			// 6.对结果集的处理
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return rs > 0;
	}

	public boolean delete(int id) {
		int rs = 0;
		try {
			getStatement();
			// 5.执行语句
			String sql = "delete from student where id=" + id;
			rs = stat.executeUpdate(sql);
			// 6.对结果集的处理
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return rs > 0;
	}

	public List<Student> searchbyCondition(Student condition) {

		List<Student> stus = new ArrayList<Student>();
		try {
			getStatement();
			// 5.执行语句
			String where = "where 1=1";

			if (!condition.getName().equals("")) {
				where += " and stu.name='" + condition.getName() + "'";
			}
			if (!condition.getSex().equals("")) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge() + "";
			}
			if (condition.getBj().getId() != null) {
				where += " and bj_id=" + condition.getBj().getId();
			}

			String sql = "select stu.*,bj.name as bjName from student as stu left join banji as bj on stu.bj_id=bj.id "
					+ where;
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bj_id"));
				bj.setName(rs.getString("bjName"));
				stu.setBj(bj);
				stus.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return stus;
	}
}
