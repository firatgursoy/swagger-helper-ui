package com.gnosis.swaggerhelperui;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
    public static String getData(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public static void putClipboard(String outString) {
        Clipboard clipboard = Clipboard.getSystemClipboard();

        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(outString);
        clipboard.setContent(clipboardContent);
    }
}
