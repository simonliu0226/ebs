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
public class CustomerListController implements Initializable {
    // Elements on the customerList.fxml file
    @FXML
    private Button search_button;

    @FXML
    private Button add_button;

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
    private TableColumn<Customer, String>phone_column;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Sets up the columns on the table
        name_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        meter_number_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("meter_num"));
        address_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        city_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));
        state_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("state"));
        email_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        phone_column.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));

        // Changes to searchCustomer.fxml
        search_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "searchCustomer.fxml", "Customer Search");
            }
        });

        // Changes to addCustomer.fxml
        add_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "addCustomer.fxml", "Add New Customer");
            }
        });

        // Changes to menu.fxml
        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        });

        // Change to customerInfo.fxml using information from customer row which was double-clicked
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

    // Adds data to the tableview element
    public void setTable(ObservableList<Customer> customerList) {
        customer_table.setItems(customerList);
        System.out.println("List Added");
    }

}
