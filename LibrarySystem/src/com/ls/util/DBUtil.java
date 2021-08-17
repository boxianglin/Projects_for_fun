package com.ls.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/db_book";
	private String dbUserName = "root";
	private String dbPassword = "123";
	private String jdbcName = "com.mysql.jdbc.Driver";
	
	/**
	 * 
	 * @return connection
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		//register the driver
		Class.forName(jdbcName);
		
		//connect to the database
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * Close the connection
	 * @param connection
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if (con != null) {
			con.close();
		}
	}
	
	//test connection
	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		try{
			db.getCon();
			System.out.println("connected");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
