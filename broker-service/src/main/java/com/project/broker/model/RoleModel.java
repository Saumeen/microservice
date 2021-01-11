package com.project.broker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "bk_role")
public class RoleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long roleIdPK;

	@Column(name = "role_name")
	private String roleName;

	@OneToMany(mappedBy = "role", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<UserRolePrivilegeModel> userRolePrivilegeModels = new HashSet<>();
	
	
	


	/**
	 * @return the roleIdPK
	 */
	public long getRoleIdPK() {
		return roleIdPK;
	}

	/**
	 * @param roleIdPK the roleIdPK to set
	 */
	public void setRoleIdPK(long roleIdPK) {
		this.roleIdPK = roleIdPK;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

	/**
	 * @return the userRolePrivilegeModels
	 */
	public Set<UserRolePrivilegeModel> getUserRolePrivilegeModels() {
		return userRolePrivilegeModels;
	}

	/**
	 * @param userRolePrivilegeModels the userRolePrivilegeModels to set
	 */
	public void setUserRolePrivilegeModels(Set<UserRolePrivilegeModel> userRolePrivilegeModels) {
		this.userRolePrivilegeModels = userRolePrivilegeModels;
	}

	


}
