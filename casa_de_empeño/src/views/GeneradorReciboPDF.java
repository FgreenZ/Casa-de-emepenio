package views;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;

import javax.swing.*;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;


public class GeneradorReciboPDF {

    // ── Colores corporativos ──────────────────────────────────────────
    private static final Color AZUL_OSCURO  = new Color(26,  58,  92);
    private static final Color AZUL_MEDIO   = new Color(37,  99, 168);
    private static final Color AZUL_CLARO   = new Color(214, 228, 247);
    private static final Color GRIS_CLARO   = new Color(244, 246, 249);
    private static final Color GRIS_LINEA   = new Color(204, 204, 204);
    private static final Color VERDE        = new Color(30,  107,  46);
    private static final Color NARANJA      = new Color(180,  83,   9);
    private static final Color BLANCO       = Color.WHITE;

    /*
      Abre un JFileChooser para elegir destino y genera el PDF del pago.
  
     */
    public static void generarYGuardar(String[] pago, java.awt.Component parent) {

        // ── 1. Elegir dónde guardar ────────────────────────────────────
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar recibo de pago");
        chooser.setSelectedFile(
            new File("recibo_pago_" + pago[8] + ".pdf")
        );
        chooser.setFileFilter(
            new javax.swing.filechooser.FileNameExtensionFilter(
                "Archivos PDF (*.pdf)", "pdf"
            )
        );

        int resultado = chooser.showSaveDialog(parent);
        if (resultado != JFileChooser.APPROVE_OPTION) return;

        File archivo = chooser.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new File(archivo.getAbsolutePath() + ".pdf");
        }

