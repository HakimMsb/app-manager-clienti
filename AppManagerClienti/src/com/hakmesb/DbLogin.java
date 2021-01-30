package com.hakmesb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbLogin {

	private String url;
	private Connection con;

	public DbLogin(String username, String password) throws SQLException {
		doLogin(username, password);
	}

	private void doLogin(String username, String password) throws SQLException {
		url = "jdbc:mysql://localhost:3306/dbClienti";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection(url, username, password);
	}

	public Connection getCon() {
		return con;
	}

}
