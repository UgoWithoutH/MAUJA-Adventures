package vues.navigation;

import com.mauja.maujaadventures.utilitaires.RecuperateurRessources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Navigateur {

    public static final String menuPause = "MenuPause.fxml";
    public static final String menuPrincipal = "MenuPrincipal.fxml";
    public static final String parametres = "Parametres.fxml";
    public static final String partie = "Partie.fxml";
    private Stage myStage;
    private Map<String, Map<URL, List<Object>>> catalogueScenes = new HashMap<>();
    private RecuperateurRessources recuperateurRessources = new RecuperateurRessources();
    private Scene sceneCourante;
    private Stack<Scene> laPileDeScenes;

    public Navigateur(Stage myStage) throws IllegalArgumentException {
        if (myStage == null) throw new IllegalArgumentException("Le stage est null");
        this.myStage = myStage;
        laPileDeScenes = new Stack<>();

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
                            empilerScene(scene);
                            myStage.setScene(scene);
                            var mapUrl = new HashMap<URL, List<Object>>();
                            var list = new ArrayList<>();
                            list.add(scene);
                            list.add(controlleur);
                            mapUrl.put(element.getKey(), list);
                            catalogueScenes.put(nom, mapUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    empilerScene((Scene) element.getValue().get(0));
                    return element.getValue().get(1);
                }
            }
        }
        return controlleur;
    }

    public void faireDemiTour() {
        if (laPileDeScenes.size() != 0) {
            laPileDeScenes.pop();
            miseAJourStage(laPileDeScenes.peek());
        }
    }

    public void empilerScene(Scene scene){
        laPileDeScenes.add(scene);
        miseAJourStage(scene);
    }

    public void miseAJourStage(Scene scene){
        sceneCourante = scene;
        myStage.setScene(sceneCourante);
    }
}