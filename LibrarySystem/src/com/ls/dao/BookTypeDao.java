package com.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ls.model.BookType;
import com.ls.util.StringUtil;


public class BookTypeDao {

	
	public int add(Connection con, BookType bookType) throws Exception {
		String sql =  "insert into t_booktype values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookType());
		pstmt.setString(2, bookType.getBookTypeDesc());
		return pstmt.executeUpdate();
	}
	
	
	public int update(Connection con, BookType bookType) throws Exception{
		String sql =  "update t_booktype set bookTypeName =?, bookTypeDesc =? where id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookType());
		pstmt.setString(2, bookType.getBookTypeDesc());
		pstmt.setInt(3, bookType.getId());
		return pstmt.executeUpdate();
	}
	
	public int delete(Connection con, String id) throws Exception{
		String sql =  "delete from t_booktype where id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * Searching category
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, BookType bookType) throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_booktype");
		if (!StringUtil.isEmpty(bookType.getBookType())){
			 sb.append(" where bookTypeName like '%" + bookType.getBookType() +"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
}
