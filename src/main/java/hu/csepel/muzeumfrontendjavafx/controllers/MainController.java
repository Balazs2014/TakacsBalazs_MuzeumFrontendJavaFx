package hu.csepel.muzeumfrontendjavafx.controllers;

import hu.csepel.muzeumfrontendjavafx.Api;
import hu.csepel.muzeumfrontendjavafx.Controller;
import hu.csepel.muzeumfrontendjavafx.classes.Festmeny;
import hu.csepel.muzeumfrontendjavafx.classes.Szobor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
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
    @FXML
    private Button btnFestmenyModositas;
    @FXML
    private Button btnFestmenyTorles;

    public void initialize() {
        festmenyIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        festmenyCimCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        festmenyKiallitvaCol.setCellValueFactory(new PropertyValueFactory<>("on_display"));
        festmenyEvCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        festmenyListaFeltoltes();
    }

    private void festmenyListaFeltoltes() {
        btnFestmenyTorles.setDisable(true);
        btnFestmenyModositas.setDisable(true);
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
        try {
            Controller hozzaadasAblak = ujAblak("festmeny-hozzaadas-view.fxml", "Festmény hozzáadása", 300, 240);
            hozzaadasAblak.getStage().setOnCloseRequest(event -> tableViewFestmeny.refresh());
            hozzaadasAblak.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onFestmenyTorlesClick(ActionEvent actionEvent) {
        Festmeny torlendo = tableViewFestmeny.getSelectionModel().getSelectedItem();
        if (!confirm("Biztons törölni szeretné a(z) " + torlendo.getTitle() + " című festményt?")) {
            return;
        }
        try {
            boolean siker = Api.deleteFestmeny(torlendo.getId());
            alert(siker ? "Sikeres törlés!" : "Sikertelen törlés!");
            festmenyListaFeltoltes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onFestmenyModositasClick(ActionEvent actionEvent) {
        Festmeny selectedItem = tableViewFestmeny.getSelectionModel().getSelectedItem();
        try {
            FestmenyModositasController modositasAblak = (FestmenyModositasController) ujAblak("festmeny-modositas-view.fxml", "Festmény módosítása", 300, 240);
            modositasAblak.setModositando(selectedItem);
            modositasAblak.getStage().setOnHiding(event -> tableViewFestmeny.refresh());
            modositasAblak.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    public void onItemSelected(Event event) {
        int selectedIndex = tableViewFestmeny.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            btnFestmenyTorles.setDisable(false);
            btnFestmenyModositas.setDisable(false);
        }
    }
}