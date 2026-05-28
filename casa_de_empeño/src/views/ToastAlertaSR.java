package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class ToastAlertaSR  {

    PanelRedondeado panel = new PanelRedondeado(20, Color.decode("#FFFFFF"));
    JFrame panelPadre;
    
    public ToastAlertaSR(JFrame panelPadre, String mensaje) {
        this.panelPadre = panelPadre;
        
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panel.setBackground(Color.decode("#FFEDED"));
        panel.setBorder(new RoundedBorder(10));

        JLabel icono = new JLabel("✔️");
        icono.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel texto = new JLabel(mensaje);
        texto.setForeground(Color.DARK_GRAY);
        texto.setFont(new Font("Inter", Font.PLAIN, 14));

        panel.add(icono);
        panel.add(texto);     

        JLayeredPane layeredPane = panelPadre.getLayeredPane();
        int x = (panelPadre.getWidth() - 300) / 2;
        int y = 100;
        panel.setBounds(840, 25, 280, 50);
        layeredPane.add(panel, JLayeredPane.POPUP_LAYER);

        panel.setVisible(false);
    }
    
    public void active() {
    	panel.setVisible(true);
    	panel.revalidate();
    	panel.repaint();
    	Timer timer = new Timer(3000, e -> panel.setVisible(false));
        timer.setRepeats(false);
        timer.start();
	}
    
    
    
    
    
}