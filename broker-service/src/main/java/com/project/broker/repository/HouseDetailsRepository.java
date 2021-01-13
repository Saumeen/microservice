package com.project.broker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.broker.model.HouseDetails;
import com.project.broker.model.UserModel;

@Repository
public interface HouseDetailsRepository extends JpaRepository<HouseDetails, Long> {

	public List<HouseDetails> findByUserModel(UserModel usermodel);
}
