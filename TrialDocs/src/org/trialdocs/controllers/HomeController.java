package org.trialdocs.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.trialdocs.model.Users;
import org.trialdocs.model.UsersDao;

@Controller
public class HomeController {

	@Autowired
	private UsersDao userdao;

	@RequestMapping("/")
	public String showHome() {
		return "login";
	}

	@RequestMapping("/home")
	public String showLogin() {
		return "home";
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

	@RequestMapping(value = "/upload")
	public String authorizeUser() {

		return "upload";
		
	}
	
	@RequestMapping(value="/mainpage", method = RequestMethod.POST)
	public String postMainPage(Model model,@RequestParam("email") String email,
			@RequestParam("password") String password){
		
		if (userdao.getUser(email, password) != null) {
			return "mainpage";
		} else {
			return "login";
		}
		
	}
}
