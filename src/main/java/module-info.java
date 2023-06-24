module com.gnosis.swaggerhelperui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;
    requires swagger.models;
    requires org.json;

    opens com.gnosis.swaggerhelperui to javafx.fxml;
    exports com.gnosis.swaggerhelperui;
}