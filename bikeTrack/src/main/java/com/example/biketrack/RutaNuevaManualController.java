package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RutaNuevaManualController {
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
    private ComboBox comboSegundos;
    @FXML
    private ComboBox comboHoras;
    @FXML
    private ComboBox comboMinutos;

    @FXML
    public void initialize() {
        nombreUsuario.setText(Usuario.getUsuarioActual().getNombre());
        cargarBicicletas(); // <--- Esta línea es necesaria
        cargarCombosTiempo();
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
        String dificultad = "media";
        String ubicacion = "desconocida";

        Integer horas = (Integer) comboHoras.getValue();
        Integer minutos = (Integer) comboMinutos.getValue();
        Integer segundos = (Integer) comboSegundos.getValue();

        if (horas == null || minutos == null || segundos == null) {
            new Alert(Alert.AlertType.WARNING, "⚠ Debes seleccionar horas, minutos y segundos.").showAndWait();
            return;
        }

        String tiempoStr = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        double tiempoHoras = horas + minutos / 60.0 + segundos / 3600.0;

        double velocidadMedia = kilometrosInsertados / tiempoHoras;

        try (Connection conn = DBUtil.getConexion()) {
            String sql = "INSERT INTO Rutas (nombre, desnivel, usuario, distancia, dificultad, ubicacion, tiempo, velocidadMedia) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setInt(2, desnivelInsertado);
            stmt.setInt(3, idUsuarioActual);
            stmt.setDouble(4, kilometrosInsertados);
            stmt.setString(5, dificultad);
            stmt.setString(6, ubicacion);
            stmt.setTime(7, java.sql.Time.valueOf(tiempoStr));
            stmt.setDouble(8, velocidadMedia);

            stmt.executeUpdate();
            new Alert(Alert.AlertType.CONFIRMATION, "✅ Ruta guardada correctamente.").showAndWait();

            nombreRuta.clear();
            kilometros.clear();
            desnivel.clear();
            comboHoras.setValue(null);
            comboMinutos.setValue(null);
            comboSegundos.setValue(null);
            comboBoxBicicleta.setValue(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarCombosTiempo() {
        for (int i = 0; i <= 23; i++) comboHoras.getItems().add(i);
        for (int i = 0; i <= 59; i++) {
            comboMinutos.getItems().add(i);
            comboSegundos.getItems().add(i);
        }
    }

    @FXML
    public void onSubirRutaclick(ActionEvent actionEvent) {
    }
}
