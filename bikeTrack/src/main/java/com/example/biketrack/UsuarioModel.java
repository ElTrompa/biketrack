package com.example.biketrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioModel extends DBUtil{

    // Método para obtener los usuarios
    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> resultado = new ArrayList<>();
        try {
            PreparedStatement ps = this.getConexion().prepareStatement("SELECT id_usuario, password, usuario, id_ciclista FROM Usuario");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id_usuario = rs.getString("id_usuario");
                String password = rs.getString("password");
                String usuario = rs.getString("usuario");
                String id_ciclista = rs.getString("id_ciclista");

                Usuario u = new Usuario(id_usuario, password, usuario, id_ciclista);
                resultado.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Método para insertar un nuevo usuario
    public boolean insert(Usuario u) {
        boolean resultado = false;
        try {
            String sql = "INSERT INTO usuario (id_usuario, password, usuario, id_ciclista) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, u.getId_usuario());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getUsuario());
            ps.setString(4, u.getId_ciclista());
            resultado = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Método para actualizar el estado de estado
    public int actualizarEstadoDeForma(String id_ciclista, int carga, String estado) {
        int resultado = 0;
        try {
            String sql = "UPDATE Usuario SET estado, carga = true WHERE id_ciclista = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, id_ciclista);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Método para eliminar un usuario
    public int remove(Usuario u) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM usuario WHERE id_ciclista = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, u.getId_ciclista());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Método para eliminar un usuario por id_ciclista
    public int remove(String dni) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM Usuario WHERE id_ciclista = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, dni);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}