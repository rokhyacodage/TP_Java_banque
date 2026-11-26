package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLException;

public class Connexion {
    private static String URL ="jdbc:mysql://localhost:3306/banque_db";
    private static String username ="root";
    private static String password ="";

    public static Connection ouvrir()throws SQLException{
        return DriverManager.getConnection(URL,username,password);
    }
}
