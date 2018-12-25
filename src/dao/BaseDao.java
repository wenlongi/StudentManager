package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;

	public void getConnection() {
		// 2.加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接
			String url = "jdbc:mysql://localhost:3306/school4?characterEncoding=utf-8";
			conn = DriverManager.getConnection(url, "root", "123456");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getStatement() {
		// 4.建立sql执行器
		try {
			getConnection();
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeAll() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
