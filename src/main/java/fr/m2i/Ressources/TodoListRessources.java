package fr.m2i.Ressources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/todoRessources")
public class TodoListRessources {
	
	private static final String BDD = "jdbc:mysql://localhost:3306/todobase";
	private static final String LOGIN = "root";
	private static final String MDP = "FormationM2i";
	
	
	
	@GET
	public List<String> getTodo() {
			
		List<String> elements = new LinkedList<>(); 
		try (Connection connection = DriverManager.getConnection(BDD, LOGIN, MDP)) {
		    
			Statement state = connection.createStatement();
			 
			ResultSet rs = state.executeQuery("select * FROM todolist");
			while(rs.next()) {
				elements.add(rs.getString("tache") + " " +rs.getString("description"));
			}
			
			rs.close();
			state.close();	 
			
		} catch (SQLException e) {
			
		}
		
		return elements;
	
	}
	
	
}
