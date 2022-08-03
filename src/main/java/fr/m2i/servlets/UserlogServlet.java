package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserlogServlet
 */
@WebServlet("/userlog")
public class UserlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE = "/WEB-INF/pages/userLogPage.jsp";
	
	private static final String BDD = "jdbc:mysql://localhost:3306/userlog";
	private static final String LOGIN = "root";
	private static final String MDP = "FormationM2i";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserlogServlet() {
        super();
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
		Connection connection = null;
		Boolean transaction = false;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(BDD, LOGIN, MDP);
			
			try (PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name) VALUES ('tata')")){
				
				ps.executeUpdate();
			}
			
			try (PreparedStatement ps = connection.prepareStatement("INSERT INTO log (description) VALUES ('Une autre description')")){
				
				ps.executeUpdate();
			}
			
			transaction = true;
					
		} catch (SQLException e) {
			System.out.println("ERREUUUUUR" + e);
		} finally {
			if(transaction) {
				try {
					connection.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					connection.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
