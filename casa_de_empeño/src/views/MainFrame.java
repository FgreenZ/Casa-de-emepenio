package views;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Sistema de Gestión de Clientes");
        setSize(1150, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        add(mainPanel);
    }

    public void agregarPanel(JPanel panel, String nombre) {
        mainPanel.add(panel, nombre);
    }

    public void mostrarVista(String nombre) {
        cardLayout.show(mainPanel, nombre);
    }
    
}