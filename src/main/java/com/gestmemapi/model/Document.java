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

    @ManyToOne(
		// cascade = CascadeType.ALL
    )
	@JoinColumn(name="file_id")
	private DBFile file;

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
    
    public Document(Long id, Integer isValidated, Integer isPublished, String title, String summary) {
        this.id = id;
        this.isValidated = isValidated;
        this.isPublished = isPublished;
        this.title = title;
        this.summary = summary;
        System.out.println("Constructeur numero 2 de la classe document");
    }
    
    public Document(String title, String summary, Person student, Person supervisor,
            Speciality speciality) {
        this.title = title;
        this.summary = summary;
        this.student = student;
        this.supervisor = supervisor;
        this.speciality = speciality;
        System.out.println("Constructeur numero 3 de la classe document");
    }

    /*public Document(String title, String summary, Person student, Person supervisor,
            Speciality speciality) {
        this.title = title;
        this.summary = summary;
        this.student = student;
        this.supervisor = supervisor;
        this.speciality = speciality;
        Long millis = System.currentTimeMillis();
        this.addedDate = new Date(millis);
        this.updatedDate = new Date(millis);
        this.isPublished = 0;
        this.isValidated = 0;
        System.out.println("Constructeur numero 4 de la classe document");
    }*/

    public Document(Document document){
        this.title = document.getTitle();
        this.summary = document.getSummary();
        this.student = document.getStudent();
        this.supervisor = document.getSupervisor();
        this.speciality = document.getSpeciality();
        Long millis = System.currentTimeMillis();
        this.addedDate = new Date(millis);
        this.updatedDate = new Date(millis);
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

    public DBFile getFile() {
        return file;
    }

    public void setFile(DBFile file) {
        this.file = file;
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

    @Override
    public String toString() {
        return "Document [addedDate=" + addedDate + ", id=" + id + ", isPublished=" + isPublished + ", isValidated="
                + isValidated +", speciality=" + speciality + ", student="
                + student + ", summary=" + summary + ", supervisor=" + supervisor + ", title=" + title + ", updatedDate=" + updatedDate + "]";
    }
    
    
}
