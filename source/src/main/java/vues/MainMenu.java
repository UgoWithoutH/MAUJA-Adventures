package vues;

import com.mauja.maujaadventures.Manager;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainMenu{

    @FXML
    private GridPane mainGrid;
    @FXML
    private Button solo;
    @FXML
    private Button param;
    @FXML
    private Button quitter;
    private GridPane paramPane;
    private Navigateur navigateur;
    private Jeu jeu;
    private Manager manager;

    public MainMenu(Navigateur navigateur){
        try {
            this.navigateur = navigateur;
            manager = new Manager();
            jeu = new Jeu(manager.getOptions());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        solo.setPrefHeight(40);
        param.setPrefHeight(40);
        quitter.setPrefHeight(40);
        solo.setMaxWidth(400);
        param.setMaxWidth(400);
        quitter.setMaxWidth(400);

        Stage myStage = navigateur.getMyStage();
        myStage.widthProperty().addListener((listener) -> {
            solo.setPrefWidth(myStage.getWidth()*0.70);
            param.setPrefWidth(myStage.getWidth()*0.70);
            quitter.setPrefWidth(myStage.getWidth()*0.70);
        });
    }

    @FXML
    public void startSolo() {
        Partie partie1 = new Partie(navigateur, jeu);
        Partie partie2 = (Partie) navigateur.naviguerVers("Partie.fxml",partie1);
        if(partie2 == partie1){
            partie2.initialiserVue();
            partie2.start();
        }
        else {
            partie2.restart();
        }
    }

    @FXML
    public void parametres() {
        try {
            if (paramPane == null) {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/Parametres.fxml"));
                fxml.setController(new Parametres(navigateur, jeu));
                paramPane = fxml.load();
                mainGrid.add(paramPane, 0, 0);

            } else if (paramPane.isVisible()) {
                paramPane.setVisible(false);
            } else {
                paramPane.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void quitter(ActionEvent buttonQuitter){
        navigateur.getMyStage().close();
    }
}