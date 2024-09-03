package com.example.taskmanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {
	@Id
	private String id;
	
	private String creatorId; //reference user's id
	private String name;
	private Date createdTs;
	private Date dueTs;
	private Status status;
	private String desc;
	
	public Project() {
		this.createdTs = new Date();
		this.status = Status.CREATED;
		this.desc = null;
	}
	
	public Project(String creatorId, String name, Date dueTs, String desc) {
		this.creatorId = creatorId;
		this.name = name;
		this.dueTs = dueTs;
		this.desc = desc;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public Date getDueTs() {
		return dueTs;
	}

	public void setDueTs(Date dueTs) {
		this.dueTs = dueTs;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
		
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", creatorId=" + creatorId + ", name=" + name + ", createdTs=" + createdTs
				+ ", dueTs=" + dueTs + ", status=" + status + ", desc=" + desc + "]";
	}

	

}
