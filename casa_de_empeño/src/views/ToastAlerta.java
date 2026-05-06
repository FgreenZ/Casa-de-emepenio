package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class ToastAlerta extends JDialog {
    public ToastAlerta(JFrame parent, String mensaje) {
        super(parent, false);
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
}
