package com.ausecourse.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ausecourse.dao.IOrderDAO;
import com.ausecourse.dao.IUserDao;
import com.ausecourse.model.ListeCourse;
import com.ausecourse.model.Order;
import com.ausecourse.model.OrderState;
import com.ausecourse.model.User;
import com.ausecourse.repository.OrderRepository;
import com.ausecourse.repository.UserRepository;


@Service
public class OrderDaoImpl implements IOrderDAO {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	IUserDao userDAO;

	@Override
	public Order getById(String id) {

		return orderRepository.findById(id).get();
	}

	@Override
	public void push(Order order) {

		orderRepository.save(order);

	}

	@Override
	public void delete(String id) {
		Order order = orderRepository.findById(id).get();
		order.setOrderState(OrderState.CANCEL);
		orderRepository.save(order);

	}

	@Override
	public void update(Order order) {
		orderRepository.save(order);

	}
	
	@Override
	public String createOrder(String idClient,ListeCourse listeCourse) {
		// auto increment, see if better method
		
		return orderRepository.save(new Order(null,idClient, null, listeCourse, OrderState.CREATE)).getId();
		
	}

	@Override
	public List<User> deliverer(String idClient, String idOrder) {

		User client = userDAO.findByEmail(idClient);
		ArrayList<User> listAll = (ArrayList<User>) userDAO.findAll();
		ArrayList<User> listReturn = new ArrayList<User>();
		for (User user : listAll) {
			if (user.getCity().equals(client.getCity()) && user.isDeliverer()) { // todo change whit google map
																					// librairy.
				listReturn.add(user);
			}
		}

		return listReturn;

	}

	@Override
	public void deliveredChoice(String idOrder, String idLiveur) {

		Order order = orderRepository.findById(idOrder).get();
		order.setLivreurId(idLiveur);
		orderRepository.save(order);

	}
	

	@Override
	public List<Order> getAllOrderByIdClient(String idClient) {
	ArrayList<Order> listOrder	=(ArrayList<Order>) orderRepository.findAll();
	ArrayList<Order>  listeReturn= new ArrayList<Order>();
	for (Order order : listOrder) {
		if(order.getClientID().equals(idClient)) { // to change by get id after clean 
			listeReturn.add(order);
		}
		
	}
	return listeReturn;
	}


	@Override
	public void cancelOrder(String idOrder) {
		Order order = orderRepository.findById(idOrder).get();
		order.setOrderState(OrderState.CANCEL);
		orderRepository.save(order);

	}

	@Override
	public void acceptOrder(String idOrder) {
		Order order = orderRepository.findById(idOrder).get();
		order.setOrderState(OrderState.INPROGRESS);
		orderRepository.save(order);

	}

	@Override
	public void orderDone(String idOrder) {
		Order order = orderRepository.findById(idOrder).get();
		order.setOrderState(OrderState.DELIVRED);
		orderRepository.save(order);

	}

	@Override
	public void orderPayed(String idOrder) {
		Order order = orderRepository.findById(idOrder).get();
		order.setOrderState(OrderState.PAYED);
		orderRepository.save(order);

	}

	@Override
	public void save(Order order) {
		this.orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> target = new ArrayList<Order>(); 
		this.orderRepository.findAll().forEach(target::add) ;
		return target ;
	}



}
