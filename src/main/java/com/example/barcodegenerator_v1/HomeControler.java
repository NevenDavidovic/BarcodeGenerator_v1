package com.example.barcodegenerator_v1;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.List;
import java.io.IOException;

public class HomeControler {


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
    protected void switchToBarcodeScene() {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "barcode-generator.fxml", "Barcode Scene");
    }

    @FXML
    protected void onManageUserButtonClick() throws IOException{
        SceneSwitcher.switchScene((Stage) welcomeText.getScene().getWindow(), "user-form-view.fxml", "User Form");
    }
}
