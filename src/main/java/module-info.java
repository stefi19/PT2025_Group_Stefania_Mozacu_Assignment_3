module org.example.assignment3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.assignment3 to javafx.fxml;
    exports com.example.assignment3;
    exports Presentation;
    opens Presentation to javafx.fxml;
}