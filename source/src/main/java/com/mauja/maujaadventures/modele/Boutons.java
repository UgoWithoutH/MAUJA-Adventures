package com.mauja.maujaadventures.modele;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Boutons {

    public ArrayList<String> lecture(Scene scene){

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        System.out.println(code);

                        if ( !input.contains(code))
                            input.add(code);
                            System.out.println(input);
                    }
                });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });
    return input;
    }


}
