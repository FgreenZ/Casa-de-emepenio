package models;

import java.util.ArrayList;
import java.util.List;

/*
  Árbol Binario de Búsqueda (ABB) para clientes.
  Ordena por nombre completo (cliente[0]) en lowercase.
  Permite búsqueda por prefijo (autocomplete) en O(log n) promedio.
 */
public class ABBClientes {

    //  Nodo interno
    private static class Nodo {
        String clave;       // nombre completo en lowercase (clave de ordenamiento)
        String[] datos;     // fila completa del cliente
        Nodo izq, der;

        Nodo(String clave, String[] datos) {
            this.clave = clave;
            this.datos = datos;
        }
    }

    private Nodo raiz;

    //  Insertar 
    public void insertar(String[] cliente) {
        String clave = cliente[0].trim().toLowerCase();
        raiz = insertarRec(raiz, clave, cliente);
    }

    private Nodo insertarRec(Nodo nodo, String clave, String[] datos) {
        if (nodo == null) return new Nodo(clave, datos);
        int cmp = clave.compareTo(nodo.clave);
        if      (cmp < 0) nodo.izq = insertarRec(nodo.izq, clave, datos);
        else if (cmp > 0) nodo.der = insertarRec(nodo.der, clave, datos);
        else              nodo.datos = datos; // actualiza si ya existe
        return nodo;
    }

    // Reconstruir desde lista 
    // Vacía el árbol y lo reconstruye con todos los clientes de la lista. 
    public void reconstruir(List<String[]> clientes) {
        raiz = null;
        for (String[] c : clientes) insertar(c);
    }

    // Buscar coincidencias por prefijo o subcadena
    /*
     Devuelve todas las filas cuyo nombre completo (cliente[0])
     contiene el texto buscado como subcadena.
     Usa recorrido in-order para iterar en orden alfabético.
     */
    public List<String[]> buscar(String texto) {
        List<String[]> resultados = new ArrayList<>();
        String t = texto.trim().toLowerCase();
        buscarRec(raiz, t, resultados);
        return resultados;
    }

    private void buscarRec(Nodo nodo, String texto, List<String[]> res) {
        if (nodo == null) return;
        // In-order: izquierda → nodo → derecha  (orden alfabético)
        buscarRec(nodo.izq, texto, res);
        if (nodo.clave.contains(texto)) res.add(nodo.datos);
        buscarRec(nodo.der, texto, res);
    }

    /*
     Devuelve solo los nombres (cliente[0]) que coinciden,
     útil para el popup de sugerencias.
     */
    public List<String> buscarNombres(String texto) {
        List<String> nombres = new ArrayList<>();
        for (String[] c : buscar(texto)) nombres.add(c[0]);
        return nombres;
    }

    /* Recorrido completo (in-order) 
     Devuelve todos los clientes en orden alfabético. */
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
