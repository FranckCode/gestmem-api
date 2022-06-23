package com.gestmemapi.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_ID", updatable = false, nullable = false)
    private Long id;

	@Column(name = "FIRSTNAME", insertable=true, updatable=true, nullable=false)
    private String firstName;

	@Column(name = "LASTNAME", insertable=true, updatable=true, nullable=false)
    private String lastName;

	@Column(name = "MATRICULE", unique=true, insertable=true, updatable=true, nullable=false)
    private String matricule;

	@Column(name = "EMAIL", unique=true, insertable=true, updatable=true, nullable=false)
    private String email;
	
	@Column(name = "PASSWORD", insertable=true, updatable=true, nullable=false)
    private String password;
	
	@Column(name = "PERSON_ACTIVE", insertable=true, updatable = true, nullable=false)
	private Integer personActive;
	
	@ManyToOne(
		// cascade = CascadeType.ALL
    )
	@JoinColumn(name="role_id")
	private Role role;

    public Person() {
    	super();
    }

    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Person(String firstName, String lastName, String matricule, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.matricule = matricule;
        this.email = email;
        this.password = password;
        this.role = new Role(role);
    }
    
	public Person(Long id, String firstName, String lastName, String matricule, String email, String password, Integer personActive) {
        this.id = id;
		this.firstName = firstName;
        this.lastName = lastName;
        this.matricule = matricule;
        this.email = email;
        this.password = password;
        this.personActive = personActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPersonActive() {
        return personActive;
    }

    public void setPersonActive(Integer personActive) {
        this.personActive = personActive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
