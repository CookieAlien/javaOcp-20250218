package controller.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Customer;
import util.FileTool;
import util.TitlePanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerMenuUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Customer customer = (Customer) FileTool.load("Customer.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMenuUI frame = new CustomerMenuUI();
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
	public CustomerMenuUI() {
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
		welcomeLabel.setText("歡迎，"+customer.getName());
		
		JButton shopButton = new JButton("開始購物");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileTool.save(null, "ShoppingList.txt");
				new ShopFloor4UI().setVisible(true);
				dispose();
			}
		});
		shopButton.setBackground(new Color(0, 0, 200));
		shopButton.setForeground(new Color(255, 255, 255));
		shopButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		shopButton.setBounds(25, 182, 100, 37);
		contentPane.add(shopButton);
		
		JButton viewButton = new JButton("我的訂單");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewOrderUI().setVisible(true);
				dispose();
			}
		});
		viewButton.setForeground(Color.WHITE);
		viewButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		viewButton.setBackground(new Color(0, 0, 0));
		viewButton.setBounds(167, 182, 100, 37);
		contentPane.add(viewButton);
		
		JButton exitButton = new JButton("離開");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		exitButton.setBackground(new Color(255, 0, 0));
		exitButton.setBounds(309, 182, 100, 37);
		contentPane.add(exitButton);
		
		JButton changeButton = new JButton("修改個人資料");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerUpdateUI().setVisible(true);
				dispose();
			}
		});
		changeButton.setForeground(Color.WHITE);
		changeButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		changeButton.setBackground(new Color(0, 0, 200));
		changeButton.setBounds(25, 277, 141, 37);
		contentPane.add(changeButton);
	}
}
