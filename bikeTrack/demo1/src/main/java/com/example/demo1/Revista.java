package com.example.demo1;

public class Revista extends Publicacion{
    private String tipoPrensa;

    public Revista(String tipoPrensa) {
        this.tipoPrensa = tipoPrensa;
    }

    public Revista(int id, String nombre, int numeroPaginas, String estado, int anioPublicacion, String tipoPrensa) {
        super(id, nombre, numeroPaginas, estado, anioPublicacion);
        this.tipoPrensa = tipoPrensa;
    }

    public String getTipoPrensa() {
        return tipoPrensa;
    }

    public void setTipoPrensa(String tipoPrensa) {
        this.tipoPrensa = tipoPrensa;
    }
}
