package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import fr.m2i.Db.DaoFactory;
import fr.m2i.models.Actor;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/index.jsp";
	
	@Resource(name = "dataSource")
	private DataSource dataSource;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("exemple",DaoFactory.getInstance().getActorDao().lister());
		
		
		request.setAttribute("actors",this.exempleDataAccess());
		request.setAttribute("actor", this.jpaExemple());
		
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	protected List<String> exempleDataAccess() {
		

		List<String> elements = new LinkedList<>(); 
		try (Connection connection = dataSource.getConnection()) {
		    
			Statement state = connection.createStatement();
			 
			ResultSet rs = state.executeQuery("select first_name, last_name from actor");
			while(rs.next()) {
				elements.add(rs.getString("first_name") + " " +rs.getString("last_name"));
			}
			
			rs.close();
			state.close();	 
		} catch (SQLException e) {
			
		}
		
		return elements;
		
	}
	
	//protected Actor jpaExemple() {
	//	EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
	//	EntityManager em = factory.createEntityManager();
	//	
	//	Actor actor = em.find(Actor.class, 1);
	//	
	//	em.close();
	//	
	//	return actor;
	//}
	
	//méthode pour retrouver UN acteur via ccès direct des données
		protected Actor jpaExemple() {
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
			Actor actor = em.find(Actor.class, 1);
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
			
			return actor;
		}
	
	

}
