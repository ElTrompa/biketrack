package com.example.demo3;

public class Paciente {
    private String nombre;
    private int sexo;
    private int edad;
    private String provincia;

    public Paciente() {
        this.nombre = "";
        this.sexo = 0;
        this.edad = 0;
        this.provincia = "";
    }

    public Paciente(String nombre, int sexo, int edad, String provincia) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
