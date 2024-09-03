package kissala;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Hallinnoin aloitusikkunaa (Avaa.fxml) tiedostoa. Aloitusikkunassa annetaan kissalan nimi ja tämä merkkijono lähetetään tarkistettavaksi KissalaMain luokalle tarkistusta varten.
 * Jos kissalan nimi on entuudestaan tallennettu tietokantaan, se ladataan käytettäväksi ohjelmassa. Jos nimeä ei löydy, niin kysytään, että luodaanko uusi kissala.
 */

public class KissalanNimiController implements ModalControllerInterface<String> {

    @FXML
    private TextField textVastaus;
    String vastaus;

    @FXML
    private void avaaKissala() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }

    @FXML
    private void peru() {
        ModalController.closeStage(textVastaus);
    }

    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                KissalanNimiController.class.getResource("Avaa.fxml"), "Kissala",
                modalityStage, oletus
        );
    }

    @Override
    public String getResult() {
        return vastaus;
    }

    @Override
    public void setDefault(String s) {
        textVastaus.setText("Kissalan nimi");
    }

    @Override
    public void handleShown() {
        textVastaus.requestFocus();
    }
}
