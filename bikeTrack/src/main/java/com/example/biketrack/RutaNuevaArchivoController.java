package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
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
        connection = DBUtil.getConexion();
        if (connection == null) {
            System.out.println("❌ No se pudo conectar a la base de datos.");
            return;
        }

        try {
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
            System.out.println("❌ Error al cargar las bicicletas.");
        }
    }

    @FXML
    private void onSeleccionarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo GPX");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos GPX", "*.gpx"));

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

            // Procesar estadísticas del archivo GPX
            GpxVisualizer.Resultados resultados = GpxVisualizer.procesarGPX(archivoSeleccionado);

            // Insertar nueva ruta (ajustada a estructura real de la base de datos)
            String sqlRuta = "INSERT INTO Rutas (usuario, nombre, distancia, tiempo, desnivel) VALUES (?, ?, ?, ?, ?)";
            int idRuta = -1;

            try (PreparedStatement stmt = connection.prepareStatement(sqlRuta, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, Integer.parseInt(Usuario.getUsuarioActual().getUsuario()));
                stmt.setString(2, archivoSeleccionado.getName());
                stmt.setDouble(3, resultados.distanciaKm);

                // Convertir duración (en horas) a java.sql.Time
                long millis = (long) (resultados.duracionHoras * 3600 * 1000); // horas → milisegundos
                stmt.setTime(4, new java.sql.Time(millis));

                stmt.setInt(5, (int) resultados.desnivelPositivo);

                stmt.executeUpdate();

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idRuta = generatedKeys.getInt(1);
                }
            }

            if (idRuta == -1) {
                System.out.println("❌ No se pudo obtener el ID de la nueva ruta.");
                return;
            }

            // Insertar archivo GPX asociado
            String sqlGpx = "INSERT INTO GPXArchivo (ruta, bicicleta, archivo) VALUES (?, ?, ?)";

            try (PreparedStatement stmtGpx = connection.prepareStatement(sqlGpx)) {
                stmtGpx.setInt(1, idRuta);
                stmtGpx.setInt(2, bicicletaSeleccionada.getEquipo());
                stmtGpx.setBytes(3, gpxData);
                stmtGpx.executeUpdate();
            }

            System.out.println("✅ Archivo GPX '" + archivoSeleccionado.getName()
                    + "' subido y ruta registrada correctamente (ID: " + idRuta + ").");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error leyendo el archivo GPX.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al insertar la ruta o el archivo en la base de datos.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error procesando el archivo GPX.");
        }
    }

    @FXML
    private void onVolverAtras(ActionEvent event) {
        System.out.println("🔙 Volver atrás pulsado");
        cambiarPantalla("rutaNueva.fxml", "Volver inicio de añadir rutas", event);
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

    private List<Bicicleta> getBicicletasUsuario(int idUsuario) throws SQLException {
        List<Bicicleta> lista = new ArrayList<>();
        String sql = "SELECT equipo, usuario, peso, marca, modelo, estado FROM Bicicleta WHERE usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
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
