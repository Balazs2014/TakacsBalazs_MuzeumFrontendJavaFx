package hu.csepel.muzeumfrontendjavafx.controllers;

import hu.csepel.muzeumfrontendjavafx.Api;
import hu.csepel.muzeumfrontendjavafx.classes.Festmeny;
import hu.csepel.muzeumfrontendjavafx.classes.Szobor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private TableView<Festmeny> tableViewFestmeny;
    @FXML
    private TableColumn<Festmeny, Integer> festmenyIdCol;
    @FXML
    private TableColumn<Festmeny, String> festmenyCimCol;
    @FXML
    private TableColumn<Festmeny, Boolean> festmenyKiallitvaCol;
    @FXML
    private TableColumn<Festmeny, Integer> festmenyEvCol;

    @FXML
    private TableView<Szobor> tableViewSzobor;
    @FXML
    private TableColumn<Szobor, Integer> szoborIdCol;
    @FXML
    private TableColumn<Szobor, Integer> szoborArCol;
    @FXML
    private TableColumn<Szobor, String> szoborSzemelyCol;
    @FXML
    private TableColumn<Szobor, Integer> szoborMagassagCol;

    public void initialize() {
        festmenyIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        festmenyCimCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        festmenyKiallitvaCol.setCellValueFactory(new PropertyValueFactory<>("on_display"));
        festmenyEvCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        festmenyListaFeltoltes();
    }

    private void festmenyListaFeltoltes() {
        try {
            List<Festmeny> festmenyList = Api.getFestmenyek();
            tableViewFestmeny.getItems().clear();
            for (Festmeny festmeny : festmenyList) {
                tableViewFestmeny.getItems().add(festmeny);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onFestmenyHozzaadasClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onFestmenyTorlesClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onFestmenyModositasClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onSzoborHozzaadasClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onSzoborModositasClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onSzoborTorlesClick(ActionEvent actionEvent) {
    }
}