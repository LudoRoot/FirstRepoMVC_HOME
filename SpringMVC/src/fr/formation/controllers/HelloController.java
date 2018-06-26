package fr.formation.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.formation.dao.ICredentialsJpaRepository;
import fr.formation.entities.*;

@Secured("ROLE_USER")
@Controller
@RequestMapping("/hellocontroller")
public class HelloController {

    @Autowired
    private ICredentialsJpaRepository credentialsJpaRepository;

    @RequestMapping("/hello")
    public String hello(Model model) {
	model.addAttribute("message", "Hello Spring MVC World!");
	return "hello";
    }

    @RequestMapping("/bye")
    public String bye(
	    @RequestParam(value = "username", required = true) String username,
	    Model model) {
	model.addAttribute("message", "Bye " + username + "!");
	return "bye";
    }

    @RequestMapping(value = "/seeYou", method = RequestMethod.GET)		//dit qu'on ne peut l'appeler que par la methode GET
    public String seeYou(
	    @RequestParam(value = "username", required = false) String username,
	    Model model) {
	model.addAttribute("message", "See you " + username + "!");
	return "seeYou";
    }

    @GetMapping("/mySession")							//uniquement avec un GET
    public String mySession(HttpSession session, Model model) {			//session est connue par le contexte
	model.addAttribute("message",
		"Ma session est " + session.getId() + "!");
	return "seeYou";
    }

    @GetMapping(value = { "/nickname", "/nickname/{name}" })			//uniquement avec un GET
    public String nickname(
	    @PathVariable(value = "name", required = false) String name,	//pathparam est devenue pathVariable
	    Model model) {
	model.addAttribute("message", "Mon surnom est " + name + "!");
	return "hello";
    }

    @PostMapping("/auth")							//pour valider un formulaire on utilise la methode Post
    public String auth(
	    @RequestParam(value = "usr", required = true) String username,
	    @RequestParam(value = "pwd", required = true) String password,
	    Model model) {
	model.addAttribute("message", "Mon username est " + username
		+ " et mon password est " + password + "!");
	return "hello";								// il les tpossible de renvoyer vers une autre jsp
    }

    @PostMapping("/login")							
    public String login(@ModelAttribute(value = "usr") Credentials credentials,	//trois objects credentials, model et redirectattributes
	    Model model, RedirectAttributes redirectAttributes) {		// Credentials est vu dans la vue par l'attribut user. (credential est couple usr et psw) 
	if ("123".equals(credentials.getPassword())) {				// pour cette méthode il faut utiliser postman, lui donner dans le header l'info qu il va recevoir des données "x-www-form-urlencoded"
	    credentialsJpaRepository.save(credentials);				//dans le body on fait clé : valeur (x-www-form
	    model.addAttribute("pageTitle", "Welcome!");
	    model.addAttribute("message",
		    "Welcome " + credentials.getUsername() + "!");
	    return "welcome";
	}
	String username = credentials.getUsername();
	redirectAttributes.addFlashAttribute("credentials", credentials);	// redirectattribute pour envoyer vers autre chose qu'une jsp.
	return "redirect:/hellocontroller/loginAgain/" + username;
    }

    @GetMapping("/loginAgain/{username}")
    public String loginAgain(
	    @PathVariable(value = "username", required = false) String username,
	    @ModelAttribute("credentials") final Credentials credentials,		// on retrouve les credentials qui proviennenent de la méthode précédente
	    Model model) {
	model.addAttribute("pageTitle", "Login again!");
	model.addAttribute("message", "Login again " + username
		+ "! Bad password " + credentials.getPassword());
	return "loginAgain";
    }

    @SuppressWarnings("unused")
    @GetMapping("/goToBet")
    public String goToBet(@ModelAttribute(value = "bet") Bet bet, Model model) {
	model.addAttribute("pageTitle", "Bet!");
	model.addAttribute("message", "Need to bet!");
	return "form";
    }

    @PostMapping("/bet")
    public String bet(@ModelAttribute(value = "bet") Bet bet1, Model model) {
	model.addAttribute("bet", new Bet()); // Re-init
	model.addAttribute("pageTitle", "Bet once more!");
	model.addAttribute("message", "Thank you for your bet! France="
		+ bet1.getScoreFrance() + " : Pérou=" + bet1.getScorePerou());
	return "form";
    }
}