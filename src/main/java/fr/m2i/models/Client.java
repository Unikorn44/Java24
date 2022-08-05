package fr.m2i.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@NamedQueries({
	@NamedQuery(name="findAllClients", query="SELECT c FROM Client c"),
})


@Entity
public class Client {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	
	@Basic
	@Column(name="nom")
	private String nom;
	
	@Basic
	@Column(name="prenom")
	private String prenom;
	
	@ManyToOne
	@JoinColumn (name="id_adresse")
	private Adresse adresse;
	
	@Basic
	@Column(name="tel")
	private String tel;
	
	@Basic
	@Column(name="date_naissance")
	private Date date_naissance;
	
	//AJOUTER le fetch pour éviter les pertes de liaison
	@OneToMany(targetEntity = Commande.class, mappedBy="client", fetch=FetchType.EAGER)
	private List<Commande> commandes = new ArrayList<>();



	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}


	public Adresse getAdresse() {
		return adresse;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
}
