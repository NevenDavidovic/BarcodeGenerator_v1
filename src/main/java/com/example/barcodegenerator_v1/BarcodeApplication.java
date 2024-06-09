package com.example.barcodegenerator_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class BarcodeApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader;
        // Provjeri da li ima korisnika u bazi podataka i prikazi odgovarajuci prozor
        // Ako nema korisnika, prikazi formu za unos korisnika inace prikazi glavni prozor
        if (DatabaseHandler.getAllUsers().isEmpty()) {
            fxmlLoader = new FXMLLoader(BarcodeApplication.class.getResource("user-form-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(BarcodeApplication.class.getResource("home-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("/css/style.css").toExternalForm());
        stage.setTitle("Barcode Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseHandler.initialize();
        launch();
    }
}

// TODO napraviti background image

