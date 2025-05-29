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

public class InicioController   {

    @FXML
    private Label mostrarUsuario;
    @FXML
    private Button butonProgresoButon;
    @FXML
    private Button butonRutaButon;
    @FXML
    private Button cerrarSesionButton;
    @FXML
    private Button butonEquipamientoButon;
    @FXML
    private Button verRutasButon;

    @FXML
    public void initialize() {

       mostrarUsuario.setText(Usuario.getUsuarioActual().getNombre());

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
    public void onRutaclick(ActionEvent event) {
        cambiarPantalla("rutaNueva.fxml", "Añadir Ruta", event);
    }

    @FXML
    public void onEquipamientoclick(ActionEvent event) {
        cambiarPantalla("Equipamiento.fxml", "Añadir Equipamiento", event);
    }

    @FXML
    public void onProgresoclick(ActionEvent event) {
        cambiarPantalla("Progreso.fxml", "Ver Progreso", event);
    }

}
