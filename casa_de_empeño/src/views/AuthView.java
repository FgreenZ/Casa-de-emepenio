package views;

import java.awt.*;
import models.AuthModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;

// Clase para los bordes redondeados que ya tenías
class RoundedBorder extends AbstractBorder {
    private int radius;
    Graphics2D g2;
    RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
    
    public void setBorderColor() {
    		g2.setColor(Color.RED);
	}
    
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(5, 10, 5, 10); // margen interno: arriba, izquierda, abajo, derecha
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = 10;
        insets.right = 10;
        insets.top = 5;
        insets.bottom = 5;
        return insets;
    }
}


public class AuthView extends JFrame { 
	private JTextField txtRegNombre, txtRegCorreo, txtRegPass, txtRegConfirmar, txtUser, txtPass;
    private JPanel cuadroLogin;

    private AuthModel model;
    public AuthView() {

        model = new AuthModel();

        VentanaLogin();
    }
    
    public void VentanaLogin() {
        // Configuración básica de la ventana (se ejecuta solo la primera vez)
        setTitle("Inicio de Sesión");
        setSize(1150, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);

        // --- IMÁGENES DE FONDO (Lado izquierdo) ---
        // (Mantenemos tus iconos y adornos originales)
        agregarAdornosFondo();

        // 2. Contenedor Dinámico (El cuadro de la derecha)
        if (cuadroLogin == null) {
            cuadroLogin = new JPanel();
            cuadroLogin.setLayout(null);
            cuadroLogin.setBounds(701, 0, 400, 800);
            cuadroLogin.setBackground(Color.WHITE);
            add(cuadroLogin);
        }
        
        // Limpiamos el contenido previo por si venimos de "Registro"
        cuadroLogin.removeAll();

        // Logo superior (el que ya tenías)
        ImageIcon icon2 = new ImageIcon("src/img/logo y titulo.png");
        Image img2 = icon2.getImage().getScaledInstance(450, 245, Image.SCALE_SMOOTH);
        JLabel fondo2 = new JLabel(new ImageIcon(img2));
        fondo2.setBounds(-70, -60, 550, 300);
        cuadroLogin.add(fondo2);
        
        // Título
        JLabel lblTitulo = new JLabel("ACCESO AL SISTEMA");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 24));
        lblTitulo.setBounds(80, 200, 300, 30);
        cuadroLogin.add(lblTitulo);
        
        // Campo Usuario
        txtUser=crearCampoTexto(cuadroLogin, "Usuario", 258, 286, false);
        // Campo Password
        txtPass= crearCampoTexto(cuadroLogin, "Contraseña", 350, 376, true);
        
        // Botón Ingresar
        PanelRedondeado btnIngresar = new PanelRedondeado(15, Color.decode("#1E4992"));
        JLabel ingresar=new JLabel("INGRESAR"){
            @Override
            public boolean contains(int x, int y) {
                return false;
            }
        };
        ingresar.setBounds(150, 450, 150, 50);
        ingresar.setBackground(null);
        ingresar.setOpaque(false); 
        ingresar.setFocusable(false);
        ingresar.setForeground(Color.WHITE);
        ingresar.setFont(new Font("Inter", Font.PLAIN, 20));
        btnIngresar.setBounds(50, 450, 300, 50);
        //JButton btnIngresar = crearBotonPrincipal("INGRESAR", 460);
        ToastAlertaLogin ventanaEmergente =new ToastAlertaLogin(AuthView.this,"<html>Correo o contraseña incorrectos. Por<br>" +
        "favor, inténtalo de nuevo.</html>");
        //btnIngresar.setBorder(new RoundedBorder(10));
        btnIngresar.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseClicked(MouseEvent e) {
            String usuario =
                    txtUser.getText();

            String contrasena =
                    txtPass.getText();

            if(usuario.isEmpty() || contrasena.isEmpty())
            {

                lblTitulo.setBounds(80,170,300,30);

                ventanaEmergente.setVisible(true);

                return;
            }

            boolean acceso =
                    model.autenticar(
                        usuario,
                        contrasena
                    );

            if(acceso)
            {

                ventanaEmergente.dispose();

                HomeView x = new HomeView();

                dispose();

                x.bienvenidoDashboard();

            }
            else
            {

                lblTitulo.setBounds(80,170,300,30);

                ventanaEmergente.setVisible(true);

            }
        	}
        });
        btnIngresar.setCursor(Cursor.getDefaultCursor()); // Cursor normal

        btnIngresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar a mano (cursor de enlace)
            	btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // Volver al cursor normal
            	btnIngresar.setCursor(Cursor.getDefaultCursor());
            }
        });
        cuadroLogin.add(ingresar);
        cuadroLogin.add(btnIngresar);
        
        // --- NUEVO: Botón para ir a Registro ---
        JButton btnIrRegistro = new JButton("¿No tienes cuenta? Regístrate aquí");
        btnIrRegistro.setBounds(25, 510, 350, 30);
        btnIrRegistro.setFont(new Font("Inter", Font.PLAIN, 14));
        btnIrRegistro.setForeground(Color.GRAY);
        btnIrRegistro.setContentAreaFilled(false);
        btnIrRegistro.setBorderPainted(false);
        btnIrRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIrRegistro.addActionListener(e -> {
        	
        		ventanaEmergente.setVisible(false);
        		VentanaRegistro();
        
        }); 
        
        cuadroLogin.add(btnIrRegistro);
        cuadroLogin.repaint();
        cuadroLogin.revalidate();
        setVisible(true);
    }

    public void VentanaRegistro() {
        cuadroLogin.removeAll();

        JLabel lblTitulo = new JLabel("Crear usuario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 28));
        lblTitulo.setBounds(0, 170, 400, 40);
        cuadroLogin.add(lblTitulo);

        int inicioY = 120;
        int espacio = 76;
        
        // Logo superior (el que ya tenías)
        ImageIcon icon2 = new ImageIcon("src/img/logo y titulo.png");
        Image img2 = icon2.getImage().getScaledInstance(450, 245, Image.SCALE_SMOOTH);
        JLabel fondo2 = new JLabel(new ImageIcon(img2));
        fondo2.setBounds(-100, -60, 550, 300);
        cuadroLogin.add(fondo2);

        // Asignamos los campos a las variables de clase
        txtRegNombre = crearCampoConIcono(cuadroLogin, "Nombre completo*", "👤", inicioY);
        txtRegCorreo = crearCampoConIcono(cuadroLogin, "Correo electrónico*", "📧", inicioY + espacio);
        txtRegPass = crearCampoConIcono(cuadroLogin, "Contraseña*", "🔒", inicioY + (espacio * 2));
        txtRegConfirmar = crearCampoConIcono(cuadroLogin, "Confirmar contraseña*", "🔒", inicioY + (espacio * 3));
        
        JButton btnCrear = crearBotonPrincipal("CREAR CUENTA", 520);
        btnCrear.addActionListener(e -> {

            String usuario =
                txtRegNombre.getText().trim();

            String correo =
                txtRegCorreo.getText().trim();

            String pass =
                txtRegPass.getText().trim();

            String confirmar =
                txtRegConfirmar.getText().trim();

            if(
                usuario.isEmpty()
                || correo.isEmpty()
                || pass.isEmpty()
                || confirmar.isEmpty()
            ){

                VentanaInexitosoRegistro();
                return;
            }

            if(!pass.equals(confirmar)){

                VentanaInexitosoRegistro();
                return;
            }

            boolean creado =
                model.crearCuenta(

                    usuario,
                    correo,
                    pass,
                    "EMPLEADO"

                );

            if(creado){

                VentanaExitoRegistro();

            }
            else{

                VentanaInexitosoRegistro();

            }

        });
        
        JButton btnIrRegistro = new JButton("¿Ya tienes una cuenta? Inicia sesion aquí");
        btnIrRegistro.setBounds(25, 570, 350, 30);
        btnIrRegistro.setFont(new Font("Inter", Font.PLAIN, 14));
        btnIrRegistro.setForeground(Color.GRAY);
        btnIrRegistro.setContentAreaFilled(false);
        btnIrRegistro.setBorderPainted(false);
        btnIrRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIrRegistro.addActionListener(e -> VentanaLogin()); // Cambia la vista
        cuadroLogin.add(btnIrRegistro);
        cuadroLogin.add(btnCrear);

        cuadroLogin.repaint();
        cuadroLogin.revalidate();
    }

    public void VentanaExitoRegistro() {
        cuadroLogin.removeAll();

        ImageIcon icon2 = new ImageIcon("src/img/logo y titulo.png");
        Image img2 = icon2.getImage().getScaledInstance(450, 245, Image.SCALE_SMOOTH);
        JLabel fondo2 = new JLabel(new ImageIcon(img2));
        fondo2.setBounds(-100, -60, 550, 300);
        cuadroLogin.add(fondo2);
        
        JLabel titulo = new JLabel("¡Usuario creado!", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 30));
        titulo.setBounds(0, 190, 400, 40);
        cuadroLogin.add(titulo);
        
        ToastAlertaSR aviso=new ToastAlertaSR(AuthView.this,"<html><center>¡Tu cuenta ha sido creada exitosamente!</center></html>");
        aviso.active();
        
        JLabel lblExito = new JLabel("Puedes usar tus credenciales para inisiar sesión ahora.", SwingConstants.CENTER);
        lblExito.setFont(new Font("Inter", Font.BOLD, 12));
        lblExito.setForeground(Color.gray);
        lblExito.setBounds(0, 365, 400, 85);
        cuadroLogin.add(lblExito);

        JButton btnVolver = crearBotonPrincipal("INICIAR SESIÓN", 440);
        btnVolver.addActionListener(e -> {
            
        	VentanaLogin();
        	//aviso.dispose();
        	
        });
        cuadroLogin.add(btnVolver);

        cuadroLogin.repaint();
        cuadroLogin.revalidate();
    }
    
    public void VentanaInexitosoRegistro() {
        cuadroLogin.removeAll();

        ImageIcon icon2 = new ImageIcon("src/img/logo y titulo.png");
        Image img2 = icon2.getImage().getScaledInstance(450, 245, Image.SCALE_SMOOTH);
        JLabel fondo2 = new JLabel(new ImageIcon(img2));
        fondo2.setBounds(-100, -60, 550, 300);
        cuadroLogin.add(fondo2);
        
        JLabel titulo = new JLabel("Error al registrar", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 30));
        titulo.setBounds(0, 190, 400, 40);
        cuadroLogin.add(titulo);
        
        ToastAlertaNR aviso=new ToastAlertaNR(AuthView.this,"<html><center>El correo electrónico ya está registrado\" o \"Las contraseñas no coinciden</center></html>");
        aviso.setVisible(true);
        
        JLabel lblExito = new JLabel(
        	    "<html><center>Por favor, verifica que todos los campos estén<br>" +
        	    "correctos e intenta registrarte nuevamente.</center></html>",
        	    SwingConstants.CENTER
        	);
        lblExito.setFont(new Font("Inter", Font.BOLD, 12));
        lblExito.setForeground(Color.gray);
        lblExito.setBounds(0, 375, 400, 50);
        cuadroLogin.add(lblExito);

        JButton btnVolver = crearBotonPrincipal("INTENTAR DE NUEVO", 440);
        btnVolver.addActionListener(e -> {
            
        	VentanaRegistro();
        	aviso.dispose();
        	
        });
        cuadroLogin.add(btnVolver);

        cuadroLogin.repaint();
        cuadroLogin.revalidate();
    }
    
    // --- MÉTODOS AUXILIARES DE DISEÑO ---

    private JTextField crearCampoConIcono(JPanel panel, String placeholder, String icono, int y) {
        JLabel lbl = new JLabel(placeholder);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setForeground(Color.GRAY);
        lbl.setBounds(25, y+90, 300, 40);
        panel.add(lbl);

        JTextField txt = new JTextField(); 
        txt.setBounds(25, y + 122, 350, 35);
        txt.setBorder(new RoundedBorder(10));
        panel.add(txt);
        
        
        JLabel lblIcono = new JLabel(icono);
        lblIcono.setForeground(Color.gray);
        lblIcono.setBounds(345, y+95, 30, 35);
        panel.add(lblIcono);
        return txt;
    }

    private JTextField crearCampoTexto(JPanel panel, String titulo, int lblY, int txtY, boolean isPass) {
        
    	JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setBounds(0, lblY, 300, 14);
        panel.add(lbl);
		
        JTextField txt = isPass ? new JPasswordField() : new JTextField();
        txt.setBounds(0, txtY, 400, 38);
        txt.setBorder(new RoundedBorder(10));
        panel.add(txt);
        
        
        return txt;
    }

    private JButton crearBotonPrincipal(String texto, int y) {
        JButton btn = new JButton(texto);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.decode("#1D478F"));
        btn.setBounds(25, y, 350, 45);
        btn.setFont(new Font("Inter", Font.BOLD, 16));
        btn.setBorder(new RoundedBorder(15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        return btn;
    }

    private void agregarAdornosFondo() {
        // (Aquí pegué tu código original de las imágenes de fondo para no perderlo)
        ImageIcon icon4 = new ImageIcon("src/img/lock1.png");
        JLabel fondo4 = new JLabel(new ImageIcon(icon4.getImage().getScaledInstance(100, 172, Image.SCALE_SMOOTH)));
        fondo4.setBounds(200, 420, 100, 172);
        add(fondo4);

        ImageIcon icon3 = new ImageIcon("src/img/img (2).png");
        JLabel fondo3 = new JLabel(new ImageIcon(icon3.getImage().getScaledInstance(400, 307, Image.SCALE_SMOOTH)));
        fondo3.setBounds(15, 10, 500, 385);
        add(fondo3);

        ImageIcon fondo1 = new ImageIcon("src/img/fondoadorno123.png");
        JLabel fondo21 = new JLabel(new ImageIcon(fondo1.getImage().getScaledInstance(620, 650, Image.SCALE_SMOOTH)));
        fondo21.setBounds(-5, -75, 620, 800);
        add(fondo21);

        ImageIcon icon = new ImageIcon("src/img/fondoadorno4.png");
        JLabel fondoImg = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(620, 650, Image.SCALE_SMOOTH)));
        fondoImg.setBounds(0, -75, 647, 800);
        add(fondoImg);
    }
}