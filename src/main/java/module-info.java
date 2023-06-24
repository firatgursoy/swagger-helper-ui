module com.gnosis.swaggerhelperui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.json;

    opens com.gnosis.swaggerhelperui to javafx.fxml;
    exports com.gnosis.swaggerhelperui;
}