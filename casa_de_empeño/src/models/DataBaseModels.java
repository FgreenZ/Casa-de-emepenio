package models;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.ComboItem;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DataBaseModels {

    // Login para la base de datos

    private final String HOST = "localhost";
    private final String PUERTO = "3306";
    private final String BASE_DATOS = "la_central_empeno";

    // Usuario y contraseña de la base de datos
    private final String USUARIO = "root";
    private final String PASSWORD = "";

    private final String URL ="jdbc:mysql://" +HOST +":" +PUERTO +"/" +BASE_DATOS;


    private List<String[]> baseDatosClientes;
    private List<String[]> baseDatosArticulos;
    private List<String[]> baseDatosPagos;

    public DataBaseModels(
        List<String[]> baseDatosClientes,
        List<String[]> baseDatosArticulos,
        List<String[]> baseDatosPagos
    ) {

        this.baseDatosClientes = baseDatosClientes;
        this.baseDatosArticulos = baseDatosArticulos;
        this.baseDatosPagos = baseDatosPagos;
    }


    public Connection conectar() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
            URL,
            USUARIO,
            PASSWORD
        );
    }

    // TABLA CLIENTES

    public void cargarClientes() {

        try {

            Connection conn = conectar();

            String query =
                "SELECT * FROM clientes";

            PreparedStatement ps =
                conn.prepareStatement(query);

            ResultSet rs =
                ps.executeQuery();

            baseDatosClientes.clear();

            while(rs.next()) {

                String nombreCompleto =
                    rs.getString("nombres")
                    + " "
                    + rs.getString("apellidos");

                String telefono =
                    rs.getString("telefono");

                String correo =
                    rs.getString("correo");

                String fecha =
                    rs.getString("fecha_registro");

                String cantidadArticulos =
                    "0";

                String color =
                    "#AEE7B8";

                baseDatosClientes.add(
                	    new String[] {
                	        nombreCompleto,
                	        telefono,
                	        correo,
                	        fecha,
                	        cantidadArticulos,
                	        color,
                	        rs.getString("id_cliente")
                	    }
                	);
            }

            conn.close();

        } catch(Exception e) {

            System.out.println(
                "Error clientes: "
                + e.getMessage()
            );
        }
    }

    public JPanel crearTablaClientes() {

        JPanel tabla =
            new JPanel();

        tabla.setLayout(
            new GridLayout(
                baseDatosClientes.size() + 1,
                6
            )
        );

        tabla.add(crearHeader("CLIENTE"));
        tabla.add(crearHeader("TELÉFONO"));
        tabla.add(crearHeader("CORREO"));
        tabla.add(crearHeader("FECHA"));
        tabla.add(crearHeader("ARTÍCULOS"));
        tabla.add(crearHeader("ESTADO"));

        for(String[] cliente : baseDatosClientes) {

            JLabel nombre =
                crearCelda(cliente[0]);

            JLabel telefono =
                crearCelda(cliente[1]);

            JLabel correo =
                crearCelda(cliente[2]);

            JLabel fecha =
                crearCelda(cliente[3]);

            JLabel articulos =
                crearCelda(cliente[4]);

            JLabel estado =
                crearCelda("IVO");

            estado.setOpaque(true);

            estado.setBackground(
                Color.decode(cliente[5])
            );

            tabla.add(nombre);
            tabla.add(telefono);
            tabla.add(correo);
            tabla.add(fecha);
            tabla.add(articulos);
            tabla.add(estado);
        }

        return tabla;
    }

    // TABLA ARTICULOS
    public List<String> obtenerClientes(){

        List<String> clientes =
            new ArrayList<>();

        try{

            Connection conn =
                conectar();

            String query =
                "SELECT nombres, apellidos "
                + "FROM clientes";

            PreparedStatement ps =
                conn.prepareStatement(
                    query
                );

            ResultSet rs =
                ps.executeQuery();

            while(rs.next()){

                clientes.add(

                    rs.getString("nombres")

                    + " "

                    +

                    rs.getString("apellidos")

                );

            }

            conn.close();

        }
        catch(Exception e){

            e.printStackTrace();

        }

        return clientes;
    }
    public void cargarArticulos() {

        try {

            Connection conn = conectar();

            String query =
                "SELECT "
                + "a.*, "
                + "c.nombres, "
                + "c.apellidos "
                + "FROM articulos a "
                + "INNER JOIN clientes c "
                + "ON a.id_cliente = c.id_cliente";

            PreparedStatement ps =
                conn.prepareStatement(query);

            ResultSet rs =
                ps.executeQuery();

            baseDatosArticulos.clear();

            while(rs.next()) {

                String nombreArticulo =
                    rs.getString("nombre_articulo");

                String cliente =
                    rs.getString("nombres")
                    + " "
                    + rs.getString("apellidos");

                String categoria =
                    rs.getString("categoria");

                String monto =
                    "$" + rs.getString("monto_prestado");

                String fecha =
                    rs.getString("fecha_empeno");

                String estado =
                    rs.getString("estado");

                String colorFondo =
                    "#FFF9C4";

                String colorTexto =
                    "#FBC02D";

                if(estado.equals("RECUPERADO")) {

                    colorFondo = "#C8E6C9";
                    colorTexto = "#388E3C";
                }

                if(estado.equals("REMATADO")) {

                    colorFondo = "#FFCDD2";
                    colorTexto = "#D32F2F";
                }

                String valorEstimado =
                    "$" + rs.getString("valor_estimado");

                String fechaLimite =
                    rs.getString("fecha_limite_pago");

                String descripcion =
                    rs.getString("descripcion");

                baseDatosArticulos.add(

                    new String[] {

                        nombreArticulo,
                        cliente,
                        categoria,
                        monto,
                        fecha,
                        estado,
                        colorFondo,
                        colorTexto,
                        valorEstimado,
                        fechaLimite,
                        descripcion
                    }
                );
            }

            conn.close();

        } catch(Exception e) {

            System.out.println(
                "Error articulos: "
                + e.getMessage()
            );
        }
    }

    public JPanel crearTablaArticulos() {

        JPanel tabla =
            new JPanel();

        tabla.setLayout(
            new GridLayout(
                baseDatosArticulos.size() + 1,
                6
            )
        );

        tabla.add(crearHeader("ARTICULO"));
        tabla.add(crearHeader("CLIENTE"));
        tabla.add(crearHeader("CATEGORIA"));
        tabla.add(crearHeader("MONTO"));
        tabla.add(crearHeader("FECHA"));
        tabla.add(crearHeader("ESTADO"));

        for(String[] articulo : baseDatosArticulos) {

            JLabel nombre =
                crearCelda(articulo[0]);

            JLabel cliente =
                crearCelda(articulo[1]);

            JLabel categoria =
                crearCelda(articulo[2]);

            JLabel monto =
                crearCelda(articulo[3]);

            JLabel fecha =
                crearCelda(articulo[4]);

            JLabel estado =
                crearCelda(articulo[5]);

            estado.setOpaque(true);

            estado.setBackground(
                Color.decode(articulo[6])
            );

            estado.setForeground(
                Color.decode(articulo[7])
            );

            tabla.add(nombre);
            tabla.add(cliente);
            tabla.add(categoria);
            tabla.add(monto);
            tabla.add(fecha);
            tabla.add(estado);
        }

        return tabla;
    }

    // TABLA PAGOS

    public void cargarPagos() {

        try {

            Connection conn = conectar();

            String query =
                "SELECT "
                + "p.*, "
                + "c.nombres, "
                + "c.apellidos, "
                + "a.nombre_articulo "
                + "FROM pagos p "
                + "INNER JOIN clientes c "
                + "ON p.id_cliente = c.id_cliente "
                + "INNER JOIN articulos a "
                + "ON p.id_articulo = a.id_articulo";

            PreparedStatement ps =
                conn.prepareStatement(query);

            ResultSet rs =
                ps.executeQuery();

            baseDatosPagos.clear();

            while(rs.next()) {

                String fecha =
                    rs.getString("fecha_pago");

                String cliente =
                    rs.getString("nombres")
                    + " "
                    + rs.getString("apellidos");

                String articulo =
                    rs.getString("nombre_articulo");

                String monto =
                    "$" + rs.getString("monto_abonado");

                String tipoPago =
                    rs.getString("tipo_pago");

                String colorEliminar =
                    "#FFCDD2";

                String colorEditar =
                    "#AEE7B8";

                String colorDetalles =
                    "#AEE7B8";

                baseDatosPagos.add(

                    new String[] {

                        fecha,
                        cliente,
                        articulo,
                        monto,
                        tipoPago,
                        colorEliminar,
                        colorEditar,
                        colorDetalles
                    }
                );
            }

            conn.close();

        } catch(Exception e) {

            System.out.println(
                "Error pagos: "
                + e.getMessage()
            );
        }
    }

    public JPanel crearTablaPagos() {

        JPanel tabla =
            new JPanel();

        tabla.setLayout(
            new GridLayout(
                baseDatosPagos.size() + 1,
                5
            )
        );

        tabla.add(crearHeader("FECHA"));
        tabla.add(crearHeader("CLIENTE"));
        tabla.add(crearHeader("ARTICULO"));
        tabla.add(crearHeader("MONTO"));
        tabla.add(crearHeader("TIPO"));

        for(String[] pago : baseDatosPagos) {

            JLabel fecha =
                crearCelda(pago[0]);

            JLabel cliente =
                crearCelda(pago[1]);

            JLabel articulo =
                crearCelda(pago[2]);

            JLabel monto =
                crearCelda(pago[3]);

            JLabel tipo =
                crearCelda(pago[4]);

            tabla.add(fecha);
            tabla.add(cliente);
            tabla.add(articulo);
            tabla.add(monto);
            tabla.add(tipo);
        }

        return tabla;
    }

    // COMPONENTES

    private JLabel crearHeader(String texto) {

        JLabel label =
            new JLabel(
                texto,
                SwingConstants.CENTER
            );

        label.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        label.setBorder(
            BorderFactory.createLineBorder(
                Color.GRAY
            )
        );

        label.setOpaque(true);

        label.setBackground(
            new Color(230,230,230)
        );

        return label;
    }

    private JLabel crearCelda(String texto) {

        JLabel label =
            new JLabel(
                texto,
                SwingConstants.CENTER
            );

        label.setBorder(
            BorderFactory.createLineBorder(
                Color.LIGHT_GRAY
            )
        );

        return label;
    }
    public String convertirFecha(
    	    String fecha
    	){

    	    String[] partes =
    	        fecha.split("/");

    	    return partes[2]
    	        + "-"
    	        + partes[1]
    	        + "-"
    	        + partes[0];
    	}
    public boolean agregarArticulo(

    	    String cliente,
    	    String nombreArticulo,
    	    String categoria,
    	    String montoPrestado,
    	    String fechaLimite,
    	    String estado,
    	    String valorEstimado,
    	    String fechaEmpeno,
    	    String descripcion

    	){

    	    Connection conn = null;

    	    try{

    	        conn =
    	            DriverManager.getConnection(
    	                URL,
    	                USUARIO,
    	                PASSWORD
    	            );

    	        String buscarCliente =
    	            "SELECT id_cliente "
    	            + "FROM clientes "
    	            + "WHERE CONCAT(nombres,' ',apellidos)=?";

    	        PreparedStatement psBuscar =
    	            conn.prepareStatement(
    	                buscarCliente
    	            );

    	        psBuscar.setString(
    	            1,
    	            cliente
    	        );

    	        ResultSet rs =
    	            psBuscar.executeQuery();

    	        if(!rs.next()){

    	            conn.close();

    	            return false;
    	        }

    	        int idCliente =
    	            rs.getInt(
    	                "id_cliente"
    	            );

    	        int idUsuario = 1;

    	        double valor =
    	            Double.parseDouble(
    	                valorEstimado
    	                .replace("$","")
    	                .replace(",","")
    	                .trim()
    	            );

    	        double monto =
    	            Double.parseDouble(
    	                montoPrestado
    	                .replace("$","")
    	                .replace(",","")
    	                .trim()
    	            );

    	        String query =
    	            "INSERT INTO articulos("
    	            + "id_cliente,"
    	            + "id_usuario,"
    	            + "nombre_articulo,"
    	            + "categoria,"
    	            + "descripcion,"
    	            + "valor_estimado,"
    	            + "monto_prestado,"
    	            + "fecha_empeno,"
    	            + "fecha_limite_pago,"
    	            + "estado"
    	            + ") VALUES(?,?,?,?,?,?,?,?,?,?)";

    	        PreparedStatement ps =
    	            conn.prepareStatement(
    	                query
    	            );

    	        ps.setInt(1,idCliente);

    	        ps.setInt(
    	            2,
    	            idUsuario
    	        );

    	        ps.setString(
    	            3,
    	            nombreArticulo
    	        );

    	        ps.setString(
    	            4,
    	            categoria
    	        );

    	        ps.setString(
    	            5,
    	            descripcion
    	        );

    	        ps.setDouble(
    	            6,
    	            valor
    	        );

    	        ps.setDouble(
    	            7,
    	            monto
    	        );

    	        ps.setDate(
    	            8,
    	            java.sql.Date.valueOf(
    	                convertirFecha(
    	                    fechaEmpeno
    	                )
    	            )
    	        );

    	        ps.setDate(
    	            9,
    	            java.sql.Date.valueOf(
    	                convertirFecha(
    	                    fechaLimite
    	                )
    	            )
    	        );

    	        ps.setString(
    	            10,
    	            estado.toUpperCase()
    	        );

    	        boolean ok =
    	            ps.executeUpdate() > 0;

    	        conn.close();

    	        return ok;

    	    }
    	    catch(Exception e){

    	        System.out.println(
    	            "Error artículo: "
    	            + e.getMessage()
    	        );

    	    }

    	    return false;
    	}

	public List<ComboItem> obtenerArticulosPorCliente(
		    int idCliente
		) {

		    List<ComboItem> articulos =
		        new ArrayList<>();

		    try {

		        Connection conn =
		            conectar();

		        String query =
		            "SELECT id_articulo, nombre_articulo "
		            + "FROM articulos "
		            + "WHERE id_cliente = ?";

		        PreparedStatement ps =
		            conn.prepareStatement(query);

		        ps.setInt(1, idCliente);

		        ResultSet rs =
		            ps.executeQuery();

		        while(rs.next()) {

		            articulos.add(

		                new ComboItem(

		                    rs.getInt("id_articulo"),

		                    rs.getString(
		                        "nombre_articulo"
		                    )
		                )
		            );
		        }

		        conn.close();

		    } catch(Exception e) {

		        System.out.println(
		            "Error articulos: "
		            + e.getMessage()
		        );
		    }

		    return articulos;
	}
	public boolean registrarPago(

		    int idArticulo,
		    int idCliente,
		    int idUsuario,
		    String fechaPago,
		    double montoAbonado,
		    double montoRestante,
		    String tipoPago,
		    double interesGenerado

		) {

		    try {

		        Connection conn = conectar();

		        // OBTENER MONTO PRESTADO

		        String queryPrestamo =

		            "SELECT monto_prestado "
		            + "FROM articulos "
		            + "WHERE id_articulo=?";

		        PreparedStatement psPrestamo =
		            conn.prepareStatement(queryPrestamo);

		        psPrestamo.setInt(1, idArticulo);

		        ResultSet rsPrestamo =
		            psPrestamo.executeQuery();

		        double montoPrestado = 0;

		        if(rsPrestamo.next()) {

		            montoPrestado =
		                rsPrestamo.getDouble(
		                    "monto_prestado"
		                );
		        }

		        // SUMAR PAGOS ANTERIORES

		        String queryPagos =

		            "SELECT SUM(monto_abonado) AS total "
		            + "FROM pagos "
		            + "WHERE id_articulo=?";

		        PreparedStatement psPagos =
		            conn.prepareStatement(queryPagos);

		        psPagos.setInt(1, idArticulo);

		        ResultSet rsPagos =
		            psPagos.executeQuery();

		        double totalPagado = 0;

		        if(rsPagos.next()) {

		            totalPagado =
		                rsPagos.getDouble(
		                    "total"
		                );
		        }

		        // CALCULAR SALDO RESTANTE

		        montoRestante =

		            montoPrestado
		            - totalPagado
		            - montoAbonado;

		        // evitar negativos
		        if(montoRestante < 0) {

		            montoRestante = 0;
		        }

		        // CALCULAR INTERES

		        interesGenerado =

		            montoRestante * 0.10;

		        // INSERTAR PAGO

		        String query =

		            "INSERT INTO pagos "
		            + "("
		            + "id_articulo,"
		            + "id_cliente,"
		            + "id_usuario,"
		            + "fecha_pago,"
		            + "monto_abonado,"
		            + "monto_restante,"
		            + "tipo_pago,"
		            + "interes_generado"
		            + ") "
		            + "VALUES(?,?,?,?,?,?,?,?)";

		        PreparedStatement ps =
		            conn.prepareStatement(query);

		        ps.setInt(1, idArticulo);

		        ps.setInt(2, idCliente);

		        ps.setInt(3, idUsuario);

		        ps.setString(4, fechaPago);

		        ps.setDouble(5, montoAbonado);

		        ps.setDouble(6, montoRestante);

		        ps.setString(7, tipoPago);

		        ps.setDouble(8, interesGenerado);

		        ps.executeUpdate();

		        // CAMBIAR ESTADO A RECUPERADO

		        if(montoRestante <= 0) {

		            String update =

		                "UPDATE articulos "
		                + "SET estado='RECUPERADO' "
		                + "WHERE id_articulo=?";

		            PreparedStatement psUpdate =
		                conn.prepareStatement(update);

		            psUpdate.setInt(1, idArticulo);

		            psUpdate.executeUpdate();
		        }

		        conn.close();

		        return true;

		    } catch(Exception e) {

		        System.out.println(
		            "Error registrando pago: "
		            + e.getMessage()
		        );

		        return false;
		    }
		}
}