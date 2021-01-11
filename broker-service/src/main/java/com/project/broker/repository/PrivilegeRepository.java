package com.project.broker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.broker.model.PrivilegeModel;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeModel, Long> {

	public PrivilegeModel findByPrivilageName(String name);
}
