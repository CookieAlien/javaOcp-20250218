package model;

public class OrderItem {
	private int orderDetailsID;
	private String porderno;
	private String productno;
	private int amount;
	private int sum;
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	public OrderItem(String porderno, String productno, int amount, int sum) {
		super();
		this.porderno = porderno;
		this.productno = productno;
		this.amount = amount;
		this.setSum(sum);
	}
	public int getOrderDetailsID() {
		return orderDetailsID;
	}
	public void setOrderDetailsID(int orderDetailsID) {
		this.orderDetailsID = orderDetailsID;
	}
	public String getPorderno() {
		return porderno;
	}
	public void setPorderno(String porderno) {
		this.porderno = porderno;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
}
