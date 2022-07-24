package com.ebs.electricity_billing_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class CalculateBillController implements Initializable {
    @FXML
    private TextField customer_name;

    @FXML
    private TextField address_text;

    @FXML
    private TextField meter_number_text;

    @FXML
    private TextField rate_text;

    @FXML
    private TextField usage_text;

    @FXML
    private  TextField balance_text;

    @FXML
    private Button submit_button;

    @FXML
    private Button cancel_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // The cancel button will change the stage to the customerInfo.fxml scene.
        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToCustomerInfo(actionEvent, "customerInfo.fxml", "Customer Information", Integer.parseInt(meter_number_text.getText()));
            }
        });

        // After hitting the submit button, the cost will be calculated using the usage amount and rate then added onto the balance of the customer.
        submit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (usage_text.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter the usage amount.");
                    alert.show();
                } else {
                    DBUtils.changeBalance(Integer.parseInt(meter_number_text.getText()), "bill", Double.parseDouble(rate_text.getText()) * Double.parseDouble(usage_text.getText()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    double newBalance = Double.parseDouble(balance_text.getText()) + Math.round((Double.parseDouble(usage_text.getText()) * Double.parseDouble(rate_text.getText())) * 100.0) / 100.0;
                    balance_text.setText(Double.toString(newBalance));
                    alert.setContentText("The customer's balance has changed to " + newBalance + ".");
                    alert.show();
                }
            }
        });
    }

    // Sets the textboxes with customer information.
    public void setInfo(String name, Integer meter_num, String address, Double balance, Double rate) {
        customer_name.setText(name);
        address_text.setText(address);
        meter_number_text.setText(meter_num.toString());
        rate_text.setText(rate.toString());
        balance_text.setText(balance.toString());
    }
}
