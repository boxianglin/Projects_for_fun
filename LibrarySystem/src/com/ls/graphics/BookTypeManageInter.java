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
import javax.swing.table.DefaultTableModel;

import com.ls.dao.BookDao;
import com.ls.dao.BookTypeDao;
import com.ls.model.BookType;
import com.ls.util.DBUtil;
import com.ls.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManageInter extends JInternalFrame {
	private JTable bookTypeTable;
	private DBUtil dbUtil = new DBUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private JTextField s_bookTypeNameTxt;
	private JTextField idTxt;
	private JTextField bookTypeTxt;
	private JTextArea bookTypeDescTxt;
	private BookDao bookDao = new BookDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInter frame = new BookTypeManageInter();
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
	public BookTypeManageInter() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Categorical Search and Edit ");
		setBounds(100, 100, 445, 563);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Category Search: ");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setIcon(new ImageIcon(BookTypeManageInter.class.getResource("/images/search.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookTypeSearchActionPerformed(e);
				
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s_bookTypeNameTxt, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("ID: ");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Category: ");
		
		bookTypeTxt = new JTextField();
		bookTypeTxt.setText("");
		bookTypeTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Category Descriptions: ");
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setIcon(new ImageIcon(BookTypeManageInter.class.getResource("/images/add.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeAddAction(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.setIcon(new ImageIcon(BookTypeManageInter.class.getResource("/images/edit.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookTypeUpdateAction(e);
			}
		});
		
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.setIcon(new ImageIcon(BookTypeManageInter.class.getResource("/images/quit.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteAction(e);
			}
		});
		
		JButton btnNewButton_4 = new JButton("Clear");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				resetTextFiled(e);
				
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(BookTypeManageInter.class.getResource("/images/re-set.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(bookTypeDescTxt, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeTxt, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_4)))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addGap(18)
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_3)
							.addGap(68))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(btnNewButton_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		bookTypeTable.setFont(new Font("Arial", Font.PLAIN, 12));
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Category", "Category Description"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(0).setPreferredWidth(37);
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(135);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(159);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);
		
		this.fillTable(new BookType()); //Initialization
		
		
		//default enter: search
		this.getRootPane().setDefaultButton(btnNewButton);
		
		//set a boarder for textArea
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

	}
	

 

	protected void resetTextFiled(ActionEvent e) {
		
		int row = bookTypeTable.getSelectedRow();
		this.resetTxt();
		if (row>=0) {
			bookTypeTable.removeRowSelectionInterval(row, row);
		}
	
	}

	/**
	 * Use to fill the data into the bookTypeTable
	 * ResultSet retrieved from database through Sql syntax
	 * 
	 * @param bookType
	 */
	private void fillTable(BookType bookType) {
		DefaultTableModel dtm = (DefaultTableModel) bookTypeTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(con, bookType);

			while(rs.next()) {				 
				Vector v = new Vector();
				
				//retrieve info from mysql
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
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
	
	
	/**
	 * CategoryKeyword Search 
	 * @param e
	 */
	private void bookTypeSearchActionPerformed(ActionEvent e) {
		//get the keywords
		String s_bookTypeName = this.s_bookTypeNameTxt.getText();
		
		//We new a bookType to show the category
		BookType bookType = new BookType();
		bookType.setBookType(s_bookTypeName);
		this.fillTable(bookType);
	}
	
	
	/**
	 * Set data that mouse pressed for to the textFileds
	 * 
	 * @param e
	 */

	private void bookTypeTableMousePressed(MouseEvent e) {
		String prevId = idTxt.getText();
		int row = bookTypeTable.getSelectedRow();
		String curId = (String) bookTypeTable.getValueAt(row, 0);
		
		//when we still click the same row we want to just clear the textfields and unselected the row
		if (curId.equals(prevId)) {
			this.resetTxt();
			bookTypeTable.removeRowSelectionInterval(row, row);
		}else {
			idTxt.setText((String) bookTypeTable.getValueAt(row, 0));
			bookTypeTxt.setText((String) bookTypeTable.getValueAt(row, 1));
			bookTypeDescTxt.setText((String) bookTypeTable.getValueAt(row, 2));
		}
		
		
	}
	
	
	/**
	 * Add
	 * @param e
	 */
	private void bookTypeAddAction(ActionEvent e) {
		String bookType = bookTypeTxt.getText();
		String bookTypeDesc = bookTypeDescTxt.getText();
		
		if(StringUtil.isEmpty(bookType)) {
			JOptionPane.showMessageDialog(null, "Category cannot be empty!");
			return;
		}
		
		//convert the inputs to BookType Object
		BookType newType = new BookType(bookType, bookTypeDesc);
		Connection con = null;
		
		//pass into DAO
		try {
			con = dbUtil.getCon();
			int n = bookTypeDao.add(con, newType);
			if (n == 1) {
				JOptionPane.showMessageDialog(null, "Sucessfully Added!");
				this.resetTxt();
				this.fillTable(new BookType()); //to refresh the table
			}else {
				JOptionPane.showMessageDialog(null, "Failed to add!");
			}
		}catch(Exception event) {
			event.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to add!");
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Update
	 * @param e
	 */
	private void bookTypeUpdateAction(ActionEvent evt) {
		String id = idTxt.getText();
		String bookType  = bookTypeTxt.getText();
		String bookTypeDesc = bookTypeDescTxt.getText();
		
		//if id is not in idTxt, where mouse not pressed to any items
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(bookType) ) {
			JOptionPane.showMessageDialog(null, "Please select the category!");
			return;
		}
		
		BookType curType = new BookType(Integer.parseInt(id),bookType, bookTypeDesc);
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			int modify = bookTypeDao.update(con, curType); //Update the database
			if (modify == 1){
				JOptionPane.showMessageDialog(null, "Sucessfully Updated!");
				this.resetTxt();
				this.fillTable(new BookType()); //to refresh the table
			}else {
				JOptionPane.showMessageDialog(null, "Failed to Update!");
			}
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to Update!");
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
	 * Delete
	 * @param e
	 */
	private void bookTypeDeleteAction(ActionEvent evt) {
		String id = idTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "Please select a category!");
			return;
		}
		
		int n = JOptionPane.showConfirmDialog(null, "Please confirm to delete this category!");
		if (n==0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				
				boolean flag = bookDao.existBookbyBookTypeId(con, id);
				if (flag) {
					JOptionPane.showMessageDialog(null, "Delete Fail, There is atleast one book in this category! ");
					return;
				}
				int deleteNum = bookTypeDao.delete(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "Sucessfully Deleted!");
					this.resetTxt();
					this.fillTable(new BookType());
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
	
	//Helper 
	private void resetTxt() {
		this.bookTypeDescTxt.setText("");
		this.bookTypeTxt.setText("");
		this.idTxt.setText("");
	}
}
