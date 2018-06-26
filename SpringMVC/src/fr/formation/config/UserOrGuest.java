package fr.formation.config;

import java.lang.annotation.*;

import org.springframework.security.access.annotation.Secured;

@Retention(RetentionPolicy.RUNTIME) // Les annotations sont enregistrées dans le fichier *.class à la compilation et
									// elle sont utilisées par la machine virtuelle à l'exécution de l'application.
									// Elles peuvent donc être lues grâce à l'API de réflection (plus de détails
									// dans le chapitre sur l')
@Secured({ "ROLE_USER", "ROLE_GUEST" })

public @interface UserOrGuest {

}
