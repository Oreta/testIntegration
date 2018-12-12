package com.ausecourse.dao;

import java.util.List;

import com.ausecourse.model.ListeCourse;
import com.ausecourse.model.Order;
import com.ausecourse.model.User;

public interface IOrderDAO {
	
	Order getById(String id);
	List<Order> getAllOrderByIdClient(String idClient);

	void update(Order order);
	
	
	/* normalement a ne pas utilisée voir cancel */
	void delete(String id);
	/*
	 * pas sensée être utilisée
	 */
	void push(Order order);
	
	
	/*
	 * créer une commande 
	 */
	String createOrder(String idClient,ListeCourse listeCourse);
	
	/*
	 * retourne tous les livreurs pouvant effectuer cette commande.
	 */
	List<User> deliverer(String idClient, String idOrder); // retourne une listes de livreur pouvant la livrer. 
	
	/*
	 * permet d'indiqué quelle livreur à été choisit.
	 */
	void deliveredChoice(String idOrder, String idLiveur);
	
	void cancelOrder(String idOrder);
	
	/*
	 * étape de confirmation d'une commande de la part d'un client.
	 */
	
	void acceptOrder(String idCommande);
	
	void orderDone(String idCommande);
	
	/*
	 * Permet à un livreur d'inqiquer qu'une commande à été payée. 
	 */
	 
	void orderPayed(String idorder);
	
	void save(Order order) ; 
	
	List<Order> getAllOrders(); 
	
	 
	 
	
	
	
	

}
