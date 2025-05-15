package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioController   {

    @FXML
    private Label mostrarUsuario;

    @FXML
    private Button butonRuta;

    @FXML
    private Button butonEquipamiento;

    @FXML
    private Button butonEquipamento;

    @FXML
    private Button butonProgreso;

    @FXML
    public void initialize() {
        mostrarUsuario.setText(Session.getUsuarioActual());
    };

    @FXML
    public void cambiarPantalla(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Scene scene = new Scene(loader.load());

            // Obtener el Stage desde el evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Progreso");
        } catch (IOException e) {
            e.printStackTrace();
            LoginController.mostrarAlerta("Error", "No se pudo cargar la nueva pantalla", Alert.AlertType.ERROR);
        }
    }
}