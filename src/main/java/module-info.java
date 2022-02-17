module hu.csepel.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens hu.csepel.muzeumfrontendjavafx to javafx.fxml, com.google.gson;
    exports hu.csepel.muzeumfrontendjavafx;
    exports hu.csepel.muzeumfrontendjavafx.controllers;
    opens hu.csepel.muzeumfrontendjavafx.controllers to javafx.fxml, com.google.gson;
    exports hu.csepel.muzeumfrontendjavafx.classes;
    opens hu.csepel.muzeumfrontendjavafx.classes to javafx.base, com.google.gson;
    exports hu.csepel.muzeumfrontendjavafx.controllers.statue;
    opens hu.csepel.muzeumfrontendjavafx.controllers.statue to com.google.gson, javafx.fxml;
    exports hu.csepel.muzeumfrontendjavafx.controllers.painting;
    opens hu.csepel.muzeumfrontendjavafx.controllers.painting to com.google.gson, javafx.fxml;
    exports hu.csepel.muzeumfrontendjavafx.api;
    opens hu.csepel.muzeumfrontendjavafx.api to com.google.gson, javafx.fxml;
}