package com.ballikaya.todolist.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ballikaya.todolist.model.User;
import com.ballikaya.todolist.pres.dataholder.TaskDataHolder;
import com.ballikaya.todolist.pres.dataholder.UserDataHolder;
import com.ballikaya.todolist.pres.util.SecurityUtil;
import com.ballikaya.todolist.service.service.TaskService;
import com.ballikaya.todolist.service.service.UserService;

@Controller
public class AddTask {

	@Autowired
	TaskService taskService;

	@RequestMapping(value = "/addTask", method = RequestMethod.GET)
	public String showForm(TaskDataHolder taskDh, HttpSession httpSession, Model model) {
		model.addAttribute("task", taskDh);

		User user = getUser(httpSession);

		if (user == null)
			return "redirect:/login";

		taskDh.setUser(user);
		model.addAttribute("task", taskDh);

		return "addTask";
	}

	@PostMapping(value = "/newTaskForm")
	public String save(@Valid TaskDataHolder taskDataHolder, HttpSession httpSession, BindingResult bindingResult,
			Model model) {
		model.addAttribute("task", taskDataHolder);
		if (bindingResult.hasErrors()) {
			return "addTask";
		}

		User user= getUser(httpSession);
		taskDataHolder.setUser(user);
		
		taskService.saveTask(taskDataHolder.getTask());

		return "redirect:/todolist";

	}

	private User getUser(HttpSession httpSession) {

		Object o = httpSession.getAttribute("LOGGEDIN");
		if (o == null)
			return null;

		if (o instanceof User) {
			return (User) o;
		}
		return null;

	}

}
