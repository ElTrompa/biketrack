package com.example.demo1;

public class Publicacion {
    protected int id;
    protected String nombre;
    protected int numeroPaginas;
    protected String estado;
    protected int anioPublicacion;

    public Publicacion() {
        this.id = 0;
        this.nombre = "";
        this.numeroPaginas = 0;
        this.estado = "";
        this.anioPublicacion = 0;
    }

    public Publicacion(int id, String nombre, int numeroPaginas, String estado, int anioPublicacion) {
        this.id = id;
        this.nombre = nombre;
        this.numeroPaginas = numeroPaginas;
        this.estado = estado;
        this.anioPublicacion = anioPublicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
