package com.example.biketrack;

public class Bicicleta {
    private int equipo;
    private int usuario;
    private double peso;
    private String marca;
    private String modelo;
    private String estado;

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Bicicleta() {
        this.equipo = 0;
        this.usuario = 0;
        this.peso = 0;
        this.marca = "";
        this.modelo = "";
        this.estado = "";
    }

    public Bicicleta(int equipo, int usuario, double peso, String marca, String modelo, String estado) {
        this.equipo = equipo;
        this.usuario = usuario;
        this.peso = peso;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = estado;
    }
}
