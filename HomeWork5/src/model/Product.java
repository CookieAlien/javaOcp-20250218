package model;

public class Product {
	private int id;
	private String productno;
	private String productname;
	private int price;
	private String status;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productno, String productname, int price, String status) {
		super();
		this.productno = productno;
		this.productname = productname;
		this.price = price;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
