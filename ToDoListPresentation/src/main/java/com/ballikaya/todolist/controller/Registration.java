package com.ballikaya.todolist.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ballikaya.todolist.model.User;
import com.ballikaya.todolist.pres.dataholder.UserDataHolder;
import com.ballikaya.todolist.pres.util.SecurityUtil;
import com.ballikaya.todolist.service.service.UserService;

@Controller
public class Registration {

	private static final Logger logger = Logger.getLogger(Registration.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showForm(UserDataHolder userDh, Model model) {
		logger.debug("Init registration...");
		model.addAttribute("user", userDh);
		return "registration";
	}

	@PostMapping(value = "/registrationForm")
	public String save(@Validated UserDataHolder userDh, BindingResult bindingResult, Model model) {

		model.addAttribute("user", userDh);

		if (bindingResult.hasErrors()) {
			logger.debug("There are validation errors...");
			return "registration";
		}

		if (!userDh.getPassword().equals(userDh.getRePassword())) {
			logger.debug("Password's not matched each other.");
			return "registration";
		}

		User currentUser = userService.getUser(userDh.getUserName());

		if (currentUser != null)
			return "registration";

		String password = userDh.getPassword();

		String encryptedPass = SecurityUtil.cryptWithMD5(password);

		userDh.setPassword(encryptedPass);

		logger.debug("Saving user");
		userService.saveUser(userDh.getUser());
		logger.debug("Saving completed successfully...");
		return "redirect:/login";

	}

}
