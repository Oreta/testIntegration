package com.ausecourse.dao;

import java.util.List;

import com.ausecourse.model.ListeCourse;


public interface IListeCourse {
	public List<ListeCourse> findAll();
	public ListeCourse findById(String id);
	public String save(ListeCourse list);


}
