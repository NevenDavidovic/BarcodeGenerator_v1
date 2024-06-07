module com.example.barcodegenerator_v1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens com.example.barcodegenerator_v1 to javafx.fxml;
    exports com.example.barcodegenerator_v1;
}