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

public class FestmenyHozzaadasController extends Controller {
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

    @FXML
    public void onHozzaadasClick(ActionEvent actionEvent) {
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

        try {
            Festmeny uj = new Festmeny(0, cim, kiallitva, ev);
            Festmeny letrehozott = FestmenyApi.post(uj);
            if (letrehozott != null) {
                alert("Sikeres hozzáadás!");
                inputCim.setText("");
                inputKiallitva.setSelected(false);
                inputEv.getValueFactory().setValue(1000);
            } else {
                alert("Sikertelen hozzáadás!");
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}
