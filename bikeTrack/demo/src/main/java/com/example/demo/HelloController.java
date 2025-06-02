package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private RadioButton rbMotor;
    @FXML
    private Button btGuardar;
    @FXML
    private RadioButton rbVelero;
    @FXML
    private ComboBox cbProvincia;
    @FXML
    private TextField añoCompraField;
    @FXML
    private TextField nombreBarcoField;

    @Deprecated
    public void guardarBarco(ActionEvent actionEvent) {
        BarcoModel bm = new BarcoModel();

        String nombre = nombreBarcoField.getText();
        int motor = 3;
        int añoCompra = 0;
        String provincia = "";

        try {
            añoCompra = Integer.parseInt(añoCompraField.getText());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Debe ser numeros en años").showAndWait();
        }

        if (nombre != null && añoCompra != 0 && provincia != null && motor == 3) {
            if (bm.añadir(añoCompra, provincia)) {
                Barco b = new Barco(nombre, motor, añoCompra, provincia);
                bm.añadirBarcos(b);

                new Alert(Alert.AlertType.CONFIRMATION, "Confirmacio de guardado");
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Introduce los valores correctos").showAndWait();
        }
    }

    public void initialize() {
        cbProvincia.getItems().add("Castellón");
        cbProvincia.getItems().add("Valencia");
        cbProvincia.getItems().add("Alicante");
    }
}