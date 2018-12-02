package com.ausecourse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ausecourse.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
