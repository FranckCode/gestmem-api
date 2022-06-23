package com.gestmemapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPECIALITY")
public class Speciality implements Serializable{

	private static final long serialVersionUID = 2284252532274015507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPECIALITY_ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="SPECIALITY_NAME", updatable = true, nullable = false)
	private String specialityName;

	public Speciality(){
		super();
	}
	
	public Speciality(Long id) {
        this.id = id;
    }
    
    public Speciality(String specialityName) {
        this.specialityName = specialityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
    
}
