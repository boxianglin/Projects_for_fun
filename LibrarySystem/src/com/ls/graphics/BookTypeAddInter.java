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

import com.ls.dao.BookTypeDao;
import com.ls.model.BookType;
import com.ls.util.DBUtil;
import com.ls.util.StringUtil;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class BookTypeAddInter extends JInternalFrame {
	private JTextField bookTypeTxt;
	private JTextArea bookTypeDescTxt;
	private DBUtil dbUtil = new DBUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInter frame = new BookTypeAddInter();
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
	public BookTypeAddInter() {
		setTitle("Category Add");
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("Category Name: ");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setIcon(new ImageIcon(BookTypeAddInter.class.getResource("/images/category.png")));
		
		JLabel lblNewLabel_1 = new JLabel("Category Description: ");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1.setIcon(new ImageIcon(BookTypeAddInter.class.getResource("/images/edit.png")));
		
		bookTypeTxt = new JTextField();
		bookTypeTxt.setColumns(10);
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeActionPerformed(e);
			
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnNewButton.setIcon(new ImageIcon(BookTypeAddInter.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnNewButton_1.setIcon(new ImageIcon(BookTypeAddInter.class.getResource("/images/re-set.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(21)
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.UNRELATED))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addContainerGap(120, Short.MAX_VALUE)
								.addComponent(btnNewButton)
								.addPreferredGap(ComponentPlacement.RELATED))))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(26)
								.addComponent(btnNewButton_1)
								.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE))
							.addComponent(bookTypeDescTxt, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
						.addComponent(bookTypeTxt, 200, 200, 200))
					.addGap(73))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(37))
		);
		getContentPane().setLayout(groupLayout);
		
		//set a boarder for textArea
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

	}
	
	/**
	 * Add Category
	 * @param e
	 */
	private void bookTypeActionPerformed(ActionEvent e) {
		String bookType = bookTypeTxt.getText();
		String bookTypeDesc = bookTypeDescTxt.getText();
		if(StringUtil.isEmpty(bookType)) {
			JOptionPane.showMessageDialog(null, "Category cannot be empty!");
			return;
		}
		//convert the inputs to BookType Object
		BookType category = new BookType(bookType, bookTypeDesc);
		Connection con = null;
		
		//pass into DAO
		try {
			con = dbUtil.getCon();
			int n = bookTypeDao.add(con, category);
			if (n == 1) {
				JOptionPane.showMessageDialog(null, "Sucessfully Added!");
				resetValue();
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

	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
	}

	/**
	 * reset current inputs
	 */
	private void resetValue() {
		this.bookTypeTxt.setText("");
		this.bookTypeDescTxt.setText("");
		
	}
}
