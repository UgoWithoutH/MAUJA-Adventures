package com.mauja.maujaadventures;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.fenetres.Fenetre;
import com.mauja.maujaadventures.fenetres.MenuPrincipal;
import com.mauja.maujaadventures.jeu.GestionnaireDeJeu;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.application.Application;

import javafx.stage.Stage;

import com.mauja.maujaadventures.utilitaires.Navigateur;


public class Launcheur extends Application {
    private GestionnaireDeJeu gestionnaireDeJeu;

    @Override
    public void init() throws Exception {
        super.init();
        this.gestionnaireDeJeu = new GestionnaireDeJeu(new GestionnaireDeTouchesFX(null));
    }

    @Override
    public void start(Stage stage) {
        Navigateur navigateur = new Navigateur(stage);
        navigateur.naviguerVers(Fenetre.MENU_PRINCIPAL, new MenuPrincipal(navigateur, gestionnaireDeJeu));
        /*stage.setMaxWidth(964);
        stage.setHeight(650);*/
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        gestionnaireDeJeu.arreterJeu();
    }

    public static void main(String[] args) {
        launch();
    }
}