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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "bk_privilege")
public class PrivilegeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "privilege_id")
	private long privilegeIdPK;

	private String privilageName;

	@OneToMany(mappedBy = "privilage", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	Set<UserRolePrivilegeModel> userRolePrivilegeModels = new HashSet<>();

	/**
	 * @return the privilegeIdPK
	 */
	public long getPrivilegeIdPK() {
		return privilegeIdPK;
	}

	/**
	 * @param privilegeIdPK the privilegeIdPK to set
	 */
	public void setPrivilegeIdPK(long privilegeIdPK) {
		this.privilegeIdPK = privilegeIdPK;
	}

	/**
	 * @return the privilageName
	 */
	public String getPrivilageName() {
		return privilageName;
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

	/**
	 * @param privilageName the privilageName to set
	 */
	public void setPrivilageName(String privilageName) {
		this.privilageName = privilageName;
	}

}
