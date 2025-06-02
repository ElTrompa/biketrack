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

    @Override
    public String toString() {
        return nombre + " (" + ubicacion + ")";
    }
}
