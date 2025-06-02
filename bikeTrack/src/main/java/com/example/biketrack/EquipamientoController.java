package com.example.biketrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipamientoController {

    @FXML
    private TableView<Bicicleta> tablaEquipamiento;

    @FXML
    private Button volverAtrasButon;

    @FXML
    private Label mostrarUsuario;

    private Connection connection;
    @FXML
    private Button añadirEquipamiento;

    @FXML
    public void initialize() {
        mostrarUsuario.setText(Usuario.getUsuarioActual().getNombre());

        connection = DBUtil.getConexion();

        // Crear columnas dinámicamente para no modificar FXML
        crearColumnasTabla();

        try {
            int idUsuario = Integer.parseInt(Usuario.getUsuarioActual().getUsuario());
            List<Bicicleta> listaBicicletas = getBicicletasUsuario(idUsuario);
            ObservableList<Bicicleta> bicicletasObservable = FXCollections.observableArrayList(listaBicicletas);
            tablaEquipamiento.setItems(bicicletasObservable);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error cargando bicicletas del usuario.");
        }
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

        tablaEquipamiento.getColumns().clear(); // Por si acaso hay columnas ya
        tablaEquipamiento.getColumns().addAll(colEquipo, colMarca, colModelo, colEstado);
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
    public void onVolverAtrasclick(ActionEvent event) {
        cambiarPantalla("Inicio.fxml", "Volver a Inicio", event);
    }

    @FXML
    public void onCambioAñadirEquipamiento(ActionEvent actionEvent) {
    }
}