package com.ls.graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane = null; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/images/loginhead.png")));
		setTitle("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		mnNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/settings.png")));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Category");
		mnNewMenu_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/category.png")));
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Category");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeAddInter bookTypeAdd = new BookTypeAddInter(); //inter add frame
				bookTypeAdd.setVisible(true);
				desktopPane.add(bookTypeAdd);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Edit Category");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManageInter bookTypeManage = new BookTypeManageInter(); //inter add frame
				bookTypeManage.setVisible(true);
				desktopPane.add(bookTypeManage);
				
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_3 = new JMenu("Book");
		mnNewMenu_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/books.png")));
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Add Book");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				BookAddInterFrm bookAddInter = new BookAddInterFrm(); //inter add frame
				bookAddInter.setVisible(true);
				desktopPane.add(bookAddInter);
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Edit Book");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManagerInter bookManage = new BookManagerInter(); //inter add frame
				bookManage.setVisible(true);
				desktopPane.add(bookManage);
			}
		});
		mntmNewMenuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		mnNewMenu_3.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Quit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "Please confirm to quit the system");
				if (res == 0) dispose();
	
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/quit.png")));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("About");
		mnNewMenu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/about.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("About me");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterFrame inter = new InterFrame();
				inter.setVisible(true);
				desktopPane.add(inter);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/about.png")));
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		//set JFrame max
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
