package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Porder implements Serializable{
	private int id;
	private String porderno;
	private String customerno;
	private String employeeno;
	private int totalPrice;
	private LocalDateTime lastModified;
	public Porder() {
		// TODO Auto-generated constructor stub
	}
	
	public Porder(String porderno, String customerno, String employeeno, int totalPrice) {
		super();
		this.porderno = porderno;
		this.customerno = customerno;
		this.employeeno = employeeno;
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPorderno() {
		return porderno;
	}
	public void setPorderno(String porderno) {
		this.porderno = porderno;
	}
	public String getCustomerno() {
		return customerno;
	}
	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}
	public String getEmployeeno() {
		return employeeno;
	}
	public void setEmployeeno(String employeeno) {
		this.employeeno = employeeno;
	}
	public LocalDateTime getLastModified() {
		return lastModified;
	}
	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}
	
}
