package vues;

import com.mauja.maujaadventures.utilitaires.RecuperateurRessources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Navigateur {

    private Stage myStage;
    private Map<String, Map<URL, List<Object>>> catalogueScenes = new HashMap<>();
    private RecuperateurRessources recuperateurRessources = new RecuperateurRessources();
    private Scene sceneCourante;

    public Navigateur(Stage myStage) {
        if (myStage == null) throw new IllegalArgumentException("Le stage est null");
        this.myStage = myStage;

        var list = recuperateurRessources.getRessourcesString();
        for (Map.Entry<String, URL> element : list.entrySet()) {
            Map<URL, List<Object>> map = new HashMap<>();
            map.put(element.getValue(), null);
            catalogueScenes.put(element.getKey(), map);
        }
    }

    public Stage getMyStage() {
        return myStage;
    }

    public Scene getSceneCourante(){return sceneCourante;}

    public Object naviguerVers(String nom, Object controlleur) {
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
                            Scene scene = new Scene(fxmlLoader.load());
                            myStage.setScene(scene);
                            var mapUrl = new HashMap<URL, List<Object>>();
                            var list = new ArrayList<>();
                            list.add(scene);
                            list.add(controlleur);
                            mapUrl.put(element.getKey(), list);
                            catalogueScenes.put(nom, mapUrl);
                            sceneCourante = scene;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    sceneCourante = (Scene) element.getValue().get(0);
                    myStage.setScene(sceneCourante);
                    return element.getValue().get(1);
                }
            }
        }
        return controlleur;
    }
}