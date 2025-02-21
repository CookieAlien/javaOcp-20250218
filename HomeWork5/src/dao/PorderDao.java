package dao;

import java.util.List;

import model.Porder;

public interface PorderDao {
	//create
	void add(Porder order);
	//read
	List<Porder> selectAll();
	List<Porder> selectByCustomer(String customerno);
	List<Porder> selectByEmployee(String employeeno);
	List<Porder> selectByPorderno(String porderno);
	String SelectLastPorderno();
	//update
	void update(Porder order);
	//delete
	void delete(String porderno);
}
