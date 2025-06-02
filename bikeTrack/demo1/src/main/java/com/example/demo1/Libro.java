package com.example.demo1;

public class Libro extends Publicacion{
    private String isbn;
    private String tipoTapa;
    private String genero;

    public Libro(String isbn, String tipoTapa, String genero) {
        this.isbn = isbn;
        this.tipoTapa = tipoTapa;
        this.genero = genero;
    }

    public Libro(int id, String nombre, int numeroPaginas, String estado, int anioPublicacion, String isbn, String tipoTapa, String genero) {
        super(id, nombre, numeroPaginas, estado, anioPublicacion);
        this.isbn = isbn;
        this.tipoTapa = tipoTapa;
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTipoTapa() {
        return tipoTapa;
    }

    public void setTipoTapa(String tipoTapa) {
        this.tipoTapa = tipoTapa;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
