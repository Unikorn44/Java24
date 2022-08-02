package fr.m2i.models;

public class Todo {
	
	private String _tache;
	private String _description;
	
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
	public Todo(String tache, String description){
		this.setTache(tache);
		this.setDescription(description);
	}
}
