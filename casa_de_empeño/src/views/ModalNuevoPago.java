package views;

import java.awt.*;
import models.ComboItem;
import models.DataBaseModels;
import models.Cliente;
import models.ClienteModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ModalNuevoPago extends JDialog {

    private ClienteModel clienteModel;

    private HomeView home;

    private JComboBox<ComboItem> cbCliente;
    private JComboBox<String> cbArticulo;
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

    JPanel fecha = new JPanel(null);
    JPanel datos_pago = new JPanel(null);

    PanelRedondeado panelFondo =
        new PanelRedondeado(20, Color.WHITE);

    PanelRedondeado operacion =
        new PanelRedondeado(
            20,
            Color.decode("#C7D3EA")
        );

    PanelRedondeado btnCrear =
        new PanelRedondeado(
            10,
            Color.decode("#1D4ED8")
        );

    ToastAlerta advertencia =
        new ToastAlerta(
            ModalNuevoPago.this,
            "Complete los campos vacios"
        );

    JTextArea[] info = new JTextArea[2];

    private DataBaseModels db =
        new DataBaseModels(
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

    private void cargarClientes() {

        cbCliente.removeAllItems();

        ArrayList<Cliente> clientes =
            clienteModel.getClientes();

        for (Cliente c : clientes) {

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

    private void cargarArticulosPorCliente(
        int idCliente
    ) {

        cbArticulo.removeAllItems();

        List<ComboItem> articulos =
            db.obtenerArticulosPorCliente(
                idCliente
            );

        for (ComboItem articulo : articulos) {

            cbArticulo.addItem(
                articulo.getTexto()
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

        setSize(
            parent.getWidth(),
            parent.getHeight()
        );

        datos_pago.setSize(
            parent.getWidth(),
            parent.getHeight()
        );

        datos_pago.setOpaque(false);
        datos_pago.setLocation(0, 0);

        panelFondo.add(datos_pago);

        operacion.setBounds(
            40,
            232,
            520,
            105
        );

        operacion.setVisible(false);
        operacion.setLayout(null);

        setLocationRelativeTo(parent);

        setLayout(null);

        setBackground(
            new Color(0, 0, 0, 100)
        );

        panelFondo.setLayout(null);

        int width = 600;
        int height = 550;

        int posX =
            (getWidth() - width) / 2;

        int posY =
            (getHeight() - height) / 2;

        panelFondo.setBounds(
            posX,
            posY,
            width,
            height
        );

        panelFondo.addMouseListener(
            new MouseAdapter() {}
        );

        JLabel lblTitulo =
            new JLabel(
                "Crear nuevo pago"
            );

        lblTitulo.setFont(
            new Font(
                "Inter",
                Font.BOLD,
                22
            )
        );

        lblTitulo.setForeground(
            Color.decode("#111827")
        );

        lblTitulo.setBounds(
            40,
            30,
            400,
            30
        );

        panelFondo.add(lblTitulo);

        int marginX = 40;

        int fullWidth =
            width - (marginX * 2);

        int gap = 20;

        int halfWidth =
            (fullWidth - gap) / 2;

        int col1 = marginX;

        int col2 =
            marginX + halfWidth + gap;

        // CLIENTE

        JLabel lblCliente =
            new JLabel("Cliente:*");

        lblCliente.setBounds(
            col1,
            55,
            200,
            20
        );

        panelFondo.add(lblCliente);

        cbCliente =
            new JComboBox<>();

        cbCliente.setBounds(
            col1,
            80,
            fullWidth,
            35
        );

        panelFondo.add(cbCliente);

        cargarClientes();

        cbCliente.addActionListener(e -> {

            ComboItem clienteSeleccionado =
                (ComboItem)
                cbCliente.getSelectedItem();

            if (clienteSeleccionado != null) {

                cargarArticulosPorCliente(
                    clienteSeleccionado.getId()
                );
            }
        });

        // ARTICULO

        JLabel lblArticulo =
            new JLabel(
                "Artículo empeñado:*"
            );

        lblArticulo.setBounds(
            col1,
            135,
            250,
            20
        );

        panelFondo.add(lblArticulo);

        cbArticulo =
            new JComboBox<>();

        cbArticulo.setBounds(
            col1,
            160,
            fullWidth,
            35
        );

        panelFondo.add(cbArticulo);

        // TIPO PAGO

        JLabel lblTipoPago =
            new JLabel(
                "Tipo de pago:*"
            );

        lblTipoPago.setBounds(
            col2,
            215,
            200,
            20
        );

        panelFondo.add(lblTipoPago);

        cbTipoPago =
            new JComboBox<>();

        cbTipoPago.addItem("INTERES");
        cbTipoPago.addItem("ABONO");
        cbTipoPago.addItem("TOTAL");

        cbTipoPago.setBounds(
            col2,
            240,
            halfWidth,
            35
        );

        panelFondo.add(cbTipoPago);

        txtFechaEmpeno =
            crearInputConLabel(
                panelFondo,
                "Fecha de pago:*",
                "dd/mm/aaaa",
                col1,
                240,
                halfWidth
            );

        configurarCampoFecha(
            txtFechaEmpeno,
            parent
        );

        datos_pago.add(fecha);

        txtMonto =
            crearTextAreaConLabel(
                panelFondo,
                "Monto Abonado:*",
                "500",
                col1,
                310,
                fullWidth,
                40,
                0
            );

        datos_pago.add(txtMonto);

        txtNotas =
            crearTextAreaConLabel(
                panelFondo,
                "Notas:*",
                "Notas adicionales sobre el pago...",
                col1,
                380,
                fullWidth,
                70,
                1
            );

        datos_pago.add(txtNotas);

        panelFondo.add(operacion);

        JSeparator separator =
            new JSeparator(
                SwingConstants.HORIZONTAL
            );

        separator.setVisible(true);

        separator.setBackground(
            Color.decode("#638ADB")
        );

        separator.setBounds(
            10,
            60,
            500,
            5
        );

        JLabel MTP =
            new JLabel(
                "Monto total prestado:"
            );

        MTP.setForeground(
            Color.decode("#666666")
        );

        MTP.setFont(
            new Font(
                "Inter",
                Font.PLAIN,
                13
            )
        );

        MTP.setBounds(
            10,
            15,
            150,
            14
        );

        operacion.add(MTP);

        JLabel TB =
            new JLabel(
                "Total abonado:"
            );

        TB.setForeground(
            Color.decode("#666666")
        );

        TB.setFont(
            new Font(
                "Inter",
                Font.PLAIN,
                13
            )
        );

        TB.setBounds(
            10,
            15,
            150,
            60
        );

        operacion.add(separator);
        operacion.add(TB);

        JLabel MT =
            new JLabel(
                "Monto restante:"
            );

        MT.setForeground(
            Color.decode("#666666")
        );

        MT.setFont(
            new Font(
                "Inter",
                Font.PLAIN,
                13
            )
        );

        MT.setBounds(
            10,
            15,
            150,
            115
        );

        operacion.add(MT);

        btnCrear.setLayout(
            new BorderLayout()
        );

        btnCrear.setBounds(
            width - marginX - 160,
            495,
            160,
            40
        );

        btnCrear.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        JLabel lblCrear =
            new JLabel(
                "Registrar pago",
                SwingConstants.CENTER
            );

        lblCrear.setForeground(
            Color.WHITE
        );

        lblCrear.setFont(
            new Font(
                "Inter",
                Font.BOLD,
                14
            )
        );

        btnCrear.add(
            lblCrear,
            BorderLayout.CENTER
        );

        panelFondo.add(btnCrear);

        addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(
                    MouseEvent e
                ) {

                    dispose();
                }
            }
        );

        add(panelFondo);
    }

    // ==========================
    // MÉTODOS AUXILIARES
    // ==========================

    private JTextField crearInputConLabel(
        JPanel contenedor,
        String titulo,
        String placeholder,
        int x,
        int y,
        int w
    ) {

        fecha.setBounds(
            x,
            y,
            w,
            70
        );

        fecha.setOpaque(false);

        JLabel lbl =
            new JLabel(titulo);

        lbl.setFont(
            new Font(
                "Inter",
                Font.BOLD,
                12
            )
        );

        lbl.setForeground(
            Color.decode("#374151")
        );

        lbl.setBounds(
            0,
            0,
            w,
            20
        );

        fecha.add(lbl);

        PanelRedondeado panelInput =
            new PanelRedondeado(
                10,
                Color.decode("#F9FAFB")
            );

        panelInput.setLayout(null);

        panelInput.setBounds(
            0,
            25,
            w,
            40
        );

        panelInput.setBorder(
            BorderFactory.createLineBorder(
                Color.decode("#E5E7EB"),
                1
            )
        );

        JTextField txt =
            new JTextField(placeholder);

        txt.setBounds(
            15,
            0,
            w - 30,
            40
        );

        txt.setBorder(null);

        txt.setOpaque(false);

        txt.setFont(
            new Font(
                "Inter",
                Font.PLAIN,
                13
            )
        );

        txt.setForeground(
            Color.decode("#9CA3AF")
        );

        fechaEmpeño = txt;

        panelInput.add(txt);

        fecha.add(panelInput);

        return txt;
    }

    private JPanel crearTextAreaConLabel(
        JPanel contenedor,
        String titulo,
        String placeholder,
        int x,
        int y,
        int w,
        int h,
        int textArea
    ) {

        JPanel hola =
            new JPanel(null);

        hola.setBounds(
            x,
            y,
            w,
            120
        );

        hola.setOpaque(false);

        JLabel lbl =
            new JLabel(titulo);

        lbl.setFont(
            new Font(
                "Inter",
                Font.BOLD,
                12
            )
        );

        lbl.setForeground(
            Color.decode("#374151")
        );

        lbl.setBounds(
            0,
            10,
            w,
            20
        );

        hola.add(lbl);

        PanelRedondeado panelArea =
            new PanelRedondeado(
                10,
                Color.decode("#F9FAFB")
            );

        panelArea.setLayout(
            new BorderLayout()
        );

        panelArea.setBounds(
            0,
            35,
            w,
            h
        );

        JTextArea txtArea =
            new JTextArea(placeholder);

        txtArea.setLineWrap(true);

        txtArea.setWrapStyleWord(true);

        info[textArea] = txtArea;

        panelArea.add(txtArea);

        hola.add(panelArea);

        return hola;
    }

    private void configurarCampoFecha(
        JTextField txtFecha,
        JFrame parent
    ) {

        txtFecha.setEditable(false);

        txtFecha.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        Container panelPadre =
            txtFecha.getParent();

        JLabel lblIcono =
            new JLabel("📅");

        lblIcono.setBounds(
            panelPadre.getWidth() - 35,
            10,
            20,
            20
        );

        panelPadre.add(lblIcono);

        MouseAdapter abrirCalendario =
            new MouseAdapter() {

                @Override
                public void mouseClicked(
                    MouseEvent e
                ) {

                    CalendarioModal calendario =
                        new CalendarioModal(
                            parent,
                            txtFecha
                        );

                    calendario.setVisible(true);
                }
            };

        txtFecha.addMouseListener(
            abrirCalendario
        );

        lblIcono.addMouseListener(
            abrirCalendario
        );
    }
}