package com.example.demo;

public class Barco {
    private String nombre;
    private int tipoMotor = 3; // 1 motor y 2 velero
    private int añoCompra;
    private String provincia;

    public Barco() {
        this.nombre = "";
        this.tipoMotor = 3;
        this.añoCompra = 0;
        this.provincia = "";
    }

    public Barco(String nombre, int tipoMotor, int añoCompra, String provincia) {
        this.nombre = nombre;
        this.tipoMotor = tipoMotor;
        this.añoCompra = añoCompra;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(int tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getAñoCompra() {
        return añoCompra;
    }

    public void setAñoCompra(int añoCompra) {
        this.añoCompra = añoCompra;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
