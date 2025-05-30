package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgresoController {
    @javafx.fxml.FXML
    private Label horasTotales;
    @javafx.fxml.FXML
    private Label desnivelTotal;
    @javafx.fxml.FXML
    private Label actividadesTotales;
    @javafx.fxml.FXML
    private Label kilometrosTotales;
    @javafx.fxml.FXML
    private Label nombreUsuario;
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    public void initialize() {
        nombreUsuario.setText(Usuario.getUsuarioActual().getNombre());
        configurarGrafica();
        cargarGrafica();
    }

    private void configurarGrafica() {
        barChart.setStyle("-fx-background-color: transparent;");

        CategoryAxis xAxis = (CategoryAxis) barChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();

        xAxis.setTickLabelFill(javafx.scene.paint.Color.WHITE);
        xAxis.setTickLabelRotation(45);
        xAxis.setStyle("-fx-border-color: transparent;");

        yAxis.setTickLabelFill(javafx.scene.paint.Color.WHITE);
        yAxis.setMinorTickVisible(false);
        yAxis.setStyle("-fx-border-color: transparent;");

        barChart.setLegendVisible(false);

        yAxis.setTickMarkVisible(false);
        yAxis.setTickLength(0);
    }

    private void cargarGrafica() {
        barChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Km recorridos");

        series.getData().add(new XYChart.Data<>("Enero", 120));
        series.getData().add(new XYChart.Data<>("Febrero", 90));
        series.getData().add(new XYChart.Data<>("Marzo", 150));
        series.getData().add(new XYChart.Data<>("Abril", 200));
        series.getData().add(new XYChart.Data<>("Mayo", 180));

        barChart.getData().add(series);

        for (XYChart.Data<String, Number> data : series.getData()) {
            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-bar-fill: #00C8FF;"); // azul brillante
                }
            });
            if (data.getNode() != null) {
                data.getNode().setStyle("-fx-bar-fill: #00C8FF;");
            }
        }
    }

    private void cargarEstadisticas() {
        int idUsuarioActual = Integer.parseInt(Usuario.getUsuarioActual().getUsuario());

        try (Connection conn = DBUtil.getConexion()) {
            String sql = "SELECT " +
                    "SUM(distancia) AS total_km, " +
                    "SUM(desnivel) AS total_desnivel, " +
                    "SUM(TIME_TO_SEC(tiempo)) AS total_segundos, " +
                    "COUNT(*) AS total_actividades " +
                    "FROM Rutas WHERE usuario = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuarioActual);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double km = rs.getDouble("total_km");
                int desnivel = rs.getInt("total_desnivel");
                int totalSegundos = rs.getInt("total_segundos");
                int actividades = rs.getInt("total_actividades");

                int horas = totalSegundos / 3600;
                int minutos = (totalSegundos % 3600) / 60;
                int segundos = totalSegundos % 60;

                kilometrosTotales.setText(String.format("%.2f km", km));
                desnivelTotal.setText(desnivel + " m");
                horasTotales.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
                actividadesTotales.setText(String.valueOf(actividades));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void onVolverMenuclick(ActionEvent event) {
        cambiarPantalla("Inicio.fxml", "Volver menu", event);
    }
}
