package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthModel {

    // Login para la base de datos

    private final String HOST = "sql.freedb.tech";
    private final String PUERTO = "3306";
    private final String BASE_DATOS = "freedb_RxbStPPZ";

    // Usuario y contraseña de la base de datos
    private final String USUARIO = "u_8ckZno";
    private final String PASSWORD = "qwrKPTXQeICj";

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
}