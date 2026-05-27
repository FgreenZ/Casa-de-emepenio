	package models;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;
	import java.util.ArrayList;
	
	public class ClienteModel {
	
	
	    // Login para la base de datos
	
	    private final String HOST = "localhost";
	    private final String PUERTO = "3306";
	    private final String BASE_DATOS = "la_central_empeno";
	
	    // Usuario y contraseña de la base de datos
	    private final String USUARIO = "root";
	    private final String PASSWORD = "";
	
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
	    public String convertirFecha(
	    	    String fecha
	    	){

	    	    if(fecha.contains("-")){

	    	        return fecha;

	    	    }

	    	    String[] partes =
	    	        fecha.split("/");

	    	    return partes[2]
	    	        + "-"
	    	        + partes[1]
	    	        + "-"
	    	        + partes[0];
	    	}
	    public boolean agregarCliente(
	    	    String nombreCompleto,
	    	    String telefono,
	    	    String correo,
	    	    String fecha
	    	) {
	
	    	    try {
	
	    	        String[] partes =
	    	            nombreCompleto.trim().split("\\s+", 2);
	
	    	        String nombres =
	    	            partes[0];
	
	    	        String apellidos =
	    	            partes.length > 1
	    	            ? partes[1]
	    	            : "";
	
	    	        String query =
	    	            "INSERT INTO clientes "
	    	            + "(nombres, apellidos, telefono, correo, fecha_registro) "
	    	            + "VALUES (?, ?, ?, ?, ?)";
	
	    	        PreparedStatement ps =
	    	            conn.prepareStatement(query);
	
	    	        ps.setString(1, nombres);
	    	        ps.setString(2, apellidos);
	    	        ps.setString(3, telefono);
	    	        ps.setString(4, correo);
	    	        ps.setString(5,convertirFecha(fecha)
	    	        	);
	    	        return ps.executeUpdate() > 0;
	
	    	    } catch(Exception e) {
	
	    	        System.out.println(
	    	            "Error insertando cliente: "
	    	            + e.getMessage()
	    	        );
	
	    	        return false;
	    	    }
	    	}
	    public boolean actualizarCliente(
	    	    int idCliente,
	    	    String nombreCompleto,
	    	    String telefono,
	    	    String correo,
	    	    String fecha
	    	) {

	    	    try {

	    	        String[] partes =
	    	            nombreCompleto.trim().split("\\s+", 2);

	    	        String nombres =
	    	            partes[0];

	    	        String apellidos =
	    	            partes.length > 1
	    	            ? partes[1]
	    	            : "";

	    	        String query =
	    	            "UPDATE clientes "
	    	            + "SET nombres = ?, "
	    	            + "apellidos = ?, "
	    	            + "telefono = ?, "
	    	            + "correo = ?, "
	    	            + "fecha_registro = ? "
	    	            + "WHERE id_cliente = ?";

	    	        PreparedStatement ps =
	    	            conn.prepareStatement(query);

	    	        ps.setString(1, nombres);
	    	        ps.setString(2, apellidos);
	    	        ps.setString(3, telefono);
	    	        ps.setString(4, correo);

	    	       
	    	        ps.setString(
	    	        		5,convertirFecha(fecha)
	    	        );

	    	        ps.setInt(6, idCliente);

	    	        return ps.executeUpdate() > 0;

	    	    } catch(Exception e) {

	    	        System.out.println(
	    	            "Error actualizando cliente: "
	    	            + e.getMessage()
	    	        );

	    	        return false;
	    	    }
	    	}
	}
	