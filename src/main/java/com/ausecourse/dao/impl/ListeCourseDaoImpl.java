package com.ausecourse.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ausecourse.dao.IListeCourse;
import com.ausecourse.model.ListeCourse;
import com.ausecourse.repository.ListeCourseRepository;

@Repository
public class ListeCourseDaoImpl implements IListeCourse {

	@Autowired
	ListeCourseRepository repo;


	@Override
	public List<ListeCourse> findAll() {
		return  (List<ListeCourse>) repo.findAll();
	}

	@Override
	public ListeCourse findById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public String save(ListeCourse list) {
		repo.save(list);
		return list.getId();

	}

}
