package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
		
		//System.out.println("Le todo est : " + this.jpaExemple().getTache());
		//System.out.println("Le description est : " + this.jpaExemple().getDescription());
		//this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		
		//Création EntityFactoryManager pour les lier tous
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		//Attention pas d'autoclosable
		EntityManager em = factory.createEntityManager();
		
		//affichage de toute la liste
		//REQUEST NATIVE
		//Peut ajouter des conditions
		//List<Todo> todos = em.createNativeQuery("SELECT * FROM todos").getResultList();
		//Todo todoFounded = (Todo) em.createNativeQuery("SELECT * FROM todos WHERE tache = ?", Todo.class)
		//		.setParameter(1, "douche")
		//		.getSingleResult();
		//Peut aussi utiliser ETIQUETTES
		//Todo todoFounded = (Todo) em.createNativeQuery("SELECT * FROM todos WHERE id = :id", Todo.class)
		//		.setParameter("id", id)
		
		
		//set parameter : affecte la valeur "douche" au parametre 1
		//DONC... va chercher la tache dont le paramêtre en colonne "tache" est "douche"
		
		@SuppressWarnings("unchecked")
		List<Todo> todoListRecup = em.createNativeQuery("SELECT * FROM todolist", Todo.class).getResultList();
				
		//Préparation élement pour renvoi via request
		request.setAttribute("todoListRecup", todoListRecup);		
		
		em.close();
		
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String param = request.getParameter("parametre"); 
		String tache = request.getParameter("tache");
		String description = request.getParameter("description");
		String id = request.getParameter("id"); 
		
		if (param.equals("remove")) {			
			
			this.removeTache(id);
		}
			
		if (param.equals("creation")) {
					
			this.createTodo(tache, description);
		}
		
		if (param.equals("update")) {
			
			this.udpateTodo(id, description);
		}
	
		doGet(request, response);
		
	}	
	
	//méthode pour créer Todo
	protected void createTodo(String tache, String description) {
		
		
		/*PreparedStatement preparedStatement = null;
				
		//try {
			/* Chargement driver */
			//DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			/* Connexion à la base de données */
			//Connection connection = DriverManager.getConnection(BDD, LOGIN, MDP);
			/*DaoFactory dataFactory = null;
			try {
				dataFactory = new DaoFactory();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection = dataFactory.getConnection();
			
			/* Exécution commande */
			/*preparedStatement = connection.prepareStatement("INSERT INTO todolist (tache, description) values(?,?)");
			
			preparedStatement.setString(1, tache);
			preparedStatement.setString(2, description);
				
			preparedStatement.executeUpdate();
			
			/* Fermer liaison DB */
			/*preparedStatement.close();
			connection.close();
			
		} catch(SQLException e) {
			System.out.print(e);
		}*/
		
		//Création EntityFactoryManager pour les lier tous
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		//Attention pas d'autoclosable
		EntityManager em = factory.createEntityManager();
		
		Todo todo = new Todo(tache, description);
				
		
		//Transaction pour CREATION nouveau todo
		// !!! obligation de passer par TRANSACTION !!!
		em.getTransaction().begin();
		boolean transac = false;  
		  
		try{
		  em.persist(todo);
		  transac = true;
		}
		finally{
		  if(transac)
		  	em.getTransaction().commit();
		  else
		  	em.getTransaction().rollback();
		 
		 }
		
		em.close();
	}	
	
		//Methide pour remove tache selon id
		protected void removeTache(String id) {
		
			//Création EntityFactoryManager pour les lier tous
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
			//Attention pas d'autoclosable
			EntityManager em = factory.createEntityManager();
			
			int idInt = Integer.parseInt(id);
			
			Todo todo = em.find(Todo.class, idInt);
			
			em.getTransaction().begin();
			boolean transac = false;  
			  
			try{
			  em.remove(todo);
			  transac = true;
			}
			finally{
			  if(transac)
			  	em.getTransaction().commit();
			  else
			  	em.getTransaction().rollback();
			  }
			
			em.close();
		}
	
		
		//méthode changement description todo
		protected void udpateTodo(String id, String description) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
			
			EntityManager em = factory.createEntityManager();
			
			int idInt = Integer.parseInt(id);
			
			//retrouve todo
			Todo todo = em.find(Todo.class, idInt);
			//modification description
			todo.setDescription(description);
			
			em.getTransaction().begin();
			boolean transac = false;  
			  
			try {
				// modification valeurs dans BDD
				em.merge(todo);
				transac = true;
			}
			finally {
			
				if(transac) {
			  	em.getTransaction().commit();
			  }else {
			  	em.getTransaction().rollback();
			  }
			
			em.close();
			}
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
		
		//Utlisation namedQuery
		protected Todo SelectById(String id) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
			//Attention pas d'autoclosable
			EntityManager em = factory.createEntityManager();
			
			Todo todo = em.createNamedQuery("SelectById", Todo.class)
						.setParameter("id", id)
						.getSingleResult();
			
			em.close();
			
			return todo;
		}
}
