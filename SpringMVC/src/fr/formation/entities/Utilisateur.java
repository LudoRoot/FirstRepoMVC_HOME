package fr.formation.entities;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	// @JoinColumn (name= "CIVILITE_ID")
	private Civilite civ;

	public Utilisateur() {
		super();
	}

	@NotEmpty(message = "{champ.obligatoire}")
	private String nom, prenom, login, mdp;

	private String email;

	private String telfix, telmob;
	private Date datecreat;
	
	// private Adresse adress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTelfix() {
		return telfix;
	}

	public void setTelfix(String telfix) {
		this.telfix = telfix;
	}

	public String getTelmob() {
		return telmob;
	}

	public void setTelmob(String telmob) {
		this.telmob = telmob;
	}

	public Date getDatecreat() {
		return datecreat;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public void setDatecreat(Date datecreat) {
		this.datecreat = datecreat;
	}

	public Civilite getCiv() {
		return civ;
	}

	public void setCiv(Civilite civ) {
		this.civ = civ;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "[" +civ.toString()+ " " + nom + " " + prenom + ", mdp=" + mdp
				+ ", email=" + email + ", telfix=" + telfix + ", telmob=" + telmob + ", datecreat=" + datecreat + "]";
	}
	

}
