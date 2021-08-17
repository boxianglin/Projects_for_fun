package com.ls.graphics;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.ls.dao.BookDao;
import com.ls.dao.BookTypeDao;
import com.ls.model.Book;
import com.ls.model.BookType;
import com.ls.util.DBUtil;
import com.ls.util.StringUtil;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookAddInterFrm extends JInternalFrame {
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField priceTxt;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;
	private DBUtil dbUtil = new DBUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrm frame = new BookAddInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookAddInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Book Add");
		setBounds(100, 100, 512, 464);
		
		JLabel lblNewLabel = new JLabel("Book Name: ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Author: ");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price: ");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Book Description: ");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		
		bookDescTxt = new JTextArea();
		
		JLabel lblNewLabel_4 = new JLabel("Book Category: ");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		
		bookTypeJcb = new JComboBox();
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookAddActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton_1.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/re-set.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(127)
							.addComponent(btnNewButton)
							.addGap(52)
							.addComponent(btnNewButton_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
											.addGap(32)
											.addComponent(lblNewLabel_1))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_4)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(bookTypeJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(authorTxt, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
								.addComponent(lblNewLabel_3)
								.addComponent(bookDescTxt))))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		//set a boarder for textArea
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		
		//fill the category
		fillBookType();

	}
	
	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
		
	}

	private void bookAddActionPerformed(ActionEvent evt) {
		String bookName = this.bookNameTxt.getText();
		String author = this.authorTxt.getText();
		String price = this.priceTxt.getText();
		String bookDesc = this.bookDescTxt.getText();
		
		if(StringUtil.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "Book Name cannot be empty!");
			return;
		}
		
		if(StringUtil.isEmpty(author)) {
			JOptionPane.showMessageDialog(null, "Author Name cannot be empty!");
			return;
		}
		
		if(StringUtil.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "Book price cannot be empty!");
			return;
		}
		
		//get the selected type
		BookType bookType = (BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		
		Book book = new Book(bookName, author,Float.parseFloat(price),bookTypeId,bookDesc);
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addNum = bookDao.add(con, book);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "Book Added!");
				resetValue(); 
			}else {
				JOptionPane.showMessageDialog(null, "Book Add Failed!");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Book Add Failed!");
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetValue() {
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.priceTxt.setText("");
		this.bookDescTxt.setText("");
		if (this.bookTypeJcb.getItemCount()>0) {
			this.bookTypeJcb.setSelectedIndex(0);
		}
	}

	/**
	 * Initialize the current book type to the book add frame
	 */
	private void fillBookType() {
		Connection con = null;
		BookType bookType =  null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(con, new BookType());
			while(rs.next()) {
				bookType = new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookType(rs.getString("bookTypeName"));
				this.bookTypeJcb.addItem(bookType); //add the tostring 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
