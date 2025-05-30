package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProgresoController {
    @javafx.fxml.FXML
    private Label horasTotales;
    @javafx.fxml.FXML
    private Label desnivelTotal;
    @javafx.fxml.FXML
    private Label actividadesTotales;
    @javafx.fxml.FXML
    private Label kilometrosTotales;
    @javafx.fxml.FXML
    private Label nombreUsuario;

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

    @FXML
    public void onVolverMenuclick(ActionEvent event) {
        cambiarPantalla("Inicio.fxml", "Volver menu", event);
    }
}
