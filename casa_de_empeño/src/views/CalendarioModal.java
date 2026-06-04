package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CalendarioModal extends JDialog {
    private YearMonth mesActual;
    private JPanel panelDias;
    private JLabel lblMesAnio;
    private JTextField txtDestino;

    public CalendarioModal(JFrame parent, JTextField txtDestino) {
        super(parent, true);
        this.txtDestino = txtDestino;
        setUndecorated(true);
        setSize(300, 320); // Ligeramente más ancho para acomodar las flechas de navegación
        
        // Calcular posición justo debajo del campo de texto
        java.awt.Point p = txtDestino.getLocationOnScreen();
        setLocation(p.x, p.y + txtDestino.getHeight() -250);

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(Color.decode("#719BE6")); // Color azul estilo material
        panelFondo.setBorder(new LineBorder(Color.GRAY, 1));

        mesActual = YearMonth.now();

        // Header del calendario (Controles, Mes y Año)
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setOpaque(false);
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAnterior = crearBotonNavegacion("<");
        btnAnterior.addActionListener(e -> cambiarMes(-1));

        JButton btnSiguiente = crearBotonNavegacion(">");
        btnSiguiente.addActionListener(e -> cambiarMes(1));

        lblMesAnio = new JLabel("", SwingConstants.CENTER);
        lblMesAnio.setForeground(Color.WHITE);
        lblMesAnio.setFont(new Font("Inter", Font.BOLD, 14));
        
        panelHeader.add(btnAnterior, BorderLayout.WEST);
        panelHeader.add(lblMesAnio, BorderLayout.CENTER);
        panelHeader.add(btnSiguiente, BorderLayout.EAST);

        // Grid de Días
        panelDias = new JPanel(new GridLayout(0, 7, 5, 5));
        panelDias.setOpaque(false);
        panelDias.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Construir los días por primera vez
        actualizarCalendario();

        // Botón Cancelar abajo
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelFooter.setOpaque(false);
        JButton btnCerrar = new JButton("Cancelar");
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());
        panelFooter.add(btnCerrar);

        panelFondo.add(panelHeader, BorderLayout.NORTH);
        panelFondo.add(panelDias, BorderLayout.CENTER);
        panelFondo.add(panelFooter, BorderLayout.SOUTH);

        add(panelFondo);
        
        // Cerrar si pierde el foco (clic afuera)
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });
    }

    private JButton crearBotonNavegacion(String texto) {
        JButton btn = new JButton(texto);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Inter", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void cambiarMes(int incremento) {
        mesActual = mesActual.plusMonths(incremento);
        actualizarCalendario();
    }

    private void actualizarCalendario() {
        panelDias.removeAll(); 

        String nombreMes = mesActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        nombreMes = nombreMes.substring(0, 1).toUpperCase() + nombreMes.substring(1); 
        lblMesAnio.setText(nombreMes + " " + mesActual.getYear());

        String[] diasSemana = {"D", "L", "M", "M", "J", "V", "S"};
        for (String dia : diasSemana) {
            JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
            lblDia.setForeground(Color.decode("#D2E3FC"));
            lblDia.setFont(new Font("Inter", Font.BOLD, 12));
            panelDias.add(lblDia);
        }

        LocalDate primerDia = mesActual.atDay(1);
        int diaSemanaInicio = primerDia.getDayOfWeek().getValue() % 7; 
        int diasEnMes = mesActual.lengthOfMonth();

        for (int i = 0; i < diaSemanaInicio; i++) {
            panelDias.add(new JLabel(""));
        }

        LocalDate hoy = LocalDate.now();

        for (int i = 1; i <= diasEnMes; i++) {
            JButton btnDia = new JButton();
            
            btnDia.setMargin(new java.awt.Insets(0,0,0,0)); 
            
            btnDia.setText(Integer.toString(i));
            btnDia.setFocusPainted(false);
            btnDia.setContentAreaFilled(false);
            btnDia.setBorderPainted(false);
            btnDia.setForeground(Color.WHITE);
            btnDia.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            if (mesActual.getYear() == hoy.getYear() && 
                mesActual.getMonth() == hoy.getMonth() && 
                i == hoy.getDayOfMonth()) {
                btnDia.setOpaque(true);
                btnDia.setBackground(Color.decode("#4A74C9")); 
            }

            final int diaSeleccionado = i;
            btnDia.addActionListener(e -> {
                String fechaFormateada = String.format("%02d/%02d/%d", diaSeleccionado, mesActual.getMonthValue(), mesActual.getYear());
                txtDestino.setText(fechaFormateada);
                txtDestino.setForeground(Color.BLACK);
                dispose(); 
            });
            panelDias.add(btnDia);
        }

        panelDias.revalidate();
        panelDias.repaint();
    }
}