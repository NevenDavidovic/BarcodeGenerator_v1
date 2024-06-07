package com.example.barcodegenerator_v1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class BarcodeController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button generateBarcodeButton; // Add a button for barcode generation

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onGenerateBarcodeClick() throws IOException {
        // Get text for barcode (can be from user input field etc.)
        String textToEncode = "http://www.example.com"; // Replace with your desired text

        // Specify the output file path (adjust as needed)
        String filePath = "C:\\Users\\Neven\\Desktop\\barcode.jpg";

        BarcodeGenerator.generateBarcode(textToEncode, filePath);

        System.out.println("Barcode generated: " + filePath);
    }
}