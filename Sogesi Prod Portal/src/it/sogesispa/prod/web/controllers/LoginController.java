package it.sogesispa.prod.web.controllers;

import java.security.Principal;

import it.sogesispa.prod.web.models.User;
import it.sogesispa.prod.web.services.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController
{
	private UserService userService;

	@Autowired
	public void setUserService(UserService userservice)
	{
		this.userService = userservice;
	}

	@RequestMapping(value = "/")
	public String homePage(Model model, HttpSession session)
	{

		User user = userService.setUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());

		session.setAttribute("user", user);

		return "redirect:/main";
	}
	
	@RequestMapping(value = "/login")
	public String doLogin(Model model, HttpSession session)
	{
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String doLogout()
	{
		return "logout";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {
 
		ModelAndView model = new ModelAndView();
 
		if (user != null) {
			model.addObject("msg", "Buongiorno " + user.getName()
			+ ", siamo spiacenti ma non hai il permesso di accedere a quest'area!");
		} else {
			model.addObject("msg", 
			"Siamo spiacenti ma non hai il permesso di accedere a quest'area!");
		}
 
		model.setViewName("403");
		return model;
	}
}
