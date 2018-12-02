package com.ausecourse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ausecourse.model.ListeCourse;
import com.ausecourse.model.Product;

@Repository
public interface ListeCourseRepository extends CrudRepository<ListeCourse, String> {

}
