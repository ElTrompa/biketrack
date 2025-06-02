package com.example.biketrack;

import java.sql.Time;

public class Ruta {
    private int id;
    private String nombre;
    private double distancia;
    private int desnivel;
    private String dificultad;
    private String ubicacion;
    private Time tiempo;
    private double velocidadMedia;
    private String bicicletaUtilizada; // NUEVO campo

    public Ruta(int id, String nombre, double distancia, int desnivel, String dificultad, String ubicacion, Time tiempo, double velocidadMedia) {
        this.id = id;
        this.nombre = nombre;
        this.distancia = distancia;
        this.desnivel = desnivel;
        this.dificultad = dificultad;
        this.ubicacion = ubicacion;
        this.tiempo = tiempo;
        this.velocidadMedia = velocidadMedia;
    }

    // Si quieres inicializar también la bicicleta desde el constructor
    public Ruta(int id, String nombre, double distancia, int desnivel, String dificultad, String ubicacion, Time tiempo, double velocidadMedia, String bicicletaUtilizada) {
        this(id, nombre, distancia, desnivel, dificultad, ubicacion, tiempo, velocidadMedia);
        this.bicicletaUtilizada = bicicletaUtilizada;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public int getDesnivel() {
        return desnivel;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Time getTiempo() {
        return tiempo;
    }

    public double getVelocidadMedia() {
        return velocidadMedia;
    }

    public String getBicicletaUtilizada() {
        return bicicletaUtilizada;
    }

    public void setBicicletaUtilizada(String bicicletaUtilizada) {
        this.bicicletaUtilizada = bicicletaUtilizada;
    }

    @Override
    public String toString() {
        return nombre + " (" + ubicacion + ")";
    }
}
