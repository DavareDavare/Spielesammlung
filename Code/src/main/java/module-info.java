module com.example.fuchsundhenne {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fuchsundhenne to javafx.fxml;
    exports com.example.fuchsundhenne;
}