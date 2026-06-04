package models;

import java.util.ArrayList;
import java.util.List;

/*
  Árbol Binario de Búsqueda (ABB) para artículos empeñados.
  Ordena por nombre del artículo (articulo[0]) en lowercase.
 */
public class ABBArticulos {

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

    //  Insertar 
    public void insertar(String[] articulo) {
        String clave = articulo[0].trim().toLowerCase();
        raiz = insertarRec(raiz, clave, articulo);
    }

    private Nodo insertarRec(Nodo nodo, String clave, String[] datos) {
        if (nodo == null) return new Nodo(clave, datos);
        int cmp = clave.compareTo(nodo.clave);
        if      (cmp < 0) nodo.izq = insertarRec(nodo.izq, clave, datos);
        else if (cmp > 0) nodo.der = insertarRec(nodo.der, clave, datos);
        else              nodo.datos = datos;
        return nodo;
    }

    //  Reconstruir desde lista 
    public void reconstruir(List<String[]> articulos) {
        raiz = null;
        for (String[] a : articulos) insertar(a);
    }

    //Buscar por subcadena en nombre del artículo
    public List<String[]> buscar(String texto) {
        List<String[]> resultados = new ArrayList<>();
        String t = texto.trim().toLowerCase();
        buscarRec(raiz, t, resultados);
        return resultados;
    }

    private void buscarRec(Nodo nodo, String texto, List<String[]> res) {
        if (nodo == null) return;
        buscarRec(nodo.izq, texto, res);
        // Busca en: nombre[0], cliente[1], categoría[2]
        if (nodo.clave.contains(texto)
            || nodo.datos[1].toLowerCase().contains(texto)
            || nodo.datos[2].toLowerCase().contains(texto)) {
            res.add(nodo.datos);
        }
        buscarRec(nodo.der, texto, res);
    }

    // Solo nombres de artículos para el popup de sugerencias. 
    public List<String> buscarNombres(String texto) {
        List<String> nombres = new ArrayList<>();
        for (String[] a : buscar(texto)) nombres.add(a[0]);
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
