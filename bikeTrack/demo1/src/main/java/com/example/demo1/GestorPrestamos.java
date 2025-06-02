package com.example.demo1;

import java.util.ArrayList;

public class GestorPrestamos {
    private ArrayList<Libro> libros = new ArrayList<>();
    private ArrayList<Revista> revistas = new ArrayList<>();

    public boolean agregarLibro(Libro libro) {
        String genero = libro.getGenero();
        String estado = libro.getEstado();
        int anio = libro.getAnioPublicacion();

        if (genero.equalsIgnoreCase("Ficcion") &&
                estado.equalsIgnoreCase("Nuevo") &&
                anio >= 2000 && anio <= 2005) {
            libros.add(libro);
            return true;
        }

        if (genero.equalsIgnoreCase("No Ficcion") &&
                estado.equalsIgnoreCase("Bueno") &&
                anio >= 2005 && anio <= 2015) {
            libros.add(libro);
            return true;
        }

        return false;
    }

    public boolean agregarRevista(Revista revista) {
        String estado = revista.getEstado();
        int anio = revista.getAnioPublicacion();

        if (estado.equalsIgnoreCase("Deteriorado") &&
                anio >= 2015 && anio <= 2025) {
            revistas.add(revista);
            return true;
        }

        return false;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public ArrayList<Revista> getRevistas() {
        return revistas;
    }
}
