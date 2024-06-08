package com.example.barcodegenerator_v1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class RecepientsFormController {
    @FXML
    private Button homeButton;
    @FXML
    private Button barcodeButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField pozivNaBrojField;
    @FXML
    private TextField amountField;
    @FXML
    private Text confirmationText;

    @FXML

    private boolean containsForbiddenCharacters(String input) {
        return input.matches(".*[čćšđ].*");
    }

    @FXML
    private void handleSubmit() {
        String name = nameField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String description = descriptionField.getText();
        String email = emailField.getText();
        String model = modelField.getText();
        String pozivNaBroj = pozivNaBrojField.getText();
        String amount = amountField.getText();

        // Validate input fields
        if (containsForbiddenCharacters(name) || containsForbiddenCharacters(address) ||
                containsForbiddenCharacters(city) || containsForbiddenCharacters(description) ||
                containsForbiddenCharacters(email) || containsForbiddenCharacters(model) ||
                containsForbiddenCharacters(pozivNaBroj) || containsForbiddenCharacters(amount)) {
            // Display error message
            confirmationText.setText("Error: Input contains forbidden characters (č, ć, š, đ).");
            return; // Do not proceed with saving
        }


        // Proceed with saving if validation passes
        DatabaseHandler.insertRecepient(name, address, city, description, email, model, pozivNaBroj, amount);

        // Display confirmation message
        confirmationText.setText("Recipient saved successfully!");

        // Clear the input fields
        clearFields();
    }



    // Method to clear input fields
    private void clearFields() {
        nameField.clear();
        addressField.clear();
        cityField.clear();
        descriptionField.clear();
        emailField.clear();
        modelField.clear();
        pozivNaBrojField.clear();
        amountField.clear();
    }

    @FXML
    protected void switchToHomeScene() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "home-view.fxml", "Hello Scene");
    }
    @FXML
    protected void switchToBarcodeScene() throws IOException {
        Stage stage = (Stage) barcodeButton.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "barcode-generator.fxml", "Barcode Scene");
    }
}
