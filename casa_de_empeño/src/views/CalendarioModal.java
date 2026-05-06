package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

class CalendarioModal extends JDialog {
    private YearMonth mesActual;

    public CalendarioModal(JFrame parent, JTextField txtDestino) {
        super(parent, true);
        setUndecorated(true);
        setSize(280, 320);
        
        // Calcular posición justo debajo del campo de texto
        java.awt.Point p = txtDestino.getLocationOnScreen();
        setLocation(p.x, p.y + txtDestino.getHeight() + 5);

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(Color.decode("#719BE6")); // Color azul estilo material
        panelFondo.setBorder(new LineBorder(Color.GRAY, 1));

        mesActual = YearMonth.now();

        // Header del calendario (Mes y Año)
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setOpaque(false);
        
        JLabel lblMesAnio = new JLabel(mesActual.getMonth().name() + " " + mesActual.getYear(), SwingConstants.CENTER);
        lblMesAnio.setForeground(Color.WHITE);
        lblMesAnio.setFont(new Font("Inter", Font.BOLD, 14));
        panelHeader.add(lblMesAnio, BorderLayout.CENTER);
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Grid de Días
        JPanel panelDias = new JPanel(new GridLayout(0, 7, 5, 5));
        panelDias.setOpaque(false);
        panelDias.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] diasSemana = {"D", "L", "M", "M", "J", "V", "S"};
        for (String dia : diasSemana) {
            JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
            lblDia.setForeground(Color.decode("#D2E3FC"));
            panelDias.add(lblDia);
        }

        // Llenar los días del mes
        LocalDate primerDia = mesActual.atDay(1);
        int diaSemanaInicio = primerDia.getDayOfWeek().getValue() % 7; // Ajustar para que Domingo sea 0
        int diasEnMes = mesActual.lengthOfMonth();

        // Espacios en blanco antes del día 1
        for (int i = 0; i < diaSemanaInicio; i++) {
            panelDias.add(new JLabel(""));
        }

        // Botones de cada día
        for (int i = 1; i <= diasEnMes; i++) {
            JButton btnDia = new JButton(String.valueOf(i));
            btnDia.setFocusPainted(false);
            btnDia.setContentAreaFilled(false);
            btnDia.setBorderPainted(false);
            btnDia.setForeground(Color.WHITE);
            btnDia.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            final int diaSeleccionado = i;
            btnDia.addActionListener(e -> {
                String fechaFormateada = String.format("%02d/%02d/%d", diaSeleccionado, mesActual.getMonthValue(), mesActual.getYear());
                txtDestino.setText(fechaFormateada);
                txtDestino.setForeground(Color.BLACK);
                dispose(); // Cierra el calendario al seleccionar
            });
            panelDias.add(btnDia);
        }

        // Botón Cancelar abajo
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelFooter.setOpaque(false);
        JButton btnCerrar = new JButton("Cancelar");
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(e -> dispose());
        panelFooter.add(btnCerrar);

        panelFondo.add(panelHeader, BorderLayout.NORTH);
        panelFondo.add(panelDias, BorderLayout.CENTER);
        panelFondo.add(panelFooter, BorderLayout.SOUTH);

        add(panelFondo);
        
        // Cerrar si pierde el foco (clic afuera)
        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                dispose();
            }
        });
    }
}