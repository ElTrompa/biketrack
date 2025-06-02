package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {

    @FXML
    private CheckBox mascleBt;
    @FXML
    private Button registrarBt;
    @FXML
    private TextField edadField;
    @FXML
    private CheckBox femellaBt;
    @FXML
    private ComboBox<String> provinciaCb;
    @FXML
    private TextField nombreField;

    @FXML
    public void registrarPaciente(ActionEvent actionEvent) {
        PacienteModle pm = new PacienteModle();

        String nombre = nombreField.getText();
        int sexo = 3;
        int edad;
        String provincia = provinciaCb.getValue();

        try {
            edad = Integer.parseInt(edadField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Edad inválida (solo números)").showAndWait();
            return;
        }

        if (femellaBt.isSelected()) {
            sexo = 1;
        } else if (mascleBt.isSelected()) {
            sexo = 0;
        }

        if (nombre == null || nombre.trim().isEmpty() || provincia == null || sexo == 3) {
            new Alert(Alert.AlertType.ERROR, "Rellene todos los campos correctamente").showAndWait();
            return;
        }

        // Si tu clase tiene un método como este
        if (pm.añadir(sexo, edad)) {
            Paciente p = new Paciente(nombre, sexo, edad, provincia);
            pm.añadirPacientes(p); // o similar
            new Alert(Alert.AlertType.INFORMATION, "Paciente registrado correctamente").showAndWait();
        }
    }

    public void initialize() {
        provinciaCb.getItems().addAll("Castello", "Valencia", "Alicante");
    }
}

