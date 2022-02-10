module hu.csepel.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.csepel.muzeumfrontendjavafx to javafx.fxml;
    exports hu.csepel.muzeumfrontendjavafx;
}