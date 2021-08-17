package com.ls.graphics;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.ls.dao.BookDao;
import com.ls.dao.BookTypeDao;
import com.ls.model.Book;
import com.ls.model.BookType;
import com.ls.util.DBUtil;
import com.ls.util.StringUtil;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookManagerInter extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
	private JComboBox s_bookTypeJcb;
	 
	
	private DBUtil dbUtil = new DBUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	
	
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField priceTxt;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManagerInter frame = new BookManagerInter();
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
	public BookManagerInter() {
		setTitle("Book Manage");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 771, 826);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_3 = new JLabel("ID: ");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Book Name: ");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Authors: ");
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Price: ");
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Category: ");
		
		bookTypeJcb = new JComboBox();
		
		JLabel lblNewLabel_8 = new JLabel("Book Description: ");
		
		bookDescTxt = new JTextArea();
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookAddActionPerforemd(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(BookManagerInter.class.getResource("/images/add.png")));
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookUpdateActionPerformed(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(BookManagerInter.class.getResource("/images/edit.png")));
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookDeleteActionPerformed(e);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(BookManagerInter.class.getResource("/images/quit.png")));
		
		JButton btnNewButton_5 = new JButton("Clear");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldClear(e);
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(BookManagerInter.class.getResource("/images/re-set.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_7))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(6)
									.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookTypeJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addGap(4)
									.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton_5)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_8)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(75)
									.addComponent(btnNewButton_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewButton_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewButton_4))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookDescTxt, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)))))
					.addGap(63))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_6)
								.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7)
								.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_5)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_4))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Book Name: ");
		
		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Author: ");
		
		s_authorTxt = new JTextField();
		s_authorTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Category: ");
		
		s_bookTypeJcb = new JComboBox();
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookSearchActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookManagerInter.class.getResource("/images/search.png")));
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clearSearchText(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookManagerInter.class.getResource("/images/re-set.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s_bookTypeJcb, 0, 125, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				bookTableMousePressed(e);
			}
		});
		scrollPane.setViewportView(bookTable);
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Book Name", "Author", "Price", "Book Description", "Book Category"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(97);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(202);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(92);
		getContentPane().setLayout(groupLayout);
		
		
		this.fillBookType("search");
		this.fillBookType("modify");
		this.fillTable(new Book());
		
		//set a boarder for textArea
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
	}
	
	/**
	 * Clear search text and restore the full inventory list
	 * @param e
	 */
	private void clearSearchText(ActionEvent e) {
		//clear
		this.s_bookNameTxt.setText("");
		this.s_authorTxt.setText("");
		this.s_bookTypeJcb.setSelectedIndex(0);
		
		//restore
		this.fillTable(new Book());
		 
		
	}

	private void textFieldClear(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = bookTable.getSelectedRow();
		this.resetValue();
		if (row>=0) {
			bookTable.removeRowSelectionInterval(row, row);
		}
	}

	private void bookAddActionPerforemd(ActionEvent evt) {
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
				this.resetValue(); 
				this.fillTable(new Book());
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

	private void bookDeleteActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "Please select a book!");
		}
		
		int n = JOptionPane.showConfirmDialog(null, "Please confirm to delete this book!");
		if (n==0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int deleteNum = bookDao.delete(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "Sucessfully Deleted!");
					this.resetValue();
					this.fillTable(new Book());
				}else {
					JOptionPane.showMessageDialog(null, "Failed to Delete!");
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to Delete!");
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private void bookUpdateActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		
		if(StringUtil.isEmpty(id) ) {
			JOptionPane.showMessageDialog(null, "Please select a book!");
			return;
		}
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
			
		Book book = new Book(Integer.parseInt(id), bookName, author, Float.parseFloat(price), bookTypeId, bookDesc);
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addNum = bookDao.update(con, book);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "Book Edited!");
				resetValue(); 
				this.fillTable(new Book());
			}else {
				JOptionPane.showMessageDialog(null, "Book Edit Failed!");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Book Edit Failed!");
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
		this.idTxt.setText("");
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.priceTxt.setText("");
		this.bookDescTxt.setText("");
		this.bookTypeJcb.setSelectedIndex(-1);
		
		
	}

	/**
	 * Mouse press then map the info to the textField below
	 * @param e
	 */
	private void bookTableMousePressed(MouseEvent e) {
		String prevId = idTxt.getText();
		int row = bookTable.getSelectedRow();
		String curId = (String) bookTable.getValueAt(row, 0);
		
		
		if (curId.equals(prevId)) {
			//reset
			resetValue();
			bookTable.removeRowSelectionInterval(row, row);
		}else {
			this.idTxt.setText((String) bookTable.getValueAt(row, 0));
			this.bookNameTxt.setText((String) bookTable.getValueAt(row, 1));
			this.authorTxt.setText((String) bookTable.getValueAt(row, 2));
			this.priceTxt.setText((Float) bookTable.getValueAt(row, 3)+"");
			this.bookDescTxt.setText((String) bookTable.getValueAt(row, 4));
			
			String bookTypeName = (String)this.bookTable.getValueAt(row, 5);
			
			//total type number
			int n = this.bookTypeJcb.getItemCount();
			//select the correct type
			for (int i = 0; i<n; i++) {
				BookType item = (BookType)this.bookTypeJcb.getItemAt(i);
				if(item.getBookType().equals(bookTypeName)) {
					this.bookTypeJcb.setSelectedIndex(i);
				}
			}
		}	
		
	}

	/**
	 * Search
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent evt) {
		
		String bookName = this.s_bookNameTxt.getText();
		String author = this.s_authorTxt.getText();
		BookType bookType = (BookType) this.s_bookTypeJcb.getSelectedItem(); 
		int bookTypeId = bookType.getId();
		
		Book book = new Book(bookName, author, bookTypeId);
		this.fillTable(book);
		
	}

	/**
	 * Initialize the scrolling category type to id:-1, type: Please select
	 * @param type (two place we will need it one in search on in edit mode)
	 */
	private void fillBookType(String type) {
		Connection con = null;
		BookType bookType = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(con, new BookType());
			
			if("search".equals(type)) {
				bookType = new BookType();
				bookType.setBookType("Please Select a Category!");
				bookType.setId(-1);
				this.s_bookTypeJcb.addItem(bookType);
			}
			
			while (rs.next()) {
				bookType = new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookType(rs.getString("bookTypeName"));
				
				if("search".equals(type)) {
					this.s_bookTypeJcb.addItem(bookType); //add the tostring 
				}else if ("modify".equals(type)) {
					this.bookTypeJcb.addItem(bookType);
					this.bookTypeJcb.setSelectedIndex(-1);
					 
				}
			}
			
		}catch(Exception e){
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
	
	/**
	 * Initialize the table content 
	 * @param book
	 */
	private void fillTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookDao.list(con, book);

			while(rs.next()) {				 
				Vector v = new Vector();
				
				//retrieve info from mysql
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
				dtm.addRow(v);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
