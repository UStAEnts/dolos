module dolos {
    requires javafx.controls;
    requires javafx.fxml;
    requires oscp5;

    opens net.entscrew.dolos to javafx.fxml;
    exports net.entscrew.dolos;
}