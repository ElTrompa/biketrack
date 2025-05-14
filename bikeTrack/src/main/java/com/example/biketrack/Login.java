package com.example.biketrack;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void handleLogin() {
        String username = this.username.getText();
        String password = this.password.getText();

        // validacio
        if(validarLogin(username, password)){
            System.out.println("Login exitoso");

            Session.setId_ciclistaActual
        }
    }
}
