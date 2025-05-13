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

















	/*
	private Connection conn;
	private String cadenaConexion = "jdbc:mysql://localhost:3306/prueba";
	private String nombreUsuario = "eclipse";
	private String password = "P4ssw0rd_123";

	public Connection getConexion() {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			this.conn = DriverManager.getConnection(this.cadenaConexion, this.nombreUsuario, this.password);
			return this.conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void cerrarConexion() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}
