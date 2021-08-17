package com.ls.graphics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.ls.dao.UserDao;
import com.ls.model.User;
import com.ls.util.DBUtil;
import com.ls.util.StringUtil;

public class LogOnFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private DBUtil dbUtil = new DBUtil();
	private UserDao userDao = new UserDao();
			 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrame frame = new LogOnFrame();
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
	public LogOnFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogOnFrame.class.getResource("/images/loginhead.png")));
		setTitle("Login Portal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Library System Management");
		lblNewLabel.setForeground(new Color(51, 102, 102));
		lblNewLabel.setIcon(new ImageIcon(LogOnFrame.class.getResource("/images/loginhead.png")));
		lblNewLabel.setFont(new Font("Eras Medium ITC", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setIcon(new ImageIcon(LogOnFrame.class.getResource("/images/username.png")));
		lblNewLabel_1.setForeground(new Color(102, 102, 102));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Password : ");
		lblNewLabel_2.setIcon(new ImageIcon(LogOnFrame.class.getResource("/images/password.png")));
		lblNewLabel_2.setForeground(new Color(102, 102, 102));
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		userNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(LogOnFrame.class.getResource("/images/login.png")));
		btnNewButton.setForeground(new Color(51, 153, 51));
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(LogOnFrame.class.getResource("/images/re-set.png")));
		btnNewButton_1.setForeground(new Color(102, 102, 102));
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		passwordTxt.setEchoChar('*');
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(84)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(40)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1)
									.addGap(63))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(passwordTxt))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))))
							.addGap(86))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		//jframe at middle
		this.setLocationRelativeTo(null);
		//default enter: login
		this.getRootPane().setDefaultButton(btnNewButton);
		
	}

	/**
	 * login
	 * @param e
	 */
	private void loginActionPerformed(ActionEvent e) {
		String userName = this.userNameTxt.getText();
		String password = new String (this.passwordTxt.getPassword());
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "The username cannot be empty");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "The password cannot be empty");
			return;
		}
		
		/*
		 * Database connection
		 */
		User user = new User(userName, password);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			if (currentUser != null) {
				dispose(); //this is to close the login frame the new mainfrm will be open
				new MainFrm().setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Username or Password Incorrect!");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
 
		
	}

	/**
	 * Reset 
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
	}
}
