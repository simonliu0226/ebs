module com.ebs.electricity_billing_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.ebs.electricity_billing_system to javafx.fxml;
    exports com.ebs.electricity_billing_system;
}