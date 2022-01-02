package com.mauja.maujaadventures.entrees;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Boutons {
    private Scene scene;

    public Boutons(Scene scene) throws IllegalArgumentException {
        if (scene == null) {
            throw new IllegalArgumentException("La scene passée en paramètre pour la récupération des touches ne "
                    + "peut pas être nulle.");
        }
        this.scene = scene;
    }


    public ArrayList<String> lecture(){
        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    /**
                     * Lorsque l'on appuie sur une touche cette méthode est appelé et on le, rajoute dans la liste
                     *
                     * @param e Correspond a l'événement une touche est appuyé
                     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
                     */
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        if ( !input.contains(code))
                            input.add(code);
                    }
                });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            /**
             * Lorsque l'on appuie sur une touche cette méthode est appelé et on le, rajoute dans la liste
             *
             * @param e Correspond a l'événement une touche est appuyé
             * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
             */
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });
    return input;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
