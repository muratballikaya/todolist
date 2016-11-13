package com.ballikaya.todolist.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ballikaya.todolist.model.User;
import com.ballikaya.todolist.pres.dataholder.LoginDataHolder;
import com.ballikaya.todolist.pres.dataholder.UserDataHolder;
import com.ballikaya.todolist.pres.util.SecurityUtil;
import com.ballikaya.todolist.service.service.UserService;

@Controller
public class Login {

	private static final Logger logger = Logger.getLogger(TodoList.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showForm(LoginDataHolder loginDh, Model model) {
		logger.info("Init login...");
		model.addAttribute("user", loginDh);
		return "login";
	}

	/**
	 * Post metodu ile login işlemi gerçekleştirilir.
	 * 
	 * MD5 şifreleme yöntemi ile DB'ye kaydedilen şifre karşılaştırılarak login
	 * olan kişinin şifresi teyid edilir.
	 * 
	 * @param loginDh
	 * @param bindingResult
	 * @param model
	 * @param httpSession
	 *            ** User Session'ı tutmak için kullandım.
	 * @return
	 */
	@PostMapping(value = "/loginForm")
	public String save(@Valid LoginDataHolder loginDh, BindingResult bindingResult, Model model,
			HttpSession httpSession) {
		model.addAttribute("user", loginDh);
		if (bindingResult.hasErrors()) {
			return "login";
		}

		User user = userService.getUser(loginDh.getUserName());

		if (user == null)
			return "login";

		String encrypted = SecurityUtil.cryptWithMD5(loginDh.getPassword());

		if (encrypted.equals(user.getPassword())) {

			httpSession.setAttribute("LOGGEDIN", user);
			logger.debug("Redirecting todolist page...");
			return "redirect:/todolist";
		}

		return "login";

	}

}
