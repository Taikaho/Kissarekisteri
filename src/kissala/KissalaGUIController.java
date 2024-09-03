package kissala;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import kissalaMain.Kissa;
import kissalaMain.Kissala;
import kissalaMain.Kissalarekisteri;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

import static kissala.KissaMuokkausController.getFieldId;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Hallinnoin pääikkunaa. Näyttää pääikkunassa kissojen tiedot ja välittää painikkeiden toiminnallisuudet eri luokille.
 */
public class KissalaGUIController implements Initializable {
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
    private TextField tfHakukentta;

    @FXML
    private ComboBoxChooser<String> cbKentta;
    @FXML
    private ListChooser<Kissa> chooserKissat;
    @FXML
    private ScrollPane panelKissa;
    @FXML private GridPane gridKissa;

    @FXML private void handleHakuehto() { hae(0);}

    @FXML private void handleUusiKissa() {
        uusiKissa();
    }
    @Override
    public void initialize(URL url, ResourceBundle bundle) {alusta();}
    @FXML private void handleTallenna () throws Exception {kissalarekisteri.tallenna("kissalarekisteri");}

    @FXML private void handleTulosta () {
        Dialogs.showMessageDialog("Tulostus ei toimi vielä");
    }

    @FXML private void handleLopeta () throws Exception {
        kissalarekisteri.tallenna("kissalarekisteri");
        Platform.exit();
    }

    @FXML private void handlePoistaKissa() {
        poistaKissa();
    }

    @FXML private void handleMuokkaaKissa() throws CloneNotSupportedException {muokkaaKissa(kentta);}
    @FXML private void handleMuokkaaKissalaa() {
        muokkaaKissalaa();
    }
    @FXML private void handleTietoja() {Dialogs.showMessageDialog("Tiedot eivät vielä saatavilla");}
    @FXML private void handleApua() {Dialogs.showMessageDialog("Joudut nyt pärjäämään omillasi");}
    @FXML private void handleKissalanTiedot() {Dialogs.showMessageDialog("Kissalan tiedot eivät ole vielä saatavilla");}


    private Kissalarekisteri kissalarekisteri;
    private TextField[] edits;
    private Kissa kissaKohdalla;
    private int kentta = 0;
    private ArrayList<Kissala> kissalat;
    private KissalaDialogController kissalaDialogController;

    private Kissa apuKisu = new Kissa();
    private Kissala valittuKissala;



