package views;

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

                String idPago = rs.getString("id_pago");
                String idArticulo = rs.getString("id_articulo");
                String idCliente = rs.getString("id_cliente");

                baseDatosPagos.add(

                    new String[] {

                        fecha,         // [0]
                        cliente,       // [1]
                        articulo,      // [2]
                        monto,         // [3]
                        tipoPago,      // [4]
                        colorEliminar, // [5]
                        colorEditar,   // [6]
                        colorDetalles, // [7]
                        idPago,        // [8]
                        idArticulo,    // [9]
                        idCliente      // [10]
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

    	        psBuscar.setString(1,cliente);

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
    
    public boolean agregarPago(
            String cliente,
            String articuloEmpeñado,
            String fechaPago,
            String estado,
            String montoAbonado,
            String notas
    ) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            String buscarCliente =
                "SELECT id_cliente FROM clientes " +
                "WHERE CONCAT(nombres, ' ', apellidos) = ?";

            PreparedStatement psCliente = conn.prepareStatement(buscarCliente);
            psCliente.setString(1, cliente);
            ResultSet rsCliente = psCliente.executeQuery();

            if (!rsCliente.next()) {
                conn.close();
                return false; 
            }
            int idCliente = rsCliente.getInt("id_cliente");
            rsCliente.close();
            psCliente.close();

            String buscarArticulo =
                "SELECT id_articulo FROM articulos " +
                "WHERE nombre_articulo = ?";

            PreparedStatement psArticulo = conn.prepareStatement(buscarArticulo);
            psArticulo.setString(1, articuloEmpeñado);
            ResultSet rsArticulo = psArticulo.executeQuery();

            if (!rsArticulo.next()) {
                conn.close();
                return false; 
            }
            int idArticulo = rsArticulo.getInt("id_articulo");
            rsArticulo.close();
            psArticulo.close();

            double monto = Double.parseDouble(
                montoAbonado
                    .replace("$", "")
                    .replace(",", "")
                    .trim()
            );

            java.sql.Date fechaSql = java.sql.Date.valueOf(
                convertirFecha(fechaPago)
            );

            String query =
                "INSERT INTO pagos(" +
                    "id_cliente, id_articulo, fecha_pago, tipo_pago, " +
                    "monto_abonado, id_usuario" +
                ") VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.setInt(2, idArticulo);
            ps.setDate(3, fechaSql);
            ps.setString(4, estado.toUpperCase());
            ps.setDouble(5, monto);
            ps.setInt(6, 1); 

            boolean ok = ps.executeUpdate() > 0;

            conn.close();
            return ok;

        } catch (Exception e) {
            System.out.println("Error al agregar pago: " + e.getMessage());
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
		            + "WHERE id_cliente = ? AND estado = 'EMPE\u00d1ADO'";

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

    // ===== CLASE DE ESTADÍSTICAS =====
    // ============================
    // DATOS DE DEUDA DE UN ARTÍCULO
    // Retorna: [0]=montoPrestado, [1]=totalAbonado, [2]=montoRestante
    // ============================
    public double[] obtenerDatosDeudaArticulo(int idArticulo) {
        double montoPrestado = 0, totalAbonado = 0;
        try {
            Connection conn = conectar();

            PreparedStatement ps1 = conn.prepareStatement(
                "SELECT monto_prestado FROM articulos WHERE id_articulo = ?"
            );
            ps1.setInt(1, idArticulo);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) montoPrestado = rs1.getDouble("monto_prestado");

            PreparedStatement ps2 = conn.prepareStatement(
                "SELECT COALESCE(SUM(monto_abonado), 0) AS total FROM pagos WHERE id_articulo = ?"
            );
            ps2.setInt(1, idArticulo);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) totalAbonado = rs2.getDouble("total");

            conn.close();
        } catch (Exception e) {
            System.out.println("Error obtenerDatosDeuda: " + e.getMessage());
        }
        double restante = Math.max(0, montoPrestado - totalAbonado);
        return new double[]{ montoPrestado, totalAbonado, restante };
    }

        // ============================
    // OBTENER ULTIMO id_pago POR ARTICULO
    // ============================
    public int obtenerUltimoPagoPorArticulo(int idArticulo) {
        try {
            Connection conn = conectar();
            String query =
                "SELECT id_pago FROM pagos " +
                "WHERE id_articulo = ? " +
                "ORDER BY id_pago DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idArticulo);
            ResultSet rs = ps.executeQuery();
            int id = rs.next() ? rs.getInt("id_pago") : -1;
            conn.close();
            return id;
        } catch (Exception e) {
            System.out.println("Error obtenerUltimoPago: " + e.getMessage());
            return -1;
        }
    }

        // ============================
    // AUXILIARES: obtener IDs por nombre
    // ============================
    public int obtenerIdCliente(String nombreCompleto) {
        try {
            Connection conn = conectar();
            String query = "SELECT id_cliente FROM clientes WHERE CONCAT(nombres, ' ', apellidos) = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombreCompleto);
            ResultSet rs = ps.executeQuery();
            int id = rs.next() ? rs.getInt("id_cliente") : -1;
            conn.close();
            return id;
        } catch (Exception e) {
            System.out.println("Error obtenerIdCliente: " + e.getMessage());
            return -1;
        }
    }

    public int obtenerIdArticulo(String nombreArticulo, int idCliente) {
        try {
            Connection conn = conectar();
            String query = "SELECT id_articulo FROM articulos WHERE nombre_articulo = ? AND id_cliente = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombreArticulo);
            ps.setInt(2, idCliente);
            ResultSet rs = ps.executeQuery();
            int id = rs.next() ? rs.getInt("id_articulo") : -1;
            conn.close();
            return id;
        } catch (Exception e) {
            System.out.println("Error obtenerIdArticulo: " + e.getMessage());
            return -1;
        }
    }

        // ============================
    // ELIMINAR PAGO
    // ============================
    public boolean eliminarPago(int idPago) {
        try {
            Connection conn = conectar();
            String query = "DELETE FROM pagos WHERE id_pago = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPago);
            boolean ok = ps.executeUpdate() > 0;
            conn.close();
            return ok;
        } catch (Exception e) {
            System.out.println("Error eliminando pago: " + e.getMessage());
            return false;
        }
    }

    // ============================
    // ACTUALIZAR PAGO
    // ============================
    public boolean actualizarPago(
            int idPago,
            int idArticulo,
            int idCliente,
            String fechaPago,
            double montoAbonado,
            String tipoPago
    ) {
        try {
            Connection conn = conectar();

            // Calcular montoRestante sumando abonos anteriores (excluyendo este pago)
            String queryPrev =
                "SELECT SUM(monto_abonado) AS total " +
                "FROM pagos " +
                "WHERE id_articulo = ? AND id_pago != ?";
            PreparedStatement psPrev = conn.prepareStatement(queryPrev);
            psPrev.setInt(1, idArticulo);
            psPrev.setInt(2, idPago);
            ResultSet rsPrev = psPrev.executeQuery();
            double totalPrevio = 0;
            if (rsPrev.next()) {
                totalPrevio = rsPrev.getDouble("total");
            }

            // Obtener monto prestado del articulo
            String queryArt =
                "SELECT monto_prestado FROM articulos WHERE id_articulo = ?";
            PreparedStatement psArt = conn.prepareStatement(queryArt);
            psArt.setInt(1, idArticulo);
            ResultSet rsArt = psArt.executeQuery();
            double montoPrestado = 0;
            if (rsArt.next()) {
                montoPrestado = rsArt.getDouble("monto_prestado");
            }

            double montoRestante = montoPrestado - totalPrevio - montoAbonado;
            if (montoRestante < 0) montoRestante = 0;
            double interesGenerado = montoRestante * 0.10;

            String query =
                "UPDATE pagos SET " +
                "fecha_pago = ?, " +
                "monto_abonado = ?, " +
                "monto_restante = ?, " +
                "tipo_pago = ?, " +
                "interes_generado = ? " +
                "WHERE id_pago = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, fechaPago);
            ps.setDouble(2, montoAbonado);
            ps.setDouble(3, montoRestante);
            ps.setString(4, tipoPago.toUpperCase());
            ps.setDouble(5, interesGenerado);
            ps.setInt(6, idPago);
            boolean ok = ps.executeUpdate() > 0;

            // Actualizar estado del articulo si ya esta pagado
            if (montoRestante <= 0) {
                String upd = "UPDATE articulos SET estado='RECUPERADO' WHERE id_articulo=?";
                PreparedStatement psUpd = conn.prepareStatement(upd);
                psUpd.setInt(1, idArticulo);
                psUpd.executeUpdate();
            }

            conn.close();
            return ok;
        } catch (Exception e) {
            System.out.println("Error actualizando pago: " + e.getMessage());
            return false;
        }
    }

        public static class Estadisticas {
        public int totalClientes;
        public int articulosEmpenados;
        public int articulosRecuperados;
        public int articulosRematados;
        public double totalPrestado;
        public double totalRecuperado;
    }

    // ===== MÉTODO QUE CONSULTA LA BD Y DEVUELVE LAS ESTADÍSTICAS =====
    public Estadisticas obtenerEstadisticas() {

        Estadisticas stats = new Estadisticas();

        try {

            Connection conn = conectar();

            // Total clientes
            PreparedStatement psClientes =
                conn.prepareStatement(
                    "SELECT COUNT(*) AS total FROM clientes"
                );
            ResultSet rsClientes = psClientes.executeQuery();
            if (rsClientes.next()) {
                stats.totalClientes = rsClientes.getInt("total");
            }

            // Conteos por estado de artículos
            PreparedStatement psArts =
                conn.prepareStatement(
                    "SELECT estado, COUNT(*) AS cnt "
                    + "FROM articulos "
                    + "GROUP BY estado"
                );
            ResultSet rsArts = psArts.executeQuery();
            while (rsArts.next()) {
                String estado = rsArts.getString("estado");
                int cnt = rsArts.getInt("cnt");
                if ("EMPEÑADO".equalsIgnoreCase(estado)) {
                    stats.articulosEmpenados = cnt;
                } else if ("RECUPERADO".equalsIgnoreCase(estado)) {
                    stats.articulosRecuperados = cnt;
                } else if ("REMATADO".equalsIgnoreCase(estado)) {
                    stats.articulosRematados = cnt;
                }
            }

            // Total prestado (suma de monto_prestado de todos los artículos)
            PreparedStatement psPrestado =
                conn.prepareStatement(
                    "SELECT COALESCE(SUM(monto_prestado), 0) AS total "
                    + "FROM articulos"
                );
            ResultSet rsPrestado = psPrestado.executeQuery();
            if (rsPrestado.next()) {
                stats.totalPrestado = rsPrestado.getDouble("total");
            }

            // Total recuperado (suma de montos abonados en pagos)
            PreparedStatement psRecuperado =
                conn.prepareStatement(
                    "SELECT COALESCE(SUM(monto_abonado), 0) AS total "
                    + "FROM pagos"
                );
            ResultSet rsRecuperado = psRecuperado.executeQuery();
            if (rsRecuperado.next()) {
                stats.totalRecuperado = rsRecuperado.getDouble("total");
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(
                "Error obteniendo estadísticas: "
                + e.getMessage()
            );
        }

        return stats;
    }
}