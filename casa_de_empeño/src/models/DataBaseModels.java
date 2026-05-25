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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DataBaseModels {
	
	private List<String[]> baseDatosClientes,baseDatosArticulos,baseDatosPagos;
	
	public DataBaseModels(List<String[]> baseDatosClientes,List<String[]> baseDatosArticulos,List<String[]> baseDatosPagos) {
		
		this.baseDatosClientes=baseDatosClientes;
		this.baseDatosArticulos=baseDatosArticulos;
		this.baseDatosPagos=baseDatosPagos;

	}
	
	//TABLA CLIENTES
	public void cargarClientes() {

	    try {

	        Connection conn =
	            DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/la_central_empeno",
	                "root",
	                "793ghjlqASD"
	            );

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
	                    color
	                }
	            );
	        }

	        conn.close();

	    } catch(Exception e) {

	        System.out.println(
	            e.getMessage()
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

	    // ENCABEZADOS

	    tabla.add(crearHeader("CLIENTE"));
	    tabla.add(crearHeader("TELÉFONO"));
	    tabla.add(crearHeader("CORREO"));
	    tabla.add(crearHeader("FECHA"));
	    tabla.add(crearHeader("ARTÍCULOS"));
	    tabla.add(crearHeader("ESTADO"));

	    // FILAS

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
	            crearCelda("ACTIVO");

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
	
	//TABLA ARTICULOS
	public void cargarArticulos() {
	
	    try {
	
	        Connection conn =
	            DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/la_central_empeno",
	                "root",
	                "793ghjlqASD"
	            );
	
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
	            e.getMessage()
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

	    // HEADERS

	    tabla.add(crearHeader("ARTICULO"));
	    tabla.add(crearHeader("CLIENTE"));
	    tabla.add(crearHeader("CATEGORIA"));
	    tabla.add(crearHeader("MONTO"));
	    tabla.add(crearHeader("FECHA"));
	    tabla.add(crearHeader("ESTADO"));

	    // FILAS

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
	
	//TABLA PAGOS
	public void cargarPagos() {

	    try {

	        Connection conn =
	            DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/la_central_empeno",
	                "root",
	                "793ghjlqASD"
	            );

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
	            e.getMessage()
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

	    // HEADERS

	    tabla.add(crearHeader("FECHA"));
	    tabla.add(crearHeader("CLIENTE"));
	    tabla.add(crearHeader("ARTICULO"));
	    tabla.add(crearHeader("MONTO"));
	    tabla.add(crearHeader("TIPO"));

	    // FILAS

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
	
}
