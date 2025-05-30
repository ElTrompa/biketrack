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

public class EquipamientoAnadirController {
    @javafx.fxml.FXML
    private TextField marcaBicicleta;
    @javafx.fxml.FXML
    private Slider pesoSlider;
    @javafx.fxml.FXML
    private Button guardarButon;
    @javafx.fxml.FXML
    private ComboBox estadoComboBox;
    @javafx.fxml.FXML
    private Button volverAtrasButon;
    @javafx.fxml.FXML
    private Label nombreUsuario;
    @javafx.fxml.FXML
    private TextField modeloBicicleta;

    @FXML
    public void initialize() {
        nombreUsuario.setText(Usuario.getUsuarioActual().getNombre());
    };

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
    public void onGuardarEquipamientoclick(ActionEvent event) {
        // añadir per a guardar equipament
    }

    @FXML
    public void onVolverAtrasclick(ActionEvent event) {
        cambiarPantalla("Equipamiento.fxml", "Volver Equipamiento", event);
    }
}
