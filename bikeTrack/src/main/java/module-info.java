module com.example.biketrack {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.biketrack to javafx.fxml;
    exports com.example.biketrack;
}