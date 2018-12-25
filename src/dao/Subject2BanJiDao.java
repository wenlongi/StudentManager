package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Subject;

public class Subject2BanJiDao extends BaseDao{

	public List<Subject> searchSubjectByBanJi(int id) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			getStatement();
			// 5.执行语句
			String sql = "select sub.* from banji as bj" + " inner join m_bj_sub as m on bj.id=m.bj_id"
					+ " inner join subject as sub on m.sub_id=sub.id" + " where bj.id=" + id;
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

	public List<Subject> searchSubjectNotByBanJi(int id) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			getStatement();
			// 5.执行语句
			String sql = "select *from subject where id not in (select sub.id from banji as bj"
					+ " inner join m_bj_sub as m on bj.id=m.bj_id" + " inner join subject as sub on m.sub_id=sub.id"
					+ " where bj.id=" + id + ")";
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

	public boolean add(int bjId, int subId) {
		int rs = 0;
		getStatement();
		String sql = "insert into m_bj_sub(bj_id,sub_id) values(" + bjId + "," + subId + ")";
		try {
			rs = stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return rs > 0;
	}

	public boolean delete(int bjId, int subId) {
		int rs = 0;
		getStatement();
		String sql = "delete from m_bj_sub where bj_id=" + bjId + " and sub_id=" + subId;
		try {
			rs = stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return rs > 0;
	}

}
