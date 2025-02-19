package controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.TitlePanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseRoleUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseRoleUI frame = new ChooseRoleUI();
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
	public ChooseRoleUI() {
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
		
		JLabel lblNewLabel = new JLabel("請選擇登入身分");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		lblNewLabel.setBounds(120, 85, 191, 40);
		contentPane.add(lblNewLabel);
		
		JButton customerButton = new JButton("顧客");
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerLoginUI().setVisible(true);
				dispose();
			}
		});
		customerButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		customerButton.setBackground(new Color(0, 0, 200));
		customerButton.setForeground(new Color(255, 255, 255));
		customerButton.setBounds(55, 189, 106, 40);
		contentPane.add(customerButton);
		
		JButton employeeButton = new JButton("員工");
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeLoginUI().setVisible(true);
				dispose();
			}
		});
		employeeButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		employeeButton.setForeground(Color.WHITE);
		employeeButton.setBackground(new Color(0, 0, 0));
		employeeButton.setBounds(260, 189, 106, 40);
		contentPane.add(employeeButton);
	}
}
