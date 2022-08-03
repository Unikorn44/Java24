package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import fr.m2i.models.Actor;
import fr.m2i.models.Todo;


/**
 * Servlet implementation class Todo
 */
import fr.m2i.Db.DaoFactory;


@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE = "/WEB-INF/pages/ToDoForm.jsp";
	
	//private static final String BDD = "jdbc:mysql://localhost:3306/todobase";
	//private static final String LOGIN = "root";
	//private static final String MDP = "FormationM2i";
		
    public TodoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Le todo est : " + this.jpaExemple().getTache());
		System.out.println("Le description est : " + this.jpaExemple().getDescription());
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println(response);
		
		String tache = request.getParameter("tache");
		String description = request.getParameter("description");
		
		System.out.println("la tâche est : " + tache);
		
		PreparedStatement preparedStatement = null;
				
		try {
			/* Chargement driver */
			//DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			/* Connexion à la base de données */
			//Connection connection = DriverManager.getConnection(BDD, LOGIN, MDP);
			DaoFactory dataFactory = null;
			try {
				dataFactory = new DaoFactory();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection = dataFactory.getConnection();
			
			/* Exécution commande */
			preparedStatement = connection.prepareStatement("INSERT INTO todolist (tache, description) values(?,?)");
			
			preparedStatement.setString(1, tache);
			preparedStatement.setString(2, description);
				
			preparedStatement.executeUpdate();
			
			/* Fermer liaison DB */
			preparedStatement.close();
			connection.close();
			
		} catch(SQLException e) {
			System.out.print(e);
		}
		
		doGet(request, response);
	}

	
	//méthode pour retrouver UN acteur via ccès direct des données
		protected Todo jpaExemple() {
			//Création EntityFactoryManager pour les lier tous
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
			//Attention pas d'autoclosable
			EntityManager em = factory.createEntityManager();
			
			
			/*
			    find
				persist
				merge
				detach
				refresh
				remove
			 */
			Todo todo = em.find(Todo.class, 1);
			//em.persist(nouvel actor)
			//em.remove(un actor)
			//em.refresh(entity) -- pour rafraichir avec la data en bdd
			//em.detach(entity)
			//em.flush() ---- Attention ! OBLIGE le EM à METTRE A JOUR la BDD (sans vérifications)
			
			
			
			/*
			 * Transaction
			 * 
			 * em.getTransaction().begin()
			 * boolean transcat = false;
			 * 
			 * try{
			 * 
			 * }
			 * finally{
			 * 	if(transac)
			 * 		em.getTransaction().commit();
			 * 	else
			 * 		em.getTransaction().rollback();
			 * 
			 * }
			 */
			
			
			em.close();
			
			return todo;
		}
}
