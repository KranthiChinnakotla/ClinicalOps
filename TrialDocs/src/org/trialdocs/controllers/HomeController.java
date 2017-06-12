package org.trialdocs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trialdocs.model.Users;
import org.trialdocs.model.UsersDao;


@Controller
public class HomeController {
		
	@RequestMapping("/")
	public String showHome(){
		return "home";
	}
	
	

	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}

	
	@RequestMapping( value= "/usercreated", method= RequestMethod.POST)
	public String createUser(Model model, Users users){	
		return "usercreated";
	}
}
