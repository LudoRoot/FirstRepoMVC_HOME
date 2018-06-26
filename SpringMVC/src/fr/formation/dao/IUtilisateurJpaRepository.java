package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.entities.Utilisateur;

public interface IUtilisateurJpaRepository extends JpaRepository<Utilisateur, Long> {


//	@Query("update Utilisateur u set u.prenom = ?1, u.nom = ?2 where u.id = ?3")
//	void UpdateUserById(String prenom, String nom, Long userId);

	 @Query("select u from Utilisateur u where u.mdp =:mdp and u.login=:login") 
	    Utilisateur findUserByPwdLog(@Param("mdp") String mdp, @Param("login") String login);
	
}
