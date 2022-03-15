package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.jeu.GestionnaireDeJeu;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.mauja.maujaadventures.utilitaires.Navigateur;

public class MenuPause {
    private Navigateur navigateur;
    private GestionnaireDeJeu gestionnaireDeJeu;

    @FXML
    private VBox content;
    @FXML
    private Button reprendre;
    @FXML
    private GridPane paramPane;
    @FXML
    private StackPane stackpane;
    private FenetreDeJeu fenetreDeJeu;

    public MenuPause(Navigateur navigateur, GestionnaireDeJeu gestionnaireDeJeu, FenetreDeJeu fenetreDeJeu) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        if (gestionnaireDeJeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.fenetreDeJeu = fenetreDeJeu;

        if (gestionnaireDeJeu.isLance()) {
            gestionnaireDeJeu.arreterJeu();
        }
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
        if (paramPane == null) {
            navigateur.naviguerVers(Fenetre.PARAMETRES, new Parametres(navigateur, gestionnaireDeJeu));
        }
        else if(paramPane.isVisible()) {
            paramPane.setVisible(false);
        }
        else {
            paramPane.setVisible(true);
        }
    }

    @FXML
    public void reprendre(ActionEvent bouton) {
        if (!gestionnaireDeJeu.isLance()) {
            gestionnaireDeJeu.lancerJeu();
        }
        navigateur.faireDemiTour();
    }

    @FXML
    public void sauvegarderQuitter(ActionEvent bouton) {
        gestionnaireDeJeu.detacher(fenetreDeJeu);
        gestionnaireDeJeu.arreterJeu();
        navigateur.faireDemiTour(2);
    }
}
