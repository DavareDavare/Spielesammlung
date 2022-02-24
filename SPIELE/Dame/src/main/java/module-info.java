module com.example.dame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dame to javafx.fxml;
    exports com.example.dame;
}