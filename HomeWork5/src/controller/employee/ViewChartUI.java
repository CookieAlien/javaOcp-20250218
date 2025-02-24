package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import model.Employee;
import util.DBConnection;
import util.FileTool;
import util.TitlePanel;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewChartUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Connection connection = DBConnection.getConnection();
	private JPanel contentPane;
	private Employee operator = (Employee) FileTool.load("Employee.txt");
	private static HashMap<String, String> categoryMap;
	private static StandardChartTheme standardChartTheme = new StandardChartTheme("my theme");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewChartUI frame = new ViewChartUI();
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
	public ViewChartUI() {
		setTitle("敗家家居");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setChartTheme();

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
		
		JLabel titleLabel = new JLabel("報表查詢");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		titleLabel.setBounds(10, 10, 137, 35);
		panel.add(titleLabel);
		
		JLabel opLabel = new JLabel();
		opLabel.setText("操作員："+operator.getName());
		opLabel.setHorizontalAlignment(SwingConstants.CENTER);
		opLabel.setForeground(Color.WHITE);
		opLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		opLabel.setBounds(218, 10, 201, 35);
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
		returnButton.setBounds(522, 10, 105, 35);
		panel.add(returnButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		tabbedPane.setBounds(10, 150, 716, 403);
		contentPane.add(tabbedPane);
		

		JFreeChart barChart = createBarChart();
		JFreeChart pieChart = createPieChart();
		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:${1}"));
		tabbedPane.addTab("員工銷售資料", new ChartPanel(barChart));
		tabbedPane.addTab("商品銷售資料", new ChartPanel(pieChart));
		
		
	}
	private static void setChartTheme() {
		standardChartTheme.setExtraLargeFont(new Font("微軟正黑體", Font.BOLD, 24));
		standardChartTheme.setLargeFont(new Font("微軟正黑體", Font.PLAIN, 16));
		standardChartTheme.setRegularFont(new Font("微軟正黑體", Font.PLAIN, 16));
		ChartFactory.setChartTheme(standardChartTheme);
	}
	private static JFreeChart createBarChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql = "select * from salesbyemployee";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String employeename = resultSet.getString("employeename");
				int sales = resultSet.getInt("totalsales");
				dataset.addValue(sales, "銷售額", employeename);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ChartFactory.createBarChart(
				"員工銷售圖表", 
				"員工姓名",
				"銷售額", 
				dataset, 
				PlotOrientation.VERTICAL, 
				true, 
				true, 
				false);
	}
	private static JFreeChart createPieChart() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql = "select * from salesbycategory";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int sales = resultSet.getInt("totalsales");
				String category = categoryMap.get(resultSet.getString("category"));
				dataset.setValue(category, sales);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ChartFactory.createPieChart("商品銷售圖表", dataset);
	}
}
