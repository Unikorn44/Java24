package fr.m2i.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.m2i.Db.DaoFactory;

public class ActorDaoImpl implements ActorDao {
	 private DaoFactory daoFactory;

	    public ActorDaoImpl(DaoFactory daoFactory) {
	        this.daoFactory = daoFactory;
	    }

	    @Override
	    public void ajouter(Actor actor) {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = connexion.prepareStatement("INSERT INTO actor(first_name, last_name) VALUES(?, ?);");
	            preparedStatement.setString(1, actor.getFirst_name());
	            preparedStatement.setString(2, actor.getLast_name());

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    }

	    @Override
	    public List<Actor> lister() {
	        List<Actor> actors = new ArrayList<Actor>();
	        Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT first_name, last_name FROM actor;");

	            while (resultat.next()) {
	                String nom = resultat.getString("first_name");
	                String prenom = resultat.getString("last_name");

	                Actor actor = new Actor();
	                actor.setFirst_name(nom);
	                actor.setLast_name(prenom);

	                actors.add(actor);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return actors;
	    }
}
