package com.example.biketrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamientoController {

    @FXML
    private TableView<Bicicleta> tablaEquipamiento;

    @FXML
    private Label mostrarUsuario;

    @FXML
    private TextField marcaBicicleta;

    @FXML
    private TextField modeloBicicleta;

    @FXML
    private ComboBox<String> estadoComboBox;

    @FXML
    private Slider pesoSlider;

    private Connection connection;

    @FXML
    public void initialize() {
        mostrarUsuario.setText(Usuario.getUsuarioActual().getNombre());

        connection = DBUtil.getConexion();
        crearColumnasTabla();

        estadoComboBox.setItems(FXCollections.observableArrayList(
                "nuevo", "seminuevo", "viejo", "retirado" // Coinciden con ENUM en la BD
        ));

        cargarBicicletas();
    }

    private void crearColumnasTabla() {
        TableColumn<Bicicleta, Integer> colEquipo = new TableColumn<>("Equipo");
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));

        TableColumn<Bicicleta, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        TableColumn<Bicicleta, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Bicicleta, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablaEquipamiento.getColumns().clear();
        tablaEquipamiento.getColumns().addAll(colEquipo, colMarca, colModelo, colEstado);
    }

    private void cargarBicicletas() {
        try {
            int idUsuario = Integer.parseInt(Usuario.getUsuarioActual().getUsuario());
            List<Bicicleta> listaBicicletas = getBicicletasUsuario(idUsuario);
            ObservableList<Bicicleta> bicicletasObservable = FXCollections.observableArrayList(listaBicicletas);
            tablaEquipamiento.setItems(bicicletasObservable);
        } catch (Exception e) {
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

    @FXML
    public void onGuardarEquipamientoclick(ActionEvent event) {
        try {
            int idUsuario = Integer.parseInt(Usuario.getUsuarioActual().getUsuario());
            String marca = marcaBicicleta.getText();
            String modelo = modeloBicicleta.getText();
            String estado = estadoComboBox.getValue();
            double peso = pesoSlider.getValue();

            if (marca.isEmpty() || modelo.isEmpty() || estado == null) {
                mostrarAlerta("Error", "Todos los campos deben estar completos.");
                return;
            }

            String sql = "INSERT INTO Bicicleta (usuario, peso, marca, modelo, estado) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);
                stmt.setDouble(2, peso);
                stmt.setString(3, marca);
                stmt.setString(4, modelo);
                stmt.setString(5, estado);
                stmt.executeUpdate();
            }

            mostrarAlerta("Éxito", "Bicicleta guardada correctamente.");
            cargarBicicletas();
            limpiarCampos();

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error SQL", e.getMessage());
        }
    }

    private void limpiarCampos() {
        marcaBicicleta.clear();
        modeloBicicleta.clear();
        estadoComboBox.getSelectionModel().clearSelection();
        pesoSlider.setValue(0);
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    public void onVolverAtrasclick(ActionEvent event) {
        cambiarPantalla("Inicio.fxml", "Inicio", event);
    }

    private void cambiarPantalla(String rutaFXML, String titulo, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
