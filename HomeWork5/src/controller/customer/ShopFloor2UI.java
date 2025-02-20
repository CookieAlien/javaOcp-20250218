package controller.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.FileTool;
import util.TitlePanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.mysql.cj.xdevapi.Table;

import dao.impl.ProductDaoImpl;
import model.CartItem;
import model.Product;
import service.impl.ProductServiceImpl;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopFloor2UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable shopTable;
	private JTable CartTable;
	private static ProductServiceImpl productServiceImpl = new ProductServiceImpl();
	private List<Product> products;
	private List<CartItem> shoppingList;
	private JLabel sumLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopFloor2UI frame = new ShopFloor2UI();
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
	public ShopFloor2UI() {
		setTitle("敗家家居");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 700, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitlePanel titlePanel = new TitlePanel();
		titlePanel.setBounds(10, 10, 666, 66);
		contentPane.add(titlePanel);
		
		JPanel floorInfoPanel = new JPanel();
		floorInfoPanel.setBackground(new Color(255, 255, 255));
		floorInfoPanel.setBounds(10, 86, 666, 104);
		contentPane.add(floorInfoPanel);
		floorInfoPanel.setLayout(null);
		
		JLabel floorTitleLabel = new JLabel("歡迎來到2樓自助倉儲區！");
		floorTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		floorTitleLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		floorTitleLabel.setBounds(143, 10, 403, 30);
		floorInfoPanel.add(floorTitleLabel);
		
		JLabel lblNewLabel_1 = new JLabel("記得從這裡拿取你要購買的大型家具！");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(41, 50, 596, 30);
		floorInfoPanel.add(lblNewLabel_1);
		
		JPanel shopPanel = new JPanel();
		shopPanel.setBackground(new Color(255, 255, 255));
		shopPanel.setBounds(10, 200, 340, 444);
		contentPane.add(shopPanel);
		shopPanel.setLayout(null);
		
		JLabel floorShopLabel = new JLabel("商品一覽");
		floorShopLabel.setHorizontalAlignment(SwingConstants.CENTER);
		floorShopLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		floorShopLabel.setBounds(10, 10, 320, 30);
		shopPanel.add(floorShopLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		spinner.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		spinner.setBounds(132, 374, 64, 37);
		shopPanel.add(spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 50, 300, 313);
		shopPanel.add(scrollPane);
		
		shopTable = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				Component component = super.prepareRenderer(renderer, row, column);
				if (!isRowSelected(row)) {
					if (getModel().getValueAt(row, 3).equals("特價中")){
						component.setForeground(Color.red);
					}else if (getModel().getValueAt(row, 3).equals("再創新低")) {
						component.setForeground(Color.blue);
					}else if (getModel().getValueAt(row, 3).equals("缺貨中")) {
						component.setForeground(Color.lightGray);
					}else {
						component.setForeground(Color.black);
					}
				}
				return component;
			}
		};
		shopTable.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		shopTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"\u7DE8\u865F", "\u5546\u54C1\u540D\u7A31", "\u50F9\u683C", "\u5099\u8A3B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		shopTable.getColumnModel().getColumn(0).setResizable(false);
		shopTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		shopTable.getColumnModel().getColumn(0).setMinWidth(10);
		shopTable.getColumnModel().getColumn(1).setResizable(false);
		shopTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		shopTable.getColumnModel().getColumn(1).setMinWidth(25);
		shopTable.getColumnModel().getColumn(2).setResizable(false);
		shopTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		shopTable.getColumnModel().getColumn(3).setResizable(false);
		shopTable.setRowHeight(24);
		scrollPane.setViewportView(shopTable);
		
		JLabel amountLabel = new JLabel("數量：");
		amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		amountLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
		amountLabel.setBounds(10, 375, 111, 30);
		shopPanel.add(amountLabel);
		
		JButton addToCartButton = new JButton("加入購物車");
		addToCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = shopTable.getSelectedRow();
				if ((int)spinner.getValue()>0 && row>=0) {
					if (shopTable.getModel().getValueAt(row, 3).equals("缺貨中")) {
						JOptionPane.showMessageDialog(contentPane, "很抱歉，此商品缺貨中！");
					}else {
						addToCart((int)spinner.getValue(), row);
					}
				}
			}
		});
		addToCartButton.setBackground(new Color(0, 0, 0));
		addToCartButton.setForeground(new Color(255, 255, 255));
		addToCartButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		addToCartButton.setBounds(206, 374, 114, 37);
		shopPanel.add(addToCartButton);
		
		JPanel cartPanel = new JPanel();
		cartPanel.setBackground(new Color(255, 255, 0));
		cartPanel.setBounds(360, 200, 316, 444);
		contentPane.add(cartPanel);
		cartPanel.setLayout(null);
		
		JLabel floorCartLabel = new JLabel("購物車");
		floorCartLabel.setHorizontalAlignment(SwingConstants.CENTER);
		floorCartLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		floorCartLabel.setBounds(10, 10, 296, 30);
		cartPanel.add(floorCartLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 50, 273, 269);
		cartPanel.add(scrollPane_1);
		
		CartTable = new JTable();
		CartTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"\u7DE8\u865F", "\u5546\u54C1\u540D\u7A31", "\u6578\u91CF", "\u5C0F\u8A08"
			}
		));
		CartTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		CartTable.getColumnModel().getColumn(0).setMinWidth(10);
		CartTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		CartTable.getColumnModel().getColumn(1).setMinWidth(25);
		CartTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		CartTable.getColumnModel().getColumn(2).setMinWidth(10);
		CartTable.getColumnModel().getColumn(3).setPreferredWidth(70);
		CartTable.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		CartTable.setRowHeight(24);
		
		scrollPane_1.setViewportView(CartTable);
		
		JButton removeButton = new JButton("移除選擇的項目");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CartTable.getSelectedRow()>=0) {
					removefromCart(CartTable.getSelectedRow());
				}
			}
		});
		removeButton.setForeground(Color.WHITE);
		removeButton.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		removeButton.setBackground(Color.RED);
		removeButton.setBounds(68, 391, 186, 43);
		cartPanel.add(removeButton);
		
		sumLabel = new JLabel("總額：");
		sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sumLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
		sumLabel.setBounds(68, 329, 186, 30);
		cartPanel.add(sumLabel);
		
		JButton returnButton = new JButton("上樓");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileTool.save(shoppingList, "ShoppingList.txt");
				new ShopFloor3UI().setVisible(true);
				dispose();
			}
		});
		returnButton.setForeground(new Color(255, 255, 255));
		returnButton.setBackground(new Color(0, 0, 200));
		returnButton.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		returnButton.setBounds(108, 654, 124, 49);
		contentPane.add(returnButton);
		
		JButton nextButton = new JButton("下樓");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileTool.save(shoppingList, "ShoppingList.txt");
				new ShopFloor1UI().setVisible(true);
				dispose();
			}
		});
		nextButton.setForeground(Color.WHITE);
		nextButton.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		nextButton.setBackground(new Color(0, 0, 200));
		nextButton.setBounds(464, 654, 124, 49);
		contentPane.add(nextButton);
		
		populateShop();
		shoppingList = (List<CartItem>) FileTool.load("ShoppingList.txt");
		if (shoppingList==null) {
			shoppingList = new ArrayList<CartItem>();
		}
		displayCart();
	}
	
	
	private void populateShop() {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("normal", "");
		statusMap.put("sale", "特價中");
		statusMap.put("new_lowest", "再創新低");
		statusMap.put("out_of_stock", "缺貨中");
		products = productServiceImpl.getProductsByCategory("furniture");
		DefaultTableModel model = (DefaultTableModel) shopTable.getModel();
		model.setRowCount(0);
		for (Product product : products) {
			model.addRow(new Object[] {product.getProductno(),product.getProductname(),product.getPrice(),statusMap.get(product.getStatus())});
		}
		
	}
	private void addToCart(int amount, int row) {
		DefaultTableModel model = (DefaultTableModel) shopTable.getModel();
		boolean isInCart = false;
		for (CartItem cartItem : shoppingList) {
			if (cartItem.getProductno().equals((String) model.getValueAt(row, 0))) {
				isInCart = true;
				cartItem.setAmount(cartItem.getAmount()+amount);
			}
		}
		if(!isInCart) {
			CartItem item = new CartItem((String) model.getValueAt(row, 0), (String) model.getValueAt(row, 1), (int) model.getValueAt(row, 2), amount);
			shoppingList.add(item);
		}
		displayCart();
	}
	private void displayCart() {
		DefaultTableModel model = (DefaultTableModel) CartTable.getModel();
		model.setRowCount(0);
		int sum = 0;
		for (CartItem item : shoppingList) {
			model.addRow(new Object[] {item.getProductno(),item.getProductname(),item.getAmount(),item.getSum()});
			sum += item.getSum();
		}
		sumLabel.setText("總額：$"+sum);
	}
	private void removefromCart(int row) {
		shoppingList.remove(row);
		displayCart();
	}
}
