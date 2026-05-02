package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

class ModalNuevoCliente extends JDialog {

	private JTextField txtNombre, txtTelefono, txtCorreo, txtFecha;
    private final String phNombre = "Ej: Juan Pérez";
    private final String phTelefono = "Ej: 6123456789";
    private final String phCorreo = "Ej: ejemplo@email.com";
    private final String phFecha = "DD/MM/AAAA     📅";
    private HomeView home;
    
    
    
    public ModalNuevoCliente(HomeView home, JFrame parent) {
        super(parent, true);
        this.home = home; 
        
        setUndecorated(true);
        
        setSize(parent.getWidth(), parent.getHeight()); // 1. El JDialog ocupará toda la ventana
        setLocationRelativeTo(parent);                  // 2. Se centra exactamente sobre el JFrame
        setLayout(null);                                // 3. IMPORTANTE para que tu panel respete el setBounds
        setBackground(new Color(0, 0, 0, 100));

        // --- PANEL BLANCO CENTRAL ---
        PanelRedondeado panelFondo = new PanelRedondeado(20, Color.WHITE);
        panelFondo.setLayout(null);
        int width = 500;
        int height = 500;
        
        // Ahora getWidth() y getHeight() ya tienen valores reales, el cálculo será perfecto
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        panelFondo.setBounds(x, y, width, height);
        panelFondo.addMouseListener(new MouseAdapter() {});

        // --- TITULO ---
        JLabel lblTitulo = new JLabel("Crear nuevo cliente");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(40, 40, 300, 30);
        panelFondo.add(lblTitulo);

        // --- CAMPOS DE TEXTO ---
        txtNombre = crearCampoTexto(panelFondo, "Nombre completo:*", phNombre, 40, 95);
        txtTelefono = crearCampoTexto(panelFondo, "Teléfono:*", phTelefono, 40, 175);
        txtCorreo = crearCampoTexto(panelFondo, "Correo electrónico:*", phCorreo, 40, 255);
        
        // Campo de fecha especial (Abre el calendario)
        txtFecha = crearCampoTexto(panelFondo, "Fecha de registro:*", phFecha, 40, 335);
        txtFecha.setEditable(false); // Evita escribir manualmente
        txtFecha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtFecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Abrir el calendario interactivo
                CalendarioModal calendario = new CalendarioModal(parent, txtFecha);
                calendario.setVisible(true);
            }
        });

        // --- BOTÓN CREAR CLIENTE ---
        PanelRedondeado btnCrear = new PanelRedondeado(10, Color.decode("#1D4ED8"));
        btnCrear.setLayout(null);
        btnCrear.setBounds(310, 420, 150, 40);
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblCrear = new JLabel("Crear cliente", SwingConstants.CENTER);
        lblCrear.setForeground(Color.WHITE);
        lblCrear.setFont(new Font("Inter", Font.PLAIN, 14));
        lblCrear.setBounds(0, 0, 150, 40);
        btnCrear.add(lblCrear);

        // --- LÓGICA DE VALIDACIÓN ---
        btnCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (camposIncompletos()) {
                    ToastAlerta alerta = new ToastAlerta(parent, "Por favor completa todos los campos");
                    alerta.setVisible(true);
                } else {
                    // Como ya tenemos la variable 'home', la usamos directamente
                    if (home != null) {
                        String nombre = txtNombre.getText().trim();
                        String telefono = txtTelefono.getText().trim();
                        String correo = txtCorreo.getText().trim();
                        String fecha = txtFecha.getText().trim();
                        
                        // Llamamos al método
                        home.registrarNuevoCliente(nombre, telefono, correo, fecha);
                    }
                    
                    System.out.println("Cliente añadido exitosamente.");
                    dispose(); // Cierra el modal
                }
            }
        });
        panelFondo.add(btnCrear);

        // Cerrar al hacer clic fuera
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        add(panelFondo);
    }
    
    private boolean camposIncompletos() {
        return txtNombre.getText().equals(phNombre) || txtNombre.getText().trim().isEmpty() ||
               txtTelefono.getText().equals(phTelefono) || txtTelefono.getText().trim().isEmpty() ||
               txtCorreo.getText().equals(phCorreo) || txtCorreo.getText().trim().isEmpty() ||
               txtFecha.getText().equals(phFecha) || txtFecha.getText().trim().isEmpty();
    }

    private JTextField crearCampoTexto(JPanel panel, String titulo, String placeholder, int x, int y) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.PLAIN, 12));
        lbl.setBounds(x, y, 200, 20);
        panel.add(lbl);

        JTextField txt = new JTextField(placeholder);
        txt.setBounds(x, y + 25, 420, 40);
        txt.setForeground(Color.GRAY);
        txt.setFont(new Font("Inter", Font.PLAIN, 14));
        txt.setBorder(new CompoundBorder(new LineBorder(Color.decode("#E0E0E0"), 1, true), BorderFactory.createEmptyBorder(0, 15, 0, 15)));

        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt.getText().equals(placeholder)) {
                    txt.setText("");
                    txt.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txt.getText().trim().isEmpty()) {
                    txt.setForeground(Color.GRAY);
                    txt.setText(placeholder);
                }
            }
        });
        panel.add(txt);
        return txt;
    }
    
}

// =========================================================
// CLASE PARA LA NOTIFICACIÓN ROJA (TOAST)
// =========================================================


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

class ToastAlertaLogin extends JDialog {
    public ToastAlertaLogin(JFrame parent, String mensaje) {
        super(parent, false);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparente
        
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#FFEDED"));
        panel.setBorder(new RoundedBorder(10));
        
        JLabel icono = new JLabel("⚠️");
        icono.setForeground(Color.decode("#C92A2A"));
        icono.setFont(new Font("SansSerif", Font.BOLD, 20));
        icono.setBounds(20, -2, 50, 50);

        JLabel texto = new JLabel(mensaje);
        texto.setForeground(Color.decode("#C92A2A"));
        texto.setFont(new Font("Inter", Font.PLAIN, 12));
        texto.setBounds(50,-2,300,50);
        
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
    }
}

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

// =========================================================
// CLASE PARA EL CALENDARIO DESPLEGABLE AZUL
// =========================================================
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
