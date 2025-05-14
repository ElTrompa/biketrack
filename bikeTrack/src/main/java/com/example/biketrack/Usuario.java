package com.example.biketrack;

public class Usuario {
    private String usuario;
    private String Password;
    private String nombre;
    private int edad;
    private double peso;
    private String categoria;
    private int carga;
    private String estado;

    private static Usuario usuarioActual;

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Usuario(int carga, String categoria, int edad, String estado, String nombre, String password, double peso, String usuario) {
        this.carga = carga;
        this.categoria = categoria;
        this.edad = edad;
        this.estado = estado;
        this.nombre = nombre;
        Password = password;
        this.peso = peso;
        this.usuario = usuario;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
}