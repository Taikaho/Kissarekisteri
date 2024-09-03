package kissala;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kissalaMain.Kissala;
import kissalaMain.Kissalarekisteri;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Kerää uutta kissalaa varten tiedot FXML tiedostosta ja välittää tiedot Kissalarekisteri luokalle
 */
public class KissalaDialogController implements ModalControllerInterface<Kissalarekisteri> {

    @FXML
    private Button btnEi;

    @FXML
    private Button btnKylla;
    private Kissalarekisteri rekisteri;

    @FXML
    private void handleKylla(ActionEvent event) {
        luoKissala();
        Stage stage = (Stage) btnKylla.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handlePeru(ActionEvent event) {
    }

    @Override
    public Kissalarekisteri getResult() {
        return null;
    }

    @Override
    public void setDefault(Kissalarekisteri kissalarekisteri) {
        this.rekisteri = kissalarekisteri;
    }


    @Override
    public void handleShown() {}


    /**
     * Luodaan ja rekisteröidään uusi kissala
     */
    public void luoKissala() {
        try {
            Kissala uusi = new Kissala();
            uusi = LuoKissalaController.kysyKissala(null, uusi, 1);
            if ( uusi == null ) return;
            uusi.rekisteroi();
            rekisteri.lisaaKissala(uusi);
        } catch (Exception e) {
            Dialogs.showMessageDialog("Ongelmia uuden kissalan luomisessa \n" + e.getMessage());
            return;
        }
    }

}
