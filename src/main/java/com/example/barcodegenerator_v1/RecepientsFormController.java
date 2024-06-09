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

    private boolean isValidDouble(String amountField) {
        try {
            Double.parseDouble(amountField);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private String formatDoubleToTwoDecimalPlaces(double value) {
        return String.format("%.2f", value);
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

        // Check if any field is empty
        if (name.isEmpty() || address.isEmpty() || city.isEmpty() || description.isEmpty() ||
            email.isEmpty() || model.isEmpty() || pozivNaBroj.isEmpty() || amount.isEmpty()) {
            // Display error message
            confirmationText.setText("Error: All fields are required.");
            return; // Do not proceed with saving
        }
        // Validate input fields
        if (containsForbiddenCharacters(name) || containsForbiddenCharacters(address) ||
                containsForbiddenCharacters(city) || containsForbiddenCharacters(description) ||
                containsForbiddenCharacters(email) || containsForbiddenCharacters(model) ||
                containsForbiddenCharacters(pozivNaBroj) || containsForbiddenCharacters(amount)) {
            // Display error message
            confirmationText.setText("Error: Input contains forbidden characters (č, ć, š, đ).");
            return; // Do not proceed with saving
        }
        // Validate amount field to be a valid double type
        if (!isValidDouble(amount)) {
            // Display error message
            confirmationText.setText("Error: Invalid amount format. Please enter a valid number");
            return; // Do not proceed with saving
        }
        // Format amount to two decimal places
        amount = formatDoubleToTwoDecimalPlaces(Double.parseDouble(amount));
        amountField.setText(amount);



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
        SceneSwitcher.switchScene(stage, "home-view.fxml", "Barcode Generator");
    }
    @FXML
    protected void switchToBarcodeScene() throws IOException {
        Stage stage = (Stage) barcodeButton.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "barcode-generator.fxml", "Barcode Scene");
    }
}
