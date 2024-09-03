package com.example.taskmanagement.model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
	@Id
	private String id;
	
	private String creatorId; //reference user's id
	private String projectId;
	private String name;
	private Date createdTs;
	private Date dueTs;
	private Status status;
	private String desc;
	private List<String> assigneeIds; //reference the assigned user's id
	
	public Task() {
		this.createdTs = new Date();
		this.status = Status.CREATED;
		this.desc = null;
		this.assigneeIds = new ArrayList<>();
	}
	
	public Task(String creatorId, String projectId, String name, Date dueTs, String desc) {
		this.creatorId = creatorId;
		this.projectId = projectId;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public List<String> getAssigneeIds() {
		return assigneeIds;
	}

	public void setAssigneeIds(List<String> assigneeIds) {
		this.assigneeIds = assigneeIds;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", creatorId=" + creatorId + ", projectId=" + projectId + ", name=" + name
				+ ", createdTs=" + createdTs + ", dueTs=" + dueTs + ", status=" + status + ", desc=" + desc
				+ ", assigneeIds=" + assigneeIds + "]";
	}

	
}
