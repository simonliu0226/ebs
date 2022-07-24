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
public class DepositDetailsController implements Initializable {

    // fxml file elements
    @FXML
    private Button submit_button;

    @FXML
    private Button cancel_button;

    @FXML
    private TextField customer_name;

    @FXML
    private TextField address_text;

    @FXML
    private TextField meter_number_text;

    @FXML
    private TextField amount_text;

    @FXML
    private TextField balance_text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // changes scene to customerInfo.fxml
        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToCustomerInfo(actionEvent, "customerInfo.fxml", "Customer Information", Integer.parseInt(meter_number_text.getText()));
            }
        });

        // checks if value entered in amount is valid then passes it into changeBalance method in DBUtils
        submit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (amount_text.getText().isBlank() || Double.parseDouble(amount_text.getText()) <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter an amount greater than 0.00 to deposit.");
                    alert.show();
                } else {
                    DBUtils.changeBalance(Integer.parseInt(meter_number_text.getText()), "deposit", Double.parseDouble(amount_text.getText()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    double newBalance = Double.parseDouble(balance_text.getText()) - Math.round(Double.parseDouble(amount_text.getText()) * 100.0) / 100.0;
                    balance_text.setText(Double.toString(newBalance));
                    alert.setContentText("The customer's balance has changed to " + newBalance + ".");
                    alert.show();
                }
            }
        });
    }

    // Sets the text for scene
    public void setInfo(String name, Integer meter_num, String address, Double balance) {
        customer_name.setText(name);
        meter_number_text.setText(meter_num.toString());
        address_text.setText(address);
        balance_text.setText(balance.toString());
    }
}
