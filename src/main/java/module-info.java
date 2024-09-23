module laboratorio1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.logging; //Logger
    requires java.desktop; //XML

    opens laboratorio1.controller to javafx.fxml;
    opens laboratorio1.model to javafx.base;
    opens laboratorio1.util to javafx.base;
    exports laboratorio1;
    exports laboratorio1.model;//Para que XMLEncoder acceda
}
