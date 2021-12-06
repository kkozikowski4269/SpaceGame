module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    exports org.example.model;
    exports org.example.controller;
}