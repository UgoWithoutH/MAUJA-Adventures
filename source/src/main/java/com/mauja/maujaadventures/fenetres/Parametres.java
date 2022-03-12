package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.jeu.Jeu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import vues.navigation.Navigateur;

public class Parametres {
    private Navigateur navigateur;
    private Jeu jeu;

    @FXML
    private GridPane paramPane;
    @FXML
    private TextField zoneText;


    public Parametres(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;
    }

    @FXML
    public void initialize() {
        Stage stage = navigateur.getStage();
        stage.widthProperty().addListener((listener) -> {
            paramPane.setPrefSize(stage.getWidth() * 0.70, stage.getHeight() * 0.70);
        });
        paramPane.setMaxSize(stage.getWidth() * 0.70, stage.getHeight() * 0.70);
        zoneText.textProperty().bindBidirectional(jeu.getTableauDeJeu().getOptions().paramProperty(), new NumberStringConverter());
    }

    @FXML
    public void retour(ActionEvent boutonRetour) {
        navigateur.faireDemiTour();
    }
}
