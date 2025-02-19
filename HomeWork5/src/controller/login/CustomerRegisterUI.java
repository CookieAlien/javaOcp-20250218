package controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.TitlePanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class CustomerRegisterUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmField;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField phoneField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegisterUI frame = new CustomerRegisterUI();
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
	public CustomerRegisterUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitlePanel titlePanel = new TitlePanel();
		titlePanel.setBounds(10, 10, 416, 66);
		contentPane.add(titlePanel);
		
		JLabel titleLabel = new JLabel("客戶註冊");
		titleLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(159, 86, 117, 36);
		contentPane.add(titleLabel);
		
		JLabel usernameLabel = new JLabel("帳號：");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		usernameLabel.setBounds(10, 135, 92, 36);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		usernameField.setBounds(108, 142, 146, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel usernameHintLabel = new JLabel("帳號為6~16位英文數字，首位不能為數字");
		usernameHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameHintLabel.setForeground(Color.BLACK);
		usernameHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		usernameHintLabel.setBounds(51, 170, 375, 29);
		contentPane.add(usernameHintLabel);
		
		JLabel passwordLabel = new JLabel("密碼：");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		passwordLabel.setBounds(10, 197, 92, 36);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		passwordField.setColumns(10);
		passwordField.setBounds(108, 204, 146, 26);
		contentPane.add(passwordField);
		
		JLabel passwordHintLabel = new JLabel("密碼為6~20位，須包含英文大小寫與數字");
		passwordHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordHintLabel.setForeground(Color.BLACK);
		passwordHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		passwordHintLabel.setBounds(51, 232, 375, 29);
		contentPane.add(passwordHintLabel);
		
		JLabel confirmLabel = new JLabel("確認密碼：");
		confirmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		confirmLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		confirmLabel.setBounds(10, 261, 92, 36);
		contentPane.add(confirmLabel);
		
		confirmField = new JPasswordField();
		confirmField.setEchoChar('*');
		confirmField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		confirmField.setColumns(10);
		confirmField.setBounds(108, 268, 146, 26);
		contentPane.add(confirmField);
		
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		nameLabel.setBounds(10, 324, 92, 36);
		contentPane.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(108, 331, 146, 26);
		contentPane.add(nameField);
		
		JLabel nameHintLabel = new JLabel("2~24位中英文字");
		nameHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameHintLabel.setForeground(Color.BLACK);
		nameHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		nameHintLabel.setBounds(51, 359, 375, 29);
		contentPane.add(nameHintLabel);
		
		JLabel addressLabel = new JLabel("住家地址：");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		addressLabel.setBounds(10, 398, 92, 36);
		contentPane.add(addressLabel);
		
		addressField = new JTextField();
		addressField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addressField.setColumns(10);
		addressField.setBounds(108, 405, 301, 26);
		contentPane.add(addressField);
		
		JLabel addressHintLabel = new JLabel("接受中英文輸入，請勿空白");
		addressHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressHintLabel.setForeground(Color.BLACK);
		addressHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addressHintLabel.setBounds(51, 433, 375, 29);
		contentPane.add(addressHintLabel);
		
		JLabel emailLabel = new JLabel("電子郵件：");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		emailLabel.setBounds(10, 472, 92, 36);
		contentPane.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		emailField.setColumns(10);
		emailField.setBounds(108, 479, 301, 26);
		contentPane.add(emailField);
		
		JLabel emailHintLabel = new JLabel("須為電郵格式，例如: cba@cba.com");
		emailHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailHintLabel.setForeground(Color.BLACK);
		emailHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		emailHintLabel.setBounds(51, 507, 375, 29);
		contentPane.add(emailHintLabel);
		
		JLabel phoneLabel = new JLabel("電話：");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		phoneLabel.setBounds(10, 546, 92, 36);
		contentPane.add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		phoneField.setColumns(10);
		phoneField.setBounds(108, 553, 146, 26);
		contentPane.add(phoneField);
		
		JLabel phoneHintLabel = new JLabel("09開頭10位數字");
		phoneHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		phoneHintLabel.setForeground(Color.BLACK);
		phoneHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		phoneHintLabel.setBounds(51, 581, 375, 29);
		contentPane.add(phoneHintLabel);
		
		JButton registerButton = new JButton("註冊");
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setBackground(new Color(0, 0, 200));
		registerButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		registerButton.setBounds(159, 620, 117, 33);
		contentPane.add(registerButton);
		
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerLoginUI().setVisible(true);
			}
		});
		returnButton.setForeground(Color.WHITE);
		returnButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		returnButton.setBackground(new Color(128, 64, 0));
		returnButton.setBounds(20, 620, 117, 33);
		contentPane.add(returnButton);
		
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
		showPasswordCheckBox.setBounds(260, 205, 95, 23);
		contentPane.add(showPasswordCheckBox);
		
		JCheckBox showPasswordCheckBox_1 = new JCheckBox("顯示");
		showPasswordCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckBox_1.isSelected()) {
					passwordField.setEchoChar((char) 0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPasswordCheckBox_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		showPasswordCheckBox_1.setBounds(260, 269, 95, 23);
		contentPane.add(showPasswordCheckBox_1);
	}
}
