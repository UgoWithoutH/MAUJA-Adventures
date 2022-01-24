package vues;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigateur {

    private Stage myStage;

    public Navigateur(Stage myStage) {
        this.myStage = myStage;
    }

    public Stage getMyStage() {
        return myStage;
    }

    private Scene scenetest;

    public void mainMenu(){
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            fxml.setController(new MainMenu(this));
            Scene scene = new Scene(fxml.load());
            myStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void partie() {
        if(scenetest == null) {
            try {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/Partie.fxml"));
                fxml.setController(new Partie(this));
                scenetest = new Scene(fxml.load());
                myStage.setScene(scenetest);
                Partie partie = fxml.getController();
                partie.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myStage.setScene(scenetest);
    }
}
