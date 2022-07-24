package com.ebs.electricity_billing_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class SignUpController implements Initializable {
    // signUp.fxml elements
    @FXML
    private TextField signup_username;

    @FXML
    private TextField signup_password;

    @FXML
    private TextField signup_confirm_password;

    @FXML
    private Button signup_button;

    @FXML
    private Button back_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Changes scene to home.fxml
        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "home.fxml", "Welcome");
            }
        });

        // Checks if password and confirm password match then passes text entered in the text fields into DBUtils.signupUser
        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (signup_password.getText().equals(signup_confirm_password.getText())) {
                    DBUtils.signUpUser(actionEvent, signup_username.getText(), signup_password.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Passwords do not match");
                    alert.show();
                }
            }
        });
    }
}
