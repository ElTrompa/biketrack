package com.example.biketrack;

public class Usuario {
    private String id_usuario;
    private String usuario;
    private String password;
    private String id_ciclista;

    public String getId_ciclista() {
        return id_ciclista;
    }

    public void setId_ciclista(String id_ciclista) {
        this.id_ciclista = id_ciclista;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Usuario(String id_usuario, String password, String usuario, String id_ciclista) {
        this.id_usuario = id_usuario;
        this.password = password;
        this.usuario = usuario;
        this.id_ciclista = id_ciclista;
    }
}