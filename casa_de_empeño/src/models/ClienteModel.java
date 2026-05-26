package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ClienteModel {


    // Login para la base de datos

    private final String HOST = "sql.freedb.tech";
    private final String PUERTO = "3306";
    private final String BASE_DATOS = "freedb_RxbStPPZ";

    // Usuario y contraseña de la base de datos
    private final String USUARIO = "u_8ckZno";
    private final String PASSWORD = "qwrKPTXQeICj";

    private final String URL ="jdbc:mysql://" +HOST +":" +PUERTO +"/" +BASE_DATOS;


    private Connection conn;

    public ClienteModel() {

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

            System.out.println(
                "Conexion exitosa"
            );

        } catch (Exception e) {

            System.out.println(
                "Error: "
                + e.getMessage()
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

                        rs.getInt(
                            "id_cliente"
                        ),

                        rs.getString(
                            "nombres"
                        ),

                        rs.getString(
                            "apellidos"
                        ),

                        rs.getString(
                            "telefono"
                        ),

                        rs.getString(
                            "correo"
                        ),

                        rs.getString(
                            "direccion"
                        )
                    );

                lista.add(c);
            }

        } catch(Exception e) {

            System.out.println(
                "Error al obtener clientes: "
                + e.getMessage()
            );
        }

        return lista;
    }
}