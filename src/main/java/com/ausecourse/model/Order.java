package com.ausecourse.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
@RedisHash("Order")
public class Order implements Serializable{
	@Id
	String id;
	String clientID;
	String livreurId;
	ListeCourse listeCourse;

	OrderState orderState;
	


	public Order() {}
	
	public Order(String id, String clientID, String livreurId, ListeCourse listeCourse, OrderState orderState) {
		super();
		this.id = id;
		this.clientID = clientID;
		this.livreurId = livreurId;
		this.listeCourse = listeCourse;
		this.orderState = orderState;
	}
	public ListeCourse getListeCourse() {
		return listeCourse;
	}
	public void setListeCourse(ListeCourse listeCourse) {
		this.listeCourse = listeCourse;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getLivreurId() {
		return livreurId;
	}
	public void setLivreurId(String livreurId) {
		this.livreurId = livreurId;
	}

	public OrderState getOrderState() {
		return orderState;
	}
	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", clientID=" + clientID + ", livreurId=" + livreurId + ", listeCourse="
				+ listeCourse + ", orderState=" + orderState + "]";
	}

}
