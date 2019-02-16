package com.zc;

import java.sql.DriverManager;

import com.sun.corba.se.pept.transport.Connection;

public class GetConnection {

	java.sql.Connection conn =null;
	public java.sql.Connection getConnection() throws ClassNotFoundException {
		String drive="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/database1?useSSL=true&serverTimezone=GMT";
		String user="root";
		String password="13688377585";
		
		Class.forName(drive);
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
}
