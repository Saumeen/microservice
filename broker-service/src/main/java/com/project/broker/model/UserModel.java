package com.project.broker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "bk_user")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userIdPK;

	private String username;
	private String password;
	private String email;
	private boolean verified;
	private String contactno;
	
	
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	Set<UserRolePrivilegeModel> userRolePrivilegeModels = new HashSet<>();
	
	@OneToMany(mappedBy = "userModel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	Set<HouseDetails> houseDetails;

	/**
	 * @return the userIdPK
	 */
	public long getUserIdPK() {
		return userIdPK;
	}

	/**
	 * @param userIdPK the userIdPK to set
	 */
	public void setUserIdPK(long userIdPK) {
		this.userIdPK = userIdPK;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the verified
	 */
	public boolean isVerified() {
		return verified;
	}

	/**
	 * @param verified the verified to set
	 */
	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	/**
	 * @return the contactno
	 */
	public String getContactno() {
		return contactno;
	}

	/**
	 * @param contactno the contactno to set
	 */
	public void setContactno(String contactno) {
		this.contactno = contactno;
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
	 * @return the houseDetails
	 */
	public Set<HouseDetails> getHouseDetails() {
		return houseDetails;
	}

	/**
	 * @param houseDetails the houseDetails to set
	 */
	public void setHouseDetails(Set<HouseDetails> houseDetails) {
		this.houseDetails = houseDetails;
	}
	
	

	

	
	
}
