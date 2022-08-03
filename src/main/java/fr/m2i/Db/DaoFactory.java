package fr.m2i.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.m2i.models.ActorDao;
import fr.m2i.models.ActorDaoImpl;

public class DaoFactory {
    private DataSource dataSourceTodo;

    public DaoFactory() throws NamingException {
    	Context envContext = InitialContext.doLookup("java:/comp/env");
    	this.dataSourceTodo = (DataSource) envContext.lookup("dataSourceTodo");
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        
        DaoFactory instance = null;
		try {
			instance = new DaoFactory();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSourceTodo.getConnection();
    }

    public ActorDao getActorDao() {
        return new ActorDaoImpl(this);
    }
}
