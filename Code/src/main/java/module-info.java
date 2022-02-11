module com.example.code {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.code to javafx.fxml;
    exports com.example.code;
}