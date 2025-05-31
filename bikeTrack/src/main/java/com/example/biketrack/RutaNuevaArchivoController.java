package com.example.biketrack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutaNuevaArchivoController {

    @FXML
    private Button seleccionarArchivoButton;

    @FXML
    private Button subirRutaButton;

    @FXML
    private Button volverAtrasButon;

    @FXML
    private ComboBox<Bicicleta> comboBoxBicicleta;

    private File archivoSeleccionado;

    private Connection connection;

    @FXML
    public void initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:biketrack.db");

            String idUsuario = Usuario.getUsuarioActual().getUsuario();
            List<Bicicleta> bicicletas = getBicicletasUsuario(Integer.parseInt(idUsuario));

            if (bicicletas == null || bicicletas.isEmpty()) {
                System.out.println("⚠ No se encontraron bicicletas para el usuario.");
            } else {
                comboBoxBicicleta.getItems().addAll(bicicletas);
                comboBoxBicicleta.getSelectionModel().selectFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al conectar o cargar las bicicletas.");
        }
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
            System.out.println("⚠ No se ha seleccionado ningún archivo.");
            return;
        }

        Bicicleta bicicletaSeleccionada = comboBoxBicicleta.getSelectionModel().getSelectedItem();
        if (bicicletaSeleccionada == null) {
            System.out.println("⚠ No se ha seleccionado ninguna bicicleta.");
            return;
        }

        try {
            byte[] gpxData = Files.readAllBytes(archivoSeleccionado.toPath());

            Database.getInstance().uploadRuta(bicicletaSeleccionada.getEquipo(), gpxData);

            System.out.println("✅ Archivo GPX '" + archivoSeleccionado.getName()
                    + "' subido para la bicicleta: " + bicicletaSeleccionada);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error leyendo el archivo GPX.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al subir la ruta a la base de datos.");
        }
    }

    @FXML
    private void onVolverAtras() {
        System.out.println("🔙 Volver atrás pulsado");
    }

    private List<Bicicleta> getBicicletasUsuario(int idUsuario) throws SQLException {
        List<Bicicleta> lista = new ArrayList<>();
        String sql = "SELECT equipo, usuario, peso, marca, modelo, estado FROM bicicletas WHERE usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Bicicleta bici = new Bicicleta(
                        rs.getInt("equipo"),
                        rs.getInt("usuario"),
                        rs.getDouble("peso"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("estado")
                );
                lista.add(bici);
            }
        }

        return lista;
    }
}
