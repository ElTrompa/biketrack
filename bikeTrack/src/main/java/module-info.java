module com.example.biketrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.example.biketrack to javafx.fxml;
    exports com.example.biketrack;
}