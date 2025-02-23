package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Employee;
import service.impl.EmployeeServiceImpl;
import util.FileTool;
import util.Helper;
import util.TitlePanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewEmployeeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Employee operator = (Employee) FileTool.load("Employee.txt");
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField usernameField;
	private Border defaultBorder = new JTextField().getBorder();
	private Border errorBorder = new LineBorder(Color.red,2);
	private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEmployeeUI frame = new NewEmployeeUI();
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
	public NewEmployeeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitlePanel titlePanel = new TitlePanel();
		GridBagLayout gridBagLayout = (GridBagLayout) titlePanel.getLayout();
		gridBagLayout.rowWeights = new double[]{1.0};
		gridBagLayout.rowHeights = new int[]{300};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.columnWidths = new int[]{150, 0, 0};
		titlePanel.setBounds(10, 10, 516, 66);
		contentPane.add(titlePanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(10, 86, 516, 55);
		contentPane.add(panel);
		
		JLabel titleLabel = new JLabel("新增員工");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		titleLabel.setBounds(10, 10, 119, 35);
		panel.add(titleLabel);
		
		JLabel opLabel = new JLabel("操作員：<dynamic>");
		opLabel.setHorizontalAlignment(SwingConstants.CENTER);
		opLabel.setForeground(Color.WHITE);
		opLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		opLabel.setBounds(139, 10, 236, 35);
		opLabel.setText("操作員："+operator.getName());
		panel.add(opLabel);
		
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageEmployeeUI().setVisible(true);
				dispose();
			}
		});
		returnButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		returnButton.setBackground(Color.WHITE);
		returnButton.setBounds(385, 10, 105, 35);
		panel.add(returnButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 151, 516, 452);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel infoLabel_1 = new JLabel("帳號：");
		infoLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_1.setForeground(Color.BLACK);
		infoLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_1.setBounds(103, 42, 84, 35);
		panel_1.add(infoLabel_1);
		
		JLabel infoLabel_2 = new JLabel("密碼：");
		infoLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_2.setForeground(Color.BLACK);
		infoLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_2.setBounds(103, 109, 84, 35);
		panel_1.add(infoLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (passwordField.getText().length()>=5 && !passwordField.getText().isBlank()) {
					passwordField.setBorder(defaultBorder);
				} else {
					passwordField.setBorder(errorBorder);
				}
			}
		});
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameField.requestFocusInWindow();
			}
		});
		passwordField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		passwordField.setBounds(197, 117, 137, 27);
		panel_1.add(passwordField);
		
		JCheckBox showPasswordCheckBox = new JCheckBox("顯示");
		showPasswordCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPasswordCheckBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		showPasswordCheckBox.setBackground(Color.WHITE);
		showPasswordCheckBox.setBounds(340, 119, 69, 23);
		panel_1.add(showPasswordCheckBox);
		
		JLabel passwordHintLabel = new JLabel("至少5位任意文字");
		passwordHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordHintLabel.setForeground(Color.BLACK);
		passwordHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		passwordHintLabel.setBounds(140, 143, 265, 29);
		panel_1.add(passwordHintLabel);
		
		JLabel infoLabel_3 = new JLabel("姓名：");
		infoLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_3.setForeground(Color.BLACK);
		infoLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_3.setBounds(103, 172, 84, 27);
		panel_1.add(infoLabel_3);
		
		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Helper.validateName(nameField.getText())) {
					nameField.setBorder(defaultBorder);
				} else {
					nameField.setBorder(errorBorder);
				}
			}
		});
		nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addressField.requestFocusInWindow();
			}
		});
		nameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(197, 172, 137, 27);
		panel_1.add(nameField);
		
		JLabel nameHintLabel = new JLabel("2~24位中英文字");
		nameHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameHintLabel.setForeground(Color.BLACK);
		nameHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		nameHintLabel.setBounds(137, 192, 200, 35);
		panel_1.add(nameHintLabel);
		
		JLabel infoLabel_4 = new JLabel("地址：");
		infoLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_4.setForeground(Color.BLACK);
		infoLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_4.setBounds(103, 230, 56, 27);
		panel_1.add(infoLabel_4);
		
		addressField = new JTextField();
		addressField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Helper.validateAddress(addressField.getText())) {
					addressField.setBorder(defaultBorder);
				} else {
					addressField.setBorder(errorBorder);
				}
			}
		});
		addressField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emailField.requestFocusInWindow();
			}
		});
		addressField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addressField.setColumns(10);
		addressField.setBounds(169, 231, 218, 27);
		panel_1.add(addressField);
		
		JLabel addressHintLabel = new JLabel("接受中英文輸入，請勿空白");
		addressHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressHintLabel.setForeground(Color.BLACK);
		addressHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addressHintLabel.setBounds(134, 253, 200, 29);
		panel_1.add(addressHintLabel);
		
		JLabel infoLabel_5 = new JLabel("Email:");
		infoLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_5.setForeground(Color.BLACK);
		infoLabel_5.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_5.setBounds(103, 281, 56, 27);
		panel_1.add(infoLabel_5);
		
		emailField = new JTextField();
		emailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Helper.validateEmail(emailField.getText())) {
					emailField.setBorder(defaultBorder);
				} else {
					emailField.setBorder(errorBorder);
				}
			}
		});
		emailField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phoneField.requestFocusInWindow();
			}
		});
		emailField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		emailField.setColumns(10);
		emailField.setBounds(169, 281, 218, 27);
		panel_1.add(emailField);
		
		JLabel emailHintLabel = new JLabel("須為電郵格式，例如: cba@cba.com");
		emailHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailHintLabel.setForeground(Color.BLACK);
		emailHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		emailHintLabel.setBounds(134, 305, 253, 29);
		panel_1.add(emailHintLabel);
		
		JLabel infoLabel_6 = new JLabel("手機：");
		infoLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_6.setForeground(Color.BLACK);
		infoLabel_6.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_6.setBounds(121, 344, 66, 27);
		panel_1.add(infoLabel_6);
		
		phoneField = new JTextField();
		phoneField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Helper.validatePhoneNumber(phoneField.getText())) {
					phoneField.setBorder(defaultBorder);
				} else {
					phoneField.setBorder(errorBorder);
				}
			}
		});
		phoneField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		phoneField.setColumns(10);
		phoneField.setBounds(197, 344, 137, 27);
		panel_1.add(phoneField);
		
		JLabel phoneHintLabel = new JLabel("09開頭10位數字");
		phoneHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		phoneHintLabel.setForeground(Color.BLACK);
		phoneHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		phoneHintLabel.setBounds(169, 370, 200, 29);
		panel_1.add(phoneHintLabel);
		
		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (usernameField.getText().length()>=5 && !usernameField.getText().isBlank()) {
					usernameField.setBorder(defaultBorder);
				} else {
					usernameField.setBorder(errorBorder);
				}
			}
		});
		usernameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.requestFocusInWindow();
			}
		});
		usernameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		usernameField.setColumns(10);
		usernameField.setBounds(197, 47, 137, 27);
		panel_1.add(usernameField);
		
		JLabel usernameHintLabel = new JLabel("至少5位任意文字");
		usernameHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameHintLabel.setForeground(Color.BLACK);
		usernameHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		usernameHintLabel.setBounds(137, 78, 265, 29);
		panel_1.add(usernameHintLabel);
		
		JButton addEmpButton = new JButton("新增員工");
		addEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateAll()) {
					if (employeeServiceImpl.isUsernameTaken(usernameField.getText())) {
						JOptionPane.showMessageDialog(contentPane, "帳號名稱已被使用！", "警告", JOptionPane.WARNING_MESSAGE);
					} else {
						Employee currrentEmployee = new Employee();
						currrentEmployee.setUsername(usernameField.getText());
						currrentEmployee.setPassword(passwordField.getText());
						currrentEmployee.setName(nameField.getText());
						currrentEmployee.setAddress(addressField.getText());
						currrentEmployee.setEmail(emailField.getText());
						currrentEmployee.setPhone(phoneField.getText());
						currrentEmployee.setEmployeeno(employeeServiceImpl.generateEmployeeno());
						employeeServiceImpl.register(currrentEmployee);
						JOptionPane.showMessageDialog(contentPane, "新增成功!");
						new ManageEmployeeUI().setVisible(true);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "輸入資料有格式錯誤！", "警告", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		addEmpButton.setForeground(new Color(255, 255, 255));
		addEmpButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		addEmpButton.setBackground(new Color(0, 0, 0));
		addEmpButton.setBounds(197, 407, 115, 35);
		panel_1.add(addEmpButton);
		
		JLabel titleLabel_1 = new JLabel("新員工資訊");
		titleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_1.setForeground(new Color(0, 0, 0));
		titleLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		titleLabel_1.setBounds(140, 10, 229, 35);
		panel_1.add(titleLabel_1);
	}
	private boolean validateAll() {
		return usernameField.getText().length()>=5 && !usernameField.getText().isBlank()
				&& passwordField.getText().length()>=5 && !passwordField.getText().isBlank()
				&& Helper.validateName(nameField.getText())
				&& Helper.validateAddress(addressField.getText())
				&& Helper.validateEmail(emailField.getText())
				&& Helper.validatePhoneNumber(phoneField.getText());
	}
}
