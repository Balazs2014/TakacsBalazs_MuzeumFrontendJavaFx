package hu.csepel.muzeumfrontendjavafx.controllers.statue;

import hu.csepel.muzeumfrontendjavafx.Controller;
import hu.csepel.muzeumfrontendjavafx.api.SzoborApi;
import hu.csepel.muzeumfrontendjavafx.classes.Szobor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SzoborHozzaadasController extends Controller {
    @FXML
    private Label lblSzemelyHiba;
    @FXML
    private Label lblArHiba;
    @FXML
    private TextField inputSzemely;
    @FXML
    private Spinner<Integer> inputMagassag;
    @FXML
    private Label lblMagassagHiba;
    @FXML
    private Spinner<Integer> inputAr;

    @FXML
    public void onHozzaadasClick(ActionEvent actionEvent) {
        String szemely = inputSzemely.getText();
        if (szemely.isEmpty()) {
            lblSzemelyHiba.setText("A személy megadása kötelező!");
            return;
        } else if (szemely.length() < 5) {
            lblSzemelyHiba.setText("Minimum 5 karakter!");
            return;
        }
        lblSzemelyHiba.setText("");

        int magassag = 0;
        try {
            magassag = inputMagassag.getValue();
        } catch (NullPointerException e) {
            lblMagassagHiba.setText("A magasság megaádsa kötelező!");
            return;
        } catch (Exception e) {
            lblMagassagHiba.setText("A magasság 15 és 250 cm között lehet!");
            return;
        }

        if (magassag < 15 || magassag > 250) {
            lblMagassagHiba.setText("A magasság 15 és 250 cm között lehet!");
            return;
        }
        lblMagassagHiba.setText("");

        int ar = 0;
        try {
            ar = inputAr.getValue();
        } catch (NullPointerException e) {
            lblArHiba.setText("Az ár megadása kötelező!");
            return;
        } catch (Exception e) {
            lblArHiba.setText("Az ár 1000 és 500000 között lehet!");
            return;
        }

        if (ar < 1000 || ar > 500000) {
            lblArHiba.setText("Az ár 1000 és 500000 között lehet!");

            return;
        }
        lblArHiba.setText("");

        try {
            Szobor uj = new Szobor(0, szemely, magassag, ar);
            Szobor letrehozott = SzoborApi.post(uj);
            if (letrehozott != null) {
                alert("Sikeres hozzáadás!");
                inputSzemely.setText("");
                inputMagassag.getValueFactory().setValue(50);
                inputAr.getValueFactory().setValue(10000);
            } else {
                alert("Sikertelen hozzáadás!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