    /**
     * Alustetaan tietokentät ja tapahtumaseuraaja.
     */
    private void alusta() {
        chooserKissat.clear();
        chooserKissat.addSelectionListener(e -> naytaKissa());
        edits = KissaMuokkausController.luoKentat(gridKissa);
        for (TextField edit: edits)
            if ( edit != null ) {
                edit.setEditable(false);
                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaaKissa(getFieldId(e.getSource(),0)); });
                edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));
            }

        cbKentta.clear();
        for (int k = apuKisu.ekaKentta(); k < apuKisu.getKenttia(); k++)
            cbKentta.add(apuKisu.getKysymys(k), null);
        cbKentta.getSelectionModel().select(0);

    }


    /**
     * Kun kohde on valittu tulostetaan kissan tiedot ulostulovirtaan
     */
    private void naytaKissa() {
        kissaKohdalla = chooserKissat.getSelectedObject();
        if (kissaKohdalla == null) return;
        KissaMuokkausController.naytaKissa(edits, kissaKohdalla);
    }


    /**
     * Valitaan avattava Kissala. Jos Kissalaa ei löydy entuudestaan, niin kysytään halutaanko luoda uusi kissala
     * @return false mikäli kissala on muu kuin oletus. Muussa tapauksessa true.
     */
    public boolean avaa() {
        String kissalanNimi = KissalanNimiController.kysyNimi(null, "Sarellin");
        //if (kissalanNimi != null) return false;

        System.out.println(kissalanNimi);

        // Tarkistetaan onko syötteen mukaista kissalaa olemassa. Jos ei, kysytään, että luodaanko uusi. Jos löytyy, niin
        // kissalareksiteri asettaa kyseisen kissalan käytettäväksi kissalaksi
        kissalat = kissalarekisteri.annaKissalat();
        boolean kissalaLoytyi = false;
        for (int i = 0; i < kissalat.size(); i++) {
            if (Objects.equals(kissalanNimi.toLowerCase(), kissalat.get(i).getKissalanNimi().toLowerCase())) {
                kissalarekisteri.kaytaTataKissalaa(kissalat.get(i));
                kissalaLoytyi = true;
                break;
            }
        }
        if (!kissalaLoytyi) {
            ModalController.showModal(
                    KissalaDialogController.class.getResource("UusiKissalaDialog.fxml"), "Kissala",
                    null, kissalarekisteri
            );
        }
        hae(1);
        return true;
    }


    /**
     * Asetetaan käytettävä kissalarekisteri
     * @param kissalarekisteri
     */
    public void setKissalarekisteri(Kissalarekisteri kissalarekisteri) {
        this.kissalarekisteri = kissalarekisteri;
    }


    /** Haetaan kissan tiedot ja näytetään hakupalkissa
     * @param idNro kissan tunnus
     */
    protected void hae(int idNro) {
        int id = idNro;
        if ( id <= 0 ) {
            Kissa kohdalla = kissaKohdalla;
            if ( kohdalla != null ) id = kohdalla.getID();
        }
        int k = cbKentta.getSelectionModel().getSelectedIndex() + apuKisu.ekaKentta();
        String ehto = tfHakukentta.getText();
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";

        chooserKissat.clear();

        int index = 0;
        Collection<Kissa> kissat;
        try {
            kissat = kissalarekisteri.etsi(ehto, k);
            System.out.println("Löytyi " + kissat.size() + " kissaa");
            int i = 0;
            for (Kissa kissa:kissat) {
                if (kissa.getID() == id) index = i;
                chooserKissat.add(kissa.getNimi(), kissa);
                i++;
            }
        System.out.println("Löytyi " + kissat.size() + " kissaa");
        } catch (Exception ex) {
            Dialogs.showMessageDialog("Kissan hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserKissat.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää jäsenen
    }


    /**
     * Luo uuden kissan, rekisteröi ja lisää kissarekisteriin.
     */
    private void uusiKissa() {
        try {
            Kissa uusi = new Kissa();
            uusi.rekisteroi();
            uusi.setKissalanId(kissalarekisteri.getKaytettavaKissala().getID());
            uusi = KissaMuokkausController.kysyKissa(null, uusi, 5);
            if ( uusi == null ) return;
            kissalarekisteri.lisaa(uusi);
            hae(uusi.getID());
        } catch (Exception e) {
            Dialogs.showMessageDialog("Ongelmia uuden kissan luomisessa \n" + e.getMessage());
            return;
        }
    }


    /**
     * Välitetään valittu kissala KissalanMuokkausControllerille, jossa muokataan kissalan tietoja
     */
    private void muokkaaKissalaa() {
       valittuKissala = kissalarekisteri.getKaytettavaKissala();
       if ( valittuKissala == null ) return;
       try {
           Kissala kissala;
           kissala = KissalanMuokkausController.kysyKissala(null, valittuKissala.clone(), 1, kissalarekisteri);
           if ( kissala == null ) return;
           kissalarekisteri.korvaaTaiLisaa(kissala);
           hae(kissala.getID());
       } catch (CloneNotSupportedException e) {
       } catch (Exception e) {
           Dialogs.showMessageDialog(e.getMessage());
       }
    }


    /**
     * Välitetään valittu kissa KissaMuokkausControllerille, jossa muokataan kissan tietoja.
     * @param k
     */
    private void muokkaaKissa(int k) {
        if ( kissaKohdalla == null ) return;
        try {
            Kissa kissa;
            kissa = KissaMuokkausController.kysyKissa(null, kissaKohdalla.clone(), k);
            if ( kissa == null ) return;
            kissalarekisteri.korvaaTaiLisaa(kissa);
            hae(kissa.getID());
        } catch (CloneNotSupportedException e) {

        } catch (Exception e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }


    /**
     * Poistetaan valittu kissa kissalarekisteristä
     */
    private void poistaKissa() {
        if (kissaKohdalla == null) return;
        if (!Dialogs.showQuestionDialog("Poisto", "Poistetaanko kissa: " + kissaKohdalla.getNimi(), "Kyllä", "Ei")) return;
        kissalarekisteri.poista(kissaKohdalla);
        hae(0);
    }


    /**
     * Lisätään uusi kissala kissalarekisteriin
     * @param kissala
     */
    public void lisääKissala(Kissala kissala) {
        this.kissalat.add(kissala);
    }
}