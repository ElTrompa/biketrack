package com.example.biketrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.util.List;

public class VerRutaSelecionadaController {

    @FXML
    private Label infoLabel;
    @FXML
    private Pane mapPane;
    @FXML
    private Label nombreUsuario;

    private Ruta rutaSeleccionada;

    @FXML
    public void initialize() {
        nombreUsuario.setText(Usuario.getUsuarioActual().getNombre());
    }

    public void setRuta(Ruta ruta) {
        this.rutaSeleccionada = ruta;

        try {
            File archivoGPX = obtenerArchivoGPXDesdeBD(ruta.getId());

            if (archivoGPX != null) {
                List<GpxVisualizer.Punto> puntos = GpxVisualizerExtraerPuntos(archivoGPX);
                GpxVisualizer.visualizarGPX(puntos, mapPane, mapPane.getPrefWidth(), mapPane.getPrefHeight());

                GpxVisualizer.Resultados resultados = GpxVisualizer.procesarGPX(archivoGPX);

                infoLabel.setText(String.format(
                        "Ruta: %s\nDistancia: %.2f km\nDesnivel: %.0f m\nDuración: %.2f h\nVel. media: %.2f km/h",
                        ruta.getNombre(),
                        resultados.distanciaKm,
                        resultados.desnivelPositivo,
                        resultados.duracionHoras,
                        resultados.velocidadMediaKmh
                ));
            } else {
                infoLabel.setText("No se encontró archivo GPX para esta ruta.");
            }
        } catch (Exception e) {
            infoLabel.setText("Error al cargar ruta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private File obtenerArchivoGPXDesdeBD(int rutaId) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BikeTrackDB", "root", "tu_password");
        String sql = "SELECT archivo FROM GPXArchivo WHERE ruta = ? ORDER BY fecha_subida DESC LIMIT 1";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, rutaId);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            byte[] gpxBytes = rs.getBytes("archivo");
            File tempFile = File.createTempFile("ruta", ".gpx");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(gpxBytes);
            }
            return tempFile;
        }

        return null;
    }

    private List<GpxVisualizer.Punto> GpxVisualizerExtraerPuntos(File archivoGPX) throws Exception {
        // Versión parcial del método procesarGPX para solo extraer los puntos
        List<GpxVisualizer.Punto> puntos = new java.util.ArrayList<>();

        javax.xml.parsers.DocumentBuilder builder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse(archivoGPX);
        org.w3c.dom.NodeList trkpts = doc.getElementsByTagName("trkpt");

        for (int i = 0; i < trkpts.getLength(); i++) {
            org.w3c.dom.Element e = (org.w3c.dom.Element) trkpts.item(i);
            double lat = Double.parseDouble(e.getAttribute("lat"));
            double lon = Double.parseDouble(e.getAttribute("lon"));

            double ele = 0;
            java.time.Instant tiempo = null;

            org.w3c.dom.NodeList hijos = e.getChildNodes();
            for (int j = 0; j < hijos.getLength(); j++) {
                org.w3c.dom.Node nodo = hijos.item(j);
                if (nodo.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    org.w3c.dom.Element el = (org.w3c.dom.Element) nodo;
                    switch (el.getTagName()) {
                        case "ele":
                            ele = Double.parseDouble(el.getTextContent());
                            break;
                        case "time":
                            tiempo = java.time.Instant.parse(el.getTextContent());
                            break;
                    }
                }
            }

            puntos.add(new GpxVisualizer.Punto(lat, lon, ele, tiempo));
        }

        return puntos;
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
        cambiarPantalla("SelecionarRuta.fxml", "Seleccionar ruta", event);
    }
}
