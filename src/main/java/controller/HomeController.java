package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Person;
import service.PersonService;
import service.UserService;

@Controller
public class HomeController {

	private UserService userService;

	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService ps) {
		this.userService = ps;
	}

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "birthDay", new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Authentication auth) {
		if (auth != null)
			return "redirect:/search";
		else
			return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) Boolean error, Model model) {
		if(error != null && error ==true) {
			model.addAttribute("message", "Votre login ou mot de passe est incorrect");
		}
		return "login";
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Authentication auth, Model model) {
		model.addAttribute("persons", personService.listPersons());
		model.addAttribute("role", userService.findById(auth.getName()).getRole());
		return "listPersons";
	}

	@RequestMapping(value = "/add/person", method = RequestMethod.GET)
	public String addPerson(Authentication auth, Model model) {
		String[] choice = { "M.", "Mme", "Melle" };
		model.addAttribute("person", new Person());
		model.addAttribute("choice", choice);
		model.addAttribute("role", userService.findById(auth.getName()).getRole());
		return "addPerson";
	}

	@RequestMapping(value = "/add/person", method = RequestMethod.POST)
	public String personneSubmit(@ModelAttribute("person") @Validated Person person, BindingResult bindingResult, Authentication auth, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			System.out.println("here");
			return "addPerson";
		}
		personService.addPerson(person);
		String role = userService.findById(auth.getName()).getRole();
		model.addAttribute("role", role);
		if (role.equals("ROLE_ADMIN")) {
			redirectAttributes.addFlashAttribute("message", "Personne ajouté avec success");
			return "redirect:/persons";
		} else {
			redirectAttributes.addFlashAttribute("notif", "Personne ajouté avec success");
			return "redirect:/search";
		}
	}

	@RequestMapping(value = "/update/{personID}", method = RequestMethod.GET)
	public String updatePerson(@PathVariable("personID") int id, Authentication auth, Model model) {
		String[] choice = { "M.", "Mme", "Melle" };
		model.addAttribute("person", personService.findById(id));
		model.addAttribute("choice", choice);
		String role = userService.findById(auth.getName()).getRole();
		model.addAttribute("role", role);
		if (role.equals("ROLE_ADMIN")) {
			return "updatePerson";
		} else {
			return "detailsPerson";
		}
	}

	@RequestMapping(value = "/update/{personID}", method = RequestMethod.POST)
	public String updatePerson(@PathVariable("personID") int id, @ModelAttribute("person") @Validated Person person,
			BindingResult bindingResult, Authentication auth, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			System.out.println("here");
			return "updatePerson";
		}
		personService.updatePerson(person);
		String role = userService.findById(auth.getName()).getRole();
		model.addAttribute("role", role);
		redirectAttributes.addFlashAttribute("message", "Personne modifié avec success");
		System.out.println("id = " + person.getId());
		return "redirect:/persons";
	}

	@RequestMapping(value = "/delete/{personID}", method = RequestMethod.GET)
	public String deletePerson(@PathVariable("personID") int id, Model model, RedirectAttributes redirectAttributes) {
		personService.deletePerson(id);
		redirectAttributes.addFlashAttribute("message", "La personne est supprimé avec succes");
		return "redirect:/persons";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchPerson(Authentication auth, Model model) {
		model.addAttribute("role", userService.findById(auth.getName()).getRole());
		return "searchPerson";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchPerson(@RequestParam Map<String, String> reqPar,Authentication auth,Model model, HttpServletResponse response) {
		List<Person> result = personService.searchPersons(reqPar);
		if(result.isEmpty()) {
			model.addAttribute("message","Aucune personne est trouve");
		}else {
			model.addAttribute("persons",result);
		}
		model.addAttribute("role", userService.findById(auth.getName()).getRole());
		model.addAttribute("gender", reqPar.get("gender"));
		model.addAttribute("firstName", reqPar.get("firstName"));
		model.addAttribute("lastName", reqPar.get("lastName"));
		//Cookie gender = new Cookie("gender", reqPar.get("gender"));
		//Cookie firstName = new Cookie("firstName", reqPar.get("firstName"));
		//Cookie lastName = new Cookie("lastName", reqPar.get("lastName"));
		//response.addCookie(gender);response.addCookie(firstName);response.addCookie(lastName);
		return "searchPerson";
	}

}