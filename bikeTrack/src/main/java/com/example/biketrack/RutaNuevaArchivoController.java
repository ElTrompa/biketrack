package com.example.biketrack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class RutaNuevaArchivoController {

    @FXML
    private Button seleccionarArchivoButton;

    @FXML
    private Button subirRutaButton;

    @FXML
    private Button volverAtrasButon;

    @FXML
    private ComboBox<String> comboBoxBicicleta;

    @FXML
    private ImageView fotoPerfilUsuario;

    @FXML
    private Label nombreUsuario;

    private File archivoSeleccionado;

    @FXML
    public void initialize() {
        // Aquí puedes cargar las bicicletas en el comboBox, ejemplo:
        comboBoxBicicleta.getItems().addAll("Bicicleta 1", "Bicicleta 2", "Bicicleta 3");
        comboBoxBicicleta.getSelectionModel().selectFirst();
    }

    @FXML
    private void onSeleccionarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo GPX");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos GPX", "*.gpx"));
        Window window = seleccionarArchivoButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            archivoSeleccionado = file;
            seleccionarArchivoButton.setText(file.getName());
        }
    }

    @FXML
    private void onSubirRuta() {
        if (archivoSeleccionado == null) {
            System.out.println("No se ha seleccionado ningún archivo.");
            return;
        }
        String bicicletaSeleccionada = comboBoxBicicleta.getSelectionModel().getSelectedItem();
        if (bicicletaSeleccionada == null) {
            System.out.println("No se ha seleccionado ninguna bicicleta.");
            return;
        }

        try {
            // Ejemplo: convertir el archivo GPX a un byte array para almacenarlo
            byte[] gpxData = Files.readAllBytes(archivoSeleccionado.toPath());

            // Suponiendo que tienes una clase Database con un método para subir rutas
            Database db = Database.getInstance(); // método singleton o similar
            db.uploadRuta(bicicletaSeleccionada, gpxData);

            System.out.println("Archivo GPX '" + archivoSeleccionado.getName() + "' subido para " + bicicletaSeleccionada);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error leyendo el archivo GPX.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al subir la ruta a la base de datos.");
        }
    }


    @FXML
    private void onVolverAtras() {
        System.out.println("Volver atras pulsado");
    }
}
