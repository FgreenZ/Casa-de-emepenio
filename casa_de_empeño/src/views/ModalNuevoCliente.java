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



// =========================================================
// CLASE PARA EL CALENDARIO DESPLEGABLE AZUL
// =========================================================

