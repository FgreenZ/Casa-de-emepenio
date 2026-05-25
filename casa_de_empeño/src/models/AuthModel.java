package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AuthModel {

    public AuthModel() {

    }

    public boolean autenticar(
            String usuario,
            String contrasena)
    {

        String query =
        "SELECT * FROM usuarios " +
        "WHERE nombre_usuario = ? " +
        "AND contrasena = ?";

        Connection conn = null;

        try {

            Class.forName(
                "com.mysql.cj.jdbc.Driver"
            );

            conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/la_central_empeno",
                "root",
                "793ghjlqASD"
            );

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs =
                    ps.executeQuery();

            boolean acceso = rs.next();

            rs.close();
            ps.close();
            conn.close();

            return acceso;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

}