package com.ballikaya.todolist.pres.dataholder;

import java.util.List;

import com.ballikaya.todolist.model.Task;


public class TodoListDataHolder {

	private List<Task> taskList;
	
	public List<Task> getTaskList() {
		return taskList;
	}
	
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
}
