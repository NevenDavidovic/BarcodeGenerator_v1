package com.example.barcodegenerator_v1;
import com.google.zxing.WriterException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class BarcodeController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button editProfileButton;

    @FXML
    public void initialize() {
        // get the user name from the database and display a welcome message
        List<User> users = DatabaseHandler.getAllUsers();
        String userName;
        if (!users.isEmpty()) {
            userName = users.getFirst().getName();
        } else {
            userName = null; // or any default value
        }

        if (userName != null) {
            welcomeText.setText("Dobrodosao, " + userName + "!");
            editProfileButton.setText("Uredi profil");
        } else {
            welcomeText.setText("Dobrodosao u Barcode Generator!");
            editProfileButton.setText("Dodaj korisnika");
        }
    }

    @FXML
    protected void onManageUserButtonClick() throws IOException{
        // TODO zamjeni sa funkcijom za proimjenu prikaza
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-form-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

    }

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
        // TODO postaviti mogucnost odabira putanje
        String filePath = "C:\\Users\\tinpr\\Desktop\\barcode.jpg";

        BarcodeGenerator.generateBarcode(textToEncode, filePath);

        System.out.println("Barcode generated: " + filePath);
    }

    @FXML
    protected void switchToHelloScene() {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "hello-view.fxml", "Hello Scene");
    }

    @FXML
    protected void switchToBarcodeScene() {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "barcode-generator.fxml", "Barcode Scene");
    }

}