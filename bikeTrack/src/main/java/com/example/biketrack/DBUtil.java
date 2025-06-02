package com.example.biketrack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static Connection conexion = null;

    public static Connection getConexion() {
        if (conexion == null) {
            String cadenaConexion = "jdbc:mysql://localhost:3306/biketrackdb";
            String usuario = "root";
            String password = ""; // tu contraseña aquí

            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos:");
                e.printStackTrace();
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }
}
