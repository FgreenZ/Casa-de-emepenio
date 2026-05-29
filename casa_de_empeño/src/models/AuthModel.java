package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthModel {

    // Login para la base de datos
    private final String HOST = "localhost";
    private final String PUERTO = "3306";
    private final String BASE_DATOS = "la_central_empeno";

    // Usuario y contraseña de la base de datos
    private final String USUARIO = "root";
    private final String PASSWORD = "793ghjlqASD";

    private final String URL ="jdbc:mysql://" +HOST +":" +PUERTO +"/" +BASE_DATOS;


    public AuthModel() {

    }

    public boolean autenticar(
        String usuario,
        String contrasena
    ) {

        String query =
            "SELECT * FROM usuarios "
            + "WHERE nombre_usuario = ? "
            + "AND contrasena = ?";

        Connection conn = null;

        try {

            Class.forName(
                "com.mysql.cj.jdbc.Driver"
            );

            conn =
                DriverManager.getConnection(
                    URL,
                    USUARIO,
                    PASSWORD
                );

            PreparedStatement ps =
                conn.prepareStatement(query);

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs =
                ps.executeQuery();

            boolean acceso =
                rs.next();

            rs.close();
            ps.close();
            conn.close();

            return acceso;

        } catch(Exception e) {

            System.out.println(
                "Error de autenticacion: "
                + e.getMessage()
            );
        }

        return false;
    }
    public boolean crearCuenta(

    	    String usuario,
    	    String correo,
    	    String contrasena,
    	    String rol

    	){

    	    String verificar =
    	        "SELECT * FROM usuarios "
    	        + "WHERE nombre_usuario = ? "
    	        + "OR correo = ?";

    	    String insertar =
    	        "INSERT INTO usuarios "
    	        + "(nombre_usuario, correo, contrasena, rol) "
    	        + "VALUES (?, ?, ?, ?)";

    	    Connection conn = null;

    	    try{

    	        Class.forName(
    	            "com.mysql.cj.jdbc.Driver"
    	        );

    	        conn =
    	            DriverManager.getConnection(
    	                URL,
    	                USUARIO,
    	                PASSWORD
    	            );

    	        PreparedStatement check =
    	            conn.prepareStatement(
    	                verificar
    	            );

    	        check.setString(1, usuario);
    	        check.setString(2, correo);

    	        ResultSet rs =
    	            check.executeQuery();

    	        if(rs.next()){

    	            rs.close();
    	            check.close();
    	            conn.close();

    	            System.out.println(
    	                "Usuario ya existe."
    	            );

    	            return false;
    	        }

    	        PreparedStatement ps =
    	            conn.prepareStatement(
    	                insertar
    	            );

    	        ps.setString(1, usuario);
    	        ps.setString(2, correo);
    	        ps.setString(3, contrasena);
    	        ps.setString(4, rol);

    	        boolean exito =
    	            ps.executeUpdate() > 0;

    	        ps.close();
    	        conn.close();

    	        return exito;

    	    }
    	    catch(Exception e){

    	        System.out.println(
    	            "Error creando cuenta: "
    	            + e.getMessage()
    	        );

    	    }

    	    return false;
    	}
    
}