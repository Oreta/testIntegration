package com.ausecourse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ausecourse.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

}
