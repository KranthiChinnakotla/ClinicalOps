package org.trialdocs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.trialdocs.model.Users;
import org.trialdocs.model.UsersDao;

@Controller
public class HomeController {

	@Autowired
	private UsersDao userdao;

	@RequestMapping("/")
	public String showHome() {
		return "home";
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	// @RequestParam("name") String name,@RequestParam("email") String
	// email,@RequestParam("password") String password,@RequestParam("role")
	// String role

	@RequestMapping(value = "/usercreated", method = RequestMethod.POST)
	public String createUser(Model model, Users users) {
		System.out.println(users);
		userdao.createUser(users);
		return "login";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String authorizeUser(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		if (userdao.getUser(email, password) != null) {

			return "upload";
		} else {
			return "login";
		}
	}

}
