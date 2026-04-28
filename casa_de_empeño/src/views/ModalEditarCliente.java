package views;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import java.awt.event.*;
import java.awt.Font;

class ModalEditarCliente extends JDialog {

    private JTextField txtNombre, txtTelefono, txtCorreo, txtFecha;
    private HomeView home;
    private int indexCliente; // Guardamos qué cliente estamos editando

    public ModalEditarCliente(HomeView home, JFrame parent, String[] cliente, int index) {
        super(parent, true);
        this.home = home; 
        this.indexCliente = index;
        
        setUndecorated(true);
        setSize(parent.getWidth(), parent.getHeight());
        setLocationRelativeTo(parent);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 100));

        // --- PANEL BLANCO CENTRAL ---
        PanelRedondeado panelFondo = new PanelRedondeado(20, Color.WHITE);
        panelFondo.setLayout(null);
        int width = 500;
        int height = 500;
        
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        panelFondo.setBounds(x, y, width, height);
        panelFondo.addMouseListener(new MouseAdapter() {}); // Evita clics pasantes

        // --- TITULO ---
        JLabel lblTitulo = new JLabel("Editar cliente");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setBounds(40, 40, 300, 30);
        panelFondo.add(lblTitulo);

        // --- CAMPOS DE TEXTO PRE-LLENADOS ---
        txtNombre = crearCampoTexto(panelFondo, "Nombre completo:*", cliente[0], 40, 95);
        txtTelefono = crearCampoTexto(panelFondo, "Teléfono:*", cliente[1], 40, 175);
        txtCorreo = crearCampoTexto(panelFondo, "Correo electrónico:*", cliente[2], 40, 255);
        
        txtFecha = crearCampoTexto(panelFondo, "Fecha de registro:*", cliente[3], 40, 335);
        txtFecha.setEditable(false); 
        txtFecha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtFecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CalendarioModal calendario = new CalendarioModal(parent, txtFecha);
                calendario.setVisible(true);
            }
        });

        // --- BOTÓN ACTUALIZAR ---
        PanelRedondeado btnActualizar = new PanelRedondeado(10, Color.decode("#1D4ED8")); // Azul oscuro
        btnActualizar.setLayout(null);
        btnActualizar.setBounds(290, 420, 170, 40);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblActualizar = new JLabel("Actualizar cliente", SwingConstants.CENTER);
        lblActualizar.setForeground(Color.WHITE);
        lblActualizar.setFont(new Font("Inter", Font.PLAIN, 14));
        lblActualizar.setBounds(0, 0, 170, 40);
        btnActualizar.add(lblActualizar);

        // --- LÓGICA DE ACTUALIZACIÓN ---
        btnActualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (camposIncompletos()) {
                    ToastAlertaNR alerta = new ToastAlertaNR(parent, "Por favor completa todos los campos");
                    alerta.setVisible(true);
                } else {
                    if (home != null) {
                        String nombre = txtNombre.getText().trim();
                        String telefono = txtTelefono.getText().trim();
                        String correo = txtCorreo.getText().trim();
                        String fecha = txtFecha.getText().trim();
                        
                        // Llamamos al método de actualizar
                        home.actualizarCliente(indexCliente, nombre, telefono, correo, fecha);
                    }
                    dispose(); // Cierra el modal
                }
            }
        });
        panelFondo.add(btnActualizar);

        // Cerrar al hacer clic fuera del panel blanco
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        add(panelFondo);
    }

    private boolean camposIncompletos() {
        return txtNombre.getText().trim().isEmpty() ||
               txtTelefono.getText().trim().isEmpty() ||
               txtCorreo.getText().trim().isEmpty() ||
               txtFecha.getText().trim().isEmpty();
    }

    // Método más sencillo que el de creación, ya que no necesita Placeholders grises
    private JTextField crearCampoTexto(JPanel panel, String titulo, String valorActual, int x, int y) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.PLAIN, 12));
        lbl.setBounds(x, y, 200, 20);
        panel.add(lbl);

        JTextField txt = new JTextField(valorActual);
        txt.setBounds(x, y + 25, 420, 40);
        txt.setForeground(Color.BLACK); // Texto negro por defecto porque ya trae datos
        txt.setFont(new Font("Inter", Font.PLAIN, 14));
        txt.setBorder(new CompoundBorder(new LineBorder(Color.decode("#E0E0E0"), 1, true), BorderFactory.createEmptyBorder(0, 15, 0, 15)));

        panel.add(txt);
        return txt;
    }
}
