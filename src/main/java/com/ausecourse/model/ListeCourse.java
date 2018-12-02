package com.ausecourse.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@RedisHash("ListeCourse")
public class ListeCourse implements Serializable{

    @Id
	String id;
 
   HashMap<Product, Integer> liste;

	public ListeCourse(String id,HashMap map) {
		super();
		this.liste = map;
	}

	public ListeCourse(String id) {
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

	public HashMap<Product,Integer> getList(){
		return liste;
	}
	
	
}

