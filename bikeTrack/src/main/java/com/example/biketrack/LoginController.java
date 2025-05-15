package com.example.biketrack; // Define el paquete al que pertenece esta clase

// Importaciones necesarias de JavaFX y clases relacionadas
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

// La clase LoginController hereda de UsuarioModel, posiblemente para acceder a lógica de validación de usuario
public class LoginController extends UsuarioModel {

    // Campos de entrada para el nombre de usuario y contraseña, vinculados desde la interfaz FXML
    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField passwordField;

    // Método que se ejecuta al hacer clic en el botón de login
    @FXML
    private void handleLogin() {
        // Obtiene el texto ingresado por el usuario
        String username = usuarioField.getText();
        String password = passwordField.getText();

        // Valida las credenciales usando un método de UsuarioModel
        if (validarLogin(username, password)) {
            System.out.println("Login exitos");

            // Establece el usuario actual en la sesión
            Session.setUsuarioActual(username);

            // Cambia a la pantalla principal de la aplicación
            cambiarPantalla();
        } else {
            // Muestra una alerta de error si las credenciales no son válidas
            mostrarAlerta("Error", "Credenciales incorrectas", Alert.AlertType.ERROR);
        }
    }

    // Método auxiliar para mostrar alertas
    public static void mostrarAlerta(String title, String message, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método que cambia la escena actual a la pantalla de inicio
    private void cambiarPantalla() {
        try {
            // Obtiene el usuario actual desde la clase Usuario
            Usuario usuarioActual = Usuario.getUsuarioActual();

            // Carga la nueva interfaz desde el archivo FXML "inicio.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));

            // Crea una nueva escena con el contenido cargado
            Scene scene = new Scene(loader.load());

            // Obtiene la ventana actual y establece la nueva escena
            Stage stage = (Stage) usuarioField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("General"); // Establece el título de la ventana
        } catch (IOException e) {
            e.printStackTrace(); // Imprime el error en consola para depuración

            // Muestra una alerta en caso de error al cambiar la pantalla
            mostrarAlerta("Error", "Error", Alert.AlertType.ERROR);
        }
    }
}
