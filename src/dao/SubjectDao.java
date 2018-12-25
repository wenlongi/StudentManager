
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Subject;

public class SubjectDao extends BaseDao{

	public List<Subject> searchAll() {
		List<Subject> list = new ArrayList<Subject>();
		try {
			getStatement();
			// 5.执行语句
			String sql = "select * from subject";
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public boolean add(Subject sub) {
		int rs = 0;
		try {
			getStatement();
			// 5.执行语句
			String sql = "insert into subject(name) values('" + sub.getName() + "')";
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

	public boolean update(Subject sub) {
		int rs = 0;
		try {
			getStatement();
			// 5.执行语句
			String sql = "update subject set name='" + sub.getName() + "' where id='" + sub.getId() + "'";
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
//			
//			conn.setAutoCommit(false);
			// 5.执行语句
//			String sql = "update student set bj_id =null where bj_id=" + id;
//			rs = stat.executeUpdate(sql);
			String sql = "delete from subject where id=" + id;
			rs = stat.executeUpdate(sql);
//			conn.commit();
//			
			// 6.对结果集的处理
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			
//			rs=0;
//			
		} finally {
			closeAll();
		}
		return rs > 0;
	}

	public List<Subject> searchbyCondition(Subject condition) {
		
		List<Subject> stus = new ArrayList<Subject>();
		try {
			getStatement();
			// 5.执行语句
			String where = "where 1=1";
			if (!condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
			String sql = "select * from subject " + where;
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
				stus.add(sub);
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
