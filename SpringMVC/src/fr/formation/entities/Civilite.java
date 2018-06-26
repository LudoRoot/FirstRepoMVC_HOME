package fr.formation.entities;

import javax.persistence.*;

@Entity
public class Civilite {

	@Id
	private Long id;
	private String abbreviation;

	// @OneToMany(mappedBy = "civ"/*, fetch = FetchType.EAGER*/)
	// private Collection<Utilisateur> liste;

	public Civilite() {
		super();
	}

	// public Collection<Utilisateur> getListe() {
	// return liste;
	// }
	//
	// public void setListe(Collection<Utilisateur> liste) {
	// this.liste = liste;
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Override
	public String toString() {
		return abbreviation;
	}

}
