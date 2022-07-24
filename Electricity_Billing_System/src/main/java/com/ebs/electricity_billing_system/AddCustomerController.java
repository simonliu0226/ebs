package com.ebs.electricity_billing_system;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class AddCustomerController implements Initializable {
    @FXML
    private TextField name_text;

    @FXML
    private TextField meter_num_text;

    @FXML
    private TextField address_text;

    @FXML
    private TextField city_text;

    @FXML
    private TextField state_text;

    @FXML
    private TextField email_text;

    @FXML
    private TextField phone_text;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Changes scene to customer list when back button is pressed
        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToTableScene(actionEvent, "customerList.fxml", "Customer List", "all", "", 0);
            }
        });

        // adds customer to sql table when button is pressed using information filled in the text fields.
        add_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // If any text field is blank, an alert will show to make sure all text boxes are completed.
                if (name_text.getText().isBlank() || meter_num_text.getText().isBlank() || address_text.getText().isBlank() || city_text.getText().isBlank() || state_text.getText().isBlank() || email_text.getText().isBlank() || phone_text.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all text boxes.");
                    alert.show();
                } else {
                    try {
                        DBUtils.addCustomer(name_text.getText(), Integer.parseInt(meter_num_text.getText()), address_text.getText(), city_text.getText(), state_text.getText(), email_text.getText(), Long.parseLong(phone_text.getText()));
                        DBUtils.changeToTableScene(actionEvent, "customerList.fxml", "Customer List", "all", "", 0);
                    } catch (NumberFormatException exception) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please fill in correct information into the text boxes.");
                        alert.show();
                    }


                }
            }
        });
    }
}
