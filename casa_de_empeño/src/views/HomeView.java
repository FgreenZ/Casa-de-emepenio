package views;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import controllers.HomeController;

class PanelRedondeado extends JPanel {
    private int radio;
    private Color colorFondo;

    public PanelRedondeado(int radio, Color colorFondo) {
        this.radio = radio;
        this.colorFondo = colorFondo;
        setOpaque(false);
    }
    private void limpiarPanelTabla(JPanel panel) {
        if (panel != null) {
            panel.removeAll(); // Elimina todos los componentes (etiquetas, botones, etc.)
            panel.revalidate(); // Recalcula la estructura interna del panel
            panel.repaint();    // Borra visualmente lo que quedó en pantalla
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        super.paintComponent(g);
    }
}

class PanelCirculo extends JPanel {
    private Color colorFondo;

    public PanelCirculo(Color colorFondo) {
        this.colorFondo = colorFondo;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorFondo);
        g2.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
//|----VISTAS----|
public class HomeView extends JPanel{

    private List<String[]> baseDatosClientes = new ArrayList<>(Arrays.asList(
            new String[]{"Juan Pérez García", "6121234567", "juan.perez@email.com", "14/1/2025", "1", "#AEE7B8"},
            new String[]{"María López Hernández", "6121418223", "maria.lopez@email.com", "19/2/2025", "1", "#AEE7B8"},
            new String[]{"Carlos Rodríguez Martínez", "6122898724", "carlos.rdgz@email.com", "9/3/2025", "0", "#AEE7B8"},
            new String[]{"Emmanuel García", "6125551234", "emmanuel@email.com", "10/4/2025", "2", "#AEE7B8"}
        ));
    
    private List<String[]> baseDatosArticulos = new ArrayList<>(Arrays.asList(
    		new String[]{"Anillo de Oro 14K", "Juan Pérez García", "Joyería", "$5,000", "14/6/2025", "Empeñado", "#FFF9C4", "#FBC02D","$10,000","20/13/2027","Sin descripcion por el momento"},
    		new String[]{"Laptop Dell XPS 15", "Juan Pérez García", "Electrónica", "$8,000", "30/4/2025", "Recuperado", "#C8E6C9", "#388E3C","$10,000","20/13/2027","Sin descripcion por el momento"},
    		new String[]{"Collar de Perlas", "María López Hernández", "Joyería", "$3,500", "19/6/2025", "Empeñado", "#FFF9C4", "#FBC02D","$10,000","20/13/2027","Sin descripcion por el momento"},
    		new String[] {"iPhone 14 Pro", "Carlos Rodríguez Martínez", "Electrónica", "$10,000", "9/4/2025", "Rematado", "#FFCDD2", "#D32F2F","$10,000","20/13/2027","Sin descripcion por el momento"}
    		));
    
    private JPanel panelTablaGlobal,panelTablaArticulos;

	public HomeView() {
		// TODO Auto-generated constructor stub
	}
	
	public void home()
	{
		
		JFrame ventana = new JFrame();
		
		ventana.setSize(1000, 640);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setMinimumSize(new Dimension(200,200));
		ventana.setMaximumSize(new Dimension(1200,800));
		ventana.setTitle("Hola"); 
		ventana.getContentPane().setBackground(Color.decode("#C8DEBD"));
		ventana.setLayout(null); 
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255)); 
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setSize(1000, 600);  
		
		ventana.add(panel);
		
		ventana.setVisible(true);
		
	}
	
    public void bienvenidoDashboard() {
    // 1. Configuración de la Ventana Principal
		JFrame ventana = new JFrame();
		ventana.setTitle("Sistema Administrativo - Dashboard");
		ventana.setSize(1150, 660);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
		ventana.setLayout(null); // Diseño absoluto para usar setBounds
		ventana.getContentPane().setBackground(Color.decode("#F4F6F9")); // Fondo base de la ventana

        // 2. Panel Izquierdo (Menú lateral)
		 // 2. Panel Izquierdo (Menú lateral)
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 140, 220, 540); 
        panelMenu.setBackground(Color.decode("#375A9B"));
        ventana.add(panelMenu);
        
