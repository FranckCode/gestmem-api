package com.gestmemapi.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable{

	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCUMENT_ID", updatable = false, nullable = false)
    private Long id;

	@Column(name = "IS_VALIDATED", insertable=true, updatable=true, nullable=false)
    private Integer isValidated;

	@Column(name = "IS_PUBLISHED", insertable=true, updatable=true, nullable=false)
    private Integer isPublished;

	@Column(name = "ADDED_DATE", insertable=true, updatable=true, nullable=false)
    private Date addedDate;

	@Column(name = "UPDATED_DATE", insertable=true, updatable=true, nullable=false)
    private Date updatedDate;
	
	@Column(name = "TITLE", insertable=true, updatable=true, nullable=false)
    private String title;
	
	@Column(name = "SUMMARY", insertable=true, updatable = true, nullable=false)
	private String summary;

    @Column(name = "NAME", unique=true, insertable=true, updatable=true, nullable=false)
    private String name;

    @Lob
	@Column(name = "DATA", insertable=true, updatable=true, nullable=false)
    private byte[] data;

    @Column(name = "TYPE", insertable=true, updatable = true, nullable=false)
	private String type;

    @Column(name = "SIZE", insertable=true, updatable = true, nullable=false)
	private Long size;

	@ManyToOne(
		// cascade = CascadeType.ALL
    )
	@JoinColumn(name="student_id")
	private Person student;

    @ManyToOne(
		// cascade = CascadeType.ALL
    )
	@JoinColumn(name="supervisor_id")
	private Person supervisor;

    @ManyToOne(
		// cascade = CascadeType.ALL
    )
	@JoinColumn(name="speciality_id")
	private Speciality speciality;

    public Document() {
    	super();
        System.out.println("Constructeur numero 1 de la classe document");
    }
    
    public Document(Long id, Integer isValidated, Integer isPublished, String title, String summary, String name,
            byte[] data) {
        this.id = id;
        this.isValidated = isValidated;
        this.isPublished = isPublished;
        this.title = title;
        this.summary = summary;
        this.name = name;
        this.data = data;
        this.size = Long.valueOf(1);
        System.out.println("Constructeur numero 2 de la classe document");
    }
    
    public Document(String title, String summary, String name, String type, Person student, Person supervisor,
            Speciality speciality) {
        this.title = title;
        this.summary = summary;
        this.name = name;
        this.type = type;
        this.student = student;
        this.supervisor = supervisor;
        this.speciality = speciality;
        this.size = Long.valueOf(1);
        System.out.println("Constructeur numero 3 de la classe document");
    }

    public Document(String title, String summary, String name, byte[] data, String type, Person student, Person supervisor,
            Speciality speciality) {
        this.title = title;
        this.summary = summary;
        this.name = name;
        this.data = data;
        this.type = type;
        this.student = student;
        this.supervisor = supervisor;
        this.speciality = speciality;
        this.size = Long.valueOf(data.length);
        Long millis = System.currentTimeMillis();
        this.addedDate = new Date(millis);
        this.updatedDate = new Date(millis);
        this.size = Long.valueOf(this.data.length);
        this.isPublished = 0;
        this.isValidated = 0;
        System.out.println("Constructeur numero 4 de la classe document");
    }

    public Document(Document document){
        this.title = document.getTitle();
        this.summary = document.getSummary();
        this.name = document.getName();
        this.data = document.getData();
        this.type = document.getType();
        this.student = document.getStudent();
        this.supervisor = document.getSupervisor();
        this.speciality = document.getSpeciality();
        Long millis = System.currentTimeMillis();
        this.addedDate = new Date(millis);
        this.updatedDate = new Date(millis);
        this.size = Long.valueOf(data.length);
        this.isPublished = 0;
        this.isValidated = 0;
        System.out.println("Constructeur numero 5 de la classe document");
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "Document [addedDate=" + addedDate + ", id=" + id + ", isPublished=" + isPublished + ", isValidated="
                + isValidated + ", name="  + name  + ", data="  + data + ", size=" + size + ", speciality=" + speciality + ", student="
                + student + ", summary=" + summary + ", supervisor=" + supervisor + ", title=" + title + ", type="
                + type + ", updatedDate=" + updatedDate + "]";
    }
    
    
}
