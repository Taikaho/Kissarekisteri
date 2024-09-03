package kissala;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kissalaMain.Kissala;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Hallitsee LuoKissala.fxml tiedostoa ja välittää Kissalarekisteri luokalle uuden kissalan tiedot luomista varten.
 */
public class LuoKissalaController implements ModalControllerInterface <Kissala>, Initializable {
    @FXML private TextField InputPaikkakunta;
    @FXML private Button btnLuoKissala;
    @FXML private Button btnPeru;
    @FXML private TextField editKissalanID;
    @FXML private TextField editKasvattajanEtu;
    @FXML private TextField editKasvattajanSuku;
    @FXML private TextField editKissalanNimi;
    @FXML private TextField editOsoite;
    @FXML private TextField editPostinumero;
    @FXML private TextField editPaikkakunta;
    @FXML private Label labelVirhe;
    @FXML private GridPane gridKissala;
    @FXML
    private void peru() {}
    private KissalaGUIController kissalaGUIController;
    private Kissala kissalakohdalla;
    private TextField[] edits;

    private static Kissala apukissala = new Kissala();
    private int kentta = 0;

    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }


    public void handleLuoKissala(){
        if (kissalakohdalla != null && kissalakohdalla.getNimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }


    @Override
    public Kissala getResult() {
        return kissalakohdalla;
    }


    /**
     * Saa parametrina kysykissa metodin, eli Modal controlleri palauttaman kissan. Kutsuu naytaKissa metodia.
     *
     * @param kissala
     */
    @Override
    public void setDefault(Kissala kissala) {
        kissalakohdalla = kissala;
        naytaKissala(edits, kissalakohdalla);
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


    /**
     * Käsittelijä peruuta napille
     */
    public void handleCancel() {
        kissalakohdalla = null;
        ModalController.closeStage(labelVirhe);
    }


    /**
     * Alustetaan tetstikentät ja niiden tapahtumaseuraajat
     */
    private void alusta() {
        edits = new TextField[]{editKissalanID, editKasvattajanEtu, editKasvattajanSuku, editKissalanNimi, editOsoite, editPostinumero, editPaikkakunta};

        editKissalanID.setOnKeyReleased(e -> kasitteleMuutosKissalaan(0, editKissalanID));
        editKissalanNimi.setOnKeyReleased(e -> kasitteleMuutosKissalaan(1, editKissalanNimi));
        editKasvattajanEtu.setOnKeyReleased(e -> kasitteleMuutosKissalaan(2, editKasvattajanEtu));
        editKasvattajanSuku.setOnKeyReleased(e -> kasitteleMuutosKissalaan(3, editKasvattajanSuku));
        editOsoite.setOnKeyReleased(e -> kasitteleMuutosKissalaan(4, editOsoite));
        editPostinumero.setOnKeyReleased(e -> kasitteleMuutosKissalaan(5, editPostinumero));
        editPaikkakunta.setOnKeyReleased(e -> kasitteleMuutosKissalaan(6, editPaikkakunta));
    }


    /**
     * Käsitellään muutos kissalaan
     *
     * @param k   mikä kenttä muuttui
     * @param edit mikä tekstikenttä
     */
    private void kasitteleMuutosKissalaan(int k, TextField edit) {
        if (kissalakohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = kissalakohdalla.aseta(k, s);
        if (virhe == null) {
        }
    }


    /**
     * Haetaan tekstikentän id
     */
    public static int getFieldId(Object obj, int oletus) {
        if (!(obj instanceof Node)) return oletus;
        Node node = (Node) obj;
        return Mjonot.erotaInt(node.getId().substring(1), oletus);
    }


    /**
     * Luodaan GridPaneen jäsenen tiedot
     *
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
     * Näytetään kissalan tiedot tekstikenttiin
     *
     * @param edits   taulukko tekstikentistä
     * @param kissala näytettävä kissala
     */
    public static void naytaKissala(TextField[] edits, Kissala kissala) {
        if (kissala == null) return;
        edits[0].setText(Integer.toString(kissala.getID()));
        edits[1].setText(kissala.getKissalanNimi());
        edits[2].setText(kissala.getKasvattajanNimi());
        edits[3].setText(kissala.getKasvattajanSukunimi());
        edits[4].setText(kissala.getOsoite());
        edits[5].setText(Integer.toString(kissala.getPostinumero()));
        edits[6].setText(kissala.getPaikkakunta());
    }

    /**
     * Luodaan jäsenen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * TODO: korjattava toimimaan
     *
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus        mitä dataan näytetään oletuksena
     * @param kentta        mikä kenttä saa fokuksen kun näytetään
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Kissala kysyKissala(Stage modalityStage, Kissala oletus, int kentta) {
        return ModalController.<Kissala, LuoKissalaController>showModal(
                LuoKissalaController.class.getResource("/kissala/LuoKissala.fxml"),
                "Kissala",
                modalityStage, oletus,
                ctrl -> ctrl.setKentta(kentta)
        );
    }


    /**
     * Asettaa kissan tiedot
     */
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }

}
