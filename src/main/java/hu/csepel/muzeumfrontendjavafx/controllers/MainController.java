package hu.csepel.muzeumfrontendjavafx.controllers;

import hu.csepel.muzeumfrontendjavafx.Controller;
import hu.csepel.muzeumfrontendjavafx.api.FestmenyApi;
import hu.csepel.muzeumfrontendjavafx.api.SzoborApi;
import hu.csepel.muzeumfrontendjavafx.classes.Festmeny;
import hu.csepel.muzeumfrontendjavafx.classes.Szobor;
import hu.csepel.muzeumfrontendjavafx.controllers.painting.FestmenyModositasController;
import hu.csepel.muzeumfrontendjavafx.controllers.statue.SzoborModositasController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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
    @FXML
    private Button btnSzoborTorles;
    @FXML
    private Button btnSzoborModositas;

    public void initialize() {
        festmenyIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        festmenyCimCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        festmenyKiallitvaCol.setCellValueFactory(new PropertyValueFactory<>("on_display"));
        festmenyEvCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        szoborIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        szoborSzemelyCol.setCellValueFactory(new PropertyValueFactory<>("person"));
        szoborMagassagCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        szoborArCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        festmenyListaFeltoltes();
        szoborListaFeltoltes();
    }

    private void festmenyListaFeltoltes() {
        btnFestmenyTorles.setDisable(true);
        btnFestmenyModositas.setDisable(true);
        try {
            List<Festmeny> festmenyList = FestmenyApi.get();
            tableViewFestmeny.getItems().clear();
            for (Festmeny festmeny : festmenyList) {
                tableViewFestmeny.getItems().add(festmeny);
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    private void szoborListaFeltoltes() {
        btnSzoborTorles.setDisable(true);
        btnSzoborModositas.setDisable(true);
        try {
            List<Szobor> szoborList = SzoborApi.get();
            tableViewSzobor.getItems().clear();
            for (Szobor szobor : szoborList) {
                tableViewSzobor.getItems().add(szobor);
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onFestmenyHozzaadasClick(ActionEvent actionEvent) {
        try {
            Controller hozzaadasAblak = ujAblak("festmeny-hozzaadas-view.fxml", "Festmény hozzáadása", 300, 240);
            hozzaadasAblak.getStage().setOnCloseRequest(event -> festmenyListaFeltoltes());
            hozzaadasAblak.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onFestmenyTorlesClick(ActionEvent actionEvent) {
        Festmeny torlendo = tableViewFestmeny.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos törölni szeretné a(z) " + torlendo.getTitle() + " című festményt?")) {
            return;
        }
        try {
            boolean siker = FestmenyApi.delete(torlendo.getId());
            alert(siker ? "Sikeres törlés!" : "Sikertelen törlés!");
            festmenyListaFeltoltes();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onFestmenyModositasClick(ActionEvent actionEvent) {
        Festmeny selectedItem = tableViewFestmeny.getSelectionModel().getSelectedItem();
        try {
            FestmenyModositasController modositasAblak = (FestmenyModositasController) ujAblak("festmeny-modositas-view.fxml", "Festmény módosítása", 300, 240);
            modositasAblak.setModositando(selectedItem);
            modositasAblak.getStage().setOnHiding(event -> festmenyListaFeltoltes());
            modositasAblak.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onSzoborHozzaadasClick(ActionEvent actionEvent) {
        try {
            Controller hozzaadasAblak = ujAblak("szobor-hozzaadas-view.fxml", "Szobor hozzáadása", 300, 240);
            hozzaadasAblak.getStage().setOnCloseRequest(event -> szoborListaFeltoltes());
            hozzaadasAblak.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onSzoborModositasClick(ActionEvent actionEvent) {
        Szobor selectedItem = tableViewSzobor.getSelectionModel().getSelectedItem();
        try {
            SzoborModositasController modositasAblak = (SzoborModositasController) ujAblak("szobor-modositas-view.fxml", "Szobor módosítása", 300, 240);
            modositasAblak.setModositando(selectedItem);
            modositasAblak.getStage().setOnHiding(event -> szoborListaFeltoltes());
            modositasAblak.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onSzoborTorlesClick(ActionEvent actionEvent) {
        Szobor torlendo = tableViewSzobor.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos törölni szeretné " + torlendo.getPerson() + " mintázott szobrot?")) {
            return;
        }
        try {
            boolean siker = SzoborApi.delete(torlendo.getId());
            alert(siker ? "Sikeres törlés!" : "Sikertelen törlés!");
            szoborListaFeltoltes();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onSzoborSelected(Event event) {
        int selectedIndex = tableViewSzobor.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            btnSzoborTorles.setDisable(false);
            btnSzoborModositas.setDisable(false);
        }
    }

    @FXML
    public void onFestmenySelected(Event event) {
        int selectedIndex = tableViewFestmeny.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            btnFestmenyTorles.setDisable(false);
            btnFestmenyModositas.setDisable(false);
        }
    }
}