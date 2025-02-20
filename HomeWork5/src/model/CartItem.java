package model;

import java.io.Serializable;

public class CartItem implements Serializable{
	private String productno;
	private String productname;
	private int price;
	private int amount;
	private int sum;
	public CartItem(String productno, String productname, int price, int amount) {
		super();
		this.productno = productno;
		this.productname = productname;
		this.price = price;
		this.amount = amount;
		this.sum = price * amount;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
		this.sum = price * amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
		this.sum = price * amount;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
		
	}
	
}
