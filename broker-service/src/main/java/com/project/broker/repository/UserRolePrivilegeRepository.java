package com.project.broker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.broker.model.PrivilegeModel;
import com.project.broker.model.RoleModel;
import com.project.broker.model.UserModel;
import com.project.broker.model.UserRolePrivilegeModel;

@Repository
public interface UserRolePrivilegeRepository extends JpaRepository<UserRolePrivilegeModel, Long> {

	public List<UserRolePrivilegeModel> findByPrivilage(PrivilegeModel privilegeModel);
	
	public List<UserRolePrivilegeModel> findByRole(RoleModel roleModel);
	
	public List<UserRolePrivilegeModel> findByUser(UserModel userModel);
	
}
