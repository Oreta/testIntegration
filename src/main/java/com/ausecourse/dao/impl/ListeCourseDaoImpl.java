package com.ausecourse.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ausecourse.dao.IListeCourseDAO;
import com.ausecourse.model.ListeCourse;
import com.ausecourse.repository.ListeCourseRepository;

@Repository
public class ListeCourseDaoImpl implements IListeCourseDAO {

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

	@Override
	public List<ListeCourse> getAllByIdClient(String mail) {
		ArrayList<ListeCourse> rep= new ArrayList<ListeCourse>();
		ArrayList<ListeCourse> all = (ArrayList) repo.findAll();
		for (ListeCourse listeCourse : all) {
			if(listeCourse.getMail().equals(mail)) {
				rep.add(listeCourse);
			}
		}
		return rep;
		
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	

}
