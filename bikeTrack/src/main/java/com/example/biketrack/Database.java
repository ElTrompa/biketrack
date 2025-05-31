package com.example.biketrack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() throws SQLException {
        // Conectar a la base de datos SQLite
        connection = DriverManager.getConnection("jdbc:sqlite:biketrack.db");
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void uploadRuta(int idEquipo, byte[] gpxData) throws SQLException {
        String sql = "INSERT INTO rutas (equipo_id, gpx_data) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEquipo);
            stmt.setBytes(2, gpxData);
            stmt.executeUpdate();
        }
    }
}
