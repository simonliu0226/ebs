package com.ebs.electricity_billing_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class CustomerInfoController implements Initializable {

    // Elements from the customerInfo.fxml file
    @FXML
    private Button bill_button;

    @FXML
    private Button deposit_button;

    @FXML
    private Button home_button;

    @FXML
    private Button back_button;

    @FXML
    private Button remove_button;

    @FXML
    private Button change_status_button;

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
    private TextField status_text;

    @FXML
    private TextField balance_text;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Pressing the home button will change the scene to menu.fxml
        home_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        });

        // The back button will change the scene back to customerList.fxml
        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToTableScene(actionEvent, "customerList.fxml", "Customer List", "all", "", 0);
            }
        });

        // The change_status button will change the customer status to either active or inactive
        change_status_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeStatus(Integer.parseInt(meter_num_text.getText()));
                if (status_text.getText().equals("active")) {
                    status_text.setText("inactive");
                } else {
                    status_text.setText("active");
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You have successfully changed the user's status.");
                alert.show();
            }
        });

        // the remove button will trigger a confirmation popup where the user will have to confirm or deny the removal of a customer
        remove_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // alert to ask user to confirm
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + name_text.getText() + "?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    // If user selects yes, the customer will be removed from the database
                    DBUtils.removeCustomer(Integer.parseInt(meter_num_text.getText()));
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setContentText("You have successfully removed " + name_text.getText() + ".");
                    infoAlert.show();
                    DBUtils.changeToTableScene(actionEvent, "customerList.fxml", "Customer List", "all", "", 0);

                }
            }
        });

        // Change scene to calculateBill.fxml
        bill_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToFinance(actionEvent, "calculateBill.fxml", "Calculate Bill", Integer.parseInt(meter_num_text.getText()));
            }
        });

        // Change scene to depositDetails.fxml
        deposit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeToFinance(actionEvent, "depositDetails.fxml", "Deposit Details", Integer.parseInt(meter_num_text.getText()));
            }
        });

    }

    // This method will set up the text boxes with customer information.
    public void setText(String name, Integer meter_num, String address, String city, String state, String email, Long phone, String status, Double balance) {
        name_text.setText(name);
        meter_num_text.setText(meter_num.toString());
        address_text.setText(address);
        city_text.setText(city);
        state_text.setText(state);
        email_text.setText(email);
        phone_text.setText(phone.toString());
        status_text.setText(status);
        balance_text.setText(balance.toString());
    }
}
