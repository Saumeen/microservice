package com.project.broker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.broker.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	public UserModel findByUsername(String username);
	
	
}
