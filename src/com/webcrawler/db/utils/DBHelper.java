package com.webcrawler.db.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	private static String driverString = "com.mysql.jdbc.Driver";

	private static String username = "root";

	private static String password = "";

	static private String IPADRRES = "127.0.0.1";

	private static String url = String.format("jdbc:mysql://%s:3306/crawler",
			IPADRRES);

	public static Connection getConnection() {
		try {
			Class.forName(driverString);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Map> executeQuery(String sql, Object[] params) {
		Connection con = getConnection();
		List<Map> res = new ArrayList<Map>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map map = new HashMap();
				ResultSetMetaData meta = rs.getMetaData();
				int no = meta.getColumnCount();
				for (int i = 1; i <= no; i++) {
					map.put(meta.getColumnName(i), rs.getObject(i));
				}
				res.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static boolean execute(String sql, Object[] params) {
		Connection con = getConnection();
		List<Map> res = new ArrayList<Map>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}

	public static void main(String[] args) {

		DBHelper db = new DBHelper();

		Connection c = db.getConnection();
		String sql = "insert into content_item values (null,?,?,?,?)";
		DBHelper.execute(sql, new Object[] { 1, "1",
				"1", "1" }); 
	}
}
