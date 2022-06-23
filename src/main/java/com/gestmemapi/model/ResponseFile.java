package com.gestmemapi.model;

import java.sql.Date;

public class ResponseFile {
    
    private Long id;

    private Integer isValidated;

    private Integer isPublished;

    private Date addedDate;

    private Date updatedDate;
	
    private String title;
	
	private String summary;

    private String name;

	private String type;

	private Long size;

    private String url;

    private Person student;

    private Person supervisor;

    private Speciality speciality;

    public ResponseFile() {
        super();
    }

    public ResponseFile(Long id) {
        this.id = id;
    }

    public ResponseFile(String name, String url, String type, Long size) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.url = url;
    }
    
    public ResponseFile(Long id, Integer isValidated, Integer isPublished, Date addedDate, Date updatedDate,
            String title, String summary, String name, String type, Long size, String url, Person student,
            Person supervisor, Speciality speciality) {
        this.id = id;
        this.isValidated = isValidated;
        this.isPublished = isPublished;
        this.addedDate = addedDate;
        this.updatedDate = updatedDate;
        this.title = title;
        this.summary = summary;
        this.name = name;
        this.type = type;
        this.size = size;
        this.url = url;
        this.student = student;
        this.supervisor = supervisor;
        this.speciality = speciality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(Integer isValidated) {
        this.isValidated = isValidated;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Person getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Person supervisor) {
        this.supervisor = supervisor;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }



}