        // 3. Logo y Título del Sistema
        ImageIcon icon = new ImageIcon("src/img/logo (1).png");
        Image img = icon.getImage().getScaledInstance(67, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBounds(80, 20, 67, 100);
        ventana.add(logo);
        
        JLabel titleimg = new JLabel("Sistema administrativo");
        titleimg.setFont(new Font("Inter", Font.PLAIN, 12));
        titleimg.setBounds(55, 105, 200, 14);
        titleimg.setForeground(Color.GRAY);
        ventana.add(titleimg);
        
        // 4. Botones del Menú
        // Botón: Dashboard (Activo)
        PanelRedondeado btnDashboard = new PanelRedondeado(10, new Color(138, 172, 235, 100)); // Color con transparencia
        btnDashboard.setLayout(null);
        btnDashboard.setBounds(25, 30, 200, 40);
        JButton lblMenuDash = new JButton("  \u25A6  Dashboard");
        lblMenuDash.setForeground(Color.WHITE);
        lblMenuDash.setFont(new Font("Inter", Font.BOLD, 18));
        lblMenuDash.setBounds(11, 0, 200, 40);
        lblMenuDash.setBackground(Color.decode("#375A9B"));
        lblMenuDash.setBorderPainted(false);
        lblMenuDash.setFocusPainted(false);
        lblMenuDash.setBorder(null);
        lblMenuDash.setContentAreaFilled(false);
        btnDashboard.add(lblMenuDash);
        panelMenu.add(btnDashboard);

        // Botón: Clientes
        JButton lblMenuClientes = new JButton("  \u25A6  Clientes");
        lblMenuClientes.setForeground(Color.decode("#C8C8C8"));
        lblMenuClientes.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuClientes.setBounds(20, 150, 200, 40);
        lblMenuClientes.setBackground(Color.decode("#375A9B"));
        lblMenuClientes.setBorderPainted(false);
        lblMenuClientes.setFocusPainted(false);
        lblMenuClientes.setBorder(null);
        lblMenuClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuClientes);
        lblMenuClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();       // 1. Cierra la ventana actual (Dashboard)
                dashboardClientes();     // 2. Abre la ventana de Clientes
            }
        });

        // Botón: Artículos
        JButton lblMenuArticulos = new JButton("  \u25A6  Artículos");
        lblMenuArticulos.setForeground(Color.decode("#C8C8C8"));
        lblMenuArticulos.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuArticulos.setBounds(20, 270, 200, 40);
        lblMenuArticulos.setBackground(Color.decode("#375A9B"));
        lblMenuArticulos.setBorderPainted(false);
        lblMenuArticulos.setFocusPainted(false);
        lblMenuArticulos.setBorder(null);
        lblMenuArticulos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblMenuArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();       // 1. Cierra la ventana actual (Dashboard)
                dashboardArticulos();     // 2. Abre la ventana de Clientes
            }
        });
        panelMenu.add(lblMenuArticulos);

        // Botón: Pagos
        JButton lblMenuPagos = new JButton("  \u25A6  Pagos");
        lblMenuPagos.setForeground(Color.decode("#C8C8C8"));
        lblMenuPagos.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuPagos.setBounds(20, 390, 200, 40);
        lblMenuPagos.setBackground(Color.decode("#375A9B"));
        lblMenuPagos.setBorderPainted(false);
        lblMenuPagos.setFocusPainted(false);
        lblMenuPagos.setBorder(null);
        lblMenuPagos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuPagos);

        // 5. Contenedor Principal (El área derecha)
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(220, 0, 930, 660); // Ocupa el resto de la ventana
        panelContenido.setBackground(Color.decode("#F4F6F9"));
        ventana.add(panelContenido);

        // 6. Tarjeta de Bienvenida
        PanelRedondeado panelBienvenida = new PanelRedondeado(15, Color.WHITE);
        panelBienvenida.setLayout(null);
        panelBienvenida.setBounds(680, 20, 220, 40);
        
        JLabel lblCheck = new JLabel("✔");
        lblCheck.setForeground(Color.GRAY);
        lblCheck.setBounds(40, 10, 20, 20);
        panelBienvenida.add(lblCheck);
        
        JLabel lblBienvenida = new JLabel("¡Bienvenido!");
        lblBienvenida.setFont(new Font("Inter", Font.BOLD, 14));
        lblBienvenida.setBounds(70, 10, 150, 20);
        panelBienvenida.add(lblBienvenida);
        
        panelContenido.add(panelBienvenida);

        // 7. Etiqueta de Título y Subtítulo
        JLabel lblTitulo = new JLabel("Dashboard");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(40, 60, 200, 30);
        panelContenido.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Resumen general del sistema");
        lblSubtitulo.setFont(new Font("Inter", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBounds(40, 90, 300, 20);
        panelContenido.add(lblSubtitulo);

        // 8. Fila 1: Tarjetas de Indicadores (KPIs)
        crearTarjetaKPI(panelContenido, "Total clientes", "3", "👥", Color.decode("#D2E6FF"), Color.decode("#3278FF"), 40, 140, 195, 100);
        crearTarjetaKPI(panelContenido, "Artículos empeñados", "2", "📦", Color.decode("#F0D7FF"), Color.decode("#9632FF"), 255, 140, 195, 100);
        crearTarjetaKPI(panelContenido, "Total prestado", "$8,500", "$", Color.decode("#FFB4B4"), Color.decode("#FF5050"), 470, 140, 195, 100);
        crearTarjetaKPI(panelContenido, "Total recuperado", "$8,500", "$", Color.decode("#B4F0BE"), Color.decode("#32B450"), 685, 140, 195, 100);

        // 9. Fila 2: Tarjetas de Estado de Artículos
        crearTarjetaEstado(panelContenido, "Artículos empeñados", "2", "Artículos activos", Color.decode("#3C82F0"), 40, 270, 270, 120);
        crearTarjetaEstado(panelContenido, "Artículos recuperados", "1", "Artículos devueltos", Color.decode("#28B450"), 330, 270, 270, 120);
        crearTarjetaEstado(panelContenido, "Artículos rematados", "1", "Artículos vendidos", Color.decode("#F03C3C"), 620, 270, 270, 120);

        // 10. Fila 3: Alerta de Vencimiento
        PanelRedondeado panelAlerta = new PanelRedondeado(20, Color.WHITE);
        panelAlerta.setLayout(null);
        panelAlerta.setBounds(40, 420, 850, 150);

        JLabel lblIconoAlerta = new JLabel("❗");
        lblIconoAlerta.setFont(new Font("Inter", Font.BOLD, 20));
        lblIconoAlerta.setForeground(Color.RED);
        lblIconoAlerta.setBounds(25, 20, 30, 30);
        panelAlerta.add(lblIconoAlerta);

        JLabel lblTituloAlerta = new JLabel("Artículos próximos a vencer");
        lblTituloAlerta.setFont(new Font("Inter", Font.BOLD, 12));
        lblTituloAlerta.setBounds(60, 25, 200, 20);
        panelAlerta.add(lblTituloAlerta);

        JLabel lblMensajeAlerta = new JLabel("No hay artículos próximos a vencer");
        lblMensajeAlerta.setFont(new Font("Inter", Font.PLAIN, 12));
        lblMensajeAlerta.setForeground(Color.GRAY);
        lblMensajeAlerta.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensajeAlerta.setBounds(0, 80, 850, 20);
        panelAlerta.add(lblMensajeAlerta);

        panelContenido.add(panelAlerta);
        ventana.setVisible(true);
        ventana.repaint();
        ventana.revalidate();
        
    }
    
    public void dashboardClientes() {
        // 1. Configuración de la Ventana Principal
        JFrame ventana = new JFrame();
        ventana.setTitle("Sistema Administrativo - Clientes");
        ventana.setSize(1150, 660);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(Color.decode("#F4F6F9")); 
        //setCursor(new Cursor(Cursor.HAND_CURSOR));
        ///////////////////////////////////////////////////
        // 2. Panel Izquierdo (Menú lateral)
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 140, 220, 540); 
        panelMenu.setBackground(Color.decode("#375A9B"));
        ventana.add(panelMenu);
        
        // 3. Logo y Título del Sistema
        ImageIcon icon = new ImageIcon("src/img/logo (1).png");
        Image img = icon.getImage().getScaledInstance(67, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBounds(80, 20, 67, 100);
        ventana.add(logo);
        
        JLabel titleimg = new JLabel("Sistema administrativo");
        titleimg.setFont(new Font("Inter", Font.PLAIN, 12));
        titleimg.setBounds(55, 105, 200, 14);
        titleimg.setForeground(Color.GRAY);
        ventana.add(titleimg);
        //////////////////////////////////////////////
        // 4. Botones del Menú
        JButton lblMenuDash = new JButton("  \u25A6  Dashboard");
        lblMenuDash.setForeground(Color.decode("#C8C8C8"));
        lblMenuDash.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuDash.setBounds(20, 42, 200, 40);
        lblMenuDash.setBackground(Color.decode("#375A9B"));
        lblMenuDash.setBorderPainted(false);
        lblMenuDash.setFocusPainted(false);
        lblMenuDash.setBorder(null);
        lblMenuDash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuDash);
        lblMenuDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();       // 1. Cierra la ventana actual (Clientes)
                bienvenidoDashboard();   // 2. Abre la ventana del Dashboard
            }
        });

        // Botón: Clientes (Activo)
        PanelRedondeado btnDashboard = new PanelRedondeado(10, new Color(138, 172, 235, 100)); // Color con transparencia
        btnDashboard.setLayout(null);
        btnDashboard.setBounds(20, 150, 200, 40);
        JButton lblMenuClientes = new JButton("  \u25A6  Clientes");
        lblMenuClientes.setForeground(Color.WHITE);
        lblMenuClientes.setFont(new Font("Inter", Font.BOLD, 18));
        lblMenuClientes.setBounds(0, 0, 200, 40);
        lblMenuClientes.setBackground(Color.decode("#375A9B"));
        lblMenuClientes.setBorderPainted(false);
        lblMenuClientes.setFocusPainted(false);
        lblMenuClientes.setBorder(null);
        lblMenuClientes.setContentAreaFilled(false);
        btnDashboard.add(lblMenuClientes);
        panelMenu.add(btnDashboard);

        // Botón: Artículos
        JButton lblMenuArticulos = new JButton("  \u25A6  Artículos");
        lblMenuArticulos.setForeground(Color.decode("#C8C8C8"));
        lblMenuArticulos.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuArticulos.setBounds(20, 270, 200, 40);
        lblMenuArticulos.setBackground(Color.decode("#375A9B"));
        lblMenuArticulos.setBorderPainted(false);
        lblMenuArticulos.setFocusPainted(false);
        lblMenuArticulos.setBorder(null);
        lblMenuArticulos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblMenuArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();       // 1. Cierra la ventana actual (Dashboard)
                dashboardArticulos();     // 2. Abre la ventana de Clientes
            }
        });
        panelMenu.add(lblMenuArticulos);

        // Botón: Pagos
        JButton lblMenuPagos = new JButton("  \u25A6  Pagos");
        lblMenuPagos.setForeground(Color.decode("#C8C8C8"));
        lblMenuPagos.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuPagos.setBounds(20, 390, 200, 40);
        lblMenuPagos.setBackground(Color.decode("#375A9B"));
        lblMenuPagos.setBorderPainted(false);
        lblMenuPagos.setFocusPainted(false);
        lblMenuPagos.setBorder(null);
        lblMenuPagos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuPagos);
        
        // 5. Contenedor Principal (El área derecha)
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(220, 0, 930, 660); 
        panelContenido.setBackground(Color.decode("#F4F6F9"));
        ventana.add(panelContenido);
        
        // 6. Header Derecho (Usuario y Cerrar Sesión)
        JLabel lblAdmin = new JLabel("Administrador", SwingConstants.RIGHT);
        lblAdmin.setFont(new Font("Inter", Font.BOLD, 12));
        lblAdmin.setBounds(580, 25, 130, 20);
        panelContenido.add(lblAdmin);
        
        JLabel lblEmail = new JLabel("1234@email.com", SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Inter", Font.PLAIN, 10));
        lblEmail.setForeground(Color.GRAY);
        lblEmail.setBounds(580, 45, 130, 20);
        panelContenido.add(lblEmail);
        
        PanelRedondeado btnCerrarSesion = new PanelRedondeado(10, Color.decode("#8AACED"));
        btnCerrarSesion.setLayout(null);
        btnCerrarSesion.setBounds(730, 25, 160, 40);
        
        JLabel lblCerrarSesion = new JLabel("→]  Cerrar sesión", SwingConstants.CENTER);
        lblCerrarSesion.setFont(new Font("Inter", Font.PLAIN, 14));
        lblCerrarSesion.setBounds(0, 0, 160, 40);
        btnCerrarSesion.add(lblCerrarSesion);
        panelContenido.add(btnCerrarSesion);

        // 7. Título y Subtítulo de Clientes
        JLabel lblTitulo = new JLabel("Clientes");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(40, 90, 200, 30);
        panelContenido.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Gestiona la información de tus clientes");
        lblSubtitulo.setFont(new Font("Inter", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBounds(40, 120, 300, 20);
        panelContenido.add(lblSubtitulo);

        PanelRedondeado btnNuevoCliente = new PanelRedondeado(10, Color.decode("#829ECF"));
        btnNuevoCliente.setLayout(null);
        btnNuevoCliente.setBounds(730, 90, 160, 40);
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        JLabel lblNuevoCliente = new JLabel("+  Nuevo cliente", SwingConstants.CENTER);
        lblNuevoCliente.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNuevoCliente.setBounds(0, 0, 160, 40);
        btnNuevoCliente.add(lblNuevoCliente);
        panelContenido.add(btnNuevoCliente);
        btnNuevoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Instanciamos y mostramos la nueva ventana modal
            	ModalNuevoCliente modal = new ModalNuevoCliente(HomeView.this, ventana);
                modal.setVisible(true);
            }
        });
         
        
        
        // 9. Barra de Búsqueda Interactiva
        PanelRedondeado panelBusquedaC = new PanelRedondeado(15, Color.WHITE);
        panelBusquedaC.setLayout(null);
        panelBusquedaC.setBounds(40, 165, 850, 70);

        PanelRedondeado inputBusqueda = new PanelRedondeado(10, Color.decode("#EAECEF"));
        inputBusqueda.setLayout(null);
        inputBusqueda.setBounds(20, 15, 810, 40);
        
        JTextField txtBusqueda = new JTextField("Busca por nombre, teléfono o correo...");
        txtBusqueda.setBorder(null);
        txtBusqueda.setOpaque(false);
        txtBusqueda.setBounds(15, 0, 780, 40);
        txtBusqueda.setForeground(Color.GRAY);
        txtBusqueda.setFont(new Font("Inter", Font.PLAIN, 12));
        
        // Lógica para que el texto de ejemplo desaparezca al dar clic
        txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtBusqueda.getText().equals("Busca por nombre, teléfono o correo...")) {
                    txtBusqueda.setText("");
                    txtBusqueda.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtBusqueda.getText().trim().isEmpty()) {
                    txtBusqueda.setText("Busca por nombre, teléfono o correo...");
                    txtBusqueda.setForeground(Color.GRAY);
                }
            }
        });

        // ... (código previo del input de busqueda)
        inputBusqueda.add(txtBusqueda);
        panelBusquedaC.add(inputBusqueda);
        panelContenido.add(panelBusquedaC);

        // 10. Contenedor de la Tabla de Clientes
        panelTablaGlobal = new PanelRedondeado(15, Color.WHITE);
        panelTablaGlobal.setLayout(null);
        panelTablaGlobal.setBounds(40, 255, 850, 280);
        panelContenido.add(panelTablaGlobal);

        // AQUÍ CONECTAMOS LA BARRA DE BÚSQUEDA CON LA TABLA
        configurarBusquedaInteractiva(txtBusqueda, ventana, panelTablaGlobal);

        // Renderizamos la tabla inicial (vacío para mostrar a todos)
        renderizarTabla(panelTablaGlobal, "");

        // Mostrar la ventana
        ventana.setVisible(true);
        ventana.repaint();
        ventana.revalidate();
    }
    
    public void dashboardArticulos() {
        // 1. Configuración de la Ventana Principal
        JFrame ventana = new JFrame();
        ventana.setTitle("Sistema Administrativo - Artículos");
        ventana.setSize(1150, 660);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(Color.decode("#F4F6F9")); 

        // 2. Panel Izquierdo (Menú lateral)
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 140, 220, 540); 
        panelMenu.setBackground(Color.decode("#375A9B"));
        ventana.add(panelMenu);
        
        // 3. Logo y Título del Sistema
        ImageIcon icon = new ImageIcon("src/img/logo (1).png");
        Image img = icon.getImage().getScaledInstance(67, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBounds(80, 20, 67, 100);
        ventana.add(logo);
        
        JLabel titleimg = new JLabel("Sistema administrativo");
        titleimg.setFont(new Font("Inter", Font.PLAIN, 12));
        titleimg.setBounds(55, 105, 200, 14);
        titleimg.setForeground(Color.GRAY);
        ventana.add(titleimg);
        //////////////////////////////////////////////
        // 4. Botones del Menú
        JButton lblMenuDash = new JButton("  \u25A6  Dashboard");
        lblMenuDash.setForeground(Color.decode("#C8C8C8"));
        lblMenuDash.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuDash.setBounds(20, 42, 200, 40);
        lblMenuDash.setBackground(Color.decode("#375A9B"));
        lblMenuDash.setBorderPainted(false);
        lblMenuDash.setFocusPainted(false);
        lblMenuDash.setBorder(null);
        lblMenuDash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuDash);
        lblMenuDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();       // 1. Cierra la ventana actual (Clientes)
                bienvenidoDashboard();   // 2. Abre la ventana del Dashboard
            }
        });

        // Botón: Clientes (Activo)
        
        JButton lblMenuClientes = new JButton("  \u25A6  Clientes");
        lblMenuClientes.setForeground(Color.decode("#C8C8C8"));
        lblMenuClientes.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuClientes.setBounds(20, 150, 200, 40);
        lblMenuClientes.setBackground(Color.decode("#375A9B"));
        lblMenuClientes.setBorderPainted(false);
        lblMenuClientes.setFocusPainted(false);
        lblMenuClientes.setBorder(null);
        lblMenuClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuClientes);
        lblMenuClientes.addActionListener(e -> {
            ventana.dispose();
            dashboardClientes();
        });
        
        PanelRedondeado btnMenuArticulos = new PanelRedondeado(10, new Color(138, 172, 235, 100));
        btnMenuArticulos.setLayout(null);
        btnMenuArticulos.setBounds(20, 270, 200, 40);
        JButton lblMenuArticulos = new JButton("  \u25A6  Artículos");
        lblMenuArticulos.setForeground(Color.WHITE);
        lblMenuArticulos.setFont(new Font("Inter", Font.BOLD, 18));
        lblMenuArticulos.setBounds(20, 270, 200, 40);
        lblMenuArticulos.setContentAreaFilled(false);
        lblMenuArticulos.setBorderPainted(false);
        lblMenuArticulos.setFocusPainted(false);
        lblMenuArticulos.setBorder(null);
        btnMenuArticulos.add(lblMenuArticulos);
        panelMenu.add(lblMenuArticulos);
        panelMenu.add(btnMenuArticulos);
        
        // Botón: Pagos
        JButton lblMenuPagos = new JButton("  \u25A6  Pagos");
        lblMenuPagos.setForeground(Color.decode("#C8C8C8"));
        lblMenuPagos.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuPagos.setBounds(20, 390, 200, 40);
        lblMenuPagos.setBackground(Color.decode("#375A9B"));
        lblMenuPagos.setBorderPainted(false);
        lblMenuPagos.setFocusPainted(false);
        lblMenuPagos.setBorder(null);
        lblMenuPagos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuPagos);
        
        // 5. Contenedor Principal Derecho
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(220, 0, 930, 660); 
        panelContenido.setBackground(Color.decode("#F4F6F9"));
        ventana.add(panelContenido);

        // 6. Header Derecho (Usuario y Cerrar Sesión)
        JLabel lblAdmin = new JLabel("Administrador", SwingConstants.RIGHT);
        lblAdmin.setFont(new Font("Inter", Font.BOLD, 12));
        lblAdmin.setBounds(580, 25, 130, 20);
        panelContenido.add(lblAdmin);

        JLabel lblEmail = new JLabel("1234@email.com", SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Inter", Font.PLAIN, 10));
        lblEmail.setForeground(Color.GRAY);
        lblEmail.setBounds(580, 45, 130, 20);
        panelContenido.add(lblEmail);

        PanelRedondeado btnCerrarSesion = new PanelRedondeado(10, Color.decode("#8AACED"));
        btnCerrarSesion.setLayout(null);
        btnCerrarSesion.setBounds(730, 25, 160, 40);
        btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel lblCerrarSesion = new JLabel("→]  Cerrar sesión", SwingConstants.CENTER);
        lblCerrarSesion.setFont(new Font("Inter", Font.PLAIN, 14));
        lblCerrarSesion.setBounds(0, 0, 160, 40);
        btnCerrarSesion.add(lblCerrarSesion);
        panelContenido.add(btnCerrarSesion);

        // 7. Título y Subtítulo
        JLabel lblTitulo = new JLabel("Artículos");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(40, 90, 200, 30);
        panelContenido.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Gestiona todos los artículos en empeño");
        lblSubtitulo.setFont(new Font("Inter", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBounds(40, 120, 300, 20);
        panelContenido.add(lblSubtitulo);

        PanelRedondeado btnNuevoCliente = new PanelRedondeado(10, Color.decode("#829ECF"));
        btnNuevoCliente.setLayout(null);
        btnNuevoCliente.setBounds(730, 90, 160, 40);
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        JLabel lblNuevoCliente = new JLabel("+  Nuevo artículo", SwingConstants.CENTER);
        lblNuevoCliente.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNuevoCliente.setBounds(0, 0, 160, 40);
        btnNuevoCliente.add(lblNuevoCliente);
        panelContenido.add(btnNuevoCliente);
        btnNuevoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Instanciamos y mostramos la nueva ventana modal
            	ModalNuevoArticulo modal = new ModalNuevoArticulo(HomeView.this, ventana);
                modal.setVisible(true);
            }
        });


        // 8. Barra de Filtros
        PanelRedondeado panelFiltros = new PanelRedondeado(15, Color.WHITE);
        panelFiltros.setLayout(null);
        panelFiltros.setBounds(40, 165, 850, 70);
        panelContenido.add(panelFiltros);

        // Input de búsqueda
        PanelRedondeado inputBusqueda = new PanelRedondeado(10, Color.decode("#EAECEF"));
        inputBusqueda.setLayout(null);
        inputBusqueda.setBounds(20, 15, 250, 40);
        JTextField txtBusqueda = new JTextField("Buscar artículo...");
        txtBusqueda.setBorder(null);
        txtBusqueda.setOpaque(false);
        txtBusqueda.setBounds(35, 0, 200, 40);
        txtBusqueda.setForeground(Color.GRAY);
        txtBusqueda.setFont(new Font("Inter", Font.PLAIN, 12));
        JLabel iconLupa = new JLabel("🔍");
        iconLupa.setBounds(10, 0, 20, 40);
        iconLupa.setForeground(Color.GRAY);
        inputBusqueda.add(iconLupa);
        inputBusqueda.add(txtBusqueda);
        panelFiltros.add(inputBusqueda);
        
        

        // Combobox Estado
        JComboBox<String> comboEstado = new JComboBox<>(new String[]{"Todos los estados", "Empeñados", "Recuperados", "Rematados"});
        comboEstado.setBounds(290, 15, 260, 40);
        comboEstado.setBackground(Color.decode("#EAECEF"));
        comboEstado.setFont(new Font("Inter", Font.PLAIN, 12));
        comboEstado.setBorder(null);
        panelFiltros.add(comboEstado);

        // Combobox Categoría
        JComboBox<String> comboCat = new JComboBox<>(new String[]{"Todas las categorías", "Joyería", "Electrónica", "Otros"});
        comboCat.setBounds(570, 15, 260, 40);
        comboCat.setBackground(Color.decode("#EAECEF"));
        comboCat.setFont(new Font("Inter", Font.PLAIN, 12));
        comboCat.setBorder(null);
        panelFiltros.add(comboCat);

        // 9. Tarjetas de Resumen (KPIs)
        PanelRedondeado cardEmpenados = new PanelRedondeado(15, Color.WHITE);
        cardEmpenados.setLayout(null);
        cardEmpenados.setBounds(40, 255, 260, 90);
        JLabel lblTEmpenados = new JLabel("Empeñados", SwingConstants.CENTER);
        lblTEmpenados.setFont(new Font("Inter", Font.PLAIN, 12));
        lblTEmpenados.setForeground(Color.GRAY);
        lblTEmpenados.setBounds(0, 15, 260, 20);
        JLabel lblVEmpenados = new JLabel("2", SwingConstants.CENTER);
        lblVEmpenados.setFont(new Font("Inter", Font.BOLD, 24));
        lblVEmpenados.setForeground(Color.decode("#3278FF"));
        lblVEmpenados.setBounds(0, 45, 260, 30);
        cardEmpenados.add(lblTEmpenados);
        cardEmpenados.add(lblVEmpenados);
        panelContenido.add(cardEmpenados);

        PanelRedondeado cardRecuperados = new PanelRedondeado(15, Color.WHITE);
        cardRecuperados.setLayout(null);
        cardRecuperados.setBounds(335, 255, 260, 90);
        JLabel lblTRecuperados = new JLabel("Recuperados", SwingConstants.CENTER);
        lblTRecuperados.setFont(new Font("Inter", Font.PLAIN, 12));
        lblTRecuperados.setForeground(Color.GRAY);
        lblTRecuperados.setBounds(0, 15, 260, 20);
        JLabel lblVRecuperados = new JLabel("1", SwingConstants.CENTER);
        lblVRecuperados.setFont(new Font("Inter", Font.BOLD, 24));
        lblVRecuperados.setForeground(Color.decode("#28B450"));
        lblVRecuperados.setBounds(0, 45, 260, 30);
        cardRecuperados.add(lblTRecuperados);
        cardRecuperados.add(lblVRecuperados);
        panelContenido.add(cardRecuperados);

        PanelRedondeado cardRematados = new PanelRedondeado(15, Color.WHITE);
        cardRematados.setLayout(null);
        cardRematados.setBounds(630, 255, 260, 90);
        JLabel lblTRematados = new JLabel("Rematados", SwingConstants.CENTER);
        lblTRematados.setFont(new Font("Inter", Font.PLAIN, 12));
        lblTRematados.setForeground(Color.GRAY);
        lblTRematados.setBounds(0, 15, 260, 20);
        JLabel lblVRematados = new JLabel("1", SwingConstants.CENTER);
        lblVRematados.setFont(new Font("Inter", Font.BOLD, 24));
        lblVRematados.setForeground(Color.decode("#F03C3C"));
        lblVRematados.setBounds(0, 45, 260, 30);
        cardRematados.add(lblTRematados);
        cardRematados.add(lblVRematados);
        panelContenido.add(cardRematados);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        inputBusqueda.add(txtBusqueda);
        panelFiltros.add(inputBusqueda);
        panelContenido.add(panelFiltros);

        // 10. Contenedor de la Tabla de Clientes
        panelTablaArticulos = new PanelRedondeado(15, Color.WHITE);
        panelTablaArticulos.setLayout(null);
        panelTablaArticulos.setBounds(40, 255, 850, 280);
        panelContenido.add(panelTablaArticulos);

        // AQUÍ CONECTAMOS LA BARRA DE BÚSQUEDA CON LA TABLA
        configurarBusquedaInteractivaArticulos(txtBusqueda, ventana, panelTablaArticulos);

        // Renderizamos la tabla inicial (vacío para mostrar a todos)
        renderizarTablaArticulos(panelTablaArticulos, "");

        // Mostrar la ventana
        ventana.setVisible(true);
        ventana.repaint();
        ventana.revalidate();
    }
    
    // ================= MÉTODOS AUXILIARES ================= //
    public void registrarNuevoCliente(String nombre, String telefono, String correo, String fecha) {
        // Añadimos el nuevo registro (por defecto le ponemos "0" artículos y color "#AEE7B8")
        String[] nuevoCliente = {nombre, telefono, correo, fecha, "0", "#AEE7B8"};
        baseDatosClientes.add(nuevoCliente);

        // Refrescamos la tabla para que aparezca inmediatamente
        if (panelTablaGlobal != null) {
            renderizarTabla(panelTablaGlobal, "");
        }
    }
    
    public void actualizarCliente(int index, String nombre, String telefono, String correo, String fecha) {
        // Verificamos que el índice sea válido
        if (index >= 0 && index < baseDatosClientes.size()) {
            String[] cliente = baseDatosClientes.get(index);
            // Actualizamos los datos
            cliente[0] = nombre;
            cliente[1] = telefono;
            cliente[2] = correo;
            cliente[3] = fecha;
            
            // Refrescamos la tabla
            if (panelTablaGlobal != null) {
                renderizarTabla(panelTablaGlobal, "");
            }
        }
    }
    
    private void configurarBusquedaInteractiva(JTextField txtBusqueda, JFrame ventana, JPanel panelTabla) {
        JPopupMenu popupSugerencias = new JPopupMenu();
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaSugerencias = new JList<>(modeloLista);
        
        listaSugerencias.setFont(new Font("Inter", Font.PLAIN, 14));
        listaSugerencias.setSelectionBackground(Color.decode("#8AACED"));
        listaSugerencias.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JScrollPane scrollPane = new JScrollPane(listaSugerencias);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(txtBusqueda.getWidth(), 150));
        
        popupSugerencias.add(scrollPane);
        popupSugerencias.setFocusable(false); 

        txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = txtBusqueda.getText().trim().toLowerCase();
                modeloLista.clear();

                // ACTUALIZA LA TABLA EN TIEMPO REAL
                renderizarTabla(panelTabla, texto);

                if (texto.isEmpty() || texto.equals("busca por nombre, teléfono o correo...")) {
                    popupSugerencias.setVisible(false);
                    return;
                }

                // Generar sugerencias para la barra desplegable basadas en el arreglo global
                List<String> filtrados = new ArrayList<>();
                for (String[] cliente : baseDatosClientes) {
                    if (cliente[0].toLowerCase().contains(texto)) {
                        filtrados.add(cliente[0]);
                    }
                }

                if (!filtrados.isEmpty()) {
                    filtrados.forEach(modeloLista::addElement);
                    scrollPane.setPreferredSize(new Dimension(txtBusqueda.getWidth(), Math.min(150, filtrados.size() * 25 + 10)));
                    popupSugerencias.show(txtBusqueda, 0, txtBusqueda.getHeight());
                    txtBusqueda.requestFocus(); 
                } else {
                    popupSugerencias.setVisible(false);
                }
            }
        });

        listaSugerencias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String seleccionado = listaSugerencias.getSelectedValue();
                    if (seleccionado != null) {
                        txtBusqueda.setText(seleccionado);
                        popupSugerencias.setVisible(false);
                    }
                }
            }
        });
    }
    
    private void configurarBusquedaInteractivaArticulos(JTextField txtBusqueda, JFrame ventana, JPanel panelTabla) {
        JPopupMenu popupSugerencias = new JPopupMenu();
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaSugerencias = new JList<>(modeloLista);
        
        listaSugerencias.setFont(new Font("Inter", Font.PLAIN, 14));
        listaSugerencias.setSelectionBackground(Color.decode("#8AACED"));
        listaSugerencias.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JScrollPane scrollPane = new JScrollPane(listaSugerencias);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(txtBusqueda.getWidth(), 150));
        
        popupSugerencias.add(scrollPane);
        popupSugerencias.setFocusable(false); 

        txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = txtBusqueda.getText().trim().toLowerCase();
                modeloLista.clear();

                // ACTUALIZA LA TABLA EN TIEMPO REAL
                renderizarTablaArticulos(panelTabla, texto);

                if (texto.isEmpty() || texto.equals("busca por nombre, teléfono o correo...")) {
                    popupSugerencias.setVisible(false);
                    return;
                }

                // Generar sugerencias para la barra desplegable basadas en el arreglo global
                List<String> filtrados = new ArrayList<>();
                for (String[] cliente : baseDatosArticulos) {
                    if (cliente[0].toLowerCase().contains(texto)) {
                        filtrados.add(cliente[0]);
                    }
                }

                if (!filtrados.isEmpty()) {
                    filtrados.forEach(modeloLista::addElement);
                    scrollPane.setPreferredSize(new Dimension(txtBusqueda.getWidth(), Math.min(150, filtrados.size() * 25 + 10)));
                    popupSugerencias.show(txtBusqueda, 0, txtBusqueda.getHeight());
                    txtBusqueda.requestFocus(); 
                } else {
                    popupSugerencias.setVisible(false);
                }
            }
        });

        listaSugerencias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String seleccionado = listaSugerencias.getSelectedValue();
                    if (seleccionado != null) {
                        txtBusqueda.setText(seleccionado);
                        popupSugerencias.setVisible(false);
                    }
                }
            }
        });
    }
    
    private void renderizarTabla(JPanel panelTabla, String filtro) {
        panelTabla.removeAll(); // Limpiamos la tabla actual
        
        // 1. Volvemos a dibujar el Encabezado
        JPanel headerTabla = new JPanel();
        headerTabla.setLayout(null);
        headerTabla.setBackground(Color.decode("#F4F6F9"));
        headerTabla.setBounds(0, 0, 850, 40);
        
        String[] columnas = {"NOMBRE", "TELÉFONO", "CORREO", "FECHA DE REGISTRO", "ARTÍCULOS ACT.", "ACCIONES"};
        int[] posX = {20, 180, 290, 430, 580, 700};

        for (int i = 0; i < columnas.length; i++) {
            JLabel lblColumna = new JLabel(columnas[i]);
            lblColumna.setFont(new Font("Inter", Font.BOLD, 10));
            lblColumna.setBounds(posX[i], 10, 140, 20);
            headerTabla.add(lblColumna);
        }
        panelTabla.add(headerTabla);

        // 2. Filtramos y dibujamos los datos
        boolean hayResultados = false;
        int rowY = 50; 

        String busqueda = filtro.trim().toLowerCase();
        if (busqueda.equals("busca por nombre, teléfono o correo...")) {
            busqueda = ""; // Ignorar el texto de placeholder
        }

        for (String[] cliente : baseDatosClientes) {
            // Verifica si el nombre, teléfono o correo contienen el texto buscado
        	// Refrescar panel visualmente
            if (busqueda.isEmpty() || 
                cliente[0].toLowerCase().contains(busqueda) || 
                cliente[1].toLowerCase().contains(busqueda) || 
                cliente[2].toLowerCase().contains(busqueda)) {
                panelTabla.setPreferredSize(new Dimension(panelTabla.getWidth(), rowY + 50));

                hayResultados = true;

                JLabel lblNom = new JLabel(cliente[0]);
                lblNom.setFont(new Font("Inter", Font.PLAIN, 11));
                lblNom.setBounds(posX[0], rowY, 150, 20);
                panelTabla.add(lblNom);

                JLabel lblTel = new JLabel(cliente[1]);
                lblTel.setFont(new Font("Inter", Font.PLAIN, 11));
                lblTel.setBounds(posX[1], rowY, 100, 20);
                panelTabla.add(lblTel);

                JLabel lblCorreo = new JLabel(cliente[2]);
                lblCorreo.setFont(new Font("Inter", Font.PLAIN, 11));
                lblCorreo.setBounds(posX[2], rowY, 150, 20);
                panelTabla.add(lblCorreo);

                JLabel lblFecha = new JLabel(cliente[3]);
                lblFecha.setFont(new Font("Inter", Font.PLAIN, 11));
                lblFecha.setBounds(posX[3] + 20, rowY, 100, 20);
                panelTabla.add(lblFecha);
                
                PanelCirculo circuloActivos = new PanelCirculo(Color.decode(cliente[5]));
                circuloActivos.setLayout(new BorderLayout());
                circuloActivos.setBounds(posX[4] + 30, rowY - 2, 24, 24);
                
                JLabel lblNumActivos = new JLabel(cliente[4], SwingConstants.CENTER);
                lblNumActivos.setFont(new Font("Inter", Font.BOLD, 10));
                lblNumActivos.setForeground(Color.decode("#2E7D32"));
                circuloActivos.add(lblNumActivos, BorderLayout.CENTER);
                panelTabla.add(circuloActivos);

                //|-ACCIONES-|
                // Crear una copia final del cliente para poder usarla dentro del ActionListener
                final String[] clienteSeleccionado = cliente; 
                JButton lblVer = new JButton("👁");
                lblVer.setForeground(Color.BLACK);
                lblVer.setFont(new Font("SansSerif", Font.PLAIN, 25));
                lblVer.setContentAreaFilled(false);
                lblVer.setBorderPainted(false);
                lblVer.setFocusPainted(false);
                lblVer.setOpaque(false);
                lblVer.setBounds(posX[5], rowY-10, lblVer.getPreferredSize().width, lblVer.getPreferredSize().height);
                lblVer.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                // --- NUEVO EVENTO PARA ABRIR LOS DETALLES --- //
                lblVer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener la ventana actual para cerrarla
                        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panelTabla);
                        if (topFrame != null) {
                            topFrame.dispose();
                        }
                        // Abrir la nueva vista pasándole los datos del cliente
                        verDetallesCliente(clienteSeleccionado);
                    }
                });
                panelTabla.add(lblVer);
                
                final int indexCliente = baseDatosClientes.indexOf(cliente);
                JButton lblEditar = new JButton("📝");
                lblEditar.setForeground(Color.DARK_GRAY);
                lblEditar.setFont(new Font("SansSerif", Font.PLAIN, 25));
                lblEditar.setContentAreaFilled(false);
                lblEditar.setBorderPainted(false);
                lblEditar.setFocusPainted(false);
                lblEditar.setOpaque(false);
                lblEditar.setBounds(posX[5]+42, rowY-10, lblVer.getPreferredSize().width, lblVer.getPreferredSize().height);
                lblEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                lblEditar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panelTabla);
                        ModalEditarCliente modal = new ModalEditarCliente(HomeView.this, topFrame, clienteSeleccionado, indexCliente);
                        modal.setVisible(true);
                    }
                });
                panelTabla.add(lblEditar);

                // Código existente...
                JButton lblEliminar = new JButton("🗑");
                lblEliminar.setForeground(Color.RED);
                lblEliminar.setFont(new Font("SansSerif", Font.PLAIN, 25));
                lblEliminar.setContentAreaFilled(false);
                lblEliminar.setBorderPainted(false);
                lblEliminar.setFocusPainted(false);
                lblEliminar.setOpaque(false);
                lblEliminar.setBounds(posX[5]+80, rowY-10, lblVer.getPreferredSize().width, lblVer.getPreferredSize().height);
                lblEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // --- NUEVO CÓDIGO: EVENTO DE ELIMINACIÓN ---
                lblEliminar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmarEliminacion(clienteSeleccionado, panelTabla,1);
                    }
                });
                // -------------------------------------------

                panelTabla.add(lblEliminar); // Esta línea ya la tienes
	            

                JPanel separador = new JPanel();
                separador.setBackground(Color.decode("#F0F0F0"));
                separador.setBounds(10, rowY + 35, 830, 1);
                panelTabla.add(separador);

                rowY += 55;
            }
        }

        // 3. Si no hubo coincidencias, mostramos el mensaje
        if (!hayResultados) {
            JLabel lblSinResultados = new JLabel("No hay resultados coincidentes");
            lblSinResultados.setFont(new Font("Inter", Font.ITALIC, 14));
            lblSinResultados.setForeground(Color.GRAY);
            lblSinResultados.setHorizontalAlignment(SwingConstants.CENTER);
            lblSinResultados.setBounds(0, 120, 850, 30);
            panelTabla.add(lblSinResultados);
        }

        
        
        // 3. REFRESCAR LA INTERFAZ
        panelTabla.revalidate();
        panelTabla.repaint();
    }
    
    private void limpiarPanelTabla(JPanel panel) {
        if (panel != null) {
            panel.removeAll(); // Elimina todos los componentes (etiquetas, botones, etc.)
            panel.revalidate(); // Recalcula la estructura interna del panel
            panel.repaint();    // Borra visualmente lo que quedó en pantalla
        }
    }
    
    private void renderizarTablaArticulos(JPanel panelTabla, String filtro) {

    	limpiarPanelTabla(panelTabla);
    	panelTabla.setBounds(40, 350, 850, 250);
    	//panelTabla.setLocation(40, 350);
        // 1. Volvemos a dibujar el Encabezado
        JPanel headerTabla = new JPanel();
        headerTabla.setLayout(null);
        headerTabla.setBackground(Color.decode("#F4F6F9"));
        headerTabla.setBounds(0, 0, 850, 40);
        
        String[] columnas = {"ARTÍCULO", "CLIENTE", "CATEGORÍA", "MONTO PRESTADO", "FECHA LÍMITE", "ESTADO", "ACCIONES"};
        int[] posX = {20, 150, 300, 400, 520, 620, 720};

        for (int i = 0; i < columnas.length; i++) {
            JLabel lblColumna = new JLabel(columnas[i]);
            lblColumna.setFont(new Font("Inter", Font.BOLD, 10));
            lblColumna.setBounds(posX[i], 10, 140, 20);
            headerTabla.add(lblColumna);
        }
        panelTabla.add(headerTabla);

        // 2. Filtramos y dibujamos los datos
        boolean hayResultados = false;
        int rowY = 50;

        String busqueda = filtro.trim().toLowerCase();
        // CUIDADO AQUÍ: Asegúrate de que coincida con el placeholder de tu JTextField en dashboardArticulos
        if (busqueda.equals("buscar artículo...") || busqueda.equals("busca por nombre, teléfono o correo...")) {
            busqueda = ""; 
        }
        for (String[] articulo : baseDatosArticulos) {
            // Variable para guardar el cliente que coincide con este artículo
            final String[] clienteParaArticulo;  // ← final para poder usarla en el listener
            
            // Buscar el cliente correspondiente
            String[] clienteEncontrado = null;
            for (String[] cliente : baseDatosClientes) {
                if(articulo[1].equals(cliente[0])) {
                    clienteEncontrado = cliente;
                    break;
                }
            }
            clienteParaArticulo = clienteEncontrado;
        	/*

            private List<String[]> baseDatosClientes = new ArrayList<>(Arrays.asList(
                    new String[]{"Juan Pérez García", "6121234567", "juan.perez@email.com", "14/1/2025", "1", "#AEE7B8"},
                    new String[]{"María López Hernández", "6121418223", "maria.lopez@email.com", "19/2/2025", "1", "#AEE7B8"},
                    new String[]{"Carlos Rodríguez Martínez", "6122898724", "carlos.rdgz@email.com", "9/3/2025", "0", "#AEE7B8"},
                    new String[]{"Emmanuel García", "6125551234", "emmanuel@email.com", "10/4/2025", "2", "#AEE7B8"}
                ));
            
            private List<String[]> baseDatosArticulos = new ArrayList<>(Arrays.asList(
            		new String[]{"Anillo de Oro 14K", "Juan Pérez García", "Joyería", "$5,000", "14/6/2025", "Empeñado", "#FFF9C4", "#FBC02D","$10,000","20/13/2027","Sin descripcion por el momento"},
            		new String[]{"Laptop Dell XPS 15", "Juan Pérez García", "Electrónica", "$8,000", "30/4/2025", "Recuperado", "#C8E6C9", "#388E3C","$10,000","20/13/2027","Sin descripcion por el momento"},
            		new String[]{"Collar de Perlas", "María López Hernández", "Joyería", "$3,500", "19/6/2025", "Empeñado", "#FFF9C4", "#FBC02D","$10,000","20/13/2027","Sin descripcion por el momento"},
            		new String[] {"iPhone 14 Pro", "Carlos Rodríguez Martínez", "Electrónica", "$10,000", "9/4/2025", "Rematado", "#FFCDD2", "#D32F2F","$10,000","20/13/2027","Sin descripcion por el momento"}
            		));
            */

            // Filtro interactivo: Busca en Nombre de Artículo [0], Nombre de Cliente [1] y Categoría [2]
            if (busqueda.isEmpty() || 
                articulo[0].toLowerCase().contains(busqueda) || 
                articulo[1].toLowerCase().contains(busqueda) || 
                articulo[2].toLowerCase().contains(busqueda)) {
                
                //panelTabla.setPreferredSize(new Dimension(panelTabla.getWidth(), rowY + 50));
                hayResultados = true;

                // Artículo [0]
                JLabel lblArt = new JLabel(articulo[0]);
                lblArt.setFont(new Font("Inter", Font.BOLD, 11));
                lblArt.setBounds(posX[0], rowY, 120, 20);
                panelTabla.add(lblArt);

                // Cliente [1]
                JLabel lblCli = new JLabel(articulo[1]);
                lblCli.setFont(new Font("Inter", Font.PLAIN, 11));
                lblCli.setBounds(posX[1], rowY, 140, 20);
                panelTabla.add(lblCli);

                // Categoría [2]
                JLabel lblCat = new JLabel(articulo[2]);
                lblCat.setFont(new Font("Inter", Font.PLAIN, 11));
                lblCat.setBounds(posX[2], rowY, 110, 20);
                panelTabla.add(lblCat);

                // Monto Prestado [3]
                JLabel lblMonto = new JLabel(articulo[3]);
                lblMonto.setFont(new Font("Inter", Font.BOLD, 11));
                lblMonto.setBounds(posX[3] + 10, rowY, 100, 20);
                panelTabla.add(lblMonto);
                
                // Fecha Límite [4]
                JLabel lblFecha = new JLabel(articulo[4]);
                lblFecha.setFont(new Font("Inter", Font.PLAIN, 11));
                lblFecha.setBounds(posX[4], rowY, 100, 20);
                panelTabla.add(lblFecha);

                // Estado [5], Color de Fondo [6], Color de Texto [7]
                PanelRedondeado badge = new PanelRedondeado(10, Color.decode(articulo[6]));
                badge.setLayout(new BorderLayout());
                badge.setBounds(posX[5] - 10, rowY, 80, 20);
                
                JLabel lblEstado = new JLabel(articulo[5], SwingConstants.CENTER);
                lblEstado.setFont(new Font("Inter", Font.BOLD, 10));
                lblEstado.setForeground(Color.decode(articulo[7]));
                badge.add(lblEstado, BorderLayout.CENTER);
                panelTabla.add(badge);

                // |-ACCIONES-| (Reutilizamos la lógica gráfica, solo ajustando posiciones)
                JButton lblVer = new JButton("👁");
                lblVer.setForeground(Color.BLACK);
                lblVer.setFont(new Font("SansSerif", Font.PLAIN, 16));
                lblVer.setContentAreaFilled(false);
                lblVer.setBorderPainted(false);
                lblVer.setFocusPainted(false);
                lblVer.setBounds(posX[6] - 15, rowY-5, 50, 30);
                lblVer.setCursor(new Cursor(Cursor.HAND_CURSOR));
                lblVer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	
                    	verDetallesArticulo(articulo,clienteParaArticulo); // 2. Abre la ventana del Dashboard
                    }
                });
                panelTabla.add(lblVer);
                
                JButton lblEditar = new JButton("📝");
                lblEditar.setForeground(Color.DARK_GRAY);
                lblEditar.setFont(new Font("SansSerif", Font.PLAIN, 16));
                lblEditar.setContentAreaFilled(false);
                lblEditar.setBorderPainted(false);
                lblEditar.setFocusPainted(false);
                lblEditar.setBounds(posX[6] + 20, rowY-5, 50, 30);
                lblEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                lblEditar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panelTabla);
                        ModalEditarArticulo x=new  ModalEditarArticulo(HomeView.this,topFrame,articulo);  // 2. Abre la ventana del Dashboard
                        x.setVisible(true);
                    }
                });
                panelTabla.add(lblEditar);

                JButton lblEliminar = new JButton("🗑");
                lblEliminar.setForeground(Color.RED);
                lblEliminar.setFont(new Font("SansSerif", Font.PLAIN, 20));
                lblEliminar.setContentAreaFilled(false);
                lblEliminar.setBorderPainted(false);
                lblEliminar.setFocusPainted(false);
                lblEliminar.setBounds(posX[6] + 55, rowY-5, 50, 30);
                lblEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                lblEliminar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmarEliminacion(articulo, panelTabla,0);
                    }
                });
                panelTabla.add(lblEliminar);

                // Separador visual
                JPanel separador = new JPanel();
                separador.setBackground(Color.decode("#F0F0F0"));
                separador.setBounds(10, rowY + 35, 830, 1);
                panelTabla.add(separador);

                rowY += 55;
            }
        }
        
        

        // 3. Si no hubo coincidencias, mostramos el mensaje
        if (!hayResultados) {
            JLabel lblSinResultados = new JLabel("No hay resultados coincidentes");
            lblSinResultados.setFont(new Font("Inter", Font.ITALIC, 14));
            lblSinResultados.setForeground(Color.GRAY);
            lblSinResultados.setHorizontalAlignment(SwingConstants.CENTER);
            lblSinResultados.setBounds(0, 120, 850, 30);
            panelTabla.add(lblSinResultados);
        }
        
        // 4. REFRESCAR LA INTERFAZ
        panelTabla.revalidate();
        panelTabla.repaint();
    }
    
    public void verDetallesCliente(String[] cliente) {
        // 1. Configuración de la Ventana
        JFrame ventana = new JFrame();
        ventana.setTitle("Detalles del Cliente - " + cliente[0]);
        ventana.setSize(1150, 660);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(Color.decode("#F4F6F9")); 

        // 2. Menú Lateral (Igual al de Clientes)
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 140, 220, 540); 
        panelMenu.setBackground(Color.decode("#375A9B"));
        ventana.add(panelMenu);
        
        ImageIcon icon = new ImageIcon("src/img/logo (1).png"); // Ajusta tu ruta
        Image img = icon.getImage().getScaledInstance(67, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBounds(80, 20, 67, 100);
        ventana.add(logo);
        
        JLabel titleimg = new JLabel("Sistema administrativo");
        titleimg.setFont(new Font("Inter", Font.PLAIN, 12));
        titleimg.setBounds(55, 105, 200, 14);
        titleimg.setForeground(Color.GRAY);
        ventana.add(titleimg);
        
        // Botones Menú
        JButton lblMenuDash = new JButton("  \u25A6  Dashboard");
        lblMenuDash.setForeground(Color.decode("#C8C8C8"));
        lblMenuDash.setFont(new Font("Inter", Font.PLAIN, 14));
        lblMenuDash.setBounds(20, 20, 200, 40);
        lblMenuDash.setBackground(Color.decode("#375A9B"));
        lblMenuDash.setBorderPainted(false);
        lblMenuDash.setFocusPainted(false);
        lblMenuDash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuDash);
        lblMenuDash.addActionListener(e -> { ventana.dispose(); bienvenidoDashboard(); });

        PanelRedondeado btnMenuClientes = new PanelRedondeado(10, new Color(138, 172, 235, 100)); 
        btnMenuClientes.setLayout(null);
        btnMenuClientes.setBounds(20, 150, 200, 40);
        JButton lblMenuClientes = new JButton("  \u25A6  Clientes");
        lblMenuClientes.setForeground(Color.WHITE);
        lblMenuClientes.setFont(new Font("Inter", Font.BOLD, 14));
        lblMenuClientes.setBounds(0, 0, 200, 40);
        lblMenuClientes.setContentAreaFilled(false);
        lblMenuClientes.setBorderPainted(false);
        lblMenuClientes.setFocusPainted(false);
        btnMenuClientes.add(lblMenuClientes);
        panelMenu.add(btnMenuClientes);

        JButton lblMenuArticulos = new JButton("  \u25A6  Artículos");
        lblMenuArticulos.setForeground(Color.decode("#C8C8C8"));
        lblMenuArticulos.setFont(new Font("Inter", Font.PLAIN, 14));
        lblMenuArticulos.setBounds(20, 270, 200, 40);
        lblMenuArticulos.setBackground(Color.decode("#375A9B"));
        lblMenuArticulos.setBorderPainted(false);
        lblMenuArticulos.setFocusPainted(false);
        panelMenu.add(lblMenuArticulos);

        // 3. Contenedor Principal Derecho
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(220, 0, 930, 660); 
        panelContenido.setBackground(Color.decode("#F4F6F9"));
        ventana.add(panelContenido);

        // Header Superior
        JLabel lblAdmin = new JLabel("Administrador", SwingConstants.RIGHT);
        lblAdmin.setFont(new Font("Inter", Font.BOLD, 12));
        lblAdmin.setBounds(580, 25, 130, 20);
        panelContenido.add(lblAdmin);

        PanelRedondeado btnCerrarSesion = new PanelRedondeado(10, Color.decode("#8AACED"));
        btnCerrarSesion.setLayout(null);
        btnCerrarSesion.setBounds(730, 25, 160, 40);
        JLabel lblCerrarSesion = new JLabel("→] Cerrar sesión", SwingConstants.CENTER);
        lblCerrarSesion.setFont(new Font("Inter", Font.PLAIN, 14));
        lblCerrarSesion.setBounds(0, 0, 160, 40);
        btnCerrarSesion.add(lblCerrarSesion);
        panelContenido.add(btnCerrarSesion);

        // Botón Regresar
        PanelRedondeado btnRegresar = new PanelRedondeado(10, Color.WHITE);
        btnRegresar.setLayout(null);
        btnRegresar.setBounds(40, 80, 100, 35);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel lblRegresar = new JLabel("← Regresar", SwingConstants.CENTER);
        lblRegresar.setFont(new Font("Inter", Font.PLAIN, 12));
        lblRegresar.setBounds(0, 0, 100, 35);
        btnRegresar.add(lblRegresar);
        panelContenido.add(btnRegresar);
        btnRegresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dashboardClientes();
                ventana.dispose();
            }
        });

        // Título Cliente
        JLabel lblNombreCliente = new JLabel(cliente[0]);
        lblNombreCliente.setFont(new Font("Inter", Font.BOLD, 22));
        lblNombreCliente.setBounds(160, 80, 400, 30);
        panelContenido.add(lblNombreCliente);

        JLabel lblSubtitulo = new JLabel("Detalles del cliente");
        lblSubtitulo.setFont(new Font("Inter", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBounds(160, 110, 300, 20);
        panelContenido.add(lblSubtitulo);

        // Botón Descargar PDF
        PanelRedondeado btnPdf = new PanelRedondeado(10, Color.decode("#829ECF"));
        btnPdf.setLayout(null);
        btnPdf.setBounds(680, 80, 210, 40);
        JLabel lblPdf = new JLabel("📥 Descargar resumen PDF", SwingConstants.CENTER);
        lblPdf.setFont(new Font("Inter", Font.PLAIN, 14));
        lblPdf.setForeground(Color.WHITE);
        lblPdf.setBounds(0, 0, 210, 40);
        btnPdf.add(lblPdf);
        panelContenido.add(btnPdf);

        // ================= TARJETA 1: INFORMACIÓN PERSONAL =================
        PanelRedondeado cardInfo = new PanelRedondeado(15, Color.WHITE);
        cardInfo.setLayout(null);
        cardInfo.setBounds(40, 150, 850, 140);
        panelContenido.add(cardInfo);

        JLabel tInfo = new JLabel("Información personal");
        tInfo.setFont(new Font("Inter", Font.BOLD, 12));
        tInfo.setBounds(20, 15, 200, 20);
        cardInfo.add(tInfo);

        // Correo
        JLabel lblCorreoTit = new JLabel("Correo electrónico");
        lblCorreoTit.setFont(new Font("Inter", Font.PLAIN, 10));
        lblCorreoTit.setForeground(Color.GRAY);
        lblCorreoTit.setBounds(60, 50, 200, 15);
        cardInfo.add(lblCorreoTit);
        JLabel lblCorreoVal = new JLabel(cliente[2]);
        lblCorreoVal.setFont(new Font("Inter", Font.BOLD, 12));
        lblCorreoVal.setBounds(60, 65, 200, 15);
        cardInfo.add(lblCorreoVal);

        // Teléfono
        JLabel lblTelTit = new JLabel("Teléfono");
        lblTelTit.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTelTit.setForeground(Color.GRAY);
        lblTelTit.setBounds(460, 50, 200, 15);
        cardInfo.add(lblTelTit);
        JLabel lblTelVal = new JLabel(cliente[1]);
        lblTelVal.setFont(new Font("Inter", Font.BOLD, 12));
        lblTelVal.setBounds(460, 65, 200, 15);
        cardInfo.add(lblTelVal);

        // Fecha Registro
        JLabel lblFecTit = new JLabel("Fecha de registro");
        lblFecTit.setFont(new Font("Inter", Font.PLAIN, 10));
        lblFecTit.setForeground(Color.GRAY);
        lblFecTit.setBounds(60, 95, 200, 15);
        cardInfo.add(lblFecTit);
        JLabel lblFecVal = new JLabel(cliente[3]);
        lblFecVal.setFont(new Font("Inter", Font.BOLD, 12));
        lblFecVal.setBounds(60, 110, 200, 15);
        cardInfo.add(lblFecVal);

        // Artículos Activos
        JLabel lblArtTit = new JLabel("Artículos actualmente empeñados");
        lblArtTit.setFont(new Font("Inter", Font.PLAIN, 10));
        lblArtTit.setForeground(Color.GRAY);
        lblArtTit.setBounds(460, 95, 200, 15);
        cardInfo.add(lblArtTit);
        JLabel lblArtVal = new JLabel(cliente[4]);
        lblArtVal.setFont(new Font("Inter", Font.BOLD, 12));
        lblArtVal.setBounds(460, 110, 200, 15);
        cardInfo.add(lblArtVal);

        // ================= TARJETA 2: ARTÍCULOS EMPEÑADOS =================
        PanelRedondeado cardActivos = new PanelRedondeado(15, Color.WHITE);
        cardActivos.setLayout(null);
        cardActivos.setBounds(40, 310, 850, 110);
        panelContenido.add(cardActivos);

        JLabel tActivos = new JLabel("Artículos actualmente empeñados");
        tActivos.setFont(new Font("Inter", Font.BOLD, 12));
        tActivos.setBounds(20, 15, 300, 20);
        cardActivos.add(tActivos);

        PanelRedondeado itemCaja = new PanelRedondeado(10, Color.WHITE);
        itemCaja.setBorder(new LineBorder(Color.decode("#EAECEF"), 1, true));
        itemCaja.setLayout(null);
        itemCaja.setBounds(20, 45, 810, 50);
        
        JLabel itmNom = new JLabel("Anillo de Oro 14K");
        itmNom.setFont(new Font("Inter", Font.BOLD, 12));
        itmNom.setBounds(20, 10, 200, 15);
        itemCaja.add(itmNom);
        JLabel itmDet = new JLabel("Categoría: Joyería - Fecha: 14/3/2025");
        itmDet.setFont(new Font("Inter", Font.PLAIN, 11));
        itmDet.setForeground(Color.GRAY);
        itmDet.setBounds(20, 25, 300, 15);
        itemCaja.add(itmDet);
        
        JLabel itmMonto = new JLabel("$5,000", SwingConstants.RIGHT);
        itmMonto.setFont(new Font("Inter", Font.BOLD, 12));
        itmMonto.setBounds(700, 10, 90, 15);
        itemCaja.add(itmMonto);
        JLabel itmVence = new JLabel("Vence: 14/6/2025", SwingConstants.RIGHT);
        itmVence.setFont(new Font("Inter", Font.PLAIN, 11));
        itmVence.setForeground(Color.GRAY);
        itmVence.setBounds(680, 25, 110, 15);
        itemCaja.add(itmVence);

        cardActivos.add(itemCaja);

        // ================= TARJETA 3: HISTORIAL =================
        PanelRedondeado cardHistorial = new PanelRedondeado(15, Color.WHITE);
        cardHistorial.setLayout(null);
        cardHistorial.setBounds(40, 440, 850, 170);
        panelContenido.add(cardHistorial);

        JLabel tHistorial = new JLabel("Historial de empeño (2)");
        tHistorial.setFont(new Font("Inter", Font.BOLD, 12));
        tHistorial.setBounds(20, 15, 200, 20);
        cardHistorial.add(tHistorial);

        // Cabecera Tabla Historial
        JPanel headerHist = new JPanel(null);
        headerHist.setBackground(Color.decode("#F4F6F9"));
        headerHist.setBounds(20, 45, 810, 30);
        String[] colHist = {"ARTÍCULO", "CATEGORÍA", "FECHA LÍMITE", "MONTO PRESTADO", "ESTADO", "ACCIONES"};
        int[] posXH = {20, 150, 280, 420, 560, 700};
        for (int i = 0; i < colHist.length; i++) {
            JLabel lblH = new JLabel(colHist[i]);
            lblH.setFont(new Font("Inter", Font.BOLD, 10));
            lblH.setBounds(posXH[i], 5, 120, 20);
            headerHist.add(lblH);
        }
        cardHistorial.add(headerHist);

        // Fila 1 - Historial (Simulada para diseño)
        crearFilaHistorial(cardHistorial, 85, posXH, "Anillo de Oro 14K", "Joyería", "14/6/2025", "$5,000", "Empeñado", "#FFF9C4", "#FBC02D");
        // Fila 2 - Historial
        crearFilaHistorial(cardHistorial, 125, posXH, "Laptop Dell XPS 15", "Electrónica", "30/4/2025", "$8,000", "Recuperado", "#C8E6C9", "#388E3C");

        ventana.setVisible(true);
    }
    
    public void verDetallesArticulo(String[] articulo, String[] cliente) {
        // 1. Configuración de la Ventana
        JFrame ventana = new JFrame();
        // Usamos articulo[0] asumiendo que el array trae los datos del artículo
        ventana.setTitle("Detalles del Artículo - " + articulo[0]); 
        ventana.setSize(1150, 660); // Aumentamos la altura para acomodar las tarjetas inferiores
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(Color.decode("#F4F6F9")); 
        // ================= TOP HEADER Y LOGO =================
     // 2. Panel Izquierdo (Menú lateral)
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 140, 220, 540); 
        panelMenu.setBackground(Color.decode("#375A9B"));
        ventana.add(panelMenu);
        
        // 3. Logo y Título del Sistema
        ImageIcon icon = new ImageIcon("src/img/logo (1).png");
        Image img = icon.getImage().getScaledInstance(67, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setBounds(80, 20, 67, 100);
        ventana.add(logo);
        
        JLabel titleimg = new JLabel("Sistema administrativo");
        titleimg.setFont(new Font("Inter", Font.PLAIN, 12));
        titleimg.setBounds(55, 105, 200, 14);
        titleimg.setForeground(Color.GRAY);
        ventana.add(titleimg);
        //////////////////////////////////////////////
        // 4. Botones del Menú
        JButton lblMenuDash = new JButton("  \u25A6  Dashboard");
        lblMenuDash.setForeground(Color.decode("#C8C8C8"));
        lblMenuDash.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuDash.setBounds(20, 42, 200, 40);
        lblMenuDash.setBackground(Color.decode("#375A9B"));
        lblMenuDash.setBorderPainted(false);
        lblMenuDash.setFocusPainted(false);
        lblMenuDash.setBorder(null);
        lblMenuDash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuDash);
        lblMenuDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();       // 1. Cierra la ventana actual (Clientes)
            }
        });

        // Botón: Clientes (Activo)
        PanelRedondeado btnDashboard = new PanelRedondeado(10, new Color(138, 172, 235, 100)); // Color con transparencia
        btnDashboard.setLayout(null);
        btnDashboard.setBounds(20, 150, 200, 40);
        JButton lblMenuClientes = new JButton("  \u25A6  Clientes");
        lblMenuClientes.setForeground(Color.WHITE);
        lblMenuClientes.setFont(new Font("Inter", Font.BOLD, 18));
        lblMenuClientes.setBounds(0, 0, 200, 40);
        lblMenuClientes.setBackground(Color.decode("#375A9B"));
        lblMenuClientes.setBorderPainted(false);
        lblMenuClientes.setFocusPainted(false);
        lblMenuClientes.setBorder(null);
        lblMenuClientes.setContentAreaFilled(false);
        btnDashboard.add(lblMenuClientes);
        panelMenu.add(btnDashboard);

        // Botón: Artículos
        JButton lblMenuArticulos = new JButton("  \u25A6  Artículos");
        lblMenuArticulos.setForeground(Color.decode("#C8C8C8"));
        lblMenuArticulos.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuArticulos.setBounds(20, 270, 200, 40);
        lblMenuArticulos.setBackground(Color.decode("#375A9B"));
        lblMenuArticulos.setBorderPainted(false);
        lblMenuArticulos.setFocusPainted(false);
        lblMenuArticulos.setBorder(null);
        lblMenuArticulos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblMenuArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();       // 1. Cierra la ventana actual (Dashboard)
                dashboardArticulos();     // 2. Abre la ventana de Clientes
            }
        });
        panelMenu.add(lblMenuArticulos);

        // Botón: Pagos
        JButton lblMenuPagos = new JButton("  \u25A6  Pagos");
        lblMenuPagos.setForeground(Color.decode("#C8C8C8"));
        lblMenuPagos.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMenuPagos.setBounds(20, 390, 200, 40);
        lblMenuPagos.setBackground(Color.decode("#375A9B"));
        lblMenuPagos.setBorderPainted(false);
        lblMenuPagos.setFocusPainted(false);
        lblMenuPagos.setBorder(null);
        lblMenuPagos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(lblMenuPagos);

        // ================= CONTENEDOR PRINCIPAL =================
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setBounds(220, 0, 930, 800); 
        panelContenido.setBackground(Color.decode("#F4F6F9"));
        ventana.add(panelContenido);

        // Header Superior Derecho (Admin y Cerrar Sesión)
        JLabel lblAdmin = new JLabel("Administrador", SwingConstants.RIGHT);
        lblAdmin.setFont(new Font("Inter", Font.BOLD, 12));
        lblAdmin.setBounds(580, 30, 130, 20);
        panelContenido.add(lblAdmin);

        JLabel lblEmail = new JLabel("1234@email.com", SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Inter", Font.PLAIN, 10));
        lblEmail.setForeground(Color.GRAY);
        lblEmail.setBounds(580, 50, 130, 15);
        panelContenido.add(lblEmail);

        PanelRedondeado btnCerrarSesion = new PanelRedondeado(10, Color.decode("#8AACED"));
        btnCerrarSesion.setLayout(null);
        btnCerrarSesion.setBounds(730, 30, 160, 40);
        JLabel lblCerrarSesion = new JLabel("→] Cerrar sesión", SwingConstants.CENTER);
        lblCerrarSesion.setFont(new Font("Inter", Font.PLAIN, 14));
        lblCerrarSesion.setBounds(0, 0, 160, 40);
        btnCerrarSesion.add(lblCerrarSesion);
        panelContenido.add(btnCerrarSesion);

        // Botón Regresar
        PanelRedondeado btnRegresar = new PanelRedondeado(10, Color.WHITE);
        btnRegresar.setLayout(null);
        btnRegresar.setBounds(40, 90, 100, 35);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setBorder(new LineBorder(Color.decode("#EAECEF"), 1, true));
        JLabel lblRegresar = new JLabel("← Regresar", SwingConstants.CENTER);
        lblRegresar.setFont(new Font("Inter", Font.PLAIN, 12));
        lblRegresar.setBounds(0, 0, 100, 35);
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Instanciamos y mostramos la nueva ventana modal
            	ventana.dispose();
            	dashboardArticulos();
            }
        });
        btnRegresar.add(lblRegresar);
        /*
        PanelRedondeado btnNuevoCliente = new PanelRedondeado(10, Color.decode("#829ECF"));
        btnNuevoCliente.setLayout(null);
        btnNuevoCliente.setBounds(730, 90, 160, 40);
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
        JLabel lblNuevoCliente = new JLabel("+  Nuevo artículo", SwingConstants.CENTER);
        lblNuevoCliente.setFont(new Font("Inter", Font.PLAIN, 16));
        lblNuevoCliente.setBounds(0, 0, 160, 40);
        btnNuevoCliente.add(lblNuevoCliente);
        panelContenido.add(btnNuevoCliente);
        btnNuevoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Instanciamos y mostramos la nueva ventana modal
            	ModalNuevoArticulo modal = new ModalNuevoArticulo(HomeView.this, ventana);
                modal.setVisible(true);
            }
        });
        */
        panelContenido.add(btnRegresar);

        // Título Artículo
        JLabel lblNombreArticulo = new JLabel(articulo[0]); // Remplazar por articulo[0]
        lblNombreArticulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblNombreArticulo.setBounds(160, 85, 400, 30);
        panelContenido.add(lblNombreArticulo);

        JLabel lblSubtitulo = new JLabel("Detalles del artículo");
        lblSubtitulo.setFont(new Font("Inter", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBounds(160, 115, 300, 20);
        panelContenido.add(lblSubtitulo);

        // Botón Descargar PDF
        PanelRedondeado btnPdf = new PanelRedondeado(10, Color.decode("#829ECF"));
        btnPdf.setLayout(null);
        btnPdf.setBounds(680, 85, 210, 40);
        JLabel lblPdf = new JLabel("📥 Descargar ficha PDF", SwingConstants.CENTER);
        lblPdf.setFont(new Font("Inter", Font.PLAIN, 14));
        lblPdf.setForeground(Color.WHITE);
        lblPdf.setBounds(0, 0, 210, 40);
        btnPdf.add(lblPdf);
        panelContenido.add(btnPdf);

        // ================= TARJETA 1: INFORMACIÓN DEL ARTÍCULO (IZQ-ARRIBA) =================
        PanelRedondeado cardInfo = new PanelRedondeado(15, Color.WHITE);
        cardInfo.setLayout(null);
        cardInfo.setBounds(40, 150, 520, 280);
        cardInfo.setBorder(new LineBorder(Color.decode("#EAECEF"), 1, true));
        panelContenido.add(cardInfo);

        JLabel tInfo = new JLabel("Información del artículo");
        tInfo.setFont(new Font("Inter", Font.BOLD, 13));
        tInfo.setBounds(25, 20, 200, 20);
        cardInfo.add(tInfo);

        // Badge Empeñado
        PanelRedondeado badgeEstado = new PanelRedondeado(15, Color.decode("#FFF5CC"));
        badgeEstado.setLayout(null);
        badgeEstado.setBounds(410, 20, 90, 25);
        JLabel lblEstado = new JLabel(articulo[5], SwingConstants.CENTER);
        lblEstado.setFont(new Font("Inter", Font.BOLD, 11));
        lblEstado.setForeground(Color.decode("#B78500"));
        lblEstado.setBounds(0, 0, 90, 25);
        badgeEstado.add(lblEstado);
        cardInfo.add(badgeEstado);
		//new String[] {"iPhone 14 Pro", "Carlos Rodríguez Martínez", "Electrónica", "$10,000", "9/4/2025", "Rematado", "#FFCDD2", "#D32F2F","$10,000","20/13/2027","Sin descripcion por el momento"}
        // Grid de datos (Se pueden reemplazar los emojis por JLabels con ImageIcons)
        agregarDatoGrid(cardInfo, 25, 60, "📦 Nombre del artículo", articulo[0]);
        agregarDatoGrid(cardInfo, 260, 60, "🏷️ Categoría", articulo[2]);
        agregarDatoGrid(cardInfo, 25, 120, "💲 Monto prestado", articulo[3]);
        agregarDatoGrid(cardInfo, 260, 120, "💰 Valor estimado", articulo[8]);
        agregarDatoGrid(cardInfo, 25, 180, "📅 Fecha de empeño", articulo[4]);
        agregarDatoGrid(cardInfo, 260, 180, "📆 Fecha límite de pago", articulo[9]);

        // Línea divisora
        JSeparator sep = new JSeparator();
        sep.setForeground(Color.decode("#EAECEF"));
        sep.setBounds(25, 235, 475, 1);
        cardInfo.add(sep);

        // Descripción
        JLabel lblDescTit = new JLabel("Descripción");
        lblDescTit.setFont(new Font("Inter", Font.PLAIN, 11));
        lblDescTit.setForeground(Color.GRAY);
        lblDescTit.setBounds(25, 245, 200, 15);
        cardInfo.add(lblDescTit);
        
        JLabel lblDescVal = new JLabel(articulo[10]);
        lblDescVal.setFont(new Font("Inter", Font.BOLD, 12));
        lblDescVal.setBounds(25, 260, 400, 15);
        cardInfo.add(lblDescVal);

        // ================= TARJETA 2: RESUMEN FINANCIERO (DER-ARRIBA) =================
        PanelRedondeado cardFinanzas = new PanelRedondeado(15, Color.WHITE);
        cardFinanzas.setLayout(null);
        cardFinanzas.setBounds(580, 150, 310, 280);
        cardFinanzas.setBorder(new LineBorder(Color.decode("#EAECEF"), 1, true));
        panelContenido.add(cardFinanzas);

        JLabel tFinanzas = new JLabel("Resumen financiero");
        tFinanzas.setFont(new Font("Inter", Font.BOLD, 13));
        tFinanzas.setBounds(25, 20, 200, 20);
        cardFinanzas.add(tFinanzas);

        agregarDatoFinanciero(cardFinanzas, 70, "Total prestado", articulo[3], Color.BLACK, 16);
        agregarDatoFinanciero(cardFinanzas, 120, "Total abonado", "$500", Color.decode("#2E7D32"), 14);

        JSeparator sepFin = new JSeparator();
        sepFin.setForeground(Color.decode("#EAECEF"));
        sepFin.setBounds(25, 175, 260, 1);
        cardFinanzas.add(sepFin);

        agregarDatoFinanciero(cardFinanzas, 190, "Total abonado", "$500", Color.RED, 14); // Asumiendo que el de rojo es cargo/restante

        // ================= TARJETA 3: HISTORIAL DE PAGOS (IZQ-ABAJO) =================
        PanelRedondeado cardHistorial = new PanelRedondeado(15, Color.WHITE);
        cardHistorial.setLayout(null);
        cardHistorial.setBounds(40, 450, 520, 200);
        cardHistorial.setBorder(new LineBorder(Color.decode("#EAECEF"), 1, true));
        panelContenido.add(cardHistorial);

        JLabel tHistorial = new JLabel("Historial de pagos (1)");
        tHistorial.setFont(new Font("Inter", Font.BOLD, 13));
        tHistorial.setBounds(25, 20, 200, 20);
        cardHistorial.add(tHistorial);

        // Ítem Historial
        PanelRedondeado itemPago = new PanelRedondeado(10, Color.WHITE);
        itemPago.setLayout(null);
        itemPago.setBounds(25, 60, 470, 70);
        itemPago.setBorder(new LineBorder(Color.GRAY, 1, true));
        
        JLabel lblMontoPago = new JLabel("$500");
        lblMontoPago.setFont(new Font("Inter", Font.BOLD, 14));
        lblMontoPago.setBounds(15, 10, 100, 20);
        itemPago.add(lblMontoPago);

        JLabel lblFechaPago = new JLabel("19/3/2024");
        lblFechaPago.setFont(new Font("Inter", Font.PLAIN, 12));
        lblFechaPago.setForeground(Color.GRAY);
        lblFechaPago.setBounds(15, 30, 100, 15);
        itemPago.add(lblFechaPago);

        JLabel lblConcepto = new JLabel("Pago de interés mensual");
        lblConcepto.setFont(new Font("Inter", Font.PLAIN, 13));
        lblConcepto.setBounds(15, 50, 200, 15);
        itemPago.add(lblConcepto);

        cardHistorial.add(itemPago);

        // ================= TARJETA 4: CLIENTE (DER-ABAJO) =================
        PanelRedondeado cardCliente = new PanelRedondeado(15, Color.WHITE);
        cardCliente.setLayout(null);
        cardCliente.setBounds(580, 450, 310, 260);
        cardCliente.setBorder(new LineBorder(Color.decode("#EAECEF"), 1, true));
        panelContenido.add(cardCliente);

        JLabel tCliente = new JLabel("Cliente");
        tCliente.setFont(new Font("Inter", Font.BOLD, 13));
        tCliente.setBounds(25, 20, 200, 20);
        cardCliente.add(tCliente);

        agregarDatoCliente(cardCliente, 60, "Nombre", cliente[0]); // Reemplazar por cliente[1]
        agregarDatoCliente(cardCliente, 110, "Teléfono", cliente[2]);      // Reemplazar por cliente[2]
        agregarDatoCliente(cardCliente, 160, "Correo electrónico", cliente[3]); // Reemplazar por cliente[3]

        PanelRedondeado btnPerfil = new PanelRedondeado(10, Color.decode("#829ECF"));
        btnPerfil.setLayout(null);
        btnPerfil.setBounds(25, 215, 260, 30);
        JLabel lblPerfil = new JLabel("Ver perfil completo", SwingConstants.CENTER);
        lblPerfil.setFont(new Font("Inter", Font.PLAIN, 13));
        lblPerfil.setForeground(Color.BLACK);
        lblPerfil.setBounds(0, 0, 260, 30);
        btnPerfil.add(lblPerfil);
        cardCliente.add(btnPerfil);

        ventana.setVisible(true);
    }

    // ================= MÉTODOS AUXILIARES PARA EVITAR REPETIR CÓDIGO =================
    private void agregarDatoGrid(JPanel panel, int x, int y, String titulo, String valor) {
        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Inter", Font.PLAIN, 11));
        lblTit.setForeground(Color.GRAY);
        lblTit.setBounds(x, y, 200, 15);
        panel.add(lblTit);

        JLabel lblVal = new JLabel(valor);
        lblVal.setFont(new Font("Inter", Font.BOLD, 12));
        lblVal.setBounds(x, y + 15, 200, 20);
        panel.add(lblVal);
    }

    private void agregarDatoFinanciero(JPanel panel, int y, String titulo, String valor, Color colorValor, int fontSize) {
        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Inter", Font.PLAIN, 12));
        lblTit.setForeground(Color.GRAY);
        lblTit.setBounds(25, y, 200, 15);
        panel.add(lblTit);

        JLabel lblVal = new JLabel(valor);
        lblVal.setFont(new Font("Inter", Font.BOLD, fontSize));
        lblVal.setForeground(colorValor);
        lblVal.setBounds(25, y + 20, 200, 25);
        panel.add(lblVal);
    }

    private void agregarDatoCliente(JPanel panel, int y, String titulo, String valor) {
        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Inter", Font.PLAIN, 11));
        lblTit.setForeground(Color.GRAY);
        lblTit.setBounds(25, y, 200, 15);
        panel.add(lblTit);

        JLabel lblVal = new JLabel(valor);
        lblVal.setFont(new Font("Inter", Font.BOLD, 12));
        lblVal.setBounds(25, y + 15, 260, 15);
        panel.add(lblVal);
    }
    
    private void confirmarEliminacion(String[] clienteAEliminar, JPanel panelTabla, int metod) {
        // Obtener la ventana principal para que el modal se superponga correctamente
        JFrame ventanaPadre = (JFrame) SwingUtilities.getWindowAncestor(panelTabla);
        
        JDialog dialogo = new JDialog(ventanaPadre, true);
        dialogo.setUndecorated(true);
        dialogo.setBackground(new Color(0, 0, 0, 150)); // Fondo oscuro traslúcido
        dialogo.setSize(ventanaPadre.getWidth(), ventanaPadre.getHeight());
        dialogo.setLocationRelativeTo(ventanaPadre);
        dialogo.setLayout(null);

        // Panel blanco central
        PanelRedondeado panel = new PanelRedondeado(20, Color.WHITE);
        panel.setLayout(null);
        panel.setBounds((dialogo.getWidth() - 420) / 2, (dialogo.getHeight() - 130) / 2, 500, 140);

        // Título
        JLabel lblTitulo = new JLabel("¿Estás seguro?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 19));
        lblTitulo.setBounds(-100, 20, 400, 30);
        panel.add(lblTitulo);

        // Mensaje de advertencia
        JLabel lblMsg = new JLabel();
        if (metod==1){
        	lblMsg.setText("<html>Esta acción no se puede deshacer. Se eliminara el cliente y\r\n"+"todos sus registros asociados.</html>");
        }else {
        	lblMsg.setText("<html>Esta acción no se puede deshacer. Se eliminara el articulo y\r\n"+"todos sus registros asociados.</html>");
        }
        lblMsg.setFont(new Font("Inter", Font.PLAIN, 14));
        lblMsg.setForeground(Color.GRAY);
        lblMsg.setBounds(40, 50, 440, 50);
        panel.add(lblMsg);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(260, 97, 100, 27);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setFont(new Font("Inter", Font.BOLD, 12));
        btnCancelar.setBorder(new LineBorder(new Color(200, 200, 200)));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dialogo.dispose());
        panel.add(btnCancelar);

        // Botón Eliminar
        JButton btnEliminarConfirmar = new JButton("Eliminar");
        btnEliminarConfirmar.setBounds(385, 97, 100, 27);
        btnEliminarConfirmar.setFocusPainted(false);
        btnEliminarConfirmar.setBackground(new Color(220, 53, 69));
        btnEliminarConfirmar.setForeground(Color.WHITE);
        btnEliminarConfirmar.setFont(new Font("Inter", Font.BOLD, 12));
        btnEliminarConfirmar.setBorderPainted(false);
        btnEliminarConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminarConfirmar.addActionListener(e -> {
            
            // 1. Eliminamos el cliente de la base de datos
        	if (metod==1){
        		baseDatosClientes.remove(clienteAEliminar);
            }else {
            	baseDatosArticulos.remove(clienteAEliminar);
            }
            
            
            // 2. Cerramos el modal
            dialogo.dispose();
            
            // 3. Refrescamos la tabla global (vacía el filtro para mostrar todos los restantes)
            if (metod==1){
            	renderizarTabla(panelTabla, "");
            }else {
            	renderizarTablaArticulos(panelTabla, "");
            }
            
        });
        panel.add(btnEliminarConfirmar);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }
    
    // Método auxiliar para crear las filas del historial con los estados (Badge de colores)
    private void crearFilaHistorial(JPanel contenedor, int y, int[] posX, String art, String cat, String fecha, String monto, String estado, String bgEstado, String txtEstado) {
        JLabel lArt = new JLabel(art); lArt.setFont(new Font("Inter", Font.BOLD, 11)); lArt.setBounds(posX[0], y, 120, 20);
        JLabel lCat = new JLabel(cat); lCat.setFont(new Font("Inter", Font.PLAIN, 11)); lCat.setBounds(posX[1], y, 100, 20);
        JLabel lFec = new JLabel(fecha); lFec.setFont(new Font("Inter", Font.PLAIN, 11)); lFec.setBounds(posX[2], y, 100, 20);
        JLabel lMon = new JLabel(monto); lMon.setFont(new Font("Inter", Font.BOLD, 11)); lMon.setBounds(posX[3]+10, y, 100, 20);
        
        PanelRedondeado badge = new PanelRedondeado(10, Color.decode(bgEstado));
        badge.setLayout(new BorderLayout());
        badge.setBounds(posX[4], y, 80, 20);
        JLabel lEst = new JLabel(estado, SwingConstants.CENTER);
        lEst.setFont(new Font("Inter", Font.BOLD, 10));
        lEst.setForeground(Color.decode(txtEstado));
        badge.add(lEst, BorderLayout.CENTER);

        PanelRedondeado btnAccion = new PanelRedondeado(10, Color.decode("#8AACED"));
        btnAccion.setLayout(new BorderLayout());
        btnAccion.setBounds(posX[5], y, 80, 20);
        JLabel lAcc = new JLabel("Ver detalle", SwingConstants.CENTER);
        lAcc.setFont(new Font("Inter", Font.PLAIN, 10));
        lAcc.setForeground(Color.WHITE);
        btnAccion.add(lAcc, BorderLayout.CENTER);

        contenedor.add(lArt); contenedor.add(lCat); contenedor.add(lFec);
        contenedor.add(lMon); contenedor.add(badge); contenedor.add(btnAccion);
        
        JPanel sep = new JPanel(); sep.setBackground(Color.decode("#F0F0F0"));
        sep.setBounds(20, y+25, 810, 1);
        contenedor.add(sep);
    }

    private void crearTarjetaKPI(JPanel contenedor, String titulo, String valor, String icono, Color colorFondoIco, Color colorTextoIco, int x, int y, int w, int h) {
        PanelRedondeado tarjeta = new PanelRedondeado(20, Color.WHITE);
        tarjeta.setLayout(null);
        tarjeta.setBounds(x, y, w, h);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblTitulo.setForeground(Color.GRAY);
        lblTitulo.setBounds(15, 20, 120, 20);
        tarjeta.add(lblTitulo);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblValor.setBounds(15, 45, 100, 30);
        tarjeta.add(lblValor);

        PanelCirculo circulo = new PanelCirculo(colorFondoIco);
        circulo.setLayout(new BorderLayout());
        circulo.setBounds(w - 60, 25, 45, 45);
        
        JLabel lblIcono = new JLabel(icono, SwingConstants.CENTER);
        lblIcono.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblIcono.setForeground(colorTextoIco);
        circulo.add(lblIcono, BorderLayout.CENTER);
        tarjeta.add(circulo);

        contenedor.add(tarjeta);
    }

    private void crearTarjetaEstado(JPanel contenedor, String titulo, String valor, String subtitulo, Color colorValor, int x, int y, int w, int h) {
        PanelRedondeado tarjeta = new PanelRedondeado(20, Color.WHITE);
        tarjeta.setLayout(null);
        tarjeta.setBounds(x, y, w, h);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 12));
        lblTitulo.setBounds(20, 20, 200, 20);
        tarjeta.add(lblTitulo);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Inter", Font.BOLD, 22));
        lblValor.setForeground(colorValor);
        lblValor.setBounds(20, 50, 100, 30);
        tarjeta.add(lblValor);

        JLabel lblSubtitulo = new JLabel(subtitulo);
        lblSubtitulo.setFont(new Font("Inter", Font.PLAIN, 11));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBounds(20, 85, 200, 20);
        tarjeta.add(lblSubtitulo);

        contenedor.add(tarjeta);
    }
	
	
	
	
	
	
	
	

	
}
