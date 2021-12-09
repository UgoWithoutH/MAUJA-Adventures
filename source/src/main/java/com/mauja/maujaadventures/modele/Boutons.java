package com.mauja.maujaadventures.modele;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Boutons {
    /**
     * Méthode qui récupère la touche que l'utilisateur appuie et renvoie sa liste
     *
     * @param scene Conteneur de tout le contenu de l'affichage
     * @return Liste de chaine de caractère contenant les informations des touches que l'utilisateur appuie
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public ArrayList<String> lecture(Scene scene){

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


}
