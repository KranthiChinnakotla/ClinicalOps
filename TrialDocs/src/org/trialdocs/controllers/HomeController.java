package org.trialdocs.controllers;

import java.util.List;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller

public class HomeController {
	
	/*@Autowired
	private UsersDao usersDao;

	@CrossOrigin
	@RequestMapping("/allusers")
	public List<Users> getAllUsers(){
		return usersDao.getAll();
	}*/
	
	
	@RequestMapping("/")
	public String showHome(){
		return "home";
	}
	

	@RequestMapping("/login")
	public String showLogin(){
		return "/login";
	}

}
