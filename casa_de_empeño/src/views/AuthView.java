package views;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//import models.AuthModel;

import javax.swing.ImageIcon;

import javax.swing.border.AbstractBorder;


class RoundedBorder extends AbstractBorder {
    private int radius;
    RoundedBorder(int radius) {
        this.radius = radius;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}


public class AuthView extends JFrame{ 
	
	//private AuthModel model;
	
	public AuthView() { 
		//this.menu(); 
		//this.router("registro");
		
		//model = new AuthModel();
	}
	
	public void Ventana() {
		
		this.setSize(1150, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		//this.setMinimumSize(new Dimension(1200,600));
		//this.setMaximumSize(new Dimension(1200,600));
		this.setTitle("Calculadora");
		this.setLocation(100,100);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());
        ImageIcon icono = new ImageIcon("icono.png");
		this.setIconImage(icono.getImage());
		
		
		/*MENU*/
		this.setVisible(true);
		this.repaint();
	}
	
	public void VentanaLogin() {
        // 1. Configuración de la Ventana Principal
        setTitle("Inicio de Sesión");
        setSize(1150, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(null); // Diseño absoluto para usar setBounds, igual que en tu archivo
        getContentPane().setBackground(Color.WHITE); // Fondo base de la ventana
        
        ImageIcon icon4 = new ImageIcon("lock1.png");
        Image img4 = icon4.getImage().getScaledInstance(100, 172, Image.SCALE_SMOOTH);
        JLabel fondo4 = new JLabel(new ImageIcon(img4));
        fondo4.setBounds(200, 500, 100, 172);
        add(fondo4);
        
        ImageIcon icon3 = new ImageIcon("img (2).png");
        Image img3 = icon3.getImage().getScaledInstance(500, 385, Image.SCALE_SMOOTH);
        JLabel fondo3 = new JLabel(new ImageIcon(img3));
        fondo3.setBounds(20, 50, 500, 385);
        add(fondo3);
        
        ImageIcon icon = new ImageIcon("fondoadorno4.png");
        JLabel fondo = new JLabel(icon);
        fondo.setBounds(0, 0, 647, 800);
        fondo.setVisible(true);
        add(fondo);
        
        ImageIcon icon2 = new ImageIcon("logo y titulo.png");
        Image img2 = icon2.getImage().getScaledInstance(550, 300, Image.SCALE_SMOOTH);
        JLabel fondo2 = new JLabel(new ImageIcon(img2));
        fondo2.setBounds(600, -20, 550, 300);
        add(fondo2);
        
        // 2. Contenedor del Login (El cuadro central)
        // Ajusta las coordenadas (x, y) si necesitas mover el cuadro en la pantalla de 1440x1024
        JPanel cuadroLogin = new JPanel();
        cuadroLogin.setLayout(null);
        cuadroLogin.setBounds(701, 0, 400, 800); // Posicionado aproximadamente al centro
        cuadroLogin.setBackground(Color.WHITE);
        add(cuadroLogin);
        
        // 4. Etiqueta de Título
        JLabel lblTitulo = new JLabel("ACCESO AL SISTEMA");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 24));
        //lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBounds(80, 210, 300, 30);
        cuadroLogin.add(lblTitulo);

        // 5. Campo de Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsuario.setBounds(0, 258, 300, 20);
        cuadroLogin.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(0, 286, 400, 38);
        txtUsuario.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtUsuario.setBorder(new RoundedBorder(10));
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        cuadroLogin.add(txtUsuario);

        // 6. Campo de Contraseña
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setBounds(0, 380, 300, 20);
        cuadroLogin.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(0, 406, 400, 35);
        txtPassword.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtPassword.setBorder(new RoundedBorder(10));
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        cuadroLogin.add(txtPassword);

        // 7. Botón de Ingreso (Estilizado como en tu archivo Ventana.java)
        JButton btnIngresar = new JButton("I N G R E S A R");
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setBounds(30, 550, 350, 40);
        //btnIngresar.setBorder(new LineBorder(Color.decode("#1D478F"), 4, true));
        btnIngresar.setBorder(new RoundedBorder(15));
        btnIngresar.setFont(new Font("Inter Light", Font.PLAIN, 20));
        btnIngresar.setBackground(Color.decode("#1D478F"));
        cuadroLogin.add(btnIngresar);

        // 8. Espacio para Imagen de Fondo (Opcional)
        // Si tu diseño lleva una imagen que cubra toda la ventana detrás del cuadro de login
        JLabel lblFondoGeneral = new JLabel();
        lblFondoGeneral.setBounds(0, 0, 1440, 1024);
        // lblFondoGeneral.setIcon(new ImageIcon("fondo_1440x1024.jpg"));
        // add(lblFondoGeneral); // Descomenta esta línea cuando agregues el fondo
        
        this.add(cuadroLogin);
        this.repaint();
        this.revalidate();
        this.setVisible(true);
        
    }



	
	
	
	
	
}
