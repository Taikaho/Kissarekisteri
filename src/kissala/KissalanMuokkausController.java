package kissala;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kissalaMain.Kissala;
import kissalaMain.Kissalarekisteri;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Hallitsee kissalanMuokaus.fxml tiedostoa ja välittää Kissalarekisteri luokalle halutut muutokset Kissalan tietoihin.
 */

public class KissalanMuokkausController implements ModalControllerInterface<Kissala>, Initializable {

    @FXML private TextField tfId;

    @FXML private TextField tfKasvattajanEnimi;

    @FXML private TextField tfKasvattajanSnimi;

    @FXML private TextField tfKatuosoite;

    @FXML private TextField tfKissalanNimi;

    @FXML private TextField tfPaikkakunta;

    @FXML private TextField tfPostinumero;
    @FXML private GridPane gridKissala;
    private Kissalarekisteri kissalarekisteri;
    @FXML
    private Label labelVirhe;
    private int kentta = 0;
    private TextField[] edits;
    private Kissala valittuKissala;
    private static Kissala apukissala = new Kissala();
    private Kissala paivitettyKissala;


    /**
     * Konstruktori
     */
    public KissalanMuokkausController() {}


    @Override
    public Kissala getResult() {
        return paivitettyKissala;
    }


    @Override
    public void setDefault(Kissala kissala) {
        this.valittuKissala = kissala;
        naytaKissala();
    }


    @Override
    public void handleShown() {
        if (this.kentta < 0) this.kentta = 0;
        if (this.kentta > 7) this.kentta = 7;
        edits[kentta].requestFocus();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alusta();
    }


    @FXML private void handleOK (ActionEvent event) {
        //valittuKissala = getResult();
        if (valittuKissala != null && valittuKissala.getNimi().trim().isEmpty()) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        paivitettyKissala = valittuKissala;
        ModalController.closeStage(labelVirhe);
    }


    @FXML
    private void handleCancel(ActionEvent event) {
        ModalController.closeStage(labelVirhe);
    }


    /**
     * Alustetaan tekstikentät ja niiden tapahtumaseuraajat
     */
    private void alusta() {
        edits = new TextField[] {tfId, tfKasvattajanEnimi, tfKasvattajanSnimi, tfKatuosoite, tfKissalanNimi, tfPaikkakunta, tfPostinumero};

        tfId.setOnKeyReleased(e -> kasitteleMuutosKissalaan(0, tfId));
        tfKissalanNimi.setOnKeyReleased(e -> kasitteleMuutosKissalaan(1, tfKissalanNimi));
        tfKasvattajanEnimi.setOnKeyReleased(e -> kasitteleMuutosKissalaan(2, tfKasvattajanEnimi));
        tfKasvattajanSnimi.setOnKeyReleased(e -> kasitteleMuutosKissalaan(3, tfKasvattajanSnimi));
        tfKatuosoite.setOnKeyReleased(e -> kasitteleMuutosKissalaan(4, tfKatuosoite));
        tfPaikkakunta.setOnKeyReleased(e -> kasitteleMuutosKissalaan(5, tfPaikkakunta));
        tfPostinumero.setOnKeyReleased(e -> kasitteleMuutosKissalaan(6, tfPostinumero));
    }


    /**
     * Käsitellään muutos kissalaan
     * @param k muutettava kenttä
     * @param edit muutettava tekstikenttä
     */
    private void kasitteleMuutosKissalaan(int k, TextField edit) {
        if (valittuKissala == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = valittuKissala.aseta(k, s);
       if (virhe == null) {
           naytaVirhe(virhe);
       } else {
           naytaVirhe(virhe);
       }
    }


    /**
     * Näyttää virheen
     * @param virhe näytettävä virhe
     */
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }


    /**
     * Palauttaa kentän id:n numeron
     * @param obj tutkittava kenttä
     * @param oletus oletusarvo
     * @return kentän id:n numero
     */
    public static int getFieldId(Object obj, int oletus) {
        if (!(obj instanceof Node)) return oletus;
        Node node = (Node) obj;
        return Mjonot.erotaInt(node.getId().substring(1), oletus);
    }

    /**
     * Luodaan GridPaneen jäsenen tiedot
     * @param gridKissala mihin tiedot luodaan
     * @return luodut tekstikentät
     */
    public static TextField[] luoKentat(GridPane gridKissala) {
        gridKissala.getChildren().clear();
        TextField[] edits = new TextField[apukissala.getKenttia()];

        for (int i = 0, k = apukissala.ekaKentta(); k < apukissala.getKenttia(); k++, i++) {
            Label label = new Label(apukissala.getKysymys(k));
            gridKissala.add(label, 0, i);
            TextField edit = new TextField();
            edits[k] = edit;
            edit.setId("e" + k);
            gridKissala.add(edit, 1, i);
        }
        return edits;
    }


    /**
     * Asettaa valitun kissalan tiedot tekstikenttiin
     */
    private void naytaKissala() {
        if (valittuKissala != null) {
            tfId.setText(Integer.toString(valittuKissala.getId()));
            tfKasvattajanEnimi.setText(valittuKissala.getKasvattajanEnimi());
            tfKasvattajanSnimi.setText(valittuKissala.getKasvattajanSnimi());
            tfKatuosoite.setText(valittuKissala.getKatuosoite());
            tfKissalanNimi.setText(valittuKissala.getKissalanNimi());
            tfPaikkakunta.setText(valittuKissala.getPaikkakunta());
            tfPostinumero.setText(Integer.toString(valittuKissala.getPostinumero()));
        }
    }


    /**
     * Näyttää kissalan muokkausikkunan
     * @param modalityStage mille ollaan modaalisia
     * @param oletus mitä dataan näytetään oletuksena
     * @param kentta mikä kenttä saa fokuksen
     * @param kissalarekisteri mihin muutokset tallennetaan
     * @return null jos painetaan cancel, muuten täytetty olio
     */
    public static Kissala kysyKissala(Stage modalityStage, Kissala oletus, int kentta, Kissalarekisteri kissalarekisteri) {
        return ModalController.<Kissala, KissalanMuokkausController>showModal(
                KissalanMuokkausController.class.getResource("kissalanMuokkaus.fxml"),
                "Kissala",
                modalityStage, oletus,
                ctrl -> {
                    ctrl.setKentta(kentta);
                    ctrl.setKissalarekisteri(kissalarekisteri);
                }
        );
    }


    /**
     * Asettaa kissalarekisterin
     * @param kissalarekisteri käytettävä kissalarekisteri
     */
    public void setKissalarekisteri(Kissalarekisteri kissalarekisteri) {
        this.kissalarekisteri = kissalarekisteri;
    }


    /**
     * Asettaa kentän
     * @param kentta käytettävä kenttä
     */
    public void setKentta(int kentta) {
        this.kentta = kentta;
    }
}

