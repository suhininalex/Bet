package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import logic.WithDataProvider;

public class MySqlUtil {
    private static final String url;
    private static final String user;
    private static final String password;
    
    /**
     * Initializing...
     * Driver and configuration
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("There are not mysql jdbc driver!");
        }

        Properties configuration = new Properties();
        try {
            configuration.load(new FileInputStream("database.config"));
        } catch (IOException ex) {
            throw new RuntimeException("Missing configuration file for database: (/database.config)");
        }
        url = configuration.getProperty("url");
        user = configuration.getProperty("user");
        password = configuration.getProperty("password");
        if (url==null || user==null || password==null) throw new RuntimeException("Bad configuration file for database!");
    }
       
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException ex) {
            throw new RuntimeException("Can not connect to the database");
        }
    }
    
    public static Connection extractConnection(WithDataProvider object){
        return (Connection)object.getDataProvider();
    }
    
}
