package com.ausecourse.controller;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.tree.ExpandVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ausecourse.dao.IListeCourseDAO;
import com.ausecourse.dao.IOrderDAO;
import com.ausecourse.dao.IProductDao;
import com.ausecourse.dao.IUserDao;
import com.ausecourse.model.ListeCourse;
import com.ausecourse.model.Order;
import com.ausecourse.model.Product;
import com.ausecourse.model.User;

@RestController
@RequestMapping("/ListeCourse")
public class ListeCourseControlleur {
//routes

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IOrderDAO orderDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IListeCourseDAO listeCourseDao;

	@RequestMapping(value = "/getAllByIdClient", method = RequestMethod.GET)
	public List<ListeCourse> getAllByIdClient(@RequestBody String mail) throws Exception {
		List<ListeCourse> listeCourse = null;

		try {
			listeCourse= listeCourseDao.getAllByIdClient(mail);
		} catch (ExceptionInInitializerError e) {
			System.err.println(e.getCause()+e.getMessage());
			return null;

		}

		return listeCourse;

	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	public ListeCourse getById(@RequestBody String id) throws Exception {
		ListeCourse listeCourse = null;
		System.out.println("marcheee " + id);
		try {
			listeCourse= listeCourseDao.findById(id);
			return listeCourse ; 
		} catch (ExceptionInInitializerError e) {
			System.err.println(e.getCause()+e.getMessage());
			return null;

		}

		//return listeCourse;

	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestBody ListeCourse l) throws Exception {
		//List<ListeCourse> listeCourse = null;
		String rep = null;
		System.out.println("liste de coursee farine" + l.getMail());
//		HashMap<String, Integer> hm = new HashMap<>();
//		hm.put("pain", 1);
//		hm.put("confiture",2);
//		 ListeCourse l = new ListeCourse(null,"mailCli",hm);
		try {

			rep = listeCourseDao.save(l);
			//return rep ;
		} catch (ExceptionInInitializerError e) {
			System.err.println(e.getCause()+e.getMessage());
			return null;
		}

		return rep;

	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody ListeCourse l) throws Exception {
		List<ListeCourse> listeCourse = null;
		String rep = null;
		try {
			rep = listeCourseDao.save(l);
		} catch (ExceptionInInitializerError e) {
			System.err.println(e.getStackTrace());
			return null;

		}

		return rep;

	}
	
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity delete(@RequestBody String ListeCourseID) throws Exception {
		List<ListeCourse> listeCourse = null;
		String rep = null;
		try {
			listeCourseDao.delete(ListeCourseID);
		} catch (Exception e) {
			System.err.println(e.getCause()+e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity(HttpStatus.OK);


	}
	
	@RequestMapping(value = "/addToList", method = RequestMethod.POST)
	public ListeCourse addToList(HttpServletRequest request,
			@RequestBody HashMap<String,String> mapper) throws Exception {
			
		String listId = mapper.get("listId") ;
		String name = mapper.get("name");
		String qty = mapper.get("qty") ;
		
		
		ListeCourse listeCourse = this.listeCourseDao.findById(listId) ; 
		Product product = new Product() ;
		product.setNom(name);
		product.setPrix(Integer.parseInt(qty));
		this.productDao.save(product);
		//listeCourse.setListeCourse(new ArrayList<Product>());
		listeCourse.getListeCourse().add(product); 
		this.listeCourseDao.save(listeCourse); 
		return listeCourse ;
	}

}