package com.ls.model;

public class BookType {
	
	private int id;
	private String bookType;
	private String bookTypeDesc;  //Book type description
	
	
	public BookType() {
		super();
		// TODO Auto-generated constructor stub
	} 
	

	public BookType(String bookType, String bookTypeDesc) {
		super();
		this.bookType = bookType;
		this.bookTypeDesc = bookTypeDesc;
	}

	
	public BookType(int id, String bookType, String bookTypeDesc) {
		super();
		this.id = id;
		this.bookType = bookType;
		this.bookTypeDesc = bookTypeDesc;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookTypeDesc() {
		return bookTypeDesc;
	}
	public void setBookTypeDesc(String bookTypeDesc) {
		this.bookTypeDesc = bookTypeDesc;
	}
	
	@Override 
	public String toString() {
		if (this.id == -1) {
			return bookType;
		}else {
			return id + ". " + bookType;
		}
	}
	
	
	
}
