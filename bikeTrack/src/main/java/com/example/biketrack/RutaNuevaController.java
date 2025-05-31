package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class RutaNuevaController {
    @javafx.fxml.FXML
    private Button subirArchivoButon;
    @javafx.fxml.FXML
    private Label nombreUsuario;
    @javafx.fxml.FXML
    private Button volverAtrasButon;
    @javafx.fxml.FXML
    private Button manualButon;

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
    public void onManualclick(ActionEvent event) {
        cambiarPantalla("rutaNuevaManual.fxml", "Manual Ruta", event);
    }

    @FXML
    public void onArchivoclick(ActionEvent event) {
        cambiarPantalla("RutaNuevaArchivo.fxml", "Archivo Ruta", event);
    }

    @FXML
    public void onVolverAtrasclick(ActionEvent event) {
        cambiarPantalla("Inicio.fxml", "Volver atras", event);
    }
}
