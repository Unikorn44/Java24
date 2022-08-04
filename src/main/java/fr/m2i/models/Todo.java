package fr.m2i.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todolist")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="tache")
	private String _tache;
	
	@Basic
	@Column(name="description")
	private String _description;
	
	//INDISPENSABLE POUR JSTL
	public Integer getId() {
		return id;
	}

	public String getTache() {
		return _tache;
	}
	public void setTache(String tache) {
		this._tache = tache;
	}
	public String getDescription() {
		return _description;
	}
	public void setDescription(String description) {
		this._description = description;
	}
	
	//constructeur
	
	public Todo() {	
	}
	
	public Todo(String tache, String description){
		this.setTache(tache);
		this.setDescription(description);
	}
	

	
}
