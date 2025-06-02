package com.example.biketrack;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GpxVisualizer {

    public static class Punto {
        public double lat, lon, ele;
        public Instant tiempo;

        public Punto(double lat, double lon, double ele, Instant tiempo) {
            this.lat = lat;
            this.lon = lon;
            this.ele = ele;
            this.tiempo = tiempo;
        }
    }

    public static class Resultados {
        public double distanciaKm;
        public double desnivelPositivo; // en metros
        public double duracionHoras;
        public double velocidadMediaKmh;
    }

    // ========================
    // VISUALIZADOR (opcional)
    // ========================
    public static void visualizarGPX(List<Punto> puntos, Pane pane, double width, double height) {
        if (puntos == null || puntos.size() < 2) return;

        for (int i = 1; i < puntos.size(); i++) {
            double[] p1 = mapLatLonToXY(puntos, puntos.get(i - 1).lat, puntos.get(i - 1).lon, width, height);
            double[] p2 = mapLatLonToXY(puntos, puntos.get(i).lat, puntos.get(i).lon, width, height);

            Line line = new Line(p1[0], p1[1], p2[0], p2[1]);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);
            pane.getChildren().add(line);
        }

        for (Punto p : puntos) {
            double[] coords = mapLatLonToXY(puntos, p.lat, p.lon, width, height);
            Circle circle = new Circle(coords[0], coords[1], 5, Color.RED);
            pane.getChildren().add(circle);
        }
    }

    private static double[] mapLatLonToXY(List<Punto> puntos, double lat, double lon, double width, double height) {
        double minLat = puntos.stream().mapToDouble(p -> p.lat).min().orElse(lat);
        double maxLat = puntos.stream().mapToDouble(p -> p.lat).max().orElse(lat);
        double minLon = puntos.stream().mapToDouble(p -> p.lon).min().orElse(lon);
        double maxLon = puntos.stream().mapToDouble(p -> p.lon).max().orElse(lon);

        // Evitar división por cero cuando todos los valores son iguales
        double lonRange = maxLon - minLon;
        double latRange = maxLat - minLat;
        if (lonRange == 0) lonRange = 1;
        if (latRange == 0) latRange = 1;

        double xNorm = (lon - minLon) / lonRange;
        double yNorm = 1 - ((lat - minLat) / latRange); // invertido

        double x = xNorm * width;
        double y = yNorm * height;

        return new double[]{x, y};
    }

    // ========================
    // EXTRACTOR DE ESTADÍSTICAS
    // ========================

    public static Resultados procesarGPX(File archivoGPX) throws Exception {
        List<Punto> puntos = new ArrayList<>();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(archivoGPX);
        NodeList trkpts = doc.getElementsByTagName("trkpt");

        for (int i = 0; i < trkpts.getLength(); i++) {
            Element e = (Element) trkpts.item(i);
            double lat = Double.parseDouble(e.getAttribute("lat"));
            double lon = Double.parseDouble(e.getAttribute("lon"));

            double ele = 0;
            Instant tiempo = null;

            NodeList hijos = e.getChildNodes();
            for (int j = 0; j < hijos.getLength(); j++) {
                Node nodo = hijos.item(j);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nodo;
                    if ("ele".equals(el.getTagName())) {
                        try {
                            ele = Double.parseDouble(el.getTextContent());
                        } catch (NumberFormatException ex) {
                            ele = 0; // valor por defecto si no puede parsear
                        }
                    } else if ("time".equals(el.getTagName())) {
                        try {
                            tiempo = Instant.parse(el.getTextContent());
                        } catch (Exception ex) {
                            tiempo = null; // ignorar si formato inválido
                        }
                    }
                }
            }

            puntos.add(new Punto(lat, lon, ele, tiempo));
        }

        return calcularEstadisticas(puntos);
    }

    private static Resultados calcularEstadisticas(List<Punto> puntos) {
        Resultados res = new Resultados();
        if (puntos == null || puntos.size() < 2) return res;

        double distancia = 0;
        double desnivel = 0;

        Instant inicio = null;
        Instant fin = null;

        for (int i = 1; i < puntos.size(); i++) {
            Punto p1 = puntos.get(i - 1);
            Punto p2 = puntos.get(i);

            distancia += haversine(p1.lat, p1.lon, p2.lat, p2.lon);

            double deltaEle = p2.ele - p1.ele;
            if (deltaEle > 0) {
                desnivel += deltaEle;
            }

            if (i == 1) inicio = p1.tiempo;
            if (i == puntos.size() - 1) fin = p2.tiempo;
        }

        double duracionHoras = 0;
        if (inicio != null && fin != null) {
            duracionHoras = Duration.between(inicio, fin).toSeconds() / 3600.0;
        }

        res.distanciaKm = distancia;
        res.desnivelPositivo = desnivel;
        res.duracionHoras = duracionHoras;
        res.velocidadMediaKmh = duracionHoras > 0 ? distancia / duracionHoras : 0;

        return res;
    }

    // Distancia entre coordenadas (Haversine, en km)
    private static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
