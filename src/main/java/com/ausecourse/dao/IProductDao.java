package com.ausecourse.dao;

import java.util.List;

import com.ausecourse.model.Product;

public interface IProductDao {
	public List<Product> findAll();
	
	public Product findById(int id);
	public Product save(Product product);

}
