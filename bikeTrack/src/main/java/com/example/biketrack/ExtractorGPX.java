package com.example.biketrack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.List;

import static javafx.application.Application.launch;

public class ExtractorGPX {

    // Ejemplo de lista de puntos con lat, lon
    public static class Punto {
        double lat, lon;

        public Punto(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }

    // Método simple para convertir lat/lon a coordenadas X/Y dentro del pane
    // mapea lat/lon a un rectángulo [0..width] x [0..height]
    private double[] mapLatLonToXY(List<Punto> puntos, double lat, double lon, double width, double height) {
        // Primero, calculamos el rango lat/lon
        double minLat = puntos.stream().mapToDouble(p -> p.lat).min().orElse(lat);
        double maxLat = puntos.stream().mapToDouble(p -> p.lat).max().orElse(lat);
        double minLon = puntos.stream().mapToDouble(p -> p.lon).min().orElse(lon);
        double maxLon = puntos.stream().mapToDouble(p -> p.lon).max().orElse(lon);

        // Normalizamos lat y lon en 0..1
        double xNorm = (lon - minLon) / (maxLon - minLon);
        double yNorm = (lat - minLat) / (maxLat - minLat);

        // Invertir Y para que latitudes altas estén arriba en la pantalla
        yNorm = 1 - yNorm;

        // Escalamos a tamaño del pane
        double x = xNorm * width;
        double y = yNorm * height;

        return new double[]{x, y};
    }

    public void start(Stage primaryStage) {
        // Datos ejemplo (puedes cargar los puntos del GPX con el método anterior)
        List<Punto> puntos = List.of(
                new Punto(40.0, -3.7),
                new Punto(40.1, -3.68),
                new Punto(40.15, -3.66),
                new Punto(40.2, -3.7)
        );

        Pane pane = new Pane();
        pane.setPrefSize(600, 400);

        double width = 600;
        double height = 400;

        // Dibujar líneas entre puntos consecutivos
        for (int i = 1; i < puntos.size(); i++) {
            double[] p1 = mapLatLonToXY(puntos, puntos.get(i - 1).lat, puntos.get(i - 1).lon, width, height);
            double[] p2 = mapLatLonToXY(puntos, puntos.get(i).lat, puntos.get(i).lon, width, height);

            Line line = new Line(p1[0], p1[1], p2[0], p2[1]);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);
            pane.getChildren().add(line);
        }

        // Dibujar puntos
        for (Punto p : puntos) {
            double[] coords = mapLatLonToXY(puntos, p.lat, p.lon, width, height);
            Circle circle = new Circle(coords[0], coords[1], 5, Color.RED);
            pane.getChildren().add(circle);
        }

        primaryStage.setTitle("Visualización GPS");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}