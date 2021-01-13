package com.project.broker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.broker.dto.RoleDto;
import com.project.broker.exception.CustomRuntimeException;
import com.project.broker.model.PrivilegeModel;
import com.project.broker.model.RoleModel;
import com.project.broker.model.UserModel;
import com.project.broker.model.UserRolePrivilegeModel;
import com.project.broker.repository.PrivilegeRepository;
import com.project.broker.repository.RoleRepository;
import com.project.broker.repository.UserRepository;
import com.project.broker.repository.UserRolePrivilegeRepository;
import com.project.broker.service.RolePrivilageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Role and privilage service impl
 * 
 * @author saumeen
 *
 */
@Service
@Slf4j
public class RolePrivilageServiceImpl implements RolePrivilageService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepo;

	@Autowired
	private UserRolePrivilegeRepository userRolePrivilegeRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRolePrivilegeRepository userRolePrivilegeRepository;

	@Override
	@CacheEvict(value = {"role-privilege","user-role"},allEntries = true)
	public void addRole(RoleDto roleDto) {

		RoleModel roleModel = roleRepository.findByRoleName(roleDto.getRoleName());
		if (roleModel == null) {
			roleModel = new RoleModel();
			roleModel.setRoleName(roleDto.getRoleName().toUpperCase());
			for (String pri : roleDto.getPrivilages()) {
				PrivilegeModel privilegeTemp = privilegeRepo.findByPrivilageName(pri.toUpperCase());
				UserRolePrivilegeModel userRolePrivilegeModel = new UserRolePrivilegeModel();
				if (privilegeTemp == null) {

					privilegeTemp = new PrivilegeModel();
					privilegeTemp.setPrivilageName(pri.toUpperCase());

					userRolePrivilegeModel.setPrivilage(privilegeTemp);

				} else {
					userRolePrivilegeModel.setPrivilage(privilegeTemp);

				}

				userRolePrivilegeModel.setRole(roleModel);
				userRolePrivilegeRepo.save(userRolePrivilegeModel);

			}

		} else {
			throw new CustomRuntimeException("Role already exits!!");
		}

	}

	@Override
	@Cacheable("role-privilege")
	public List<String> getPrivilegeByRole(String roleName) {

		RoleModel roleModel = roleRepository.findByRoleName(roleName.toUpperCase());

		List<UserRolePrivilegeModel> findByRole = userRolePrivilegeRepo.findByRole(roleModel);

		List<String> privilegeList = new ArrayList<>();
		findByRole.forEach(privilege -> {
			if (privilege.getPrivilage() != null) {
				privilegeList.add(privilege.getPrivilage().getPrivilageName());
			}
		});

		return privilegeList;

	}

	@Override
	@Cacheable("user-role")
	public String getUserRole(String username) {

		UserModel user = userRepository.findByUsername(username.toUpperCase());
		if (user == null) {
			throw new CustomRuntimeException("User not Found!!");
		}
		log.info("User found {}", user.getUsername());
		List<UserRolePrivilegeModel> users = userRolePrivilegeRepository.findByUser(user);
		log.info("{}", users.get(0).getRole().getRoleName());

		return users.get(0).getRole().getRoleName();

	}

	@Override
	public boolean isRoleContainPrivilege(String role, String privilege) {

		return getPrivilegeByRole(role).contains(privilege.toUpperCase());
	}

}
