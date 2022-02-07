package com.mauja.maujaadventures.fenetres;

import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;

import vues.codebehind.MenuPrincipal;
import vues.navigation.Navigateur;


public class FenetrePrincipale extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigateur navigateur = new Navigateur(stage);
        navigateur.naviguerVers("MenuPrincipal.fxml",new MenuPrincipal(navigateur));
        stage.setWidth(964);
        stage.setHeight(650);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}