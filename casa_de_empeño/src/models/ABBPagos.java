package models;

import java.util.ArrayList;
import java.util.List;

/*
  Árbol Binario de Búsqueda (ABB) para pagos.
  Ordena por nombre del cliente (pago[1]) en lowercase.
 */
public class ABBPagos {

    private static class Nodo {
        String clave;
        String[] datos;
        Nodo izq, der;

        Nodo(String clave, String[] datos) {
            this.clave = clave;
            this.datos = datos;
        }
    }

    private Nodo raiz;

    // ── Insertar ────────────────────────────────────────────────────
    /**
     * Inserta un pago en el árbol.
     * Clave = cliente[1] (nombre del cliente) + id_pago[8] para evitar
     * colisiones cuando un mismo cliente tiene varios pagos.
     */
    public void insertar(String[] pago) {
        // Usamos cliente + idPago como clave compuesta para no perder registros
        String clave = pago[1].trim().toLowerCase() + "_" + pago[8];
        raiz = insertarRec(raiz, clave, pago);
    }

    private Nodo insertarRec(Nodo nodo, String clave, String[] datos) {
        if (nodo == null) return new Nodo(clave, datos);
        int cmp = clave.compareTo(nodo.clave);
        if      (cmp < 0) nodo.izq = insertarRec(nodo.izq, clave, datos);
        else if (cmp > 0) nodo.der = insertarRec(nodo.der, clave, datos);
        else              nodo.datos = datos;
        return nodo;
    }

    //Reconstruir desde lista
    public void reconstruir(List<String[]> pagos) {
        raiz = null;
        for (String[] p : pagos) insertar(p);
    }

    //  Buscar por subcadena en cliente o artículo
    public List<String[]> buscar(String texto) {
        List<String[]> resultados = new ArrayList<>();
        String t = texto.trim().toLowerCase();
        buscarRec(raiz, t, resultados);
        return resultados;
    }

    private void buscarRec(Nodo nodo, String texto, List<String[]> res) {
        if (nodo == null) return;
        buscarRec(nodo.izq, texto, res);
        // Busca en: fecha[0], cliente[1], artículo[2]
        if (nodo.datos[1].toLowerCase().contains(texto)
            || nodo.datos[2].toLowerCase().contains(texto)
            || nodo.datos[0].toLowerCase().contains(texto)) {
            res.add(nodo.datos);
        }
        buscarRec(nodo.der, texto, res);
    }

    // Nombres de clientes para el popup de sugerencias. 
    public List<String> buscarNombres(String texto) {
        List<String> nombres = new ArrayList<>();
        for (String[] p : buscar(texto)) {
            if (!nombres.contains(p[1])) nombres.add(p[1]);
        }
        return nombres;
    }

    // Recorrido completo (in-order) 
    public List<String[]> todos() {
        List<String[]> lista = new ArrayList<>();
        inOrder(raiz, lista);
        return lista;
    }

    private void inOrder(Nodo nodo, List<String[]> lista) {
        if (nodo == null) return;
        inOrder(nodo.izq, lista);
        lista.add(nodo.datos);
        inOrder(nodo.der, lista);
    }
}
