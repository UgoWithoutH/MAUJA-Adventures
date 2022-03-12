package vues.navigation;

import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.utilitaires.RecuperateurRessources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Navigateur {
    private Map<Fenetre, URL> vues;
    private Stack<Scene> laPileDeScenes;
    private Scene sceneCourante;
    private Stage stage;

    public Navigateur(Stage stage) throws IllegalArgumentException {
        if (stage == null) {
            throw new IllegalArgumentException("Le stage donné en paramètre ne peut pas être null.");
        }
        this.stage = stage;
        Ressources ressources = Ressources.getInstance();
        vues = ressources.getLesVues();
        laPileDeScenes = new Stack<>();
    }

    public Stage getStage() {
        return stage;
    }

    public void naviguerVers(Fenetre fenetre, Object controleur) {
        Scene scene = null;
        FXMLLoader chargeur = new FXMLLoader();
        if (!vues.containsKey(fenetre)) {
            throw new IllegalArgumentException("La fenetre spécifiée ne possède pas de fichier FXML");
        }

        chargeur.setController(controleur);
        chargeur.setLocation(vues.get(fenetre));

        try {
            Parent racine = chargeur.load();
            scene = new Scene(racine);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        naviguerVers(scene);
    }


    public void naviguerVers(Fenetre fenetre) throws IllegalArgumentException {
        Scene scene = null;
        if (!vues.containsKey(fenetre)) {
            throw new IllegalArgumentException("La fenetre spécifiée ne possède pas de fichier FXML");
        }

        try {
            Parent racine = FXMLLoader.load(vues.get(fenetre));
            scene = new Scene(racine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        naviguerVers(scene);
    }

    public void naviguerVers(Scene scene) {
        if (scene != null) {
            laPileDeScenes.add(scene);
            miseAJourScene();
        }
    }

    public void faireDemiTour() {
        if (laPileDeScenes.size() != 0) {
            laPileDeScenes.pop();
            miseAJourScene();
        }
    }

    public Scene getSceneCourante() {
        return sceneCourante;
    }

    private void miseAJourScene() {
        sceneCourante = laPileDeScenes.peek();
        stage.setScene(sceneCourante);
    }
}