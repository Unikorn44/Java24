package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Todo
 */
@WebServlet("/Todo")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private static final String BDD = "jdbc:mysql://localhost:3306/todobase";
	private static final String LOGIN = "root";
	private static final String MDP = "FormationM2i";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println(response);
		
		String tache = request.getParameter("tache");
		String description = request.getParameter("description");
		
		System.out.println("la tâche est : " + tache);
		
		PreparedStatement preparedStatement = null;
				
		try {
			/* Chargement driver */
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			/* Connexion à la base de données */
			Connection connection = DriverManager.getConnection(BDD, LOGIN, MDP);
			Statement statement = connection.createStatement();
			
			/* Exécution commande */
			preparedStatement = connection.prepareStatement("INSERT INTO todolist (tache, description) values(?,?)");
			
			preparedStatement.setString(1, tache);
			preparedStatement.setString(2, description);
				
			preparedStatement.executeUpdate();
			
			/* Fermer liaison DB */
			statement.close();
			connection.close();
			
		} catch(SQLException e) {
			System.out.print(e);
		}
		
		doGet(request, response);
	}

}
