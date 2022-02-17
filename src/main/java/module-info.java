module hu.csepel.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens hu.csepel.muzeumfrontendjavafx to javafx.fxml, com.google.gson;
    exports hu.csepel.muzeumfrontendjavafx;
    exports hu.csepel.muzeumfrontendjavafx.controllers;
    opens hu.csepel.muzeumfrontendjavafx.controllers to javafx.fxml, com.google.gson;
    opens hu.csepel.muzeumfrontendjavafx.classes to javafx.base, com.google.gson;
}