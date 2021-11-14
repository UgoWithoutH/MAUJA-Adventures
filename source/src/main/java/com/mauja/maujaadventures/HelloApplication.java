package com.mauja.maujaadventures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("carte2.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Scene scene = new Scene(root);
        URL imgURl= getClass().getResource("carte2.png");
        Image carte = new Image(imgURl.toExternalForm());
        ImageView imageView= new ImageView(carte);
        //imageView.setX(500);
        imageView.setFitWidth(1600);
        imageView.setFitHeight(1600);
        Pane carteA = new Pane();
        carteA.getChildren().setAll(imageView);
        Scene scene = new Scene(carteA, 320, 240);
        stage.setTitle("Carte");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}