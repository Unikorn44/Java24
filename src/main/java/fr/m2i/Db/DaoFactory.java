package fr.m2i.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.m2i.models.ActorDao;
import fr.m2i.models.ActorDaoImpl;

public class DaoFactory {
	private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/sakila", "root", "FormationM2i");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public ActorDao getActorDao() {
        return new ActorDaoImpl(this);
    }
}
