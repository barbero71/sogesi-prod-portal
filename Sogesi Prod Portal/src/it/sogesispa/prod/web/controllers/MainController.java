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

@Controller
public class MainController {

	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/main")
	public String getMainPage(Model model, HttpSession session) {

		User user = userService.setUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());

		session.setAttribute("user", user);
		
		return "redirect:charts";

	}

	@RequestMapping(value = "/ecolab")
	public String getEcolabPage(Model model, HttpSession session) 
	{
		User user = userService.setUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		EcolabSessionFilter ecolabSessionFilter =  new EcolabSessionFilter();
		
		session.setAttribute("ecolabSessionFilter", ecolabSessionFilter);
		session.setAttribute("user", user);

		return "ecolab";
	}
	
	@RequestMapping(value = "/charts")
	public String getChartsPage(Model model, HttpSession session) 
	{
		User user = userService.setUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());

		session.setAttribute("user", user);

		return "charts";
	}
	
}
