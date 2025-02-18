package model;

public class OrderItem {
	private String porderid;
	private String productid;
	private int amount;
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	public OrderItem(String porderid, String productid, int amount) {
		super();
		this.porderid = porderid;
		this.productid = productid;
		this.amount = amount;
	}
	public String getPorderid() {
		return porderid;
	}
	public void setPorderid(String porderid) {
		this.porderid = porderid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
