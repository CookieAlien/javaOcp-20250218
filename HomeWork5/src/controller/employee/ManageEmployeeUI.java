package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import service.NewEmployeeUI;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageEmployeeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Employee operator = (Employee) FileTool.load("Employee.txt");
	private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
	private List<Employee> employees;
	private Employee currrentEmployee;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField phoneField;
	private JLabel customernoLabel;
	private JLabel usernameLabel;
	private Border defaultBorder = new JTextField().getBorder();
	private Border errorBorder = new LineBorder(Color.red,2);
	private JButton updateButton;
	private JButton deleteButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageEmployeeUI frame = new ManageEmployeeUI();
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
	public ManageEmployeeUI() {
		setTitle("敗家家居員工管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 650);
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
		titlePanel.setBounds(10, 10, 566, 66);
		contentPane.add(titlePanel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(10, 86, 566, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel titleLabel = new JLabel("員工管理");
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		titleLabel.setBounds(10, 10, 137, 35);
		panel.add(titleLabel);
		
		JLabel opLabel = new JLabel("操作員：");
		opLabel.setHorizontalAlignment(SwingConstants.CENTER);
		opLabel.setForeground(Color.WHITE);
		opLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		opLabel.setBounds(176, 10, 201, 35);
		opLabel.setText("操作員："+operator.getName());
		panel.add(opLabel);
		
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeMenuUI().setVisible(true);
				dispose();
			}
		});
		returnButton.setBackground(new Color(255, 255, 255));
		returnButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		returnButton.setBounds(415, 10, 105, 35);
		panel.add(returnButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBounds(10, 151, 566, 452);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel titleLabel_1 = new JLabel("員工清單");
		titleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_1.setForeground(new Color(0, 0, 0));
		titleLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		titleLabel_1.setBounds(56, 10, 137, 35);
		mainPanel.add(titleLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 234, 312);
		mainPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCustomerInfo();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"\u9867\u5BA2\u7DE8\u865F", "\u5E33\u865F", "\u59D3\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JLabel titleLabel_1_1 = new JLabel("點擊清單項目查看與修改詳細資訊");
		titleLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_1_1.setForeground(Color.BLACK);
		titleLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		titleLabel_1_1.setBounds(10, 55, 232, 23);
		mainPanel.add(titleLabel_1_1);
		
		JButton refreshButton = new JButton("重新整理");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCustomers();
			}
		});
		refreshButton.setForeground(new Color(255, 255, 255));
		refreshButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		refreshButton.setBackground(new Color(0, 0, 200));
		refreshButton.setBounds(35, 407, 121, 35);
		mainPanel.add(refreshButton);
		
		updateButton = new JButton("更新資訊");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currrentEmployee.getEmployeeno().equals("e001")) {
					JOptionPane.showMessageDialog(contentPane, "不能修改系統帳號！", "警告", JOptionPane.WARNING_MESSAGE);
				} else {
					if (validateAll()) {
						currrentEmployee.setPassword(passwordField.getText());
						currrentEmployee.setName(nameField.getText());
						currrentEmployee.setAddress(addressField.getText());
						currrentEmployee.setEmail(emailField.getText());
						currrentEmployee.setPhone(phoneField.getText());
						employeeServiceImpl.updateInfo(currrentEmployee);
						JOptionPane.showMessageDialog(contentPane, "修改成功！");
						resetInfo();
					}else {
						JOptionPane.showMessageDialog(contentPane, "輸入資料有格式錯誤！", "警告", JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		updateButton.setBackground(new Color(0, 0, 0));
		updateButton.setBounds(304, 407, 121, 35);
		mainPanel.add(updateButton);
		
		deleteButton = new JButton("刪除員工");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currrentEmployee.getEmployeeno().equals(operator.getEmployeeno())) {
					JOptionPane.showMessageDialog(contentPane, "不能刪除自己帳號！", "警告", JOptionPane.WARNING_MESSAGE);
				} else if (currrentEmployee.getEmployeeno().equals("e001")) {
					JOptionPane.showMessageDialog(contentPane, "不能刪除系統帳號！", "警告", JOptionPane.WARNING_MESSAGE);
				} else {
					int option = JOptionPane.showConfirmDialog(contentPane, "確定要刪除此顧客?與該顧客相關的訂單都會消失且無法復原！");
					if (option==JOptionPane.YES_OPTION) {
						employeeServiceImpl.deleteEmployee(currrentEmployee.getEmployeeno());
						resetInfo();
					}
				}
				
			}
		});
		deleteButton.setEnabled(false);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		deleteButton.setBackground(new Color(255, 0, 0));
		deleteButton.setBounds(435, 407, 121, 35);
		mainPanel.add(deleteButton);
		
		JLabel infoLabel = new JLabel("員工編號：");
		infoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel.setForeground(Color.BLACK);
		infoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		infoLabel.setBounds(252, 10, 121, 35);
		mainPanel.add(infoLabel);
		
		customernoLabel = new JLabel("");
		customernoLabel.setForeground(Color.BLACK);
		customernoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		customernoLabel.setBounds(383, 10, 121, 35);
		mainPanel.add(customernoLabel);
		
		JLabel infoLabel_1 = new JLabel("帳號：");
		infoLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_1.setForeground(Color.BLACK);
		infoLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_1.setBounds(254, 55, 84, 35);
		mainPanel.add(infoLabel_1);
		
		usernameLabel = new JLabel("");
		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		usernameLabel.setBounds(348, 55, 190, 35);
		mainPanel.add(usernameLabel);
		
		JLabel infoLabel_2 = new JLabel("密碼：");
		infoLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_2.setForeground(Color.BLACK);
		infoLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_2.setBounds(254, 92, 84, 35);
		mainPanel.add(infoLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (passwordField.getText().length() >= 5) {
					passwordField.setBorder(defaultBorder);
				}else {
					passwordField.setBorder(errorBorder);
				}
			}
		});
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		passwordField.setBounds(348, 100, 137, 27);
		mainPanel.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel infoLabel_3 = new JLabel("姓名：");
		infoLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_3.setForeground(Color.BLACK);
		infoLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_3.setBounds(254, 155, 84, 27);
		mainPanel.add(infoLabel_3);
		
		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Helper.validateName(nameField.getText())) {
					nameField.setBorder(defaultBorder);
				}else {
					nameField.setBorder(errorBorder);
				}
			}
		});
		nameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(348, 155, 137, 27);
		mainPanel.add(nameField);
		
		JLabel infoLabel_4 = new JLabel("地址：");
		infoLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_4.setForeground(Color.BLACK);
		infoLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_4.setBounds(254, 213, 56, 27);
		mainPanel.add(infoLabel_4);
		
		JLabel passwordHintLabel = new JLabel("至少5位任意文字");
		passwordHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordHintLabel.setForeground(Color.BLACK);
		passwordHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		passwordHintLabel.setBounds(291, 126, 265, 29);
		mainPanel.add(passwordHintLabel);
		
		JLabel nameHintLabel = new JLabel("2~24位中英文字");
		nameHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameHintLabel.setForeground(Color.BLACK);
		nameHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		nameHintLabel.setBounds(288, 175, 200, 35);
		mainPanel.add(nameHintLabel);
		
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
		addressField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addressField.setColumns(10);
		addressField.setBounds(320, 214, 218, 27);
		mainPanel.add(addressField);
		
		JLabel addressHintLabel = new JLabel("接受中英文輸入，請勿空白");
		addressHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressHintLabel.setForeground(Color.BLACK);
		addressHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addressHintLabel.setBounds(285, 236, 200, 29);
		mainPanel.add(addressHintLabel);
		
		JLabel infoLabel_5 = new JLabel("Email:");
		infoLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_5.setForeground(Color.BLACK);
		infoLabel_5.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_5.setBounds(254, 264, 56, 27);
		mainPanel.add(infoLabel_5);
		
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
		emailField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		emailField.setColumns(10);
		emailField.setBounds(320, 264, 218, 27);
		mainPanel.add(emailField);
		
		JLabel emailHintLabel = new JLabel("須為電郵格式，例如: cba@cba.com");
		emailHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailHintLabel.setForeground(Color.BLACK);
		emailHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		emailHintLabel.setBounds(285, 288, 253, 29);
		mainPanel.add(emailHintLabel);
		
		JLabel infoLabel_6 = new JLabel("手機：");
		infoLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		infoLabel_6.setForeground(Color.BLACK);
		infoLabel_6.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel_6.setBounds(272, 327, 66, 27);
		mainPanel.add(infoLabel_6);
		
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
		phoneField.setBounds(348, 327, 137, 27);
		mainPanel.add(phoneField);
		
		JLabel phoneHintLabel = new JLabel("09開頭10位數字");
		phoneHintLabel.setHorizontalAlignment(SwingConstants.LEFT);
		phoneHintLabel.setForeground(Color.BLACK);
		phoneHintLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		phoneHintLabel.setBounds(320, 353, 200, 29);
		mainPanel.add(phoneHintLabel);
		
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
		showPasswordCheckBox.setBackground(new Color(255, 255, 255));
		showPasswordCheckBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		showPasswordCheckBox.setBounds(491, 102, 69, 23);
		mainPanel.add(showPasswordCheckBox);
		
		JButton newEmpButton = new JButton("新增員工");
		newEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewEmployeeUI().setVisible(true);
				dispose();
			}
		});
		newEmpButton.setForeground(Color.WHITE);
		newEmpButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		newEmpButton.setBackground(new Color(128, 64, 0));
		newEmpButton.setBounds(173, 407, 121, 35);
		mainPanel.add(newEmpButton);
		displayCustomers();
	}
	private void displayCustomers() {
		employees = employeeServiceImpl.getAllEmployees();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Employee emp : employees) {
			model.addRow(new Object[] {emp.getEmployeeno(),emp.getUsername(),emp.getName()});
		}
	}
	private void showCustomerInfo() {
		int row = table.getSelectedRow();
		if (row>=0) {
			currrentEmployee = employees.get(row);
			customernoLabel.setText(currrentEmployee.getEmployeeno());
			usernameLabel.setText(currrentEmployee.getUsername());
			passwordField.setText(currrentEmployee.getPassword());
			nameField.setText(currrentEmployee.getName());
			addressField.setText(currrentEmployee.getAddress());
			emailField.setText(currrentEmployee.getEmail());
			phoneField.setText(currrentEmployee.getPhone());
			passwordField.setBorder(defaultBorder);
			nameField.setBorder(defaultBorder);
			addressField.setBorder(defaultBorder);
			emailField.setBorder(defaultBorder);
			phoneField.setBorder(defaultBorder);
			updateButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}else {
			currrentEmployee = null;
		}
	}
	private boolean validateAll() {
		return passwordField.getText().length() >= 5
				&& Helper.validateName(nameField.getText())
				&& Helper.validateAddress(addressField.getText())
				&& Helper.validateEmail(emailField.getText())
				&& Helper.validatePhoneNumber(phoneField.getText());
	}
	private void resetInfo() {
		customernoLabel.setText("");
		usernameLabel.setText("");
		passwordField.setText("");
		nameField.setText("");
		addressField.setText("");
		emailField.setText("");
		phoneField.setText("");
		currrentEmployee = null;
		table.clearSelection();
		displayCustomers();
		updateButton.setEnabled(false);
		deleteButton.setEnabled(false);
	}
}
