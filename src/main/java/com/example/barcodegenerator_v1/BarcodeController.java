package com.example.barcodegenerator_v1;
import com.google.zxing.WriterException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class BarcodeController {

    @FXML
    private Button homeButton;

    @FXML
    protected void onGenerateBarcodeClick() throws IOException, WriterException {
        // Get text for barcode (can be from user input field etc.)
        String textToEncode = "HRVHUB30\n" +
                "HRK\n" +
                "000000000012355\n" +
                "ZELJKO SENEKOVIC\n" +
                "IVANECKA ULICA 125\n" +
                "42000 VARAZDIN\n" +
                "2DBK d.d.\n" +
                "ALKARSKI PROLAZ 13B\n" +
                "21230 SINJ\n" +
                "HR1210010051863000160\n" +
                "HR01\n" +
                "7269-68949637676-00019\n" +
                "COST\n" +
                "Troskovi za 1. mjesec"; // Replace with your desired text

        // Specify the output file path (adjust as needed)
        String filePath = "C:\\Users\\tinpr\\Desktop\\barcode.jpg";

        BarcodeGenerator.generateBarcode(textToEncode, filePath);

        System.out.println("Barcode generated: " + filePath);
    }

    @FXML
    protected void switchToHelloScene() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "home-view.fxml", "Hello Scene");
    }



}