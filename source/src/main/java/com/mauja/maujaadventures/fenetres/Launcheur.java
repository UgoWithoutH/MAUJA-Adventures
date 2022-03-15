package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;

import vues.navigation.Fenetre;
import vues.navigation.Navigateur;


public class Launcheur extends Application {
    private Jeu jeu;

    @Override
    public void init() throws Exception {
        super.init();
        this.jeu = new Jeu(new GestionnaireDeTouchesFX(null));
    }

    @Override
    public void start(Stage stage) {
        Navigateur navigateur = new Navigateur(stage);
        navigateur.naviguerVers(Fenetre.MENU_PRINCIPAL, new MenuPrincipal(navigateur, jeu));
        stage.setWidth(964);
        stage.setHeight(650);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        jeu.arreterJeu();
    }

    public static void main(String[] args) {
        launch();
    }
}