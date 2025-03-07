package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.impl.OrderDetailDaoImpl;
import dao.impl.PorderDaoImpl;
import model.CartItem;
import model.OrderItem;
import model.Porder;
import service.PorderService;

public class PorderServiceImpl implements PorderService {
	private static PorderDaoImpl porderDaoImpl = new PorderDaoImpl();
	private static OrderDetailDaoImpl orderDetailDaoImpl = new OrderDetailDaoImpl();
	@Override
	public void createOrder(Porder porder, List<CartItem> items) {
		if (porder.getTotalPrice()>0 && items.size()>0) {
			porderDaoImpl.add(porder);
			for (CartItem cartItem : items) {
				OrderItem orderItem = new OrderItem(porder.getPorderno(), cartItem.getProductno(),cartItem.getProductname(), cartItem.getAmount(),cartItem.getSum());
				orderDetailDaoImpl.add(orderItem);
			}
		}

	}

	@Override
	public List<Porder> getAllPorders() {
		return porderDaoImpl.selectAll();
	}

	@Override
	public List<Porder> getPordersByCustomer(String customerno) {
		// TODO Auto-generated method stub
		return porderDaoImpl.selectByCustomer(customerno);
	}

	@Override
	public List<Porder> getPordersByEmployee(String Employeeno) {
		// TODO Auto-generated method stub
		return porderDaoImpl.selectByEmployee(Employeeno);
	}

	@Override
	public List<OrderItem> getOrderItems(String porderno) {
		// TODO Auto-generated method stub
		return orderDetailDaoImpl.selectByOrder(porderno);
	}


	@Override
	public List<CartItem> getItemsToCart(String porderno) {
		List<CartItem> shoppingList = new ArrayList<CartItem>();
		List<OrderItem> orderItems = getOrderItems(porderno);
		for (OrderItem orderItem : orderItems) {
			CartItem cartItem = new CartItem(orderItem.getProductno(), orderItem.getProductname(), orderItem.getSum()/orderItem.getAmount() ,orderItem.getAmount());
			shoppingList.add(cartItem);
		}
		return shoppingList;
	}
	
	@Override
	public void updateOrder(Porder porder) {
		if (porder.getTotalPrice()>0) {
			porderDaoImpl.update(porder);
		}

	}

	@Override
	public void updateOrder(Porder porder, List<CartItem> items) {
		if (porder.getTotalPrice()>0) {
			porderDaoImpl.update(porder);
			orderDetailDaoImpl.delete(porder.getPorderno());
			for (CartItem cartItem : items) {
				OrderItem orderItem = new OrderItem(porder.getPorderno(), cartItem.getProductno(),cartItem.getProductname(), cartItem.getAmount(),cartItem.getSum());
				orderDetailDaoImpl.add(orderItem);
			}
		}
	}

	@Override
	public void updateOrderitem(OrderItem item) {
		orderDetailDaoImpl.update(item);

	}

	@Override
	public void deleteOrder(String porderno) {
		porderDaoImpl.delete(porderno);

	}

	@Override
	public String generateOrderno() {
		String lastno = porderDaoImpl.SelectLastPorderno();
		if (lastno != null) {
			int num = Integer.parseInt(lastno.substring(1)) + 1;
			return String.format("o%03d", num);
		} else {
			return "o001";
		}
	}

	@Override
	public Porder getPorderByPorderno(String porderno) {
		Porder porder = null;
		List<Porder> list = porderDaoImpl.selectByPorderno(porderno);
		if (list.size()>0) {
			porder = list.get(0);
		}
		return porder;
	}


}
