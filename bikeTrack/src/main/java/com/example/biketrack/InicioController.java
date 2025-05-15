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
    private Button butonRuta;

    @FXML
    private Button butonEquipamiento;

    @FXML
    private Button butonEquipamento;

    @FXML
    private Button butonProgreso;

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


    public void onRutaclick(ActionEvent event) {
        cambiarPantalla("rutaNueva.fxml", "Añadir Ruta", event);
    }

    public void onEquipamientoclick(ActionEvent event) {
        cambiarPantalla("equipamiento.fxml", "Añadir Equipamiento", event);
    }

    public void onProgresoclick(ActionEvent event) {
        cambiarPantalla("progreso.fxml", "Ver Progreso", event);
    }

}
