package com.gtn.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("/resources/jdbc.properties")
public class ConnectionUtil {
	// Connect to SQLServer
    // (Using JDBC Driver: SQLJDBC)
		
	private String url;
	private String username;
	private String password;
	
	@Autowired
	ConnectionUtil(Environment environment){
		url = environment.getRequiredProperty("jdbc.url");
		username = environment.getRequiredProperty("jdbc.username");
		password = environment.getRequiredProperty("jdbc.password");
	}
	
    public Connection getSQLServerConnection_SQLJDBC()
            throws ClassNotFoundException, SQLException {
        String hostName = "DESKTOP-MNEF780";
        String sqlInstanceName = "SQLEXPRESS";
        String database = "EASE";
        //String userName = "sa";
        //String password = "globaltrade11!!";
        
        //String url = environment.getRequiredProperty("jdbc.url");
 
        return getSQLServerConnection_SQLJDBC(hostName, sqlInstanceName, url, 
                database, username, password);
    }
 
    
    // Connect to SQLServer & using JTDS library
    /*public static Connection getSQLServerConnection_JTDS() throws SQLException,
            ClassNotFoundException {
        String hostName = "learningsql";
        String sqlInstanceName = "SQLEXPRESS";
        String database = "simplehr";
        String userName = "sa";
        String password = "12345";
 
        return getSQLServerConnection_JTDS(hostName, sqlInstanceName, database,
                userName, password);
    }*/
    
    // Connect to SQLServer
    // (Using JDBC Driver: SQLJDBC)
    private static Connection getSQLServerConnection_SQLJDBC(String hostName,
            String sqlInstanceName, String url, String database, String userName,
            String password) throws ClassNotFoundException, SQLException {
 
        // Declare the class Driver for SQLServer DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 
        // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
        String connectionURL = "jdbc:sqlserver://DESKTOP-MNEF780:1433;DatabaseName=EASE";
 
        Connection conn = DriverManager.getConnection(url, userName,
                password);
        return conn;
    }
    
    // Connect to SQLServer & using JTDS library
    private static Connection getSQLServerConnection_JTDS(String hostName,
            String sqlInstanceName, String database, String userName,
            String password) throws ClassNotFoundException, SQLException {
 
        // Declare the class Driver for SQLServer DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.    
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
 
        // jdbc:jtds:sqlserver://localhost:1433/simplehr;instance=SQLEXPRESS
        String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/"
                + database + ";instance=" + sqlInstanceName;
 
        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
