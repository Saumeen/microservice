package com.project.broker.service;

import java.util.List;

import com.project.broker.dto.HouseDetailsDto;

public interface HouseDetailService {
	
	public void addHouseDetails(HouseDetailsDto housDetailsDto);
	
	public List<HouseDetailsDto> getHouseDetailService(String username);

}
