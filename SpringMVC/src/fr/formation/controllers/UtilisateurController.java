package fr.formation.controllers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.config.UserOrGuest;
import fr.formation.dao.ICiviliteJpaRepository;
import fr.formation.dao.IUtilisateurJpaRepository;
import fr.formation.entities.Civilite;
import fr.formation.entities.Utilisateur;

//@Secured({"ROLE_USER","ROLE_GUEST"})			// méthode sans créer de nouvelle annotation
@UserOrGuest									// méthode avec la nouvelle annotation
@Controller
@RequestMapping("/usercontroller")
public class UtilisateurController {

	@Autowired
	private IUtilisateurJpaRepository userJpaRepository;
	@Autowired
	private ICiviliteJpaRepository civJpaRepository;

//	@RequestMapping("/login")							//ne sert plus a rien une fois qu'on travaille avec SPRING Security
//	public String login(Model model) {
//
//		return "Login";
//	}

//	@RequestMapping("/checklogin")						//ne sert plus a rien une fois qu'on travaille avec SPRING Security
//	public String checklogin(
//			Model model,
//			@RequestParam(value = "login", required = true) String login,
//			@RequestParam(value = "mdp", required = true) String mdp) 
//	{
//		if (userJpaRepository.findUserByPwdLog(mdp, login) != null) 
//		{
//			
//			List<Civilite> listeCivil = new ArrayList<Civilite>();
//			listeCivil = civJpaRepository.findAll();
//			model.addAttribute("listecivil", listeCivil);
//
//			model.addAttribute("message", "Vous allez modifier un utilisateur existant!");
//
//			Utilisateur u1 = userJpaRepository.findUserByPwdLog(mdp, login);
//
//			model.addAttribute("utilisateur", u1);
//			model.addAttribute("messageid", "Utilisateur à modifier");
//
//			return "FormManipUtilisateur";		
//		}
//
//		else
//
//		model.addAttribute("messagePageFormulaire", "Utilisateur inconnu, veuillez vous enregistrer");
//
//		List<Civilite> listeCivil = new ArrayList<Civilite>();
//		listeCivil = civJpaRepository.findAll();
//
//		model.addAttribute("listecivil", listeCivil);
//
//		Utilisateur userunknown = new Utilisateur();
//		userunknown.setLogin(login);
//		userunknown.setMdp(mdp);
//		model.addAttribute("utilisateur", userunknown);
//
//		return "FormManipUtilisateur";
//	}
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
	@RequestMapping("/Start")
	public String charger() {

		Civilite civ1 = new Civilite();
		civ1.setId(1L);
		civ1.setAbbreviation("Mr");
		Civilite civ2 = new Civilite();
		civ2.setId(2L);
		civ2.setAbbreviation("Mme");
		Civilite civ3 = new Civilite();
		civ3.setId(3L);
		civ3.setAbbreviation("Mlle");

		civJpaRepository.save(civ1);
		civJpaRepository.save(civ2);
		civJpaRepository.save(civ3);

		return "Entry";
	}

	@GetMapping("/goToCreer")
	public String goToCreer(Model model) {

		List<Civilite> listeCivil = new ArrayList<Civilite>();
		listeCivil = civJpaRepository.findAll();

		model.addAttribute("listecivil", listeCivil);

		model.addAttribute("utilisateur", new Utilisateur());

		return "FormManipUtilisateur";
	}

	@RequestMapping("/ModifOK")
	public String creer(@Valid @ModelAttribute(value = "utilisateur") Utilisateur user, BindingResult result,
			Model model) {

		// LUDO: la value "utilisateur" est attendue en transmission depuis la page web
		// qui a dirigé vers cette méthode. (on retrouve le "utilisateur" dans le jsp)
		// cet attribut du model est tranformé en "Utilisateur" et on lui donne le nom
		// "user".

		Long idCiv = user.getCiv().getId();
		Civilite civ1 = civJpaRepository.getOne(idCiv);
		user.setCiv(civ1);

		Date d = new Date();

		user.setDatecreat(d);

		if (!result.hasErrors()) {
			userJpaRepository.save(user);
			model.addAttribute("user", new Utilisateur());
			// on crée un nouvel utilisateur immediatement apres avoir sauvé afin de ne pas
			// avoir des champs non nuls qui trainent pour la suite
			model.addAttribute("message1", "Utilisateur créé/modifié:");
			String visu = user.toString();
			model.addAttribute("message2", visu);
			return "UserCreated";
		}

		// On recrée la liste de civilites pour la liste déroulante afin de pouvoir
		// l'avoir dans le formulaire en cas de modif ou de rebouclage s'il manque un
		// champ obligatoire
		List<Civilite> listeCivil = new ArrayList<Civilite>();
		listeCivil = civJpaRepository.findAll();
		model.addAttribute("listecivil", listeCivil);

		model.addAttribute("user", user);

		return "FormManipUtilisateur";
	}

	@GetMapping("/goToModifier")
	public String goToModifier(Model model) {

		model.addAttribute("message", "Veuillez renseigner l'ID de l'utilisateur à modifier:");

		return "FormModifbyID";
	}

	@GetMapping("/DoModifier")

	public String doModifier(@RequestParam(value = "iduser", required = true) Long userid, Model model) {
		model.addAttribute("pageTitle", "Utilisateur à modifier");

		if (userJpaRepository.getOne(userid) == null) {
			model.addAttribute("messageid", "utilisateur inconnu, réessayez");

			return "goToModifier";
		}

		List<Civilite> listeCivil = new ArrayList<Civilite>();
		listeCivil = civJpaRepository.findAll();
		model.addAttribute("listecivil", listeCivil);

		model.addAttribute("message", "Vous allez modifier l'utilisateur" + userid + " !");

		Utilisateur u1 = userJpaRepository.getOne(userid);

		model.addAttribute("utilisateur", u1);
		model.addAttribute("messageid", "Utilisateur à modifier");

		return "FormManipUtilisateur";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/goToList")
	public String goToList(Model model) {

		List<Utilisateur> listeUser = new ArrayList<Utilisateur>();
		listeUser = userJpaRepository.findAll();

		if (listeUser.size() < 1) {
			model.addAttribute("message", "Liste générée automatiquement:");
			int i = 0;
			do {

				Utilisateur user = new Utilisateur();
				user.setNom("Nom_User_Defaut " + i);
				user.setPrenom("Prenom_User_Defaut " + i);
				listeUser.add(user);
				i++;
			} while (i < 5);
			model.addAttribute("listeUser", listeUser);
			return "AfficheListe";
		}

		model.addAttribute("message", "Les utilisateurs dans la liste sont les suivants:");

		model.addAttribute("listeUser", listeUser);

		return "AfficheListe";
	}

	@GetMapping("/goToDelete")
	public String goToDelete(Model model) {

		model.addAttribute("message", "Veuillez renseigner l'ID de l'utilisateur à supprimer:");

		return "FormDeletebyID";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/DoDelete")
	public String doDelete(@RequestParam(value = "iduser", required = true) Long userid, Model model) {

		if (userJpaRepository.getOne(userid) == null) {
			model.addAttribute("messageid", "utilisateur inconnu, réessayez");

			return "goToDelete";
		}

		Utilisateur u1 = userJpaRepository.getOne(userid);

		model.addAttribute("utilisateur", u1);

		model.addAttribute("message1", "Utilisateur effacé:");
		String visu = u1.toString();
		model.addAttribute("message2", visu);

		userJpaRepository.deleteById(userid);

		return "DeleteOK";
	}
}
