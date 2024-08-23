module laboratorio1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens laboratorio1.controller to javafx.fxml;
    exports laboratorio1;
}
