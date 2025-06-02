package com.example.biketrack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.time.Instant;
import java.util.List;

public class MapaController {

    @FXML
    private Pane mapPane;
    @FXML
    private Label infoLabel;

    @FXML
    public void initialize() {
        List<GpxVisualizer.Punto> puntos = List.of(
                new GpxVisualizer.Punto(40.0, -3.7, 650, Instant.parse("2023-01-01T10:00:00Z")),
                new GpxVisualizer.Punto(40.1, -3.68, 660, Instant.parse("2023-01-01T10:10:00Z")),
                new GpxVisualizer.Punto(40.15, -3.66, 670, Instant.parse("2023-01-01T10:20:00Z")),
                new GpxVisualizer.Punto(40.2, -3.7, 680, Instant.parse("2023-01-01T10:30:00Z"))
        );

        GpxVisualizer.visualizarGPX(puntos, mapPane, mapPane.getPrefWidth(), mapPane.getPrefHeight());
    }
}
