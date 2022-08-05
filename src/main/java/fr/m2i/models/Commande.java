package fr.m2i.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Commande {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Basic
	@Column(name="reference")
	private String reference;
	
	@Basic
	@Column(name="date")
	private Date date;

	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
