package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;
import model.Product;
import service.impl.ProductServiceImpl;
import util.FileTool;
import util.TitlePanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.util.ArrayUtil;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageProductUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Employee operator = (Employee) FileTool.load("Employee.txt");
	private ProductServiceImpl productServiceImpl = new ProductServiceImpl();
	private List<Product> products;
	private Product currentProduct;
	private JTable table;
	private JTextField addNameField;
	private JTextField updateNameField;
	private Map<String, String> statusMap;
	private Map<String, String> categoryMap;
	private String[] categories = {"children","decoration","furniture","food"};
	private String[] statuses = {"normal","sale","new_lowest","out_of_stock"};
	private JComboBox addCategoryBox;
	private JComboBox addStatusBox;
	private JComboBox updateCategoryBox;
	private JComboBox updateStatusBox;
	private JSpinner addPriceSpinner;
	private JSpinner updatePriceSpinner;
	private JLabel productnoLabel;
	private JButton updateButton;
	private JButton deleteButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProductUI frame = new ManageProductUI();
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
	public ManageProductUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 750, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		statusMap = new HashMap<String, String>();
		statusMap.put("normal", "");
		statusMap.put("sale", "特價中");
		statusMap.put("new_lowest", "再創新低");
		statusMap.put("out_of_stock", "缺貨中");
		categoryMap = new HashMap<String, String>();
		categoryMap.put("children", "兒童用品");
		categoryMap.put("decoration", "居家裝飾");
		categoryMap.put("furniture", "家具");
		categoryMap.put("food", "美食");
		
		TitlePanel titlePanel = new TitlePanel();
		GridBagLayout gridBagLayout = (GridBagLayout) titlePanel.getLayout();
		gridBagLayout.rowWeights = new double[]{1.0};
		gridBagLayout.rowHeights = new int[]{300};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.columnWidths = new int[]{150, 0, 0};
		titlePanel.setBounds(10, 10, 716, 66);
		contentPane.add(titlePanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(10, 86, 716, 55);
		contentPane.add(panel);
		
		JLabel titleLabel = new JLabel("商品管理");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		titleLabel.setBounds(10, 10, 137, 35);
		panel.add(titleLabel);
		
		JLabel opLabel = new JLabel("操作員：");
		opLabel.setHorizontalAlignment(SwingConstants.CENTER);
		opLabel.setForeground(Color.WHITE);
		opLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		opLabel.setBounds(226, 10, 201, 35);
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
		returnButton.setBounds(531, 10, 105, 35);
		panel.add(returnButton);
		
		JPanel shopPanel = new JPanel();
		shopPanel.setLayout(null);
		shopPanel.setBackground(Color.WHITE);
		shopPanel.setBounds(10, 151, 400, 502);
		contentPane.add(shopPanel);
		
		JLabel floorShopLabel = new JLabel("商品一覽");
		floorShopLabel.setHorizontalAlignment(SwingConstants.CENTER);
		floorShopLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		floorShopLabel.setBounds(10, 10, 380, 30);
		shopPanel.add(floorShopLabel);
		
		JButton reloadButton = new JButton("重新整理");
		reloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateShop();
			}
		});
		reloadButton.setForeground(Color.WHITE);
		reloadButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		reloadButton.setBackground(Color.BLACK);
		reloadButton.setBounds(144, 455, 114, 37);
		shopPanel.add(reloadButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 380, 300);
		shopPanel.add(scrollPane);
		
		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				Component component = super.prepareRenderer(renderer, row, column);
				if (!isRowSelected(row)) {
					if (getModel().getValueAt(row, 4).equals("特價中")){
						component.setForeground(Color.red);
					}else if (getModel().getValueAt(row, 4).equals("再創新低")) {
						component.setForeground(Color.blue);
					}else if (getModel().getValueAt(row, 4).equals("缺貨中")) {
						component.setForeground(Color.lightGray);
					}else {
						component.setForeground(Color.black);
					}
				}
				return component;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showSelectedProduct();
			}

		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, ""},
			},
			new String[] {
				"\u7522\u54C1\u7DE8\u865F", "\u7522\u54C1\u540D\u7A31", "\u50F9\u683C", "\u72C0\u614B", "\u985E\u5225"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(65);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		table.setRowHeight(24);
		
		JLabel selectCategoryLabel = new JLabel("類別：");
		selectCategoryLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		selectCategoryLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		selectCategoryLabel.setBounds(68, 363, 48, 23);
		shopPanel.add(selectCategoryLabel);
		
		JComboBox selectCategoryBox = new JComboBox();
		selectCategoryBox.setModel(new DefaultComboBoxModel(new String[] {"兒童用品", "居家裝飾", "家具", "美食"}));
		selectCategoryBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		selectCategoryBox.setBounds(116, 363, 90, 23);
		shopPanel.add(selectCategoryBox);
		
		JLabel selectStatusLabel = new JLabel("狀態：");
		selectStatusLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		selectStatusLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		selectStatusLabel.setBounds(68, 409, 48, 23);
		shopPanel.add(selectStatusLabel);
		
		JComboBox selectStatusBox = new JComboBox();
		selectStatusBox.setModel(new DefaultComboBoxModel(new String[] {"正常", "特價中", "再創新低", "缺貨中"}));
		selectStatusBox.setSelectedIndex(0);
		selectStatusBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		selectStatusBox.setBounds(116, 409, 90, 23);
		shopPanel.add(selectStatusBox);
		
		JButton selectCategotyButton = new JButton("依類別篩選");
		selectCategotyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShopByCategory(selectCategoryBox.getSelectedIndex());
			}
		});
		selectCategotyButton.setForeground(new Color(0, 0, 0));
		selectCategotyButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		selectCategotyButton.setBackground(new Color(255, 255, 255));
		selectCategotyButton.setBounds(216, 360, 114, 30);
		shopPanel.add(selectCategotyButton);
		
		JButton selectStatusButton = new JButton("依狀態篩選");
		selectStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShopByStatus(selectStatusBox.getSelectedIndex());
			}
		});
		selectStatusButton.setForeground(new Color(0, 0, 0));
		selectStatusButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		selectStatusButton.setBackground(new Color(255, 255, 255));
		selectStatusButton.setBounds(216, 407, 114, 30);
		shopPanel.add(selectStatusButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(420, 151, 306, 220);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel newProductLabel = new JLabel("新增商品");
		newProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newProductLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		newProductLabel.setBounds(10, 10, 296, 30);
		panel_1.add(newProductLabel);
		
		JLabel addNameLabel = new JLabel("商品名稱：");
		addNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addNameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		addNameLabel.setBounds(10, 50, 80, 23);
		panel_1.add(addNameLabel);
		
		addNameField = new JTextField();
		addNameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addNameField.setBounds(100, 50, 194, 23);
		panel_1.add(addNameField);
		addNameField.setColumns(10);
		
		JLabel addNameLabel_1 = new JLabel("價格：");
		addNameLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		addNameLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		addNameLabel_1.setBounds(10, 95, 80, 23);
		panel_1.add(addNameLabel_1);
		
		addPriceSpinner = new JSpinner();
		addPriceSpinner.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addPriceSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(100)));
		addPriceSpinner.setBounds(100, 95, 80, 24);
		panel_1.add(addPriceSpinner);
		
		JLabel addStatusLabel = new JLabel("狀態：");
		addStatusLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addStatusLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		addStatusLabel.setBounds(156, 140, 48, 23);
		panel_1.add(addStatusLabel);
		
		addStatusBox = new JComboBox();
		addStatusBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addStatusBox.setModel(new DefaultComboBoxModel(new String[] {"正常", "特價中", "再創新低", "缺貨中"}));
		addStatusBox.setSelectedIndex(0);
		addStatusBox.setBounds(204, 140, 90, 23);
		panel_1.add(addStatusBox);
		
		JButton addButton = new JButton("新增商品");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
		});
		addButton.setForeground(Color.WHITE);
		addButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		addButton.setBackground(new Color(0, 0, 200));
		addButton.setBounds(110, 175, 114, 37);
		panel_1.add(addButton);
		
		JLabel addCategoryLabel = new JLabel("類別：");
		addCategoryLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addCategoryLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		addCategoryLabel.setBounds(10, 140, 48, 23);
		panel_1.add(addCategoryLabel);
		
		addCategoryBox = new JComboBox();
		addCategoryBox.setModel(new DefaultComboBoxModel(new String[] {"兒童用品", "居家裝飾", "家具", "美食"}));
		addCategoryBox.setSelectedIndex(0);
		addCategoryBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		addCategoryBox.setBounds(58, 140, 90, 23);
		panel_1.add(addCategoryBox);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(420, 381, 306, 272);
		contentPane.add(panel_1_1);
		
		JLabel updateProductLabel = new JLabel("修改/刪除商品");
		updateProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateProductLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		updateProductLabel.setBounds(10, 10, 296, 30);
		panel_1_1.add(updateProductLabel);
		
		JLabel updateNameLabel = new JLabel("商品名稱：");
		updateNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		updateNameLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		updateNameLabel.setBounds(10, 102, 80, 23);
		panel_1_1.add(updateNameLabel);
		
		updateNameField = new JTextField();
		updateNameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		updateNameField.setColumns(10);
		updateNameField.setBounds(100, 102, 194, 23);
		panel_1_1.add(updateNameField);
		
		JLabel updatePriceLabel = new JLabel("價格：");
		updatePriceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		updatePriceLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		updatePriceLabel.setBounds(10, 147, 80, 23);
		panel_1_1.add(updatePriceLabel);
		
		updatePriceSpinner = new JSpinner();
		updatePriceSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(100)));
		updatePriceSpinner.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		updatePriceSpinner.setBounds(100, 147, 80, 24);
		panel_1_1.add(updatePriceSpinner);
		
		JLabel updateStatusLabel = new JLabel("狀態：");
		updateStatusLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		updateStatusLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		updateStatusLabel.setBounds(156, 190, 48, 23);
		panel_1_1.add(updateStatusLabel);
		
		updateStatusBox = new JComboBox();
		updateStatusBox.setModel(new DefaultComboBoxModel(new String[] {"正常", "特價中", "再創新低", "缺貨中"}));
		updateStatusBox.setSelectedIndex(0);
		updateStatusBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		updateStatusBox.setBounds(204, 190, 90, 23);
		panel_1_1.add(updateStatusBox);
		
		updateButton = new JButton("修改商品");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProduct();
			}
		});
		updateButton.setEnabled(false);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		updateButton.setBackground(new Color(0, 0, 200));
		updateButton.setBounds(40, 225, 114, 37);
		panel_1_1.add(updateButton);
		
		deleteButton = new JButton("刪除商品");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteProduct();
			}
		});
		deleteButton.setEnabled(false);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		deleteButton.setBackground(new Color(255, 0, 0));
		deleteButton.setBounds(182, 225, 114, 37);
		panel_1_1.add(deleteButton);
		
		JLabel updateproductLabel = new JLabel("商品編號：");
		updateproductLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		updateproductLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		updateproductLabel.setBounds(10, 54, 80, 23);
		panel_1_1.add(updateproductLabel);
		
		productnoLabel = new JLabel("");
		productnoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		productnoLabel.setBounds(100, 54, 194, 23);
		panel_1_1.add(productnoLabel);
		
		JLabel updateCategoryLabel = new JLabel("類別：");
		updateCategoryLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		updateCategoryLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		updateCategoryLabel.setBounds(10, 190, 48, 23);
		panel_1_1.add(updateCategoryLabel);
		
		updateCategoryBox = new JComboBox();
		updateCategoryBox.setModel(new DefaultComboBoxModel(new String[] {"兒童用品", "居家裝飾", "家具", "美食"}));
		updateCategoryBox.setSelectedIndex(0);
		updateCategoryBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		updateCategoryBox.setBounds(58, 190, 90, 23);
		panel_1_1.add(updateCategoryBox);
		populateShop();
	}
	private void populateShop() {
		products = productServiceImpl.getAllProducts();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Product product : products) {
			model.addRow(new Object[] {product.getProductno(),product.getProductname(),product.getPrice(),categoryMap.get(product.getCategory()),statusMap.get(product.getStatus())});
		}
		productnoLabel.setText("");
		updateNameField.setText("");
		updatePriceSpinner.setValue(0);
		updateButton.setEnabled(false);
		deleteButton.setEnabled(false);
	}
	private void showShopByCategory(int index) {
		if (index >= 0) {
			products = productServiceImpl.getProductsByCategory(categories[index]);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (Product product : products) {
				model.addRow(new Object[] {product.getProductno(),product.getProductname(),product.getPrice(),categoryMap.get(product.getCategory()),statusMap.get(product.getStatus())});
			}
		}
	}
	private void showShopByStatus(int index) {
		if (index >= 0) {
			products = productServiceImpl.getProductsByStatus(statuses[index]);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (Product product : products) {
				model.addRow(new Object[] {product.getProductno(),product.getProductname(),product.getPrice(),categoryMap.get(product.getCategory()),statusMap.get(product.getStatus())});
			}
		}
	}
	private void addProduct() {
		String productname = addNameField.getText();
		if (!productname.isBlank()) {
			int price = (int)addPriceSpinner.getValue();
			if (price>0) {
				int categoryindex = addCategoryBox.getSelectedIndex();
				if (categoryindex>=0) {
					int statusindex = addStatusBox.getSelectedIndex();
					if (statusindex>=0) {
						Product product = new Product();
						String productno = productServiceImpl.generateProductno();
						product.setProductno(productno);
						product.setProductname(productname);
						product.setPrice(price);
						product.setCategory(categories[categoryindex]);
						product.setStatus(statuses[statusindex]);
						productServiceImpl.addProduct(product);
						JOptionPane.showMessageDialog(contentPane, "新增商品成功！商品編號："+productno);
						addNameField.setText("");
						addPriceSpinner.setValue(0);
						populateShop();
					} else {
						JOptionPane.showMessageDialog(contentPane, "請選擇狀態！", "警告", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "請選擇類別！", "警告", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "價格需大於0！", "警告", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(contentPane, "請輸入商品名稱！", "警告", JOptionPane.WARNING_MESSAGE);
		}
	}
	private void showSelectedProduct() {
		int row = table.getSelectedRow();
		currentProduct = productServiceImpl.getProductByNo((String) table.getValueAt(row, 0));
		productnoLabel.setText(currentProduct.getProductno());
		updateNameField.setText(currentProduct.getProductname());
		updatePriceSpinner.setValue(currentProduct.getPrice());
		updateCategoryBox.setSelectedIndex(ArrayUtils.indexOf(categories, currentProduct.getCategory()));
		updateStatusBox.setSelectedIndex(ArrayUtils.indexOf(statuses, currentProduct.getStatus()));
		updateButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}
	private void updateProduct() {
		String productname = updateNameField.getText();
		if (!productname.isBlank()) {
			int price = (int)updatePriceSpinner.getValue();
			if (price>0) {
				int categoryindex = updateCategoryBox.getSelectedIndex();
				if (categoryindex>=0) {
					int statusindex = updateStatusBox.getSelectedIndex();
					if (statusindex>=0) {
						currentProduct.setProductname(productname);
						currentProduct.setPrice(price);
						currentProduct.setCategory(categories[categoryindex]);
						currentProduct.setStatus(statuses[statusindex]);
						productServiceImpl.updateProduct(currentProduct);
						JOptionPane.showMessageDialog(contentPane, "更新商品成功！");
						populateShop();
					} else {
						JOptionPane.showMessageDialog(contentPane, "請選擇狀態！", "警告", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "請選擇類別！", "警告", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "價格需大於0！", "警告", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(contentPane, "請輸入商品名稱！", "警告", JOptionPane.WARNING_MESSAGE);
		}
	}
	private void deleteProduct() {
		int returnval = JOptionPane.showConfirmDialog(contentPane, "確認要刪除產品編號:"+currentProduct.getProductno()+"嗎？此動作無法復原！", "資訊", JOptionPane.YES_NO_OPTION);
		if (returnval == JOptionPane.YES_OPTION) {
			productServiceImpl.deleteProduct(currentProduct.getProductno());
			populateShop();
		}
	}
}
