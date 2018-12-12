package com.ausecourse.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Product")
public class Product implements Serializable {

	@Id
	private String id;
	private String nom;
	private int qty;
	

	public Product() {
	}
	
	public Product(String id, String nom, int prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.qty = prix;
	}
	
	
	public String getId() {
		return id;
	}
	
	public String getIdByString() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return qty;
	}

	public void setPrix(int prix) {
		this.qty = prix;
	}

}
