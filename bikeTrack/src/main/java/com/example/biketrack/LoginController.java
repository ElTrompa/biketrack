package com.example.biketrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController extends UsuarioModel {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usuarioField.getText();
        String password = passwordField.getText();

        if (validarLogin(username, password)) {
            System.out.println("Login exitos");

            Session.setUsuarioActual(username);

            cambiarPantalla();
        } else {
            mostrarAlerta("Error", "Credenciales incorrectas", Alert.AlertType.ERROR);
        }
    }

    public static void mostrarAlerta(String title, String message, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void cambiarPantalla() {
        try {
            Usuario usuarioActual = Usuario.getUsuarioActual();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) usuarioField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("General");
        } catch (IOException e) {
            e.printStackTrace();

            mostrarAlerta("Error", "Error", Alert.AlertType.ERROR);
        }
    }
}
