package com.project.broker.service.impl;

import static com.project.broker.constant.Constant.ADD;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.project.broker.dto.HouseDetailsDto;
import com.project.broker.exception.CustomRuntimeException;
import com.project.broker.model.HouseDetails;
import com.project.broker.model.UserModel;
import com.project.broker.repository.HouseDetailsRepository;
import com.project.broker.repository.UserRepository;
import com.project.broker.service.HouseDetailService;
import com.project.broker.service.RolePrivilageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * House details service
 * 
 * @author saumeen
 *
 */
@Component
@Slf4j
public class HouseDetailServiceImpl implements HouseDetailService {

	@Autowired
	private HouseDetailsRepository houseDetailsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolePrivilageService rolePrivilageService;

	@Override
	@CacheEvict(cacheNames = "house-detail",allEntries = true)
	public void addHouseDetails(HouseDetailsDto housDetailsDto) {

		UserModel userModel = userRepository.findByUsername(housDetailsDto.getUsername().toUpperCase());

		if (userModel != null && rolePrivilageService
				.getPrivilegeByRole(rolePrivilageService.getUserRole(userModel.getUsername())).contains(ADD)) {
			HouseDetails houseDetails = new HouseDetails();
			houseDetails.setAddress(housDetailsDto.getAddress());
			houseDetails.setCity(housDetailsDto.getCity());
			houseDetails.setState(housDetailsDto.getState());
			houseDetails.setCountry(housDetailsDto.getCountry());
			houseDetails.setUserModel(userModel);

			houseDetailsRepository.save(houseDetails);

		} else {
			throw new CustomRuntimeException("Can not add, Do not have sufficient privilege!!");
		}

	}

	@Override
	@Cacheable("house-detail")
	public List<HouseDetailsDto> getHouseDetailService(String username) {
		UserModel userModel = userRepository.findByUsername(username);
		log.info("User model {}",userModel.getUsername());
		
		List<HouseDetails> houseDetailList = houseDetailsRepository.findByUserModel(userModel);
		log.info("{} :::: {}",houseDetailList.size(), houseDetailList.toArray());
		List<HouseDetailsDto> houseDetailsDtos = new ArrayList<>();
		houseDetailList.forEach(model->{
			HouseDetailsDto temp = new HouseDetailsDto();
			temp.setCity(model.getCity());
			temp.setCountry(model.getCountry());
			temp.setState(model.getState());
			temp.setAddress(model.getAddress());
			houseDetailsDtos.add(temp);
		});
		
		return houseDetailsDtos;
	}

	

}
