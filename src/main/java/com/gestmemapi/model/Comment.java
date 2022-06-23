package com.gestmemapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable{

	private static final long serialVersionUID = 2284252532274015507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="COMMENT_DESCRIPTION", updatable = true, nullable = false)
	private String commentDescription;

    @ManyToOne(
		// cascade = CascadeType.ALL
    )
	@JoinColumn(name="person_id")
	private Person person;

    @ManyToOne(
		// cascade = CascadeType.ALL
    )
	@JoinColumn(name="document_id")
	private Document document;

	public Comment(){
		super();
	}
	
    public Comment(Long id, String commentDescription) {
        this.id = id;
        this.commentDescription = commentDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
}
