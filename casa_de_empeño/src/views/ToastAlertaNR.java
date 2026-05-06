package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ToastAlertaNR extends JDialog {
    public ToastAlertaNR(JFrame parent, String mensaje) {
        super(parent, false);
        setUndecorated(true);
        setBackground(Color.decode("#FEE2E2")); 
        
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#FEE2E2"));
        panel.setBorder(new RoundedBorder(10));
        
        JLabel icono = new JLabel("⚠️");
        icono.setForeground(Color.decode("#B91C1C"));
        icono.setFont(new Font("SansSerif", Font.BOLD, 35));
        icono.setBounds(20, 10, 50, 50);

        
        JLabel texto = new JLabel(mensaje);
        texto.setForeground(Color.decode("#B91C1C"));
        texto.setFont(new Font("Inter", Font.PLAIN, 12));
        texto.setBounds(50, 20, 240, 35);
        
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
