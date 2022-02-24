module com.example.madn {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.madn to javafx.fxml;
    exports com.example.madn;
}