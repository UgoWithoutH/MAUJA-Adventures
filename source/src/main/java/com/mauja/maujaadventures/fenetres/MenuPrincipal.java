package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.jeu.GestionnaireDeJeu;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.mauja.maujaadventures.utilitaires.Navigateur;

public class MenuPrincipal {
    private Navigateur navigateur;
    private GestionnaireDeJeu gestionnaireDeJeu;

    @FXML
    private GridPane mainGrid;
    @FXML
    private Button solo;
    @FXML
    private Button param;
    @FXML
    private Button quitter;
    @FXML
    private GridPane paramPane;

    public MenuPrincipal(Navigateur navigateur, GestionnaireDeJeu gestionnaireDeJeu) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        if (gestionnaireDeJeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.gestionnaireDeJeu = gestionnaireDeJeu;
    }

    @FXML
    public void initialize() {
        solo.setPrefHeight(40);
        param.setPrefHeight(40);
        quitter.setPrefHeight(40);
        solo.setMaxWidth(400);
        param.setMaxWidth(400);
        quitter.setMaxWidth(400);

        /*Stage myStage = navigateur.getStage();
        myStage.widthProperty().addListener((listener) -> {
            solo.setPrefWidth(myStage.getWidth()*0.70);
            param.setPrefWidth(myStage.getWidth()*0.70);
            quitter.setPrefWidth(myStage.getWidth()*0.70);
        });*/
    }

    @FXML
    public void startSolo() {
        FenetreDeJeu fenetreDeJeu = new FenetreDeJeu(navigateur, gestionnaireDeJeu);
        navigateur.naviguerVers(fenetreDeJeu.getScene());
    }

    @FXML
    public void parametres() {
        navigateur.naviguerVers(Fenetre.PARAMETRES, new Parametres(navigateur, gestionnaireDeJeu));
    }

    @FXML
    public void quitter(ActionEvent bouttonQuitter) {
        if (gestionnaireDeJeu.isLance()) {
            gestionnaireDeJeu.arreterJeu();
        }
        navigateur.getStage().close();
    }
}