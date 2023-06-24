package com.gnosis.swaggerhelperui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.util.Iterator;

public class SwaggerHelperController {
    private static final String[] INITIAL_EXCLUSIONS = {
            "/as400/",
            "/actuator/",
            "/session-activity/",
            "/collection/",
            "/corporate/",
            "/operationAvailability/",
            "/messaging/",
            "/firm/",
            "/gev/",
            "/messagingtemplate"
    };
    @FXML
    private Label label_app_status;

    @FXML
    private Button button_do;

    @FXML
    private TextField textField_swagger_url;

    @FXML
    private ListView<String> listView_exclusion_filters;

    @FXML
    private TextField textField_exclusionFilter;

    @FXML
    private Button button_exclude_pattern;

    @FXML
    public void initialize() {
        listView_exclusion_filters.getItems().addAll(INITIAL_EXCLUSIONS);
        button_exclude_pattern.setOnMouseClicked(mouseEvent -> {
            if (textField_exclusionFilter.getText() != null
                    && !textField_exclusionFilter.getText().trim().isEmpty()
                    && !listView_exclusion_filters.getItems().contains(textField_exclusionFilter.getText().trim())) {
                listView_exclusion_filters.getItems().add(textField_exclusionFilter.getText().trim());
            }
        });
        button_do.setOnMouseClicked(mouseEvent -> {
            String swaggerUrl = textField_swagger_url.getText();
            try {
                String data = Util.getData(swaggerUrl);

                JSONObject jsonObject = new JSONObject(data);
                JSONObject paths = jsonObject.getJSONObject("paths");
                Iterator<String> keys = paths.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    if (listView_exclusion_filters.getItems()
                            .stream()
                            .anyMatch(key::contains)) {
                        keys.remove();
                    }
                }
                String outString = jsonObject.toString();
                Util.putClipboard(outString);

                label_app_status.setText("Copied to clipboard.");
                label_app_status.setTextFill(Color.GREEN);
            } catch (Exception e) {
                e.printStackTrace();
                label_app_status.setText(e.getMessage());
            }
        });
    }

}