package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ClienteModel {

    Connection conn;

    public ClienteModel() {

        try {

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/la_central_empeno",
                "root",
                ""
            );

            System.out.println("Conexion exitosa");

        } catch (Exception e) {

            System.out.println(
                "Error: " + e.getMessage()
            );
        }
    }

    public ArrayList<Cliente> getClientes() {

        ArrayList<Cliente> lista =
            new ArrayList<>();

        try {

            String query =
                "SELECT * FROM clientes";

            PreparedStatement ps =
                conn.prepareStatement(query);

            ResultSet rs =
                ps.executeQuery();

            while(rs.next()) {

                Cliente c =
                    new Cliente(

                        rs.getInt("id_cliente"),

                        rs.getString("nombres"),

                        rs.getString("apellidos"),

                        rs.getString("telefono"),

                        rs.getString("correo"),

                        rs.getString("direccion")
                    );

                lista.add(c);
            }

        } catch(Exception e) {

            System.out.println(
                e.getMessage()
            );
        }

        return lista;
    }
}