package com.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ls.model.Book;
import com.ls.model.BookType;
import com.ls.util.StringUtil;

public class BookDao {
	
	
	public int add(Connection con, Book book) throws Exception{	
		String sql = "insert into t_book values(null,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setFloat(3, book.getPrice());
		pstmt.setInt(4, book.getBookTypeId());
		pstmt.setString(5, book.getBookDesc());
		return pstmt.executeUpdate();
	}
	
	
	public int delete(Connection con, String id) throws Exception{
		String sql =  "delete from t_book where id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	public int update(Connection con, Book book) throws Exception{
		String sql =  "update t_book set bookName =?, author =?, price =?, bookDesc =?, bookTypeId =? where id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setFloat(3, book.getPrice());
		pstmt.setString(4, book.getBookDesc());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setInt(6, book.getId());
		return pstmt.executeUpdate();
	}
	
	
	public boolean existBookbyBookTypeId(Connection con, String bookTypeId) throws Exception{
		String sql = "select * from t_book where bookTypeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}
	
	/**
	 * Search sql
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	
	public ResultSet list(Connection con, Book book) throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_book b, t_bookType bt where b.bookTypeId = bt.id"); 
		if (StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and b.bookName like '%" + book.getBookName() + "%'"); 
		}
		
		if (StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%" + book.getAuthor() + "%'");
		}
		
		if (book.getBookTypeId() != null && book.getBookTypeId() != -1) {
			sb.append(" and b.bookTypeId =" + book.getBookTypeId());
		}
		
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

}
