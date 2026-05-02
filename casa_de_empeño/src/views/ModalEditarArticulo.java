package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModalEditarArticulo extends JDialog {

    private HomeView home;

    // Componentes para extraer la información posteriormente
    private JComboBox<String> cbCliente;
    private JTextField txtNombre;
    private JComboBox<String> cbCategoria;
    private JComboBox<String> cbEstado;
    private JTextField txtMonto;
    private JTextField txtValorEstimado;
    private JTextField txtFechaEmpeno;
    private JTextField txtFechaLimite;
    private JTextArea txtDescripcion;

    public ModalEditarArticulo(HomeView home, JFrame parent, String[] articulo) {
        super(parent, true);
        this.home = home;

        setUndecorated(true);
        setSize(parent.getWidth(), parent.getHeight()); // Ocupa toda la ventana de fondo
        setLocationRelativeTo(parent);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 100)); // Fondo oscuro semitransparente
        
        // --- PANEL BLANCO CENTRAL ---
        // Se aumentan las dimensiones para acomodar todas las filas espaciadas correctamente
        int width = 600;
        int height = 660;
        PanelRedondeado panelFondo = new PanelRedondeado(20, Color.WHITE);
        panelFondo.setLayout(null);

        int posX = (getWidth() - width) / 2;
        int posY = (getHeight() - height) / 2;
        panelFondo.setBounds(posX, posY, width, height);
        
        // Evitar que el modal se cierre al hacer clic dentro del panel blanco
        panelFondo.addMouseListener(new MouseAdapter() {});

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Editar Artículo");
        lblTitulo.setFont(new Font("Inter", Font.BOLD, 22));
        lblTitulo.setForeground(Color.decode("#111827"));
        lblTitulo.setBounds(40, 30, 400, 30);
        panelFondo.add(lblTitulo);

        // --- VARIABLES DE DISEÑO (GRILLA) ---
        int marginX = 40;
        int fullWidth = width - (marginX * 2); // 520
        int gap = 20;
        int halfWidth = (fullWidth - gap) / 2; // 250
        int col1 = marginX;                    // 40
        int col2 = marginX + halfWidth + gap;  // 310

        // --- FILA 1: CLIENTE (Ancho completo) ---
        cbCliente = crearComboConLabel(panelFondo, "Cliente:*", new String[]{articulo[1], "Juan Pérez García", "María López"}, col1, 80, fullWidth);

        // --- FILA 2: NOMBRE DEL ARTÍCULO (Ancho completo) ---
        txtNombre = crearInputConLabel(panelFondo, "Nombre del artículo:*", articulo[0], col1, 160, fullWidth);

        // --- FILA 3: CATEGORÍA Y ESTADO (Mitad y Mitad) ---
        cbCategoria = crearComboConLabel(panelFondo, "Categoría:*", new String[]{articulo[2], "Joyería", "Electrónica", "Herramientas"}, col1, 240, halfWidth);
        cbEstado = crearComboConLabel(panelFondo, "Estado:*", new String[]{articulo[5],"Empeñado", "Recuperado", "Rematado"}, col2, 240, halfWidth);

        // --- FILA 4: MONTO Y VALOR ESTIMADO (Mitad y Mitad) ---
        txtMonto = crearInputConLabel(panelFondo, "Monto prestado:*", articulo[3], col1, 320, halfWidth);
        txtValorEstimado = crearInputConLabel(panelFondo, "Valor estimado:*", "Null", col2, 320, halfWidth);

        // --- FILA 5: FECHAS (Mitad y Mitad + Lógica de Calendario) ---
        txtFechaEmpeno = crearInputConLabel(panelFondo, "Fecha de empeño:*", articulo[4], col1, 400, halfWidth);
        configurarCampoFecha(txtFechaEmpeno, parent);

        txtFechaLimite = crearInputConLabel(panelFondo, "Fecha límite de pago:*", "dd/mm/aaaa", col2, 400, halfWidth);
        configurarCampoFecha(txtFechaLimite, parent);

        // --- FILA 6: DESCRIPCIÓN (Ancho completo, Área de texto) ---
        txtDescripcion = crearTextAreaConLabel(panelFondo, "Descripción:*", "Descripción detallada del artículo...", col1, 480, fullWidth, 70);

        // --- BOTÓN: CREAR ARTÍCULO ---
        PanelRedondeado btnCrear = new PanelRedondeado(10, Color.decode("#1D4ED8"));
        btnCrear.setLayout(new BorderLayout());
        btnCrear.setBounds(width - marginX - 160, 590, 160, 40); // Alineado a la derecha
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ToastAlertaSR x1=new ToastAlertaSR(parent,"ya");


        JLabel lblCrear = new JLabel("Editar artículo", SwingConstants.CENTER);
        lblCrear.setForeground(Color.WHITE);
        lblCrear.setFont(new Font("Inter", Font.BOLD, 14));
        btnCrear.add(lblCrear, BorderLayout.CENTER);
        btnCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

	            System.out.println("Artículo Editado");
	        	x1.setVisible(true);
	            ModalEditarArticulo.this.dispose();

            }
        });
        panelFondo.add(btnCrear);

        // --- CERRAR MODAL AL HACER CLIC AFUERA ---
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        add(panelFondo);
    }

    // =========================================================================================
    // MÉTODOS AUXILIARES PARA REPLICAR EXACTAMENTE EL DISEÑO VISUAL
    // =========================================================================================

    private JTextField crearInputConLabel(JPanel contenedor, String titulo, String placeholder, int x, int y, int w) {
        // 1. Etiqueta superior (Label)
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 12));
        lbl.setForeground(Color.decode("#374151")); // Gris oscuro
        lbl.setBounds(x, y, w, 20);
        contenedor.add(lbl);

        // 2. Contenedor redondeado gris para el input
        PanelRedondeado panelInput = new PanelRedondeado(10, Color.decode("#F9FAFB"));
        panelInput.setLayout(null);
        panelInput.setBounds(x, y + 25, w, 40);
        panelInput.setBorder(BorderFactory.createLineBorder(Color.decode("#E5E7EB"), 1)); // Borde sutil

        // 3. Campo de texto nativo sin bordes
        JTextField txt = new JTextField(placeholder);
        txt.setBounds(15, 0, w - 30, 40);
        txt.setBorder(null);
        txt.setOpaque(false);
        txt.setFont(new Font("Inter", Font.PLAIN, 13));
        txt.setForeground(Color.decode("#9CA3AF")); // Color del placeholder

        // 4. Lógica de Placeholder
        txt.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (txt.getText().equals(placeholder)) {
                    txt.setText("");
                    txt.setForeground(Color.decode("#111827")); // Texto negro al escribir
                }
            }
            public void focusLost(FocusEvent evt) {
                if (txt.getText().trim().isEmpty()) {
                    txt.setForeground(Color.decode("#9CA3AF"));
                    txt.setText(placeholder);
                }
            }
        });

        panelInput.add(txt);
        contenedor.add(panelInput);
        return txt;
    }

    private JComboBox<String> crearComboConLabel(JPanel contenedor, String titulo, String[] opciones, int x, int y, int w) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 12));
        lbl.setForeground(Color.decode("#374151"));
        lbl.setBounds(x, y, w, 20);
        contenedor.add(lbl);

        PanelRedondeado panelCombo = new PanelRedondeado(10, Color.decode("#F9FAFB"));
        panelCombo.setLayout(new BorderLayout());
        panelCombo.setBounds(x, y + 25, w, 40);
        panelCombo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#E5E7EB"), 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));

        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setFont(new Font("Inter", Font.PLAIN, 13));
        combo.setForeground(Color.decode("#111827"));
        combo.setBackground(Color.decode("#F9FAFB"));
        combo.setBorder(null);
        combo.setFocusable(false);
        combo.setOpaque(false);

        panelCombo.add(combo, BorderLayout.CENTER);
        contenedor.add(panelCombo);
        return combo;
    }

    private JTextArea crearTextAreaConLabel(JPanel contenedor, String titulo, String placeholder, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 12));
        lbl.setForeground(Color.decode("#374151"));
        lbl.setBounds(x, y, w, 20);
        contenedor.add(lbl);

        PanelRedondeado panelArea = new PanelRedondeado(10, Color.decode("#F9FAFB"));
        panelArea.setLayout(new BorderLayout());
        panelArea.setBounds(x, y + 25, w, h);
        panelArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#E5E7EB"), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JTextArea txtArea = new JTextArea(placeholder);
        txtArea.setFont(new Font("Inter", Font.PLAIN, 13));
        txtArea.setForeground(Color.decode("#9CA3AF"));
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setBorder(null);
        txtArea.setOpaque(false);

        txtArea.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (txtArea.getText().equals(placeholder)) {
                    txtArea.setText("");
                    txtArea.setForeground(Color.decode("#111827"));
                }
            }
            public void focusLost(FocusEvent evt) {
                if (txtArea.getText().trim().isEmpty()) {
                    txtArea.setForeground(Color.decode("#9CA3AF"));
                    txtArea.setText(placeholder);
                }
            }
        });

        panelArea.add(txtArea, BorderLayout.CENTER);
        contenedor.add(panelArea);
        return txtArea;
    }

    private void configurarCampoFecha(JTextField txtFecha, JFrame parent) {
        txtFecha.setEditable(false);
        txtFecha.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agregar icono de calendario flotante a la derecha del contenedor del JTextField
        Container panelPadre = txtFecha.getParent();
        JLabel lblIcono = new JLabel("📅");
        lblIcono.setBounds(panelPadre.getWidth() - 35, 10, 20, 20);
        lblIcono.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPadre.add(lblIcono);
        panelPadre.setComponentZOrder(lblIcono, 0); // Traer al frente

        // Evento que abre tu modal
        MouseAdapter abrirCalendario = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CalendarioModal calendario = new CalendarioModal(parent, txtFecha);
                calendario.setVisible(true);
            }
        };

        // Asignar el evento tanto al texto como al icono
        txtFecha.addMouseListener(abrirCalendario);
        lblIcono.addMouseListener(abrirCalendario);
    }
}