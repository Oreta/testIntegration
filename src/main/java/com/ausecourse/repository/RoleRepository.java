package com.ausecourse.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.ausecourse.model.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>  {

}
