package com.example.barcodegenerator_v1;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.barcodegenerator_v1.SceneSwitcher.switchScene;

public class UserFormController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField IBANField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField zipField;

    @FXML
    private TextField callerField;

    @FXML
    protected void onDeleteButtonClick() {
        clearFields();
        DatabaseHandler.deleteUser();

        switchScene((Stage) nameField.getScene().getWindow(), "home-view.fxml", "Hello!");
    }

    private boolean containsForbiddenCharacters(String input) {
        return input.matches(".*[čćšđ].*");
    }


    protected void clearFields() {
        nameField.clear();
        IBANField.clear();
        emailField.clear();
        streetField.clear();
        zipField.clear();
        callerField.clear();
    }
    @FXML
    protected void onSubmitButtonClick() {
        String name = nameField.getText();
        String IBAN = IBANField.getText();
        String email = emailField.getText();
        String street = streetField.getText();
        String zip = zipField.getText();
        String caller = callerField.getText();

        // Validate input fields
        if (containsForbiddenCharacters(name) || containsForbiddenCharacters(IBAN) ||
                containsForbiddenCharacters(email) || containsForbiddenCharacters(street) ||
                containsForbiddenCharacters(zip) || containsForbiddenCharacters(caller)) {
            // Display error message
            showError("Error: Input contains forbidden characters (č, ć, š, đ).");
            return; // Do not proceed with saving
        }

        // insert the user if it does not exist
        if (DatabaseHandler.getAllUsers().isEmpty()) {
            DatabaseHandler.insertUser(name, IBAN, email, street, zip, caller);
        } else {
            // update the first user if it exists
            DatabaseHandler.updateUser(name, IBAN, email, street, zip, caller);
        }
        switchScene((Stage) nameField.getScene().getWindow(), "home-view.fxml", "Hello!");
    }

    private void showError(String message) {
        // Implement a way to show the error message to the user
        // For example, using a Label or an Alert dialog
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    protected void onCloseButtonClick() throws IOException {
        switchScene((Stage) nameField.getScene().getWindow(), "home-view.fxml", "Hello!");
    }


    // Kada se forma ucita, prikazi podatke prvog korisnika ako postoji u bazi (prefill)
    @FXML
    public void initialize() {
        List<User> users = DatabaseHandler.getAllUsers();
        if (!users.isEmpty()) {
            User user = users.getFirst(); // get the first user
            nameField.setText(user.getName());
            IBANField.setText(user.getIBAN());
            emailField.setText(user.getEmail());
            streetField.setText(user.getStreet());
            zipField.setText(user.getZip());
            callerField.setText(user.getCaller());
        }
    }
}