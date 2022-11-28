package com.nagarro.training.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.training.springmvc.entity.User;
import com.nagarro.training.springmvc.service.UserService;


@Controller
//@RequestMapping("/library.management/")
@RequestMapping("/")

public class LoginController {
	
	private UserService userService;
	
	public LoginController(UserService userService) {
		super();
		this.userService = userService;
	}
	

//	@RequestMapping("/")
//	public String rediretToLogin() {
//		return "redirect:/library.management/login";
//	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView validateLogin(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		
		boolean isUserValid = userService.isUserValid(user);
		
		if(isUserValid) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			session.setMaxInactiveInterval(60 * 2);
			mv.setViewName("redirect:/books");
			return mv;
		} else {
			mv.setViewName("login");
			mv.addObject("loginSuccess", "false");
		}
		
		return mv;
	}
}
