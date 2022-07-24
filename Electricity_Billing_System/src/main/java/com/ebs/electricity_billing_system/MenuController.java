package com.ebs.electricity_billing_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class MenuController implements Initializable {

    @FXML
    private Button customers_button;

    @FXML
    private Button adjust_rate_button;

    @FXML
    private Button about_button;

    @FXML
    private Button signOut_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Change to customerList.fxml scene
        customers_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToTableScene(actionEvent, "customerList.fxml", "Customer List", "all", "", 0);
            }
        });

        // Change to adjustRate.fxml scene
        adjust_rate_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "adjustRate.fxml", "Rate Adjustment");
            }
        });

        // Change to about.fxml scene
        about_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "about.fxml", "About");
            }
        });

        // Change to home.fxml scene
        signOut_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "home.fxml", "Welcome");
            }
        });
    }
}
