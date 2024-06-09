package com.example.barcodegenerator_v1;

import com.google.zxing.WriterException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.imageio.ImageIO;

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
        SceneSwitcher.switchScene(stage, "home-view.fxml", "Barcode generator");
    }

    @FXML
    protected void printBarcodeToPdf() {
        try {
            Document document = new Document();

            // Create a FileChooser
            FileChooser fileChooser = new FileChooser();

            // Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show save file dialog
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(barcodeImageView.getImage(), null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(baos.toByteArray());

                document.add(image);
                document.close();
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        switchToHelloScene();
    }

    private void loadRecipients() {
        // Fetching user data
        List<User> users = DatabaseHandler.getAllUsers();
        User user = users.isEmpty() ? null : users.get(0); // Assuming only one user is available

        // Formatting user data for display
        List<Recipient> recipients = DatabaseHandler.getAllRecepients();
        for (Recipient recipient : recipients) {
            recipientList.add(
                            recipient.getId() + " - " +
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

        // Display the list of recipients
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

    // Function to delete a recipient from the list
    @FXML
    protected void deleteRecipient(){
        String selectedRecipient = recipientsListView.getSelectionModel().getSelectedItem();
        if (selectedRecipient != null) {
            String[] recipientInfo = selectedRecipient.split(" - ");
            int recipientId = Integer.parseInt(recipientInfo[0]);
            DatabaseHandler.deleteRecepient(recipientId);
            recipientList.remove(selectedRecipient);
        }
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
