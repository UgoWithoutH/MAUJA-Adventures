package vues;

import com.mauja.maujaadventures.utilitaires.RecuperateurRessources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Navigateur {

    private Stage myStage;
    private Map<String, Map<URL, Scene>> catalogueScenes = new HashMap<>();
    private RecuperateurRessources recuperateurRessources = new RecuperateurRessources();
    private static final long WIDTH = 964;
    private static final long HEIGHT = 650;

    public Navigateur(Stage myStage) {
        if (myStage == null) throw new IllegalArgumentException("Le stage est null");
        this.myStage = myStage;

        var list = recuperateurRessources.getRessourcesString();
        for (Map.Entry<String, URL> element : list.entrySet()) {
            Map<URL, Scene> map = new HashMap<>();
            map.put(element.getValue(), null);
            catalogueScenes.put(element.getKey(), map);
        }
    }

    public Stage getMyStage() {
        return myStage;
    }

    public void naviguerVers(String nom, Object controlleur) {
        if (!catalogueScenes.containsKey(nom)) throw new IllegalArgumentException("Ce nom de fichier n'existe pas");

        else {
            var map = catalogueScenes.get(nom);
            var entry = map.entrySet();
            for (var element : entry) {
                if (element.getValue() == null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(element.getKey());
                    if (controlleur != null) {
                        try {
                            fxmlLoader.setController(controlleur);
                            Scene scene = new Scene(new Pane());
                            myStage.setScene(scene);
                            scene.setRoot(fxmlLoader.load());
                            var mapUrl = new HashMap<URL, Scene>();
                            mapUrl.put(element.getKey(), scene);
                            catalogueScenes.put(nom, mapUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    myStage.setScene(element.getValue());
                }
            }
        }
    }
}