package com.ausecourse.model;

import java.io.Serializable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@RedisHash("ListeCourse")
public class ListeCourse implements Serializable{

    @Id
	String id;
    String mail ; // représentation Unique du client
    HashMap<String, Integer> liste;
    
    List<Product> listeCourse = new ArrayList<Product>(); 

	public List<Product> getListeCourse() {
		return listeCourse;
	}

	public void setListeCourse(List<Product> listeCourse) {
		this.listeCourse = listeCourse;
	}

	public ListeCourse(String id,HashMap map) {
		super();
		this.liste = map;
	}

	public ListeCourse(String id) {
		super();
		this.id= id;
		this.liste = new HashMap<>();
	}
	public ListeCourse() {
		super();
		this.id= id;
		this.liste = new HashMap<>();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String,Integer> getList(){
		return liste;
	}

	@Override
	public String toString() {
		return "ListeCourse [id=" + id + ", mail=" + mail + ", liste=" + liste + "]";
	}

	public ListeCourse(String id, String mail, HashMap<String, Integer> liste) {
		super();
		this.id = id;
		this.mail = mail;
		this.liste = liste;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void add(String p, int quantite) {
		liste.put(p, quantite);
	}

	
	
}

