package it.sogesispa.prod.web.controllers;

import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.services.UserService;
import it.sogesispa.prod.web.utils.EcolabSessionFilter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getMainPage(Model model, HttpSession session) {

		User user = userService.setUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());

		session.setAttribute("user", user);
		
		return "redirect:charts";

	}

	@RequestMapping(value = "/ecolab", method = RequestMethod.GET)
	public String getEcolabPage(Model model, HttpSession session) 
	{
		User user = userService.setUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		EcolabSessionFilter ecolabSessionFilter =  new EcolabSessionFilter();
		
		session.setAttribute("ecolabSessionFilter", ecolabSessionFilter);
		session.setAttribute("user", user);

		return "ecolab";
	}
	
}
