package views;

import java.awt.*;
import models.ComboItem;
import models.Cliente;
import models.ClienteModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



public class ModalNuevoPago extends JDialog {
	private ClienteModel clienteModel;
	
    private HomeView home;

    // Componentes para extraer la información posteriormente
    private JComboBox<ComboItem> cbCliente;;
    private JComboBox<String> cbArticulo;;
    private JComboBox cbTipoPago;


    private JTextField txtNombre;
    private JComboBox<String> cbCategoria;
    private JPanel txtMonto;
    private JTextField txtValorEstimado;
    private JTextField txtFechaEmpeno;
    private JTextField txtFechaLimite;
    private JTextField fechaEmpeño;
    private JPanel txtNotas;
    JComboBox<String>[] opciones = new JComboBox[3];    
    JPanel fecha=new JPanel(null);
    JPanel datos_pago=new JPanel(null);


    PanelRedondeado panelFondo = new PanelRedondeado(20, Color.WHITE);
    PanelRedondeado operacion = new PanelRedondeado(20, Color.decode("#C7D3EA"));
    PanelRedondeado btnCrear = new PanelRedondeado(10, Color.decode("#1D4ED8"));
    ToastAlerta advertencia=new ToastAlerta(ModalNuevoPago.this,"Complete los campos vacios");
    JTextArea[] info=new JTextArea[2];
    private void cargarClientes(){

        cbCliente.removeAllItems();

        ArrayList<Cliente> clientes =
            clienteModel.getClientes();

        for(Cliente c : clientes){

            cbCliente.addItem(

                new ComboItem(

                    c.getIdCliente(),

                    c.getNombres()
                    + " "
                    + c.getApellidos()
                )
            );
        }
    }
    public ModalNuevoPago(
    		
    	    HomeView home,
    	    JFrame parent,
    	    List<String[]> baseDatosClientes,
    	    List<String[]> baseDatosArticulos
    	) {
        super(parent, true);
        clienteModel = new ClienteModel();
        this.home = home;
        setUndecorated(true);
        setSize(parent.getWidth(), parent.getHeight()); // Ocupa toda la ventana de fondo
        datos_pago.setSize(parent.getWidth(), parent.getHeight());
        datos_pago.setOpaque(false);
        datos_pago.setLocation(0,0);
        panelFondo.add(datos_pago);
        operacion.setBounds(40, 232, 520, 105);
        operacion.setVisible(false);
        operacion.setLayout(null);
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

	     // CLIENTE
	
	     JLabel lblCliente = new JLabel("Cliente:*");
	     lblCliente.setBounds(col1, 55, 200, 20);
	     panelFondo.add(lblCliente);
	
	     cbCliente = new JComboBox<>();
	     cbCliente.setBounds(col1, 80, fullWidth, 35);
	     panelFondo.add(cbCliente);
	     cargarClientes(); 
	     // ARTICULO
	
	     JLabel lblArticulo = new JLabel("Artículo empeñado:*");
	     lblArticulo.setBounds(col1, 135, 250, 20);
	     panelFondo.add(lblArticulo);
	
	     cbArticulo = new JComboBox<>();
	     cbArticulo.setBounds(col1, 160, fullWidth, 35);
	     panelFondo.add(cbArticulo);
	     // TIPO PAGO
	
	     JLabel lblTipoPago = new JLabel("Tipo de pago:*");
	     lblTipoPago.setBounds(col2, 215, 200, 20);
	     panelFondo.add(lblTipoPago);
	
	     cbTipoPago = new JComboBox<>();
	
	     cbTipoPago.addItem("INTERES");
	     cbTipoPago.addItem("ABONO");
	     cbTipoPago.addItem("TOTAL");
	
	     cbTipoPago.setBounds(col2, 240, halfWidth, 35);
	     panelFondo.add(cbTipoPago);

        txtFechaEmpeno = crearInputConLabel(panelFondo, "Fecha de pago:*", "dd/mm/aaaa", col1, 240, halfWidth);
        configurarCampoFecha(txtFechaEmpeno, parent);
        datos_pago.add(fecha);
        
        txtMonto = crearTextAreaConLabel(panelFondo, "Monto Abonado:*", "500", col1, 310, fullWidth, 40, 0);
        datos_pago.add(txtMonto);
        
        txtNotas = crearTextAreaConLabel(panelFondo, "Notas:*", "Notas adicionales sobre el pago...", col1, 380, fullWidth, 70, 1);
        datos_pago.add(txtNotas);
        panelFondo.add(operacion);
        
        /*OPERACIONES*/
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setVisible(true);
        separator.setBackground(Color.decode("#638ADB"));
        separator.setBounds(10, 60, 500, 5);
        JLabel MTP =new JLabel("Monto total prestado:");
        MTP.setForeground(Color.decode("#666666"));
        MTP.setFont(new Font("Inter", Font.PLAIN, 13));
        MTP.setBounds(10,15,150,14);
        operacion.add(MTP);
        JLabel TB =new JLabel("Total abonado:");
        TB.setForeground(Color.decode("#666666"));
        TB.setFont(new Font("Inter", Font.PLAIN, 13));
        TB.setBounds(10,15,150,60);
        operacion.add(separator);
        operacion.add(TB);
        JLabel MT =new JLabel("Monto restante:");
        MT.setForeground(Color.decode("#666666"));
        MT.setFont(new Font("Inter", Font.PLAIN, 13));
        MT.setBounds(10,15,150,115);
        operacion.add(MT);
        
        btnCrear.setLayout(new BorderLayout());
        btnCrear.setBounds(width - marginX - 160, 495, 160, 40); 
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JLabel lblCrear = new JLabel("Registrar pago", SwingConstants.CENTER);
        lblCrear.setForeground(Color.WHITE);
        lblCrear.setFont(new Font("Inter", Font.BOLD, 14));
        btnCrear.add(lblCrear, BorderLayout.CENTER);
        btnCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(info[0].getText().equals("500") || info[1].getText().equals("Notas adicionales sobre el pago...")||
                	fechaEmpeño.getText().equals("")||fechaEmpeño.getText().equals("dd/mm/aaaa")|| 
                	opciones[0].getSelectedItem().equals("Selecciona un cliente")||opciones[1].getSelectedItem().equals("Selecciona un articulo")) 
                {
                	advertencia.active();
                }else 
                {
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
    	fecha.setBounds(x, y, w, 70);
    	fecha.setOpaque(false);
    	
    	// 1. Etiqueta superior (Label)
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 12));
        lbl.setForeground(Color.decode("#374151")); // Gris oscuro
        lbl.setBounds(0, 0, w, 20);
        fecha.add(lbl);

