package com.ballikaya.todolist.pres.dataholder;

import com.ballikaya.todolist.model.Task;
import com.ballikaya.todolist.model.User;

public class TaskDataHolder {

	private User user;

	private String name;

	private String description;
	
	private Long taskId;

	public Task getTask() {
		return new Task(this.user.getId(), this.name, this.description);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public Long getTaskId() {
		return taskId;
	}
	
	
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
}
