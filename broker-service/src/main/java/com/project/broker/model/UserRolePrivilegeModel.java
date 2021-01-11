package com.project.broker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="user_role_privilege")
public class UserRolePrivilegeModel {
	
	@Id
    @GeneratedValue
    private long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "privilege_id")
    private PrivilegeModel privilage;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private RoleModel role;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public UserModel getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserModel user) {
		this.user = user;
	}

	/**
	 * @return the privilage
	 */
	public PrivilegeModel getPrivilage() {
		return privilage;
	}

	/**
	 * @param privilage the privilage to set
	 */
	public void setPrivilage(PrivilegeModel privilage) {
		this.privilage = privilage;
	}

	/**
	 * @return the role
	 */
	public RoleModel getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleModel role) {
		this.role = role;
	}
	
    
    

}
