package com.example.demo2;

public class Coche {
    private String matricula;
    private int tipoMotor; //1 diesel 2 motor
    private int anoCompra;
    private String nivelContaminacion;

    public Coche() {
        this.matricula = "";
        this.tipoMotor = 0;
        this.anoCompra = 0;
        this.nivelContaminacion = "";
    }

    public Coche(String matricula, int tipoMotor, int anoCompra, String nivelContaminacion) {
        this.matricula = matricula;
        this.tipoMotor = tipoMotor;
        this.anoCompra = anoCompra;
        this.nivelContaminacion = nivelContaminacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(int tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getAnoCompra() {
        return anoCompra;
    }

    public void setAnoCompra(int anoCompra) {
        this.anoCompra = anoCompra;
    }

    public String getNivelContaminacion() {
        return nivelContaminacion;
    }

    public void setNivelContaminacion(String nivelContaminacion) {
        this.nivelContaminacion = nivelContaminacion;
    }
}
