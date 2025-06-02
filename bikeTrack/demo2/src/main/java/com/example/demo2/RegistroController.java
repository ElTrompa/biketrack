package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {
    @javafx.fxml.FXML
    private TextField matriculaField;
    @javafx.fxml.FXML
    private TextField anoCompraField;
    @javafx.fxml.FXML
    private RadioButton gasolinaRadioButon;
    @javafx.fxml.FXML
    private Button guardarButon;
    @javafx.fxml.FXML
    private ComboBox nivelDeContencionCombobox;
    @javafx.fxml.FXML
    private RadioButton dieselRadioButon;

    @FXML
    public void onGuardarClick(ActionEvent actionEvent) {
        CochesModel cm = new CochesModel();

        String matricula = matriculaField.getText();
        int tipoMotor = 3;
        int anoComprar = 0;
        String nivelContencion;

        try {
            if (dieselRadioButon.isSelected()) {
                tipoMotor = 1;
            }else if (gasolinaRadioButon.isSelected()) {
                tipoMotor = 2;
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Algun dato a salido incorrecto").showAndWait();
        }

        nivelContencion = (String) nivelDeContencionCombobox.getValue();

        if (matricula != null && anoComprar != 0 && nivelContencion != null && tipoMotor == 3) {
            if (cm.validar(tipoMotor, anoComprar, nivelContencion)) {
                Coche c = new Coche(matricula, tipoMotor, anoComprar, nivelContencion);
                cm.anadirCoches(c);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Seleciona bien los datos").showAndWait();
        }
    }

    public void initialize() {
        nivelDeContencionCombobox.getItems().add("A");
        nivelDeContencionCombobox.getItems().add("B");
        nivelDeContencionCombobox.getItems().add("C");
        nivelDeContencionCombobox.getItems().add("D");
    }
}
