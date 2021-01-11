package com.project.broker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.broker.model.HouseDetails;

@Repository
public interface HouseDetailsRepository extends JpaRepository<HouseDetails, Long> {

}
