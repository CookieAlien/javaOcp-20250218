package model;

public class OrderItem {
	private int orderDetailsID;
	private String porderno;
	private String productno;
	private int amount;
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	public OrderItem(String porderno, String productno, int amount) {
		super();
		this.porderno = porderno;
		this.productno = productno;
		this.amount = amount;
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
	
	
}
