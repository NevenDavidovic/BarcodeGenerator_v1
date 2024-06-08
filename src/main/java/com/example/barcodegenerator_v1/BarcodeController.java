package com.example.barcodegenerator_v1;

import com.example.barcodegenerator_v1.DatabaseHandler;
import com.google.zxing.WriterException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BarcodeController {

    @FXML
    private Button homeButton;

    @FXML
    private ListView<String> recipientsListView;

    @FXML
    private ImageView barcodeImageView;

    private ObservableList<String> recipientList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        loadRecipients();
    }

    @FXML
    protected void switchToHelloScene() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "home-view.fxml", "Hello Scene");
    }

    private void loadRecipients() {
        // Fetching user data
        List<User> users = DatabaseHandler.getAllUsers();
        User user = users.isEmpty() ? null : users.get(0); // Assuming only one user is available

        List<Recepient> recipients = DatabaseHandler.getAllRecepients();
        for (Recepient recipient : recipients) {
            recipientList.add(
                    recipient.getName() + " - " +
                            recipient.getAddress() + " - " +
                            recipient.getCity() + " - " +
                            recipient.getDescription() + " - " +
                            recipient.getEmail() + " - " +
                            recipient.getModel() + " - " +
                            recipient.getPozivNaBroj() + " - " +
                            recipient.getAmount()
            );
        }

        recipientsListView.setItems(recipientList);

        recipientsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String textToEncode = constructBarcodeData(newValue.toString(), user);

                try {
                    // Generate the barcode
                    Image barcodeImage = BarcodeGenerator.generateBarcode(textToEncode, 400, 200);

                    // Display the generated barcode
                    barcodeImageView.setImage(barcodeImage);

                    System.out.println("Barcode generated and displayed on the screen.");
                } catch (IOException | WriterException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private String constructBarcodeData(String recipientData, User user) {
        if (user == null) {
            return "";
        }

        String[] recipientInfo = recipientData.split(" - ");
        if (recipientInfo.length != 8) {
            return "";
        }

        String barcodeData = "HRVHUB30\n" +
                "EUR\n" +
                recipientInfo[7] + "\n" +             // amount_from_recepients_table
                recipientInfo[0] + "\n" +             // name_from_recepients_table
                recipientInfo[1] + "\n" +             // address_from_recepients_table
                recipientInfo[2] + "\n" +             // city_from_recepients_table
                user.getName() + "\n" +               // name_from_user_table
                user.getStreet() + "\n" +             // street_from_user_table
                user.getZip() + "\n" +                // zip_from_user_table
                user.getIBAN() + "\n" +               // IBAN_from_user_table
                recipientInfo[5] + "\n" +             // model_from_recepient_table
                recipientInfo[6] + "\n" +             // poziv_na_broj_from_recepient_table
                "\n" +                                // sifra_namjene (as empty string not from table)
                recipientInfo[3];                     // opis placanja

        System.out.println("Constructed Barcode Data: " + barcodeData);

        return barcodeData;
    }



}
