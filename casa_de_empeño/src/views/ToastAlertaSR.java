package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ToastAlertaSR extends JDialog {
    public ToastAlertaSR(JFrame parent, String mensaje) {
        super(parent, false);
        setUndecorated(true);
        setBackground(Color.decode("#E0F2F1")); // Transparente
        
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#E0F2F1"));
        panel.setBorder(new RoundedBorder(10));
        
        JLabel icono = new JLabel("✅");
        icono.setForeground(Color.decode("#065F46"));
        icono.setFont(new Font("SansSerif", Font.BOLD, 35));
        icono.setBounds(20, 15, 50, 50);

        
        JLabel texto = new JLabel(mensaje);
        texto.setForeground(Color.decode("#065F46"));
        texto.setFont(new Font("Inter", Font.PLAIN, 15));
        texto.setBounds(50, 23, 240, 35);
        //texto.setSize(100, 20);
        
        panel.add(icono);
        panel.add(texto);
        add(panel);
        
        setSize(320, 80);

        int x, y;
        
        if (parent instanceof AuthView) {
            x = parent.getX() + parent.getWidth() - 400;   
            y = parent.getY() + 240; 
        } else {
            x = parent.getX() + parent.getWidth() - 340;
            y = parent.getY() + 50;
        }
        setLocation(x, y+70);

    }
}