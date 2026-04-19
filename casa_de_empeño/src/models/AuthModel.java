package models;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AuthModel {

	public AuthModel() {
		
	}
	
	public boolean login(String email,String password) {
		
		
		String query = "SELECT * FROM users WHERE email = ? AND password = ?";
		
		System.out.println(query);
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		    conn = DriverManager.getConnection(
		        "jdbc:mysql://127.0.0.1:3306/application_db",
		        "root",
		        "root"
		    );

		    PreparedStatement ps = conn.prepareStatement(query);
		    ps.setString(1, email);
		    ps.setString(2, password);

		    ResultSet rs = ps.executeQuery();
			
		    if (rs.next()) {
		    	 
		        return true;
		    }  
			
		    rs.close();
		    ps.close();
		    conn.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {}
		}
		
		return false; 
		
		 
	}
}
 */
