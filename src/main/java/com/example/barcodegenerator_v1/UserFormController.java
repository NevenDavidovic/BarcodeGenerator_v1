package com.example.barcodegenerator_v1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.barcodegenerator_v1.SceneSwitcher.switchScene;

public class UserFormController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

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
    protected void clearFields() {
        nameField.clear();
        descriptionField.clear();
        emailField.clear();
        streetField.clear();
        zipField.clear();
        callerField.clear();
    }
    @FXML
    protected void onSubmitButtonClick() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String email = emailField.getText();
        String street = streetField.getText();
        String zip = zipField.getText();
        String caller = callerField.getText();

        // insert the user if it does not exist
        if (DatabaseHandler.getAllUsers().isEmpty()) {
            DatabaseHandler.insertUser(name, description, email, street, zip, caller);
        }else {
            // update the first user if it exists
            DatabaseHandler.updateUser(name, description, email, street, zip, caller);
        }
        switchScene((Stage) nameField.getScene().getWindow(), "home-view.fxml", "Hello!");
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
            descriptionField.setText(user.getDescription());
            emailField.setText(user.getEmail());
            streetField.setText(user.getStreet());
            zipField.setText(user.getZip());
            callerField.setText(user.getCaller());
        }
    }
}