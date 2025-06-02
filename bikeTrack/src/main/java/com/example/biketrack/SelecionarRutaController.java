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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelecionarRutaController {

    @FXML
    private ComboBox<Ruta> selecionarRutaComboBox;
    @FXML
    private Button volverAtrasButon;
    @FXML
    private Label nombreUsuario;
    @FXML
    private Button rutaSelecionada;

    @FXML
    public void initialize() {
        nombreUsuario.setText(Usuario.getUsuarioActual().getNombre());
        cargarRutas();
    }

    private void cargarRutas() {
        List<Ruta> rutas = new ArrayList<>();

        Connection conn = DBUtil.getConexion();

        if (conn != null) {
            try {
                String sql = "SELECT ruta, nombre, distancia, desnivel, dificultad, ubicacion, tiempo, velocidadMedia FROM Rutas WHERE usuario = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(Usuario.getUsuarioActual().getUsuario()));

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Ruta ruta = new Ruta(
                            rs.getInt("ruta"),
                            rs.getString("nombre"),
                            rs.getDouble("distancia"),
                            rs.getInt("desnivel"),
                            rs.getString("dificultad"),
                            rs.getString("ubicacion"),
                            rs.getTime("tiempo"),
                            rs.getDouble("velocidadMedia")
                    );
                    rutas.add(ruta);
                }
                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("No se pudo obtener la conexión a la base de datos.");
        }

        selecionarRutaComboBox.getItems().setAll(rutas);
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

    @FXML
    public void onVolverAtrasclick(ActionEvent event) {
        cambiarPantalla("Inicio.fxml", "Volver atras", event);
    }

    @FXML
    public void onRutaSelecionadaclick(ActionEvent event) {
        Ruta rutaSeleccionada = selecionarRutaComboBox.getValue();
        if (rutaSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VerRutaSelecionada.fxml"));
                Parent root = loader.load();

                VerRutaSelecionadaController controller = loader.getController();
                controller.setRuta(rutaSeleccionada);

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.setTitle("Ver Ruta Seleccionada");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Debe seleccionar una ruta primero.");
        }
    }
}
