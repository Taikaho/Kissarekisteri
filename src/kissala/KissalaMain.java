package kissala;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kissalaMain.Kissalarekisteri;

/**
 * @author Toni Taikina-aho
 * @version 15.2.2024
 *
 * Pääohjelma Kissalarekisteri ohjelmaan.
 */
public class KissalaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("paaikkuna.fxml"));
            final Pane root = ldr.load();
            final KissalaGUIController kissalaCtrl = (KissalaGUIController)ldr.getController();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("kissala.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kissala");
            Kissalarekisteri kissalarekisteri = new Kissalarekisteri();
            kissalaCtrl.setKissalarekisteri(kissalarekisteri);
            primaryStage.show();

            if (!kissalaCtrl.avaa()) {
                Platform.exit();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
