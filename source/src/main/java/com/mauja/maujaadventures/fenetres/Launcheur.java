package com.mauja.maujaadventures.fenetres;

import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;

import vues.navigation.Fenetre;
import vues.navigation.Navigateur;


public class Launcheur extends Application {
    @Override
    public void start(Stage stage) {
        Navigateur navigateur = new Navigateur(stage);
        navigateur.naviguerVers(Fenetre.MENU_PRINCIPAL, new MenuPrincipal(navigateur));
        stage.setWidth(964);
        stage.setHeight(650);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}