        // ── 2. Generar el PDF
        try {
            generarPDF(pago, archivo);
            JOptionPane.showMessageDialog(
                parent,
                "Recibo guardado correctamente en:\n" + archivo.getAbsolutePath(),
                "PDF generado",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                parent,
                "Error al generar el PDF:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    // Generación interna del PDF
    private static void generarPDF(String[] pago, File archivo) throws Exception {

        Document doc = new Document(PageSize.A4, 50, 50, 60, 60);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(archivo));
        doc.open();

        // Fuentes
        Font fuenteTitulo    = new Font(Font.HELVETICA, 22, Font.BOLD,    AZUL_OSCURO);
        Font fuenteSubtitulo = new Font(Font.HELVETICA, 11, Font.NORMAL,  AZUL_MEDIO);
        Font fuenteInst      = new Font(Font.HELVETICA,  9, Font.NORMAL,  Color.GRAY);
        Font fuenteSeccion   = new Font(Font.HELVETICA, 11, Font.BOLD,    BLANCO);
        Font fuenteEtiqueta  = new Font(Font.HELVETICA,  9, Font.BOLD,    new Color(80, 80, 80));
        Font fuenteValor     = new Font(Font.HELVETICA, 10, Font.NORMAL,  new Color(30, 30, 30));
        Font fuenteValorBold = new Font(Font.HELVETICA, 10, Font.BOLD,    AZUL_OSCURO);
        Font fuentePie       = new Font(Font.HELVETICA,  8, Font.NORMAL,  Color.GRAY);
        Font fuenteFolio     = new Font(Font.HELVETICA,  9, Font.NORMAL,  Color.GRAY);

        // Tipo de pago
        String tipoPago = pago.length > 4 ? pago[4] : "—";
        Color colorBadge = AZUL_MEDIO;
        if ("TOTAL".equalsIgnoreCase(tipoPago))    colorBadge = VERDE;
        if ("INTERES".equalsIgnoreCase(tipoPago))  colorBadge = NARANJA;

        // ENCABEZADO
        PdfPTable encabezado = new PdfPTable(2);
        encabezado.setWidthPercentage(100);
        encabezado.setWidths(new float[]{3f, 2f});
        encabezado.setSpacingAfter(4);

        // Celda izquierda: nombre
        PdfPCell celdaEmpresa = new PdfPCell();
        celdaEmpresa.setBorder(Rectangle.NO_BORDER);
        celdaEmpresa.setPaddingBottom(4);
        celdaEmpresa.addElement(new Paragraph("LA CENTRAL de empeño", fuenteTitulo));
        celdaEmpresa.addElement(new Paragraph("TU DINERO AL INSTANTE, TU CONFIANZA SIEMPRE", fuenteSubtitulo));
        celdaEmpresa.addElement(new Paragraph("La Paz, Baja California Sur", fuenteInst));
        encabezado.addCell(celdaEmpresa);

        // Celda derecha: folio y fecha
        PdfPCell celdaFolio = new PdfPCell();
        celdaFolio.setBorder(Rectangle.NO_BORDER);
        celdaFolio.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaFolio.setPaddingBottom(4);
        String fechaDoc = pago.length > 0 ? pago[0] : "—";
        celdaFolio.addElement(chunk("RECIBO DE PAGO", fuenteEtiqueta, Element.ALIGN_RIGHT));
        celdaFolio.addElement(chunk("Folio # " + (pago.length > 8 ? pago[8] : "—"),
            new Font(Font.HELVETICA, 11, Font.BOLD, AZUL_OSCURO), Element.ALIGN_RIGHT));
        celdaFolio.addElement(chunk("Fecha: " + fechaDoc, fuenteFolio, Element.ALIGN_RIGHT));
        encabezado.addCell(celdaFolio);

        doc.add(encabezado);

        // Línea divisoria azul
        LineSeparator linea = new LineSeparator(2f, 100f, AZUL_OSCURO, Element.ALIGN_CENTER, -2);
        doc.add(new Chunk(linea));
        doc.add(Chunk.NEWLINE);

        // SECCIÓN: DATOS DEL CLIENTE
        doc.add(seccionHeader("  DATOS DEL CLIENTE", fuenteSeccion));
        doc.add(Chunk.NEWLINE);

        PdfPTable tablaCliente = new PdfPTable(2);
        tablaCliente.setWidthPercentage(100);
        tablaCliente.setSpacingAfter(12);
        agregarFila(tablaCliente, "Cliente",
            pago.length > 1 ? pago[1] : "—",
            fuenteEtiqueta, fuenteValorBold, GRIS_CLARO);
        agregarFila(tablaCliente, "ID Cliente",
            pago.length > 10 ? pago[10] : "—",
            fuenteEtiqueta, fuenteValor, BLANCO);
        doc.add(tablaCliente);

        // SECCIÓN: DATOS DEL ARTÍCULO
        doc.add(seccionHeader("  DATOS DEL ARTÍCULO", fuenteSeccion));
        doc.add(Chunk.NEWLINE);

        PdfPTable tablaArticulo = new PdfPTable(2);
        tablaArticulo.setWidthPercentage(100);
        tablaArticulo.setSpacingAfter(12);
        agregarFila(tablaArticulo, "Artículo empeñado",
            pago.length > 2 ? pago[2] : "—",
            fuenteEtiqueta, fuenteValorBold, GRIS_CLARO);
        agregarFila(tablaArticulo, "ID Artículo",
            pago.length > 9 ? pago[9] : "—",
            fuenteEtiqueta, fuenteValor, BLANCO);
        doc.add(tablaArticulo);

        //SECCIÓN: DETALLE DEL PAGO
        doc.add(seccionHeader("  DETALLE DEL PAGO", fuenteSeccion));
        doc.add(Chunk.NEWLINE);

        PdfPTable tablaPago = new PdfPTable(2);
        tablaPago.setWidthPercentage(100);
        tablaPago.setSpacingAfter(12);

        agregarFila(tablaPago, "Fecha de pago",
            pago.length > 0 ? pago[0] : "—",
            fuenteEtiqueta, fuenteValor, GRIS_CLARO);

        // Tipo de pago con badge de color
        PdfPCell celdaEtTipo = celdaEtiqueta("Tipo de pago", fuenteEtiqueta, BLANCO);
        PdfPCell celdaValTipo = new PdfPCell();
        celdaValTipo.setBorder(Rectangle.BOTTOM);
        celdaValTipo.setBorderColor(GRIS_LINEA);
        celdaValTipo.setBorderWidthBottom(0.5f);
        celdaValTipo.setBackgroundColor(BLANCO);
        celdaValTipo.setPadding(7);
        Phrase badgeFrase = new Phrase("  " + tipoPago + "  ",
            new Font(Font.HELVETICA, 9, Font.BOLD, BLANCO));
        Chunk badgeChunk = new Chunk("  " + tipoPago + "  ",
            new Font(Font.HELVETICA, 9, Font.BOLD, BLANCO));
        PdfPTable badgeTabla = new PdfPTable(1);
        badgeTabla.setWidthPercentage(30);
        badgeTabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell badgeCell = new PdfPCell(new Phrase("  " + tipoPago + "  ",
            new Font(Font.HELVETICA, 9, Font.BOLD, BLANCO)));
        badgeCell.setBackgroundColor(colorBadge);
        badgeCell.setBorder(Rectangle.NO_BORDER);
        badgeCell.setPadding(4);
        badgeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        badgeTabla.addCell(badgeCell);
        celdaValTipo.addElement(badgeTabla);
        tablaPago.addCell(celdaEtTipo);
        tablaPago.addCell(celdaValTipo);

        agregarFila(tablaPago, "Monto abonado",
            pago.length > 3 ? pago[3] : "—",
            fuenteEtiqueta, fuenteValorBold, GRIS_CLARO);
        agregarFila(tablaPago, "Monto restante",
            pago.length > 11 ? pago[11] : "—",
            fuenteEtiqueta, fuenteValor, BLANCO);
        agregarFila(tablaPago, "Interés generado",
            pago.length > 12 ? pago[12] : "—",
            fuenteEtiqueta, fuenteValor, GRIS_CLARO);

        doc.add(tablaPago);

        // TOTAL DESTACADO
        PdfPTable tablaTotal = new PdfPTable(1);
        tablaTotal.setWidthPercentage(50);
        tablaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablaTotal.setSpacingAfter(20);
        PdfPCell celdaTotal = new PdfPCell();
        celdaTotal.setBackgroundColor(AZUL_OSCURO);
        celdaTotal.setBorder(Rectangle.NO_BORDER);
        celdaTotal.setPadding(10);
        celdaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        Paragraph parTotal = new Paragraph();
        parTotal.add(new Chunk("TOTAL ABONADO\n",
            new Font(Font.HELVETICA, 9, Font.NORMAL, AZUL_CLARO)));
        parTotal.add(new Chunk(pago.length > 3 ? pago[3] : "—",
            new Font(Font.HELVETICA, 18, Font.BOLD, BLANCO)));
        parTotal.setAlignment(Element.ALIGN_CENTER);
        celdaTotal.addElement(parTotal);
        tablaTotal.addCell(celdaTotal);
        doc.add(tablaTotal);

        // LÍNEA DIVISORIA
        LineSeparator lineaPie = new LineSeparator(0.5f, 100f, GRIS_LINEA, Element.ALIGN_CENTER, -2);
        doc.add(new Chunk(lineaPie));
        doc.add(Chunk.NEWLINE);

        //  PIE DE PÁGINA
        Paragraph pie = new Paragraph(
            "Este documento es un comprobante oficial de pago emitido por LA CENTRAL de empeño.\n"
            + "Conserve este recibo como comprobante de su transacción.",
            fuentePie
        );
        pie.setAlignment(Element.ALIGN_CENTER);
        doc.add(pie);

        doc.close();
    }

    // Helpers 

    private static Paragraph chunk(String texto, Font fuente, int alineacion) {
        Paragraph p = new Paragraph(texto, fuente);
        p.setAlignment(alineacion);
        return p;
    }

    private static PdfPTable seccionHeader(String texto, Font fuente) {
        PdfPTable t = new PdfPTable(1);
        t.setWidthPercentage(100);
        t.setSpacingBefore(8);
        t.setSpacingAfter(4);
        PdfPCell c = new PdfPCell(new Phrase(texto, fuente));
        c.setBackgroundColor(AZUL_OSCURO);
        c.setBorder(Rectangle.NO_BORDER);
        c.setPaddingTop(6);
        c.setPaddingBottom(6);
        c.setPaddingLeft(8);
        t.addCell(c);
        return t;
    }

    private static PdfPCell celdaEtiqueta(String etiqueta, Font fuente, Color fondo) {
        PdfPCell c = new PdfPCell(new Phrase(etiqueta, fuente));
        c.setBackgroundColor(fondo);
        c.setBorder(Rectangle.BOTTOM);
        c.setBorderColor(GRIS_LINEA);
        c.setBorderWidthBottom(0.5f);
        c.setPadding(7);
        return c;
    }

    private static void agregarFila(PdfPTable tabla,
                                    String etiqueta, String valor,
                                    Font fEtiqueta, Font fValor,
                                    Color fondo) {
        PdfPCell cEt = new PdfPCell(new Phrase(etiqueta, fEtiqueta));
        cEt.setBackgroundColor(fondo);
        cEt.setBorder(Rectangle.BOTTOM);
        cEt.setBorderColor(GRIS_LINEA);
        cEt.setBorderWidthBottom(0.5f);
        cEt.setPadding(7);

        PdfPCell cVal = new PdfPCell(new Phrase(valor != null ? valor : "—", fValor));
        cVal.setBackgroundColor(fondo);
        cVal.setBorder(Rectangle.BOTTOM);
        cVal.setBorderColor(GRIS_LINEA);
        cVal.setBorderWidthBottom(0.5f);
        cVal.setPadding(7);

        tabla.addCell(cEt);
        tabla.addCell(cVal);
    }
}
