package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import controllers.HomeController;

class PanelRedondeado extends JPanel {
    private int radio;
    private Color colorFondo;

    public PanelRedondeado(int radio, Color colorFondo) {
        this.radio = radio;
        this.colorFondo = colorFondo;
        setOpaque(false);
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
public class HomeView {


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
	
    public void dashBoard() {
    // 1. Configuración de la Ventana Principal
		JFrame ventana = new JFrame();
		ventana.setTitle("Sistema Administrativo - Dashboard");
		ventana.setSize(1150, 660);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
		ventana.setLayout(null); // Diseño absoluto para usar setBounds
		ventana.getContentPane().setBackground(Color.decode("#F4F6F9")); // Fondo base de la ventana

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
        btnDashboard.setBounds(20, 60, 200, 40);
        
        JLabel lblMenuDash = new JLabel("  \u25A6  Dashboard");
        lblMenuDash.setForeground(Color.WHITE);
        lblMenuDash.setFont(new Font("Inter", Font.BOLD, 14));
        lblMenuDash.setBounds(0, 0, 200, 40);
        btnDashboard.add(lblMenuDash);
        panelMenu.add(btnDashboard);

        // Botón: Clientes
        JLabel lblMenuClientes = new JLabel("  \u25A6  Clientes");
        lblMenuClientes.setForeground(Color.decode("#C8C8C8"));
        lblMenuClientes.setFont(new Font("Inter", Font.PLAIN, 14));
        lblMenuClientes.setBounds(20, 150, 200, 40);
        panelMenu.add(lblMenuClientes);

        // Botón: Artículos
        JLabel lblMenuArticulos = new JLabel("  \u25A6  Artículos");
        lblMenuArticulos.setForeground(Color.decode("#C8C8C8"));
        lblMenuArticulos.setFont(new Font("Inter", Font.PLAIN, 14));
        lblMenuArticulos.setBounds(20, 270, 200, 40);
        panelMenu.add(lblMenuArticulos);

        // Botón: Pagos
        JLabel lblMenuPagos = new JLabel("  \u25A6  Pagos");
        lblMenuPagos.setForeground(Color.decode("#C8C8C8"));
        lblMenuPagos.setFont(new Font("Inter", Font.PLAIN, 14));
        lblMenuPagos.setBounds(20, 390, 200, 40);
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

    // ================= MÉTODOS AUXILIARES =================

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
