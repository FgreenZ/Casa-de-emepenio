package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class ToastAlerta  {
	/*
    public ToastAlerta(JFrame parent, String mensaje) {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparente
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panel.setBackground(Color.decode("#FFEDED"));
        panel.setBorder(new RoundedBorder(10));
        
        JLabel icono = new JLabel("⚠️");
        icono.setForeground(Color.decode("#C92A2A"));
        icono.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        
        JLabel texto = new JLabel(mensaje);
        texto.setForeground(Color.decode("#C92A2A"));
        texto.setFont(new Font("Inter", Font.PLAIN, 14));
        //icono.setBounds(20, 10, 50, 50);
        
        panel.add(icono);
        panel.add(texto);
        add(panel);
        
        setSize(320, 45);

        int x, y;
        
        if (parent instanceof AuthView) {
            x = parent.getX() + parent.getWidth() - 400;   
            y = parent.getY() + 240; 
        } else {
            x = parent.getX() + parent.getWidth() - 340;
            y = parent.getY() + 50;
        }
        setLocation(x, y);

        
        Timer timer = new Timer(3000, e -> dispose());
        timer.setRepeats(false);
        timer.start();
        
    }
    */
	
    PanelRedondeado panel = new PanelRedondeado(20, Color.decode("#FEE2E2"));
    JDialog panelPadre;
    
    public ToastAlerta(JDialog panelPadre, String mensaje) {
    	
    		this.panelPadre = panelPadre;
    	
        panel.setBackground(Color.decode("#FFEDED"));
        panel.setBorder(new RoundedBorder(10));
       
    	
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panel.setBackground(Color.decode("#FFEDED"));
        panel.setBorder(new RoundedBorder(10));

        JLabel icono = new JLabel("⚠️");
        icono.setForeground(Color.decode("#C92A2A"));
        icono.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel texto = new JLabel(mensaje);
        texto.setForeground(Color.decode("#C92A2A"));
        texto.setFont(new Font("Inter", Font.PLAIN, 14));
        panel.setBounds(800, 25, 300, 50);
        panel.add(icono);
        panel.add(texto);
		
        panel.add(icono);
        panel.add(texto);
        
        panel.setSize(320, 45);
        
        panel.setVisible(false);
        panelPadre.add(panel);


    }
    
    public void active() {
    	panel.setVisible(true);
    	Timer timer = new Timer(3000, e -> panel.setVisible(false));
        timer.setRepeats(false);
        timer.start();
	}
    
}
