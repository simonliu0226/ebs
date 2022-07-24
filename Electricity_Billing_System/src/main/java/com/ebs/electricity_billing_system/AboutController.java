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
public class AboutController implements Initializable {
    @FXML
    private Button back_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Changes scene back to menu.fxml when back button is pressed
        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        });
    }
}
