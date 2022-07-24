package com.ebs.electricity_billing_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class SearchCustomerController implements Initializable {
    // searchCustomer.fxml elements
    @FXML
    private TextField name_text;

    @FXML
    private TextField meter_number_text;

    @FXML
    private Button search_button;

    @FXML
    private Button back_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Uses the entered text to then switch to a table scene consisting of the searched customer
        search_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!name_text.getText().isBlank() && !meter_number_text.getText().isBlank() || name_text.getText().isBlank() && meter_number_text.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill only one text box.");
                    alert.show();
                } else if (name_text.getText().isBlank() && !meter_number_text.getText().isBlank()) {
                    DBUtils.changeToTableScene(actionEvent, "searchResult.fxml", "Search Result", "Meter Num Search", "", Integer.parseInt(meter_number_text.getText()));
                } else {
                    DBUtils.changeToTableScene(actionEvent, "searchResult.fxml", "Search Result", "Name Search", name_text.getText(), 0);
                }

            }
        });

        // Change scene to customerList.fxml
        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToTableScene(actionEvent, "customerList.fxml", "Customer List", "all", "", 0);
            }
        });

    }
}
