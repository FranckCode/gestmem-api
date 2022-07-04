package com.gestmemapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "DBFile")
public class DBFile implements Serializable{

	private static final long serialVersionUID = 2284252532274015507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DBFILE_ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "NAME", insertable=true, updatable=true, nullable=false)
    private String name;

    @Lob
	@Column(name = "DATA", insertable=true, updatable=true, nullable=false)
    private byte[] data;

    @Column(name = "TYPE", insertable=true, updatable = true, nullable=false)
	private String type;

    @Column(name = "SIZE", insertable=true, updatable = true, nullable=false)
	private Long size;
	
	public DBFile(){
		super();
	}
	public DBFile(String dbfileName){
		super();
		this.id= null;
		this.name = dbfileName;
        this.data = null;
        this.type = null;
        this.size = null;
	}
	public DBFile(Long id){
		super();
		this.id= id;
		this.name = null;
        this.data = null;
        this.type = null;
        this.size = null;
	}


	public DBFile(String name, byte[] data, String type) {
        super();
        this.name = name;
        this.data = data;
        this.type = type;
        this.size = Long.valueOf(data.length);
    }

    public DBFile(DBFile dbFile){
		super();
		this.id= dbFile.id;
		this.name = dbFile.name;
        this.data = dbFile.data;
        this.type = dbFile.type;
        this.size = dbFile.size;
	}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
	

}
