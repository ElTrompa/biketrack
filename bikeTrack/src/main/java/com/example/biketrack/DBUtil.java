package com.example.biketrack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    Connection conexion = null;

    public static Connection getConexion() {

        String cadenaConexion = "jdbc:mysql://localhost:3306/biketrackdb";
        String usuario = "root";
        String password = "";

        try {

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.conexion = DriverManager.getConnection(cadenaConexion, usuario, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexion;

    }

    public void cerrarConexion() {
        try {
            this.conexion.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
