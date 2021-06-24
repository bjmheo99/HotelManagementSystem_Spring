package com.projectHotel.PhanLam.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import javax.persistence.*;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "username", nullable = false, unique = true)
	private String userName;
	@Column(name = "password", nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "account")
	private List<Role> role;

	@OneToOne
	@JoinColumn(name = "administration_id")
	private Administration administration;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Administration getAdministration() {
		return administration;
	}

	public void setAdministration(Administration administration) {
		this.administration = administration;
	}
		
	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

}
