package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RutaNuevaManualController {
    @javafx.fxml.FXML
    private TextField tiempo;
    @javafx.fxml.FXML
    private TextField kilometros;
    @javafx.fxml.FXML
    private TextField desnivel;
    @javafx.fxml.FXML
    private Label nombreUsuario;
    @javafx.fxml.FXML
    private Button volverAtrasButon;
    @javafx.fxml.FXML
    private ComboBox comboBoxBicicleta;
    @FXML
    private TextField nombreRuta;

    @FXML
    public void initialize() {
        nombreUsuario.setText(Usuario.getUsuarioActual().getNombre());
    }

    private void cambiarPantalla(String rutaFXML, String titulo, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarBicicletas() {
        int idUsuarioActual = Integer.parseInt(Usuario.getUsuarioActual().getUsuario());

        try (Connection conn = DBUtil.getConexion()) {
            int query = Integer.parseInt("SELECT * FROM Bicicleta WHERE usuario = ?");
            PreparedStatement stmt = conn.prepareStatement(String.valueOf(query));
            stmt.setInt(1, idUsuarioActual);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bicicleta bici = new Bicicleta(
                        rs.getInt("equipo"),
                        rs.getInt("usuario"),
                        rs.getDouble("peso"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("estado")
                );
                comboBoxBicicleta.getItems().add(bici);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onVolverAtrasclick(ActionEvent event) {
        cambiarPantalla("rutaNueva.fxml", "Volver a rutaNueva", event);
    }

    @FXML
    private void guardarRuta() {
        int idUsuarioActual = Integer.parseInt(Usuario.getUsuarioActual().getUsuario());

        String nombre = nombreRuta.getText();
        int desnivelInsertado = Integer.parseInt(desnivel.getText());
        double kilometrosInsertados = Double.parseDouble(kilometros.getText());
        String dificultad = "media"; // O obtener desde otro control
        String ubicacion = "desconocida"; // O obtener desde otro control
        String tiempoStr = tiempoField.getText(); // formato HH:mm:ss
        double velocidadMedia = 0.0; // Podrías calcular o obtener

        String[] hms = tiempoStr.split(":");
        int horas = Integer.parseInt(hms[0]);
        int minutos = Integer.parseInt(hms[1]);
        int segundos = Integer.parseInt(hms[2]);
        double tiempoHoras = horas + minutos / 60.0 + segundos / 3600.0;

        try (Connection conn = DBUtil.getConexion()) {
            String sql = "INSERT INTO Rutas (nombre, desnivel, usuario, distancia, dificultad, ubicacion, tiempo, velocidadMedia) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setInt(2, desnivel);
            stmt.setInt(3, idUsuarioActual);
            stmt.setDouble(4, distancia);
            stmt.setString(5, dificultad);
            stmt.setString(6, ubicacion);
            stmt.setTime(7, Time.valueOf(tiempoStr));
            stmt.setDouble(8, velocidadMedia);

            stmt.executeUpdate();
            System.out.println("✅ Ruta guardada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSubirRutaclick(ActionEvent actionEvent) {
    }
}
