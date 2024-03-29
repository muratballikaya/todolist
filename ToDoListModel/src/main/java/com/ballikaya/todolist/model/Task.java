package com.ballikaya.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ballikaya.todolist.model.base.BaseModel;

@Entity
@Table(name = "TASK")
public class Task extends BaseModel {

	@Column(name = "USERID")
	private Long userId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	public Task() {

	}

	public Task(Long userId,String name, String description) {
		this.userId = userId;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
