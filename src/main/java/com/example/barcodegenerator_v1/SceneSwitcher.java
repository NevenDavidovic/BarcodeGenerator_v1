package com.example.barcodegenerator_v1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    private static final double DEFAULT_WIDTH = 800;
    private static final double DEFAULT_HEIGHT = 600;

    public static void switchScene(Stage stage, String fxmlFile, String title) {
        switchScene(stage, fxmlFile, title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static void switchScene(Stage stage, String fxmlFile, String title, double width, double height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

