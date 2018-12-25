
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BanJi;

public class BanJiDao extends BaseDao{

	public List<BanJi> searchAll() {
		List<BanJi> list = new ArrayList<BanJi>();
		try {
			getStatement();
			// 5.执行语句
			String sql = "select * from banji";
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	public boolean add(BanJi bj) {
		int rs = 0;
		try {
			getStatement();
			// 5.执行语句
			String sql = "insert into banji(name,stunums) values('" + bj.getName() + "',0)";
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

	public boolean update(BanJi bj) {
		int rs = 0;
		try {
			getStatement();
			// 5.执行语句
			String sql = "update banji set name='" + bj.getName() + "' where id='" + bj.getId() + "'";
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
			conn.setAutoCommit(false);
			// 5.执行语句
			String sql = "update student set bj_id =null where bj_id=" + id;
			rs = stat.executeUpdate(sql);
			sql = "delete from banji where id=" + id;
			rs = stat.executeUpdate(sql);
			conn.commit();
//			
			// 6.对结果集的处理
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			
			rs=0;
//			
		} finally {
			closeAll();
		}
		return rs > 0;
	}

	public List<BanJi> searchbyCondition(BanJi condition) {
		
		List<BanJi> stus = new ArrayList<BanJi>();
		try {
			getStatement();
			// 5.执行语句
			String where = "where 1=1";

			if (!condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
		
			if (condition.getStuNums() != -1) {
				where += " and stuNums='" + condition.getStuNums() + "'";
			}

			String sql = "select * from banji " + where;
			rs = stat.executeQuery(sql);
			// 6.对结果集的处理
			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));
				bj.setStuNums(rs.getInt("stuNums"));
				stus.add(bj);
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
