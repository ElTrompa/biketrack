package com.example.biketrack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioModel extends DBUtil{
    public ArrayList <Usuario> getUsuarios(){
        ArrayList <Usuario> resultado = new ArrayList();
        try{
            PreparedStatement ps = this.getConexion().prepareStatement("carga, categoria, edad, estado, nombre, password, peso, usuario");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                double peso = rs.getDouble("peso");
                String categoria = rs.getString("categoria");
                int carga = rs.getInt("carga");
                String estado = rs.getString("estado");

                Usuario u = new Usuario(carga, categoria, edad, estado, nombre, password, peso, usuario);
                resultado.add(u);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }

    public boolean inertar(Usuario u) {
        boolean resultado = false;

        try {
            String sql = "INSERT INTO usuario (carga, categoria, edad, estado, nombre, password, peso, usuario) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getNombre());
            ps.setInt(4, u.getEdad());
            ps.setDouble(5, u.getPeso());
            ps.setString(6, u.getCategoria());
            ps.setInt(7, u.getCarga());
            ps.setString(8, u.getEstado());

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }

    public int actualizarEstado(String usuario) {
        int resultado = 0;

        try {
            String sql = "UPDATE usuario SET estado = ? WHERE estado = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, usuario);
            resultado = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }

    public int remove(Usuario u) {
        int resultado = 0;
        try {
            String sql = "DELETE FROM Usuario WHERE usuario = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, u.getUsuario());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}