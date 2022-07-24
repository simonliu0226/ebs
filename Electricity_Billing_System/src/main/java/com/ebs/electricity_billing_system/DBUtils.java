package com.ebs.electricity_billing_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Simon
 */
public class DBUtils {
    // Method to change to basic fxml scene
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {
        Parent root = null;

        try {
            root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
    }

    // Method to change to a fxml scene containing a TableView element
    public static void changeToTableScene(ActionEvent event, String fxmlFile, String title, String type, String name, Integer meter_num) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            if (type.equals("all")) {
                CustomerListController customerListController = loader.getController();
                customerListController.setTable(getCustomersList(type, name, meter_num));
            } else {
                SearchResultController searchResultController = loader.getController();
                searchResultController.setTable(getCustomersList(type, name, meter_num));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
    }

    // Method to change to customerInfo.fxml
    public static void changeToCustomerInfo(MouseEvent event, String fxmlFile, String title, Integer meter_num) {
        Parent root = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE meter_num = ?");
            preparedStatement.setInt(1, meter_num);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            CustomerInfoController customerInfoController = loader.getController();
            customerInfoController.setText(resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getLong(8), resultSet.getString(9), resultSet.getDouble(10));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
    }

    // Overloaded changeToCustomerInfo method that has an ActionEvent parameter instead of MouseEvent
    public static void changeToCustomerInfo(ActionEvent event, String fxmlFile, String title, Integer meter_num) {
        Parent root = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE meter_num = ?");
            preparedStatement.setInt(1, meter_num);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            CustomerInfoController customerInfoController = loader.getController();
            customerInfoController.setText(resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getLong(8), resultSet.getString(9), resultSet.getDouble(10));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
    }

    // Change to either calculateBill.fxml or depositDetails.fxml
    public static void changeToFinance(ActionEvent event, String fxmlFile, String title, Integer meter_num) {
        Parent root = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement psRate = null;
        ResultSet resultSet = null;
        ResultSet rsRate = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE meter_num = ?;");
            preparedStatement.setInt(1, meter_num);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (fxmlFile.equals("calculateBill.fxml")) {
                psRate = connection.prepareStatement("SELECT * FROM electricity_rate;");
                rsRate = psRate.executeQuery();
                rsRate.next();
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                CalculateBillController calculateBillController = loader.getController();
                calculateBillController.setInfo(resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDouble(10), rsRate.getDouble(2));
            } else {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                DepositDetailsController depositDetailsController = loader.getController();
                depositDetailsController.setInfo(resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDouble(10));
            }
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psRate != null) {
                try {
                    psRate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rsRate != null) {
                try {
                    rsRate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
    }

    // Method to log in a user using entered information
    public static void loginUser(ActionEvent actionEvent, String username, String password) {
        // Create variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Check if user exists. If user exists check if password matches then change to menu.fxml scene. If password does not match
        // then alert user that credentials are incorrect.
        try {
            // Create connection to mySQL server
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            // Statement to use for query
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Customer not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while(resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if(retrievedPassword.equals(password)) {
                        changeScene(actionEvent, "menu.fxml", "Menu");
                    } else {
                        System.out.println("Passwords do not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method that adds a new user to the database granting access to the program
    public static void signUpUser(ActionEvent actionEvent, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        // Checks if the user already exists, if not then add user to database then change scene to menu.fxml
        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Customer already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                DBUtils.changeScene(actionEvent, "menu.fxml", "Menu");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to add a new customer to the customers table in the database
    public static void addCustomer(String name, Integer meter_num, String address, String city, String state, String email, Long phone) {
        Connection connection = null;
        PreparedStatement psCheckCustomerExists = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;

        // Checks if customer already exists, if not, then add customer with details to the table
        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            psCheckCustomerExists = connection.prepareStatement("SELECT * FROM customers WHERE meter_num = ?");
            psCheckCustomerExists.setInt(1, meter_num);
            resultSet = psCheckCustomerExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Meter number already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Customer with this meter number already exists.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO customers (name, meter_num, address, city, state, email, phone, status, balance) VALUES (?, ?, ?, ?, ?, ?, ?, 'active', 0.00)");
                psInsert.setString(1, name);
                psInsert.setInt(2, meter_num);
                psInsert.setString(3, address);
                psInsert.setString(4, city);
                psInsert.setString(5, state);
                psInsert.setString(6, email);
                psInsert.setLong(7, phone);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You have successfully added " + name);
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psCheckCustomerExists != null) {
                try {
                    psCheckCustomerExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Remove a customer from the database
    public static void removeCustomer(Integer meter_num) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE meter_num = ?");
            preparedStatement.setInt(1, meter_num);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method that returns an ObservableList containing every customer from the table
    public static ObservableList<Customer> getCustomersList(String type, String name, Integer meter_num) {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            if (type.equals("all")) {
                preparedStatement = connection.prepareStatement("SELECT * FROM customers");
                resultSet = preparedStatement.executeQuery();
            } else if (type.equals("Name Search")) {
                preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE name LIKE '%" + name + "%'");

            } else if (type.equals("Meter Num Search")) {
                preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE meter_num = ?");
                preparedStatement.setInt(1, meter_num);
            } else {
                preparedStatement = connection.prepareStatement("");
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Customer customer = new Customer(resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getLong(8),
                            resultSet.getString(9), resultSet.getDouble(10));
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return customerList;
    }

    // Gets rate from the electricity_rate table from database.
    public static Double getRate() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Double rate = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            preparedStatement = connection.prepareStatement("SELECT * FROM electricity_rate");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rate = resultSet.getDouble(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rate;
    }

    // Method to change the electricity rate
    public static void setRate(Double rate) {
        Connection connection = null;
        PreparedStatement psRemove = null;
        PreparedStatement psInsert = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            psRemove = connection.prepareStatement("DELETE FROM electricity_rate");
            psInsert = connection.prepareStatement("INSERT INTO electricity_rate (rate) VALUES (?)");
            psInsert.setDouble(1, rate);
            psRemove.executeUpdate();
            psInsert.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have successfully changed the rate to $" + rate + ".");
            alert.show();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psRemove != null) {
                try {
                    psRemove.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to change the status of the customer from active to inactive or inactive to active
    public static void changeStatus(Integer meter_num) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            preparedStatement = connection.prepareStatement("SELECT status FROM customers WHERE meter_num = ?");
            preparedStatement.setInt(1, meter_num);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getString(1).equals("active")) {
                preparedStatement = connection.prepareStatement("UPDATE customers SET status = 'inactive' WHERE meter_num = ?");
            } else {
                preparedStatement = connection.prepareStatement("UPDATE customers SET status = 'active' WHERE meter_num = ?");
            }
            preparedStatement.setInt(1, meter_num);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // changes the balance of customer
    public static void changeBalance(Integer meter_num, String type, Double amount) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Double newBalance = null;

        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "user1", "access123");
            System.out.println("Connected");
            preparedStatement = connection.prepareStatement("SELECT balance FROM customers WHERE meter_num = ?;");
            preparedStatement.setInt(1, meter_num);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (type.equals("bill")) {
                newBalance = resultSet.getDouble(1) + Math.round(amount * 100.0) / 100.0;
            } else {
                newBalance = resultSet.getDouble(1) - Math.round(amount * 100.0) / 100.0;
            }
            preparedStatement = connection.prepareStatement("UPDATE customers SET balance = ? WHERE meter_num = ?;");
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, meter_num);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
