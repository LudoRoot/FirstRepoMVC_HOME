package fr.formation.restcontrollers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.formation.dao.ICiviliteJpaRepository;
import fr.formation.dao.IUtilisateurJpaRepository;
import fr.formation.entities.Civilite;
import fr.formation.entities.Utilisateur;

@ControllerAdvice
@RestController
@RequestMapping("/api")

public class TestRestController extends ResponseEntityExceptionHandler {

	@Autowired
	private ICiviliteJpaRepository civiliteRepo;

	@Autowired
	private IUtilisateurJpaRepository utilisateurRepo;

	@GetMapping("/civilites/all")
	public List<Civilite> getCivilites() {
		return civiliteRepo.findAll();
	}

	@GetMapping("/utilisateur/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") Long id) {
		return utilisateurRepo.getOne(id);
	}

	@PutMapping("/utilisateur/creer")
	public Utilisateur createUser(@Valid @RequestBody Utilisateur usr1, BindingResult result) {

		if (result.hasErrors()) {
			throw new ApiValidationException();
		}

		Date d = new Date();
		usr1.setDatecreat(d);

		return utilisateurRepo.save(usr1);
	}

	@DeleteMapping("/utilisateur/effacer/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		utilisateurRepo.deleteById(id);
		return;
	}

	@PostMapping("/utilisateur/modifier/{id}")
	public Utilisateur createUser(@PathVariable("id") Long id) {

		Utilisateur usr1 = utilisateurRepo.getOne(id);

		usr1.setNom(usr1.getNom() + "modifié");

		return utilisateurRepo.save(usr1);
	}

	@PutMapping("/utilisateur/login")
	public String checkLogin(@RequestBody List<String> l)
//			@RequestBody String login, 
//			@RequestBody String mdp) 
	{
System.out.println(l);

		if (utilisateurRepo.findUserByPwdLog(l.get(1), l.get(0)) != null) {
			System.out.println("login is ok");
			return "Login is OK";
		}

		else
			System.out.println("login has failed");
		return "Login has failed";
	}

	@SuppressWarnings("unused")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getBindingResult().getAllErrors());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
