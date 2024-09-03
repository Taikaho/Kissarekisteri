package kissala;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kissalaMain.Kissa;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Hallitsee kissaMuokaus.fxml tiedostoa ja välittää Kissalarekisteri luokalle halutut muutokset Kissalan tietoihin.
 */
public class KissaMuokkausController implements ModalControllerInterface <Kissa>, Initializable {

    @FXML
    private TextField editID;
    @FXML
    private TextField editKissalanID;
    @FXML
    private TextField editRekisterinumero;
    @FXML
    private TextField editNimi;
    @FXML
    private TextField editKutsumanimi;
    @FXML
    private TextField editRotu;
    @FXML
    private TextField editVeriryhma;
    @FXML
    private TextField editKotiosoite;
    @FXML
    private TextField editPostinumero;
    @FXML
    private TextField editSyntymäaika;
    @FXML
    private TextField editIsä;
    @FXML
    private TextField editÄiti;
    @FXML
    private TextField editSijoitettu;
    @FXML
    private TextField editPennut;
    @FXML
    private TextField editFIV;
    @FXML
    private TextField editFeLV;
    @FXML
    private Label labelVirhe;
    @FXML
    private ScrollPane panelKissa;
    @FXML
    private GridPane gridKissa;

    private Kissa kissaKohdalla;
    private TextField[] edits;

    private static Kissa apukisu = new Kissa();
    private int kentta = 0;


    /**
     * Käsitellään virheen näyttäminen
     * @param virhe virheen teksti
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


    @Override
    public Kissa getResult() {
        return kissaKohdalla;
    }

    /**
     * Saa parametrina kysykissa metodin, eli Modal controlleri palauttaman kissan. Kutsuu naytaKissa metodia.
     * @param kissa
     */
    @Override
    public void setDefault(Kissa kissa) {
        kissaKohdalla = kissa;
        naytaKissa(edits, kissaKohdalla);
    }


    @Override
    public void handleShown() {
        if (this.kentta < 0) this.kentta = 0;
        if (this.kentta > 16) this.kentta = 16;
        edits[kentta].requestFocus();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alusta();
    }

    public void handleOK() {
        if (kissaKohdalla != null && kissaKohdalla.getNimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        if (kissaKohdalla != null && kissaKohdalla.getKutsumanimi().trim().equals("")) {
            naytaVirhe("Kutsumanimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }


    /**
     * Käsitellään peruutus painike
     */
    public void handleCancel() {
        kissaKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }


    /**
     * Alustetaan tekstikentät
     */
    private void alusta() {
        edits = new TextField[]{editID, editKissalanID, editRekisterinumero, editNimi, editKutsumanimi, editRotu, editVeriryhma, editKotiosoite,
                editPostinumero, editSyntymäaika, editIsä, editÄiti, editSijoitettu, editPennut, editFIV, editFeLV};

        editID.setOnKeyReleased(e -> kasitteleMuutosKissaan(0, editID));
        editKissalanID.setOnKeyReleased(e -> kasitteleMuutosKissaan(1, editKissalanID));
        editRekisterinumero.setOnKeyReleased(e -> kasitteleMuutosKissaan(2, editRekisterinumero));
        editNimi.setOnKeyReleased(e -> kasitteleMuutosKissaan(3, editNimi));
        editKutsumanimi.setOnKeyReleased(e -> kasitteleMuutosKissaan(4, editKutsumanimi));
        editRotu.setOnKeyReleased(e -> kasitteleMuutosKissaan(5, editRotu));
        editVeriryhma.setOnKeyReleased(e -> kasitteleMuutosKissaan(6, editVeriryhma));
        editKotiosoite.setOnKeyReleased(e -> kasitteleMuutosKissaan(7, editKotiosoite));
        editPostinumero.setOnKeyReleased(e -> kasitteleMuutosKissaan(8, editPostinumero));
        editSyntymäaika.setOnKeyReleased(e -> kasitteleMuutosKissaan(9, editSyntymäaika));
        editIsä.setOnKeyReleased(e -> kasitteleMuutosKissaan(10, editIsä));
        editÄiti.setOnKeyReleased(e -> kasitteleMuutosKissaan(11, editÄiti));
        editSijoitettu.setOnKeyReleased(e -> kasitteleMuutosKissaan(12, editSijoitettu));
        editPennut.setOnKeyReleased(e -> kasitteleMuutosKissaan(13, editPennut));
        editFIV.setOnKeyReleased(e -> kasitteleMuutosKissaan(14, editFIV));
        editFeLV.setOnKeyReleased(e -> kasitteleMuutosKissaan(15, editFeLV));
    }


    /**
     * Käsitellään muutos kissan tietoihin.
     *
     * @param k    kentän indeksi
     * @param edit muuttunut tekstikenttä
     */
    private void kasitteleMuutosKissaan(int k, TextField edit) {
        if (kissaKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = kissaKohdalla.aseta(k, s);
        if (virhe == null) {
            naytaVirhe(virhe);
        } else {
            naytaVirhe(virhe);
        }
    }

    public static int getFieldId(Object obj, int oletus) {
        if (!(obj instanceof Node)) return oletus;
        Node node = (Node) obj;
        return Mjonot.erotaInt(node.getId().substring(1), oletus);
    }


    /**
     * Luodaan GridPaneen jäsenen tiedot
     *
     * @param gridKissa mihin tiedot luodaan
     * @return luodut tekstikentät
     */
    public static TextField[] luoKentat(GridPane gridKissa) {
        gridKissa.getChildren().clear();
        TextField[] edits = new TextField[apukisu.getKenttia()];

        for (int i = 0, k = apukisu.ekaKentta(); k < apukisu.getKenttia(); k++, i++) {
            Label label = new Label(apukisu.getKysymys(k));
            gridKissa.add(label, 0, i);
            TextField edit = new TextField();
            edits[k] = edit;
            edit.setId("e" + k);
            gridKissa.add(edit, 1, i);
        }
        return edits;
    }


    /**
     * Näytetään kissan tiedot tekstikenttiin
     *
     * @param edits tekstikentät
     * @param kissa näytettävä kissa
     */
    public static void naytaKissa(TextField[] edits, Kissa kissa) {
        if (kissa == null) return;
        edits[0].setText(Integer.toString(kissa.getID()));
        edits[1].setText(Integer.toString(kissa.getKissalanID()));
        edits[2].setText(Integer.toString(kissa.getRekisterinumero()));
        edits[3].setText(kissa.getNimi());
        edits[4].setText(kissa.getKutsumanimi());
        edits[5].setText(kissa.getRotu());
        edits[6].setText(kissa.getVeriryhmä());
        edits[7].setText(kissa.getKotiosoite());
        edits[8].setText(Integer.toString(kissa.getPostinumero()));
        edits[9].setText(kissa.getSyntymäaika());
        edits[10].setText(kissa.getIsä());
        edits[11].setText(kissa.getÄiti());
        edits[12].setText(kissa.getSijoitettu());
        edits[13].setText(kissa.getPennut());
        edits[14].setText(kissa.getFIV());
        edits[15].setText(kissa.getFeLV());
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
    public static Kissa kysyKissa(Stage modalityStage, Kissa oletus, int kentta) {
        return ModalController.<Kissa, KissaMuokkausController>showModal(
                KissaMuokkausController.class.getResource("kissaMuokkaus.fxml"),
                "Kissa",
                modalityStage, oletus,
                ctrl -> ctrl.setKentta(kentta)
        );
    }


    /**
     * Asettaa kissan tiedot
     */
    public void setKentta(int kentta) {
        this.kentta = kentta;
    }

}
