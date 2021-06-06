module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens net.entscrew.dolos to javafx.fxml;
    exports net.entscrew.dolos;
}