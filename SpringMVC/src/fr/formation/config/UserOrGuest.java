package fr.formation.config;

import java.lang.annotation.*;

import org.springframework.security.access.annotation.Secured;

@Retention(RetentionPolicy.RUNTIME) // Les annotations sont enregistr�es dans le fichier *.class � la compilation et
									// elle sont utilis�es par la machine virtuelle � l'ex�cution de l'application.
									// Elles peuvent donc �tre lues gr�ce � l'API de r�flection (plus de d�tails
									// dans le chapitre sur l')
@Secured({ "ROLE_USER", "ROLE_GUEST" })

public @interface UserOrGuest {

}
