package controller.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CartItem;
import model.Customer;
import util.FileTool;
import util.Helper;
import util.TitlePanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class CheckoutUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<CartItem> shoppingList;
	private Customer customer;
	private int sum;
	private JSpinner cashField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea outputArea;
	private JLabel priceLabel;
	private JButton checkoutButton;
	private JButton returnButton;
	private JButton exportButton;
	private JButton printButton;
	private JButton finishButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutUI frame = new CheckoutUI();
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
	public CheckoutUI() {
		setTitle("敗家家居");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		shoppingList = (List<CartItem>) FileTool.load("ShoppingList.txt");
		customer = (Customer) FileTool.load("Customer.txt");
		getSum();
		
		TitlePanel titlePanel = new TitlePanel();
		titlePanel.setBounds(10, 10, 566, 66);
		contentPane.add(titlePanel);
		
		JPanel confirmPanel = new JPanel();
		confirmPanel.setLayout(null);
		confirmPanel.setBackground(Color.WHITE);
		confirmPanel.setBounds(10, 86, 225, 324);
		contentPane.add(confirmPanel);
		
		JLabel titleLabel_1 = new JLabel("確認與付款");
		titleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_1.setForeground(Color.BLACK);
		titleLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		titleLabel_1.setBounds(10, 10, 205, 33);
		confirmPanel.add(titleLabel_1);
		
		priceLabel = new JLabel();
		priceLabel.setText("總額：$0");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setForeground(Color.BLACK);
		priceLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		priceLabel.setBounds(10, 53, 205, 46);
		confirmPanel.add(priceLabel);
		
		JLabel paymentLabel = new JLabel("支付方式");
		paymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paymentLabel.setForeground(Color.BLACK);
		paymentLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		paymentLabel.setBounds(10, 107, 207, 33);
		confirmPanel.add(paymentLabel);
		
		JRadioButton cashRadio = new JRadioButton("現金");
		buttonGroup.add(cashRadio);
		cashRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashField.setEnabled(true);
			}
		});
		cashRadio.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		cashRadio.setFocusPainted(false);
		cashRadio.setBackground(Color.WHITE);
		cashRadio.setBounds(66, 146, 129, 23);
		confirmPanel.add(cashRadio);
		
		JRadioButton cardRadio = new JRadioButton("信用卡");
		cardRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashField.setEnabled(false);
			}
		});
		buttonGroup.add(cardRadio);
		cardRadio.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		cardRadio.setFocusPainted(false);
		cardRadio.setBackground(Color.WHITE);
		cardRadio.setBounds(66, 171, 129, 23);
		confirmPanel.add(cardRadio);
		
		JRadioButton excelRadio = new JRadioButton("Excel Pay");
		excelRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashField.setEnabled(false);
			}
		});
		buttonGroup.add(excelRadio);
		excelRadio.setToolTipText("維護中");
		excelRadio.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		excelRadio.setFocusPainted(false);
		excelRadio.setBackground(Color.WHITE);
		excelRadio.setBounds(66, 196, 129, 23);
		confirmPanel.add(excelRadio);
		
		JLabel cashLabel = new JLabel("現金付款:");
		cashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cashLabel.setForeground(Color.BLACK);
		cashLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		cashLabel.setBounds(10, 225, 77, 33);
		confirmPanel.add(cashLabel);
		
		cashField = new JSpinner();
		cashField.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(100)));
		cashField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		cashField.setEnabled(false);
		cashField.setBounds(97, 230, 98, 28);
		confirmPanel.add(cashField);
		
		checkoutButton = new JButton("結帳");
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cashRadio.isSelected()) {
					int cash = (int) cashField.getValue();
					if (cash>=sum) {
						outputArea.setText(CashPayment(cash));
						finishPayment();
					} else {
						JOptionPane.showMessageDialog(contentPane, "輸入金額不足!", "警告", JOptionPane.WARNING_MESSAGE);
					}
				}else if (cardRadio.isSelected()) {
					String nameinput = JOptionPane.showInputDialog("刷卡請簽名(需與帳號的姓名相同)");
					if (nameinput.equals(customer.getName())) {
						outputArea.setText(displayOutput()+"\n刷卡付款");
						finishPayment();
					}else {
						JOptionPane.showMessageDialog(contentPane, "簽名與會員姓名不同!", "警告", JOptionPane.WARNING_MESSAGE);
					}
				}else if (excelRadio.isSelected()) {
					JFileChooser fc = new JFileChooser();
					int returnval = fc.showOpenDialog(contentPane);
					if (returnval == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						if (file.getName().endsWith(".xlsx")) {
							//TODO: read the excel file
							
							outputArea.setText(displayOutput());
							finishPayment();
						}else {
							JOptionPane.showMessageDialog(contentPane, "選擇的非excel檔案!");
						}
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "沒有選擇支付方式!", "警告", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		checkoutButton.setForeground(Color.WHITE);
		checkoutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		checkoutButton.setBackground(new Color(0, 0, 200));
		checkoutButton.setBounds(66, 268, 85, 34);
		confirmPanel.add(checkoutButton);
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(null);
		outputPanel.setBackground(new Color(0, 0, 200));
		outputPanel.setBounds(245, 86, 331, 324);
		contentPane.add(outputPanel);
		
		JLabel titleLabel_2 = new JLabel("明細");
		titleLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_2.setForeground(Color.WHITE);
		titleLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		titleLabel_2.setBounds(73, 10, 188, 33);
		outputPanel.add(titleLabel_2);
		
		printButton = new JButton("列印");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					outputArea.print();
				} catch (PrinterException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		printButton.setEnabled(false);
		printButton.setForeground(new Color(255, 255, 255));
		printButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		printButton.setBackground(new Color(0, 0, 0));
		printButton.setBounds(193, 277, 85, 34);
		outputPanel.add(printButton);
		
		exportButton = new JButton("輸出明細");
		exportButton.setEnabled(false);
		exportButton.setForeground(Color.WHITE);
		exportButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		exportButton.setBackground(new Color(0, 128, 0));
		exportButton.setBounds(43, 277, 115, 34);
		outputPanel.add(exportButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 44, 291, 223);
		outputPanel.add(scrollPane);
		
		outputArea = new JTextArea();
		scrollPane.setViewportView(outputArea);
		outputArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		outputArea.setText(displayOutput());
		
		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShopFloor1UI().setVisible(true);
				dispose();
			}
		});
		returnButton.setForeground(Color.WHITE);
		returnButton.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		returnButton.setBackground(new Color(0, 0, 200));
		returnButton.setBounds(54, 429, 124, 49);
		contentPane.add(returnButton);
		
		finishButton = new JButton("完成");
		finishButton.setEnabled(false);
		finishButton.setForeground(Color.WHITE);
		finishButton.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		finishButton.setBackground(new Color(128, 64, 0));
		finishButton.setBounds(348, 429, 124, 49);
		contentPane.add(finishButton);

		priceLabel.setText("總額：$"+sum);
		
	}

	private void getSum() {
		sum = 0;
		for (CartItem cartItem : shoppingList) {
			sum+= cartItem.getSum();
		}
	}
	private String displayOutput() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("顧客").append(customer.getName()).append("你好\n");
		sBuilder.append("以下為你的購物清單\n");
		for (CartItem cartItem : shoppingList) {
			sBuilder.append(cartItem.getProductname()).append("  x").append(cartItem.getAmount()).append("  ").append(cartItem.getSum()).append("元\n");
		}
		sBuilder.append("==========================\n");
		sBuilder.append("總計：\t").append(sum).append("元\n");
		return sBuilder.toString();
	}
	private String CashPayment(int cash) {
		StringBuilder s = new StringBuilder(displayOutput());
		int change = cash - sum;
		if (change < 0) {
			s.append("付款金額不足!");
		}else {
			s.append("付款金額:").append(cash);
			if (change == 0) {
				s.append("\n無須找零!");
			} else {
				s.append("  找零:").append(change).append("\n");
				int[] coins = new int[] {1000,500,100,50,10,5,1};
				for (int coin : coins) {
					if (change >= coin) {
						int amount = change / coin;
						s.append(coin).append("元: ").append(amount).append(coin >= 100 ? "張" : "枚").append("\n");
						change %= coin;
					}
				}
			}
		}
		
		return s.toString();
	}

	protected void finishPayment() {
		checkoutButton.setEnabled(false);
		returnButton.setEnabled(false);
		exportButton.setEnabled(true);
		printButton.setEnabled(true);
		finishButton.setEnabled(true);
	}
}
