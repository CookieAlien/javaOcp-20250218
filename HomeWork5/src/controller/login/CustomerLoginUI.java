package controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.TitlePanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class CustomerLoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLoginUI frame = new CustomerLoginUI();
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
	public CustomerLoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitlePanel titlePanel = new TitlePanel();
		titlePanel.setBounds(16, 10, 416, 66);
		contentPane.add(titlePanel);
		
		JLabel titleLabel = new JLabel("客戶登入");
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
		passwordField.setEchoChar('*');
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
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(0, 0, 200));
		loginButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		loginButton.setBounds(180, 280, 85, 37);
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("註冊");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerRegisterUI().setVisible(true);
				dispose();
			}
		});
		registerButton.setForeground(Color.WHITE);
		registerButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		registerButton.setBackground(new Color(0, 0, 0));
		registerButton.setBounds(312, 280, 85, 37);
		contentPane.add(registerButton);
		
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
		returnButton.setBounds(37, 280, 85, 37);
		contentPane.add(returnButton);
		
	}
}
