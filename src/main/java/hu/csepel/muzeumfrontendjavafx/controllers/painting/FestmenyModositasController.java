package hu.csepel.muzeumfrontendjavafx.controllers.painting;

import hu.csepel.muzeumfrontendjavafx.Controller;
import hu.csepel.muzeumfrontendjavafx.api.FestmenyApi;
import hu.csepel.muzeumfrontendjavafx.classes.Festmeny;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FestmenyModositasController extends Controller {
    @FXML
    private TextField inputCim;
    @FXML
    private CheckBox inputKiallitva;
    @FXML
    private Label lblEvHiba;
    @FXML
    private Label lblCimHiba;
    @FXML
    private Spinner<Integer> inputEv;

    private Festmeny modositando;

    public Festmeny getModositando() {
        return modositando;
    }

    public void setModositando(Festmeny modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    public void ertekekBeallitasa() {
        inputCim.setText(modositando.getTitle());
        inputKiallitva.setSelected(modositando.getOn_display());
        inputEv.getValueFactory().setValue(modositando.getYear());
    }

    @FXML
    public void onModositasClick(ActionEvent actionEvent) {
        String cim = inputCim.getText();
        if (cim.isEmpty()) {
            lblCimHiba.setText("A cím megadása kötelező!");
            return;
        }
        lblCimHiba.setText("");

        boolean kiallitva = inputKiallitva.isSelected();

        int ev = 0;
        try {
            ev = inputEv.getValue();
        } catch (NullPointerException e) {
            lblEvHiba.setText("Az év megaádsa kötelező!");
            return;
        } catch (Exception e) {
            lblEvHiba.setText("Az év 1 és 2022 közötti lehet!");
            return;
        }

        if (ev < 1 || ev > 2022) {
            lblEvHiba.setText("Az év 1 és 2022 közötti lehet!");
            return;
        }
        lblEvHiba.setText("");

        modositando.setTitle(cim);
        modositando.setOn_display(kiallitva);
        modositando.setYear(ev);

        try {
            Festmeny modositandoFestmeny = FestmenyApi.put(modositando, modositando.getId());
            if (modositandoFestmeny != null) {
                alertWait("Sikeres módosítás!");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás!");
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}
