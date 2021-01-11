package com.project.broker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.broker.model.IpLocationModel;

@Repository
public interface IpLocationRepository extends JpaRepository<IpLocationModel,Long> {

	
	public IpLocationModel findByIpAddressAndUri(String ipAddress,String uri);
}
