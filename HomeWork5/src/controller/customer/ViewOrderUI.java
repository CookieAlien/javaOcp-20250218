package controller.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Customer;
import model.OrderItem;
import model.Porder;
import service.impl.PorderServiceImpl;
import util.FileTool;
import util.TitlePanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewOrderUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Customer customer = (Customer) FileTool.load("Customer.txt");
	private JTable orderTable;
	private JTable detailTable;
	private static PorderServiceImpl porderServiceImpl = new PorderServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewOrderUI frame = new ViewOrderUI();
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
	public ViewOrderUI() {
		setTitle("敗家家居");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
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
		titlePanel.setBounds(10, 10, 716, 66);
		contentPane.add(titlePanel);
		
		JPanel orderPanel = new JPanel();
		orderPanel.setBackground(new Color(248, 221, 186));
		orderPanel.setBounds(10, 86, 366, 370);
		contentPane.add(orderPanel);
		orderPanel.setLayout(null);
		
		JLabel infoLabel = new JLabel("你的訂單");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setForeground(new Color(0, 0, 0));
		infoLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		infoLabel.setBounds(10, 10, 320, 25);
		orderPanel.add(infoLabel);
		
		JLabel infoLabel1 = new JLabel("點擊下表任一行以查詢相關資訊");
		infoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel1.setForeground(Color.BLACK);
		infoLabel1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		infoLabel1.setBounds(10, 45, 320, 25);
		orderPanel.add(infoLabel1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 346, 245);
		orderPanel.add(scrollPane);
		
		orderTable = new JTable();
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showOrderDetails();
			}
		});
		scrollPane.setViewportView(orderTable);
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8A02\u55AE\u7DE8\u865F", "\u7BA1\u7406\u54E1\u7DE8\u865F", "\u7E3D\u91D1\u984D", "\u6700\u5F8C\u4FEE\u6539\u6642\u9593"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(55);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(65);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(55);
		orderTable.getColumnModel().getColumn(3).setPreferredWidth(130);
		orderTable.setRowHeight(20);
		orderTable.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
		
		JPanel detailPanel = new JPanel();
		detailPanel.setBackground(new Color(255, 255, 0));
		detailPanel.setBounds(386, 86, 340, 417);
		contentPane.add(detailPanel);
		detailPanel.setLayout(null);
		
		JLabel infoLabel2 = new JLabel("訂單詳細資訊");
		infoLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel2.setForeground(Color.BLACK);
		infoLabel2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		infoLabel2.setBounds(10, 10, 346, 25);
		detailPanel.add(infoLabel2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 46, 320, 290);
		detailPanel.add(scrollPane_1);
		
		detailTable = new JTable();
		scrollPane_1.setViewportView(detailTable);
		detailTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"\u5546\u54C1\u7DE8\u865F", "\u5546\u54C1\u540D\u7A31", "\u6578\u91CF", "\u5C0F\u8A08"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		detailTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		detailTable.getColumnModel().getColumn(1).setPreferredWidth(135);
		detailTable.getColumnModel().getColumn(1).setMinWidth(25);
		detailTable.getColumnModel().getColumn(2).setPreferredWidth(35);
		detailTable.getColumnModel().getColumn(2).setMinWidth(7);
		detailTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		detailTable.getColumnModel().getColumn(3).setMinWidth(10);
		detailTable.setRowHeight(20);
		detailTable.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		
		JButton printButton = new JButton("列印");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					detailTable.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		printButton.setForeground(Color.WHITE);
		printButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		printButton.setBackground(new Color(0, 0, 0));
		printButton.setBounds(126, 355, 112, 37);
		detailPanel.add(printButton);
		
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerMenuUI().setVisible(true);
				dispose();
			}
		});
		returnButton.setBackground(new Color(128, 64, 64));
		returnButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		returnButton.setForeground(new Color(255, 255, 255));
		returnButton.setBounds(128, 466, 112, 37);
		contentPane.add(returnButton);
		
		getOrders();
	}
	private void getOrders() {
		List<Porder> porders = porderServiceImpl.getPordersByCustomer(customer.getCustomerno());
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
		for (Porder porder : porders) {
			model.addRow(new Object[]{porder.getPorderno(),porder.getEmployeeno(),porder.getTotalPrice(),porder.getLastModified()});
		}
	}
	private void showOrderDetails() {
		int row = orderTable.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) detailTable.getModel();
		model.setRowCount(0);
		if (row>=0) {
			String porderno = (String) orderTable.getValueAt(row, 0);
			List<OrderItem> orderItems = porderServiceImpl.getOrderItems(porderno);
			for (OrderItem orderItem : orderItems) {
				model.addRow(new Object[] {orderItem.getProductno(),orderItem.getProductname(),orderItem.getAmount(),orderItem.getSum()});
			}
		}
	}
}
