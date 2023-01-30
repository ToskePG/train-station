package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	private String url;
	private String user;
	private String pass;
	
	public DatabaseConnection(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	public Connection open() {
		try {
			return DriverManager.getConnection(url, user, pass);
		}catch(SQLException e) {
			System.out.println("Greska u konekciji sa bazom! ");
			return null;
		}
	}
	
	public boolean close(Connection conn) {
		try {
			conn.close();
			return true;
		}catch(SQLException e) {
			System.out.println("Greska. Nemoguce je zatvoriti konekciju ");
			return false;
		}
	}
	
	
}
