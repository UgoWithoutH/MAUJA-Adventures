package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import vues.MainMenu;
import vues.Navigateur;

import java.util.*;


public class FenetrePrincipale extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigateur navigateur = new Navigateur(stage);
        navigateur.mainMenu();
        stage.setWidth(964);
        stage.setHeight(650);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}