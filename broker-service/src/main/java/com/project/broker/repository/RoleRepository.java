package com.project.broker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.broker.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel,Long>{

	public RoleModel findByRoleName(String roleName);
	
	
}
