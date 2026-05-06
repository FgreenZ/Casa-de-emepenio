package views;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ModalNuevoPago extends JDialog {

    private HomeView home;

    // Componentes para extraer la información posteriormente
    private JComboBox<String> cbCliente;
    private JComboBox<String> cbArticulo;

    private JTextField txtNombre;
    private JComboBox<String> cbCategoria;
    private JComboBox<String> cbEstado;
    private JTextArea txtMonto;
    private JTextField txtValorEstimado;
    private JTextField txtFechaEmpeno;
    private JTextField txtFechaLimite;
    private JTextArea txtNotas;

    PanelRedondeado panelFondo = new PanelRedondeado(20, Color.WHITE);
    PanelRedondeado btnCrear = new PanelRedondeado(10, Color.decode("#1D4ED8"));


    public ModalNuevoPago(HomeView home, JFrame parent, List<String[]> baseDatosPagos) {
        super(parent, true);
        this.home = home;

        setUndecorated(true);
        setSize(parent.getWidth(), parent.getHeight()); // Ocupa toda la ventana de fondo
        setLocationRelativeTo(parent);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 100)); // Fondo oscuro semitransparente
        
        // --- PANEL BLANCO CENTRAL ---
        // Se aumentan las dimensiones para acomodar todas las filas espaciadas correctamente
        
        panelFondo.setLayout(null);

        int width = 600;
        int height = 550;
        int posX = (getWidth() - width) / 2;
        int posY = (getHeight() - height) / 2;
        panelFondo.setBounds(posX, posY, width, height);
        
        // Evitar que el modal se cierre al hacer clic dentro del panel blanco
        panelFondo.addMouseListener(new MouseAdapter() {});

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Crear nuevo pago");
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

        cbCliente = crearComboConLabel(panelFondo, "Cliente:*", "Selecciona un cliente", baseDatosPagos, col1, 80, fullWidth,1);

        cbArticulo = crearComboConLabel(panelFondo, "Articulo empeñado:*", "Selecciona un articulo", baseDatosPagos, col1, 160, fullWidth,2);

        cbEstado = crearComboConLabel(panelFondo, "Estado:*", "Empeñado", baseDatosPagos, col2, 240, halfWidth,4);
        
        txtFechaEmpeno = crearInputConLabel(panelFondo, "Fecha de pago:*", "dd/mm/aaaa", col1, 240, halfWidth);
        configurarCampoFecha(txtFechaEmpeno, parent);
        
        txtMonto = crearTextAreaConLabel(panelFondo, "Monto Abonado:*", "500", col1, 310, fullWidth, 40);

        txtNotas = crearTextAreaConLabel(panelFondo, "Notas:*", "Notas adicionales sobre el pago...", col1, 380, fullWidth, 70);


        btnCrear.setLayout(new BorderLayout());
        btnCrear.setBounds(width - marginX - 160, 495, 160, 40); 
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ToastAlertaSR x1=new ToastAlertaSR(parent,"ya");
        ToastAlerta x=new ToastAlerta(parent,"Complete los campos vacios");
        JLabel lblCrear = new JLabel("Registrar pago", SwingConstants.CENTER);
        lblCrear.setForeground(Color.WHITE);
        lblCrear.setFont(new Font("Inter", Font.BOLD, 14));
        btnCrear.add(lblCrear, BorderLayout.CENTER);
        btnCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(
                txtMonto.getText().isEmpty() ||
                txtValorEstimado.getText().isEmpty() ||
                txtFechaEmpeno.getText().isEmpty() ||
                txtFechaLimite.getText().isEmpty() ) 
                {
                	x.setVisible(true);
                }else 
                {
                    System.out.println("Artículo registrado");
                	x1.setVisible(true);
                    ModalNuevoPago.this.dispose();
                }
                
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

    private JComboBox<String> crearComboConLabel(JPanel contenedor, String titulo, String subtitulo, List<String[]> baseDatosPagos , int x, int y, int w,int index) {
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
        List<String> filtrados = new ArrayList<>();
        String[] opciones =new String[baseDatosPagos.size()+1];
        opciones[0]=subtitulo;
        int i=1;
        for (String[] dato : baseDatosPagos) {
            	opciones[i]=dato[index];
            	i++;
        }
        
        PanelRedondeado operacion = new PanelRedondeado(20, Color.decode("#C7D3EA"));
        operacion.setBounds(50, 200, 500, 80);
        operacion.setVisible(false);
        
        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setFont(new Font("Inter", Font.PLAIN, 13));
        combo.setForeground(Color.decode("#111827"));
        combo.setBackground(Color.decode("#F9FAFB"));
        combo.setBorder(null);
        combo.setFocusable(false);
        combo.setOpaque(false);
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(combo.getSelectedItem()!=subtitulo) {
            		panelFondo.setBounds(((getWidth() -600)/2), ((getWidth()-550)/2)-280, 600, 620);
            		operacion.setVisible(true);
                    btnCrear.setBounds(400, 560, 160, 40); 
                 
                    panelFondo.add(btnCrear);
            		contenedor.add(operacion);
            		
            	}else {
            		//panelFondo.setBounds(((getWidth()-600)/2),  ((getWidth()-550)/2),  600, 300);
            	}
            	
            }
        });
        
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