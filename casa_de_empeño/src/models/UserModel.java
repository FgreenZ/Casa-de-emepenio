package models;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserModel {

	public UserModel() {
		
	}
	
	public ArrayList<User> get() {
		
		ArrayList<User> users = new ArrayList<>();
		String query = "SELECT * FROM users";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/application_db",
				"root",
				"root"
			);
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setNombre(rs.getString("nombre"));
				user.setCorreo(rs.getString("correo"));
				user.setTelefono(rs.getString("telefono"));
				
				users.add(user);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return users;
	}
	
	public boolean update(User user) {
		
		String query = "UPDATE users SET nombre = ?, correo = ?, telefono = ? WHERE id = ?";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/application_db",
				"root",
				"root"
			);
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getCorreo());
			ps.setString(3, user.getTelefono());
			ps.setInt(4, user.getId());
			
			int rowsAffected = ps.executeUpdate();
			
			ps.close();
			conn.close();
			
			return rowsAffected > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return false;
	}
	
	public boolean create(User user) {
		
		String query = "INSERT INTO users (nombre, correo, telefono) VALUES (?, ?, ?)";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/application_db",
				"root",
				"root"
			);
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getCorreo());
			ps.setString(3, user.getTelefono());
			
			int rowsAffected = ps.executeUpdate();
			
			ps.close();
			conn.close();
			
			return rowsAffected > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return false;
	}
	
	public boolean delete(int id) {
		
		String query = "DELETE FROM users WHERE id = ?";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/application_db",
				"root",
				"root"
			);
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			int rowsAffected = ps.executeUpdate();
			
			ps.close();
			conn.close();
			
			return rowsAffected > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {}
		}
		
		return false;
	}
}

class User {

	private int id;
	private String nombre;
	private String correo;
	private String telefono;

	public User() {
	}

	public User(int id, String nombre, String correo, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", correo='" + correo + '\'' +
				", telefono='" + telefono + '\'' +
				'}';
	}
}
*/