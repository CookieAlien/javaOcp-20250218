package controller.employee;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Employee;
import util.FileTool;
import util.TitlePanel;

public class EmployeeMenuUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Employee employee = (Employee) FileTool.load("Employee.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMenuUI frame = new EmployeeMenuUI();
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
	public EmployeeMenuUI() {
		setTitle("敗家家居");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitlePanel titlePanel = new TitlePanel();
		titlePanel.setBounds(10, 10, 416, 66);
		contentPane.add(titlePanel);
		
		JLabel welcomeLabel = new JLabel("歡迎");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		welcomeLabel.setBounds(108, 86, 205, 37);
		contentPane.add(welcomeLabel);
		welcomeLabel.setText("歡迎，"+employee.getName());
		
		JButton manageEmpButton = new JButton("員工管理");
		manageEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageEmployeeUI().setVisible(true);
				dispose();
			}
		});
		manageEmpButton.setBackground(new Color(0, 0, 200));
		manageEmpButton.setForeground(new Color(255, 255, 255));
		manageEmpButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		manageEmpButton.setBounds(71, 150, 100, 37);
		contentPane.add(manageEmpButton);
		
		JButton porderButton = new JButton("訂單管理");
		porderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageOrderUI().setVisible(true);
				dispose();
			}
		});
		porderButton.setForeground(Color.WHITE);
		porderButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		porderButton.setBackground(new Color(0, 0, 0));
		porderButton.setBounds(71, 222, 100, 37);
		contentPane.add(porderButton);
		
		JButton exitButton = new JButton("離開");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		exitButton.setBackground(new Color(255, 0, 0));
		exitButton.setBounds(252, 316, 100, 37);
		contentPane.add(exitButton);
		
		JButton manageCusButton = new JButton("顧客管理");
		manageCusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageCustomerUI().setVisible(true);
				dispose();
			}
		});
		manageCusButton.setForeground(Color.WHITE);
		manageCusButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		manageCusButton.setBackground(new Color(0, 0, 200));
		manageCusButton.setBounds(252, 150, 100, 37);
		contentPane.add(manageCusButton);
		
		JButton productButton = new JButton("商品管理");
		productButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageProductUI().setVisible(true);
				dispose();
			}
		});
		productButton.setForeground(Color.WHITE);
		productButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		productButton.setBackground(Color.BLACK);
		productButton.setBounds(252, 222, 100, 37);
		contentPane.add(productButton);
		
		JButton reportButton = new JButton("檢視報表");
		reportButton.setForeground(Color.WHITE);
		reportButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		reportButton.setBackground(new Color(128, 64, 0));
		reportButton.setBounds(71, 316, 100, 37);
		contentPane.add(reportButton);
	}
}
