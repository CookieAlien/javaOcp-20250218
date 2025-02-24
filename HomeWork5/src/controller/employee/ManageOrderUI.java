package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.FileTool;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import model.Employee;
import model.Porder;
import service.impl.CustomerServiceImpl;
import service.impl.PorderServiceImpl;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageOrderUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField addUsernameField;
	private PorderServiceImpl porderServiceImpl = new PorderServiceImpl();
	private CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	private Employee operator = (Employee) FileTool.load("Employee.txt");
	private Customer customer;
	private Porder currentPorder;
	private JTextField customernoField;
	private JTextField employeenoField;
	private List<Porder> porders;
	private JLabel pordernoLabel;
	private JLabel customernoLabel;
	private JLabel employeenoLabel;
	private JLabel totalpriceLabel;
	private JButton updateButton;
	private JButton deleteButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageOrderUI frame = new ManageOrderUI();
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
	public ManageOrderUI() {
		setTitle("敗家家居訂單管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 800, 700);
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
		titlePanel.setBounds(10, 10, 766, 66);
		contentPane.add(titlePanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(10, 86, 766, 55);
		contentPane.add(panel);
		
		JLabel titleLabel = new JLabel("訂單管理");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		titleLabel.setBounds(10, 10, 137, 35);
		panel.add(titleLabel);
		
		JLabel opLabel = new JLabel();
		opLabel.setHorizontalAlignment(SwingConstants.CENTER);
		opLabel.setForeground(Color.WHITE);
		opLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		opLabel.setBounds(245, 10, 201, 35);
		opLabel.setText("操作員："+operator.getName());
		panel.add(opLabel);
		
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeMenuUI().setVisible(true);
				dispose();
			}
		});
		returnButton.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		returnButton.setBackground(Color.WHITE);
		returnButton.setBounds(535, 10, 105, 35);
		panel.add(returnButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 151, 435, 502);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("訂單一覽");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 10, 415, 38);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 415, 270);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getCurrentPorder();
			}
		});
		table.setRowHeight(24);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\u8A02\u55AE\u7DE8\u865F", "\u9867\u5BA2\u7DE8\u865F", "\u54E1\u5DE5\u7DE8\u865F", "\u7E3D\u50F9", "\u6700\u5F8C\u4FEE\u6539\u6642\u9593"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(0).setMinWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setMinWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setMinWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JLabel filterLabel = new JLabel("顧客編號：");
		filterLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		filterLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		filterLabel.setBounds(63, 338, 99, 38);
		panel_1.add(filterLabel);
		
		customernoField = new JTextField();
		customernoField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		customernoField.setColumns(10);
		customernoField.setBounds(162, 344, 120, 32);
		panel_1.add(customernoField);
		
		JButton filterButton = new JButton("篩選");
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPordersByCustomer();
			}
		});
		filterButton.setForeground(Color.WHITE);
		filterButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		filterButton.setBackground(new Color(0, 0, 200));
		filterButton.setBounds(292, 344, 80, 32);
		panel_1.add(filterButton);
		
		JLabel filterLabel2 = new JLabel("員工編號：");
		filterLabel2.setHorizontalAlignment(SwingConstants.TRAILING);
		filterLabel2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		filterLabel2.setBounds(63, 386, 99, 38);
		panel_1.add(filterLabel2);
		
		employeenoField = new JTextField();
		employeenoField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		employeenoField.setColumns(10);
		employeenoField.setBounds(162, 392, 120, 32);
		panel_1.add(employeenoField);
		
		JButton filterButton_1 = new JButton("篩選");
		filterButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPordersByEmployee();
			}
		});
		filterButton_1.setForeground(Color.WHITE);
		filterButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		filterButton_1.setBackground(new Color(0, 0, 200));
		filterButton_1.setBounds(292, 392, 80, 32);
		panel_1.add(filterButton_1);
		
		JButton refreshButton = new JButton("重新整理");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllPorders();
			}
		});
		refreshButton.setForeground(Color.WHITE);
		refreshButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		refreshButton.setBackground(new Color(0, 0, 0));
		refreshButton.setBounds(162, 443, 106, 38);
		panel_1.add(refreshButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(455, 151, 321, 188);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel titleLabel_1 = new JLabel("新增訂單");
		titleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		titleLabel_1.setBounds(10, 10, 301, 38);
		panel_2.add(titleLabel_1);
		
		JLabel addUsernameLabel = new JLabel("顧客帳號：");
		addUsernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addUsernameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		addUsernameLabel.setBounds(10, 58, 92, 38);
		panel_2.add(addUsernameLabel);
		
		addUsernameField = new JTextField();
		addUsernameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addUsernameField.setBounds(104, 63, 170, 32);
		panel_2.add(addUsernameField);
		addUsernameField.setColumns(10);
		
		JButton addporderButton = new JButton("新增訂單");
		addporderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer = customerServiceImpl.getCustomerByUsername(addUsernameField.getText());
				if (customer!=null) {
					FileTool.save(customer, "Customer.txt");
					new AddPorderUI().setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(contentPane, "此顧客不存在！");
				}
			}
		});
		addporderButton.setBackground(new Color(0, 0, 200));
		addporderButton.setForeground(new Color(255, 255, 255));
		addporderButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		addporderButton.setBounds(104, 129, 106, 38);
		panel_2.add(addporderButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(455, 349, 321, 304);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel titleLabel_2 = new JLabel("修改/刪除訂單");
		titleLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		titleLabel_2.setBounds(10, 10, 301, 38);
		panel_3.add(titleLabel_2);
		
		JLabel updateinfoLabel = new JLabel("訂單編號：");
		updateinfoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		updateinfoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		updateinfoLabel.setBounds(10, 58, 92, 38);
		panel_3.add(updateinfoLabel);
		
		JLabel updateinfoLabel_1 = new JLabel("顧客編號：");
		updateinfoLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		updateinfoLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		updateinfoLabel_1.setBounds(10, 106, 92, 38);
		panel_3.add(updateinfoLabel_1);
		
		JLabel updateinfoLabel_2 = new JLabel("員工編號：");
		updateinfoLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		updateinfoLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		updateinfoLabel_2.setBounds(10, 154, 92, 38);
		panel_3.add(updateinfoLabel_2);
		
		JLabel updateinfoLabel_3 = new JLabel("總價：");
		updateinfoLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		updateinfoLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		updateinfoLabel_3.setBounds(10, 202, 92, 38);
		panel_3.add(updateinfoLabel_3);
		
		updateButton = new JButton("修改訂單");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileTool.save(currentPorder, "Porder.txt");
				new updatePorderUI().setVisible(true);
				dispose();
			}
		});
		updateButton.setEnabled(false);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		updateButton.setBackground(new Color(128, 64, 0));
		updateButton.setBounds(55, 256, 106, 38);
		panel_3.add(updateButton);
		
		deleteButton = new JButton("刪除訂單");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnval = JOptionPane.showConfirmDialog(contentPane, "確定要刪除此訂單嗎？此動作無法復原！", "訊息", JOptionPane.YES_NO_OPTION);
				if (returnval == JOptionPane.YES_OPTION) {
					porderServiceImpl.deleteOrder(currentPorder.getPorderno());
					showAllPorders();
				}
			}
		});
		deleteButton.setEnabled(false);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		deleteButton.setBackground(new Color(255, 0, 0));
		deleteButton.setBounds(194, 256, 106, 38);
		panel_3.add(deleteButton);
		
		pordernoLabel = new JLabel("");
		pordernoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		pordernoLabel.setBounds(100, 58, 200, 38);
		panel_3.add(pordernoLabel);
		
		customernoLabel = new JLabel("");
		customernoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		customernoLabel.setBounds(100, 106, 200, 38);
		panel_3.add(customernoLabel);
		
		employeenoLabel = new JLabel("");
		employeenoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		employeenoLabel.setBounds(100, 154, 200, 38);
		panel_3.add(employeenoLabel);
		
		totalpriceLabel = new JLabel("");
		totalpriceLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		totalpriceLabel.setBounds(100, 202, 200, 38);
		panel_3.add(totalpriceLabel);
		
		showAllPorders();
	}
	private void showPordersInfo() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Porder porder : porders) {
			model.addRow(new Object[] {porder.getPorderno(),porder.getCustomerno(),porder.getEmployeeno(),porder.getTotalPrice(),porder.getLastModified()});
		}
		pordernoLabel.setText("");
		customernoLabel.setText("");
		employeenoLabel.setText("");
		totalpriceLabel.setText("");
		updateButton.setEnabled(false);
		deleteButton.setEnabled(false);
	}
	private void showAllPorders() {
		porders = porderServiceImpl.getAllPorders();
		showPordersInfo();
	}
	private void showPordersByCustomer() {
		if (customernoField.getText().matches("c[0-9]{3}")) {
			porders = porderServiceImpl.getPordersByCustomer(customernoField.getText());
			showPordersInfo();
		}
	}
	private void showPordersByEmployee() {
		if (employeenoField.getText().matches("e[0-9]{3}")) {
			porders = porderServiceImpl.getPordersByEmployee(employeenoField.getText());
			showPordersInfo();
		}
	}
	private void getCurrentPorder() {
		int row = table.getSelectedRow();
		currentPorder = porders.get(row);
		pordernoLabel.setText(currentPorder.getPorderno());
		customernoLabel.setText(currentPorder.getCustomerno());
		employeenoLabel.setText(currentPorder.getEmployeeno());
		totalpriceLabel.setText("$"+currentPorder.getTotalPrice());
		updateButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}
}
