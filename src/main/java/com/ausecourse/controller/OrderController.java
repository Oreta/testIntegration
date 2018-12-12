package com.ausecourse.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ausecourse.dao.IListeCourseDAO;
import com.ausecourse.dao.IOrderDAO;
import com.ausecourse.dao.IUserDao;
import com.ausecourse.model.ListeCourse;
import com.ausecourse.model.Order;
import com.ausecourse.model.User;
/* dans un cas classique, ordre des principaux  ctrl a appelée:
 * createOrder
 * deliverer
 * deliveredChoice
 * acceptOrder
 * orderDone
 * orderPayed
 */
@RestController
@RequestMapping("/order")
public class OrderController {
//routes

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IOrderDAO orderDao;
	@Autowired
	private IListeCourseDAO listeCourseDao;
//	@RequestMapping(value = "/push", method = RequestMethod.POST)
//	public ResponseEntity push(@RequestBody Order order) throws Exception {
//
//		try {
//			orderDao.push(order);
//		} catch (Exception e) {
//			System.err.println(e.getStackTrace());
//			return new ResponseEntity("push faill", HttpStatus.NOT_ACCEPTABLE);
//		}
//
//		return new ResponseEntity("push Success", HttpStatus.OK);
//
//	}

//	@RequestMapping(value = "/update", method = RequestMethod.PUT)
//	public ResponseEntity update(@RequestBody Order order) throws Exception {
//
//		try {
//			orderDao.push(order);
//		} catch (Exception e) {
//			System.err.println(e.getStackTrace());
//			return new ResponseEntity("uptdate faill", HttpStatus.NOT_ACCEPTABLE);
//		}
//
//		return new ResponseEntity("uptdate Success", HttpStatus.OK);
//
//	}

	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public Order getById(@RequestBody String id) throws Exception {
		Order order = null;
		try {
			order = orderDao.getById(id);
		} catch (ExceptionInInitializerError e) {
			System.err.println(e.getStackTrace());
			return null;
		}

		return order;

	}

	@RequestMapping(value = "/getAllByIdClient", method = RequestMethod.POST)
	public List<Order> getAllByIdClient(@RequestBody String mail) {
		List<Order> order = null;
		try {
			order = orderDao.getAllOrderByIdClient(mail);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return null;
		}

		return order;

	}

//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public ResponseEntity delete(int id) throws Exception {
//
//		try {
//			orderDao.delete(id);
//		} catch (Exception e) {
//			System.err.println(e.getStackTrace());
//			return new ResponseEntity("delete faill", HttpStatus.NOT_ACCEPTABLE);
//		}
//
//		return new ResponseEntity("delete Success", HttpStatus.OK);
//
//	}

	@RequestMapping(value = "/deliverer", method = RequestMethod.GET)
	public List<User> deliverer(@RequestBody HashMap<String, String> params) throws Exception {
		String idClient= params.get("idClient"); // mail
		String idOrder= (params.get("idOrder"));
		List<User> users = null;
		
		try {
			users = orderDao.deliverer(idClient, idOrder);
		} catch (Exception e) {
			System.err.println(e.getMessage()+" "+e.getCause());

		}

		return users;

	}
	// hashmap 
//	mail->"mail"
//	listeCourse->ListeCourseJSon
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder(@RequestBody ListeCourse l) throws Exception {
		String idOrder = null;
		System.out.println(l);
		
		try {
			idOrder = orderDao.createOrder(l.getMail(),l);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());

		}

		return idOrder;

	}

	@RequestMapping(value = "/deliveredChoice", method = RequestMethod.PUT)
	public ResponseEntity deliveredChoice(@RequestBody HashMap<String, Object> hm) throws Exception {
		String mailLivreur= (String) hm.get("mailLivreur");
		String idOrder =  (String) hm.get("idOrder");
		List<User> users = null;

		try {
			orderDao.deliveredChoice(idOrder, mailLivreur);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return new ResponseEntity("delete faill", HttpStatus.NOT_ACCEPTABLE);

		}

		return new ResponseEntity("livreur choosed  Success", HttpStatus.OK);

	}

	@RequestMapping(value = "/acceptOrder", method = RequestMethod.POST)
	public ResponseEntity acceptOrder(@RequestBody String idOrder) throws Exception {
		List<User> users = null;

		try {
			orderDao.acceptOrder(idOrder);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return new ResponseEntity("acceptOrder faill", HttpStatus.NOT_ACCEPTABLE);

		}

		return new ResponseEntity("acceptOrder Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/orderDone", method = RequestMethod.POST)
	public ResponseEntity orderDone(@RequestBody String idOrder) throws Exception {
		List<User> users = null;

		try {
			orderDao.orderDone(idOrder);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return new ResponseEntity("orderDone faill", HttpStatus.NOT_ACCEPTABLE);

		}

		return new ResponseEntity("orderDone Success", HttpStatus.OK);

	}

	@RequestMapping(value = "/cancelOrder", method = RequestMethod.PUT)
	public ResponseEntity cancelOrder(@RequestBody String idOrder) throws Exception {
		List<User> users = null;

		try {
			orderDao.cancelOrder(idOrder);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return new ResponseEntity("cancelOrder faill", HttpStatus.NOT_ACCEPTABLE);

		}

		return new ResponseEntity("cancelOrder Success", HttpStatus.OK);

	}

	@RequestMapping(value = "/orderPayed", method = RequestMethod.POST)
	public ResponseEntity orderPayed(@RequestBody String idOrder) throws Exception {
		List<User> users = null;

		try {
			orderDao.orderPayed(idOrder);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			return new ResponseEntity("orderPayed faill", HttpStatus.NOT_ACCEPTABLE);

		}

		return new ResponseEntity("orderPayed Success", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getOrderByListId", method = RequestMethod.POST)
	public Order getOrderByListId(@RequestBody String listId) throws Exception {
		
		List<Order> allOrders = this.orderDao.getAllOrders() ; 
		for(int i=0;i<allOrders.size();i++) {
			if(allOrders.get(i).getListeCourse().getId().equals(listId)) {
				System.out.println("get order by list id --- "  +  allOrders.get(i).getId());
				return allOrders.get(i);
			}
			System.out.println("get order by list id --- hiiii " + allOrders.get(i).getListeCourse().getId() );
		}
		System.out.println("get order by list id --- oopss" );

		return null ; 

	}
	
	@RequestMapping(value = "/getListByOrderId", method = RequestMethod.POST)
	public ListeCourse getListByOrderId(@RequestBody String orderId) throws Exception {
		
		Order order = this.orderDao.getById(orderId);
		return order.getListeCourse() ;
	}


}