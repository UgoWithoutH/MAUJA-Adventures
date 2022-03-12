package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.jeu.Jeu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vues.navigation.Fenetre;
import vues.navigation.Navigateur;

import java.io.IOException;

public class MenuPause {
    private Navigateur navigateur;
    private Jeu jeu;

    @FXML
    private VBox content;
    @FXML
    private Button reprendre;
    @FXML
    private GridPane paramPane;
    @FXML
    private StackPane stackpane;

    public MenuPause(Navigateur navigateur, Jeu jeu, FenetreDeJeu fenetreDeJeu) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;
    }

    public void setPane(StackPane stackPane) {
        this.stackpane = stackPane;
    }

    @FXML
    public void initialize() {
        Stage stage = navigateur.getStage();
        stage.widthProperty().addListener((listener) -> {
            content.setPrefSize(stage.getWidth() * 0.70, stage.getHeight() * 0.70);
        });
        content.setMaxSize(stage.getWidth() * 0.70, stage.getHeight() * 0.70);
    }

    @FXML
    public void parametres(ActionEvent bouton) {
        if (paramPane == null){
            navigateur.naviguerVers(Fenetre.PARAMETRES, new Parametres(navigateur, jeu));
        }
        else if(paramPane.isVisible()){
            paramPane.setVisible(false);
        }
        else {
            paramPane.setVisible(true);
        }
    }

    @FXML
    public void reprendre(ActionEvent bouton) {
        content.setVisible(false);
        jeu.setPause(false);
    }

    @FXML
    public void sauvegarderQuitter(ActionEvent bouton) {
        stackpane.setVisible(false);
        navigateur.faireDemiTour();
    }
}
