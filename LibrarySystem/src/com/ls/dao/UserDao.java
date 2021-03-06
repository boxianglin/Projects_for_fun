package com.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ls.model.User;


public class UserDao {
	
	/**
	 * Login verification
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception{
		User result = null;
		String sql = "select * from t_user where userName = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			result = new User();
			result.setId(rs.getInt("id"));
			result.setUserName(rs.getString("userName"));
			result.setPassword(rs.getString("password"));
		}
		return result;
	}
	

}
