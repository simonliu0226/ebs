package com.ebs.electricity_billing_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class AdjustRateController implements Initializable {
    @FXML
    private Button adjust_button;

    @FXML
    private Button cancel_button;

    @FXML
    private TextField rate_text;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rate_text.setText(DBUtils.getRate().toString());
        // the cancel button will change the scene back to menu.fxml when pressed.
        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        });

        // The adjust button will change the electricity rate to the value the user enters in the text field.
        adjust_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.setRate(Double.parseDouble(rate_text.getText()));
            }
        });
    }
}
