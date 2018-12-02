package com.ausecourse.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ausecourse.dao.IProductDao;
import com.ausecourse.model.Product;
import com.ausecourse.repository.ProductRepository;

@Repository
public class ProductDaoImpl implements IProductDao {

	@Autowired
	ProductRepository repo;

	@Override
	public List<Product> findAll() {

		return (List<Product>) repo.findAll();
	}

	@Override
	public Product findById(int id) {

		return repo.findById("" + id).get();
	}

	@Override
	public Product save(Product product) {
		repo.save(product);
		return product;
	}

}