        // 2. Contenedor redondeado gris para el input
        PanelRedondeado panelInput = new PanelRedondeado(10, Color.decode("#F9FAFB"));
        panelInput.setLayout(null);
        panelInput.setBounds(0, 0 + 25, w, 40);
        panelInput.setBorder(BorderFactory.createLineBorder(Color.decode("#E5E7EB"), 1)); // Borde sutil

        // 3. Campo de texto nativo sin bordes
        JTextField txt = new JTextField(placeholder);
        txt.setBounds(15, 0, w - 30, 40);
        txt.setBorder(null);
        txt.setOpaque(false);
        txt.setFont(new Font("Inter", Font.PLAIN, 13));
        txt.setForeground(Color.decode("#9CA3AF")); // Color del placeholder
        fechaEmpeño =txt;
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
        fecha.add(panelInput);
        return txt;
    }

    private JPanel crearComboConLabel(JPanel contenedor, String titulo, String subtitulo, List<String[]> baseDatosPagos , int x, int y, int w,int index,int indexador) {
    	JPanel hola=new JPanel(null);
    	hola.setBounds(x, y, w, 70);
    	hola.setOpaque(false);
    	
    	JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 12));
        lbl.setForeground(Color.decode("#374151"));
        lbl.setBounds(0, 0, w, 20);
        hola.add(lbl);
        
        PanelRedondeado panelCombo = new PanelRedondeado(10, Color.decode("#F9FAFB"));
        panelCombo.setLayout(new BorderLayout());
        panelCombo.setBounds(0,  + 25, w, 40);
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
        

        
        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setFont(new Font("Inter", Font.PLAIN, 13));
        combo.setForeground(Color.decode("#111827"));
        combo.setBackground(Color.decode("#F9FAFB"));
        combo.setPreferredSize(new Dimension(500, 40));
        combo.setBorder(null);
        combo.setFocusable(false);
        combo.setOpaque(false);
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(combo.getSelectedItem()!=subtitulo && index==2) {
            		
            		panelFondo.setBounds(((getWidth() -600)/2), ((getWidth()-550)/2)-295, 600, 645);
            		datos_pago.setLocation(0,100);
            		btnCrear.setLocation(400, 595);
                    operacion.setVisible(true);                    
                    
            	}else {
            		
            	}
            	
            }
        });
        this.opciones[indexador]=combo;
        panelCombo.add(combo, BorderLayout.CENTER);
        hola.add(panelCombo);
        return hola;
    }

    private JPanel crearTextAreaConLabel(JPanel contenedor, String titulo, String placeholder, int x, int y, int w, int h,int textArea) {
    	JPanel hola=new JPanel(null);
    	hola.setBounds(x, y, w, 120);
    	hola.setOpaque(false);
    	
    	JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Inter", Font.BOLD, 12));
        lbl.setForeground(Color.decode("#374151"));
        lbl.setBounds(0, 10, w, 20);
        hola.add(lbl);

        PanelRedondeado panelArea = new PanelRedondeado(10, Color.decode("#F9FAFB"));
        panelArea.setLayout(new BorderLayout());
        panelArea.setBounds(0, 10 + 25, w, h);
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
        info[textArea]=txtArea;

        panelArea.add(txtArea);
        hola.add(panelArea);
        return hola;
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