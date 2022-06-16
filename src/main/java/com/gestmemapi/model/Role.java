package com.gestmemapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable{

	private static final long serialVersionUID = 2284252532274015507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="ROLE_NAME", updatable = true, nullable = false)
	private String roleName;

	// @OneToMany(
		// cascade = CascadeType.ALL, 
		// orphanRemoval = true, 
		// fetch = FetchType.EAGER
	// )
	// @JoinColumn(name = "role_id")
	// List<Person> persons = new ArrayList<>();
	
	public Role(){
		super();
	}
	public Role(String roleName){
		super();
		this.roleName = roleName;
	}
	public Role(Long id){
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	// public List<Person> getPersons() {
	// 	return persons;
	// }
	// public void setPersons(List<Person> persons) {
	// 	this.persons = persons;
	// }
}
