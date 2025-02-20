package controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.employee.EmployeeMenuUI;
import model.Employee;
import service.impl.EmployeeServiceImpl;
import util.FileTool;
import util.TitlePanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeLoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private static EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLoginUI frame = new EmployeeLoginUI();
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
	public EmployeeLoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitlePanel titlePanel = new TitlePanel();
		titlePanel.setBounds(16, 10, 416, 66);
		contentPane.add(titlePanel);
		
		JLabel titleLabel = new JLabel("員工登入");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		titleLabel.setBounds(155, 86, 129, 37);
		contentPane.add(titleLabel);
		
		JLabel usernameLabel = new JLabel("帳號：");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		usernameLabel.setBounds(16, 150, 129, 37);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(155, 153, 138, 31);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("密碼：");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		passwordLabel.setBounds(16, 212, 129, 37);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(155, 215, 138, 31);
		contentPane.add(passwordField);
		
		JCheckBox showPasswordCheckBox = new JCheckBox("顯示");
		showPasswordCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPasswordCheckBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		showPasswordCheckBox.setBounds(299, 212, 95, 35);
		contentPane.add(showPasswordCheckBox);
		
		JButton loginButton = new JButton("登入");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee employee = employeeServiceImpl.login(usernameField.getText(), passwordField.getText());
				if (employee != null) {
					FileTool.save(employee, "Employee.txt");
					new EmployeeMenuUI().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "帳號或密碼錯誤！", "登入失敗", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(0, 0, 200));
		loginButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		loginButton.setBounds(180, 280, 85, 37);
		contentPane.add(loginButton);
		
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChooseRoleUI().setVisible(true);
				dispose();
			}
		});
		returnButton.setForeground(Color.WHITE);
		returnButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		returnButton.setBackground(new Color(128, 64, 64));
		returnButton.setBounds(36, 280, 85, 37);
		contentPane.add(returnButton);
	}
}
