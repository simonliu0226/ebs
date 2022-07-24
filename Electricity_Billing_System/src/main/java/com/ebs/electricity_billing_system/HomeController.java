package com.ebs.electricity_billing_system;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class HomeController implements Initializable {
    // home.fxml elements
    @FXML
    private TextField login_username;

    @FXML
    private TextField login_password;

    @FXML
    private Button login_button;

    @FXML
    private Button signup_button;

    @FXML
    private Button exit_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // change to signUp.fxml scene
        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "signUp.fxml", "Sign Up");
            }
        });

        // When login button is pressed, it calls the DBUtils loginUser method using the provided username and password
        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.loginUser(actionEvent, login_username.getText(), login_password.getText());
            }
        });

        // Button will close application when clicked
        exit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

    }
}
