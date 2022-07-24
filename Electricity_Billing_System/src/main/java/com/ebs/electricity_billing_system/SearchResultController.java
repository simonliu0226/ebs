package com.ebs.electricity_billing_system;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class SearchResultController implements Initializable {
    // searchResult.fxml elements
    @FXML
    private Button home_button;

    @FXML
    private Button back_button;

    @FXML
    private TableView<Customer> customer_table;

    @FXML
    private TableColumn<Customer, String> name_column;

    @FXML
    private TableColumn<Customer, String> meter_number_column;

    @FXML
    private TableColumn<Customer, String> address_column;

    @FXML
    private TableColumn<Customer, String> city_column;

    @FXML
    private TableColumn<Customer, String> state_column;

    @FXML
    private TableColumn<Customer, String> email_column;

    @FXML
    private TableColumn<Customer, String> phone_column;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Sets up columns to display certain information
        name_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        meter_number_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("meter_num"));
        address_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        city_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));
        state_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("state"));
        email_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        phone_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));

        // Changes scene to home.fxml
        home_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "home.fxml", "Welcome");
            }
        });

        // Changes scene to searchCustomer.fxml
        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "searchCustomer.fxml", "Customer Search");
            }
        });

        // Changes scene to customerInfo.fxml and passing meter_num into DBUtils changeToCustomerInfo method
        customer_table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                    System.out.println(customer_table.getSelectionModel().getSelectedItem().getName());
                    DBUtils.changeToCustomerInfo(mouseEvent, "customerInfo.fxml", "Customer Information", customer_table.getSelectionModel().getSelectedItem().getMeter_num());
                }
            }
        });
    }

    // Sets table using an ObservableList
    public void setTable(ObservableList<Customer> customerList) {
        customer_table.setItems(customerList);
        System.out.println("List Added");
    }
}
