package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import service.UserService;


@Controller
public class HomeController {

	private UserService userService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService ps) {
		this.userService = ps;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String privateHome() {
		return "privatePage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {

		User user = new User();
		user.setUsername("ahmed");
		user.setEmail("test");
		user.setPassword("test");
		this.userService.addUser(user);

		System.out.println("Person::" + user);

		ModelAndView model = new ModelAndView("home");
		model.addObject("Element ajoute");

		return model;
	}

}