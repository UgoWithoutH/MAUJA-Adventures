package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.entrees.Touche;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class ChargeurDeTouchesFX extends ChargeurDeTouches {

    public Map<KeyCode, Touche> recupereTouches(String fichier) throws IllegalArgumentException {
        Map<String, String> lesTouches = super.charge(fichier);
        Map<KeyCode, Touche> lesTouchesFX = new HashMap<>();
        String chaineTouche, chaineToucheFX;

        for (var ensemble : lesTouches.entrySet()) {
            String touche = ensemble.getValue();
            String toucheFX = ensemble.getKey();
            chaineTouche = Enum.valueOf(Touche.class, touche).toString();
            chaineToucheFX = Enum.valueOf(KeyCode.class, toucheFX).toString();
            lesTouchesFX.put(KeyCode.valueOf(chaineToucheFX), Touche.valueOf(chaineTouche));
        }

        return lesTouchesFX;
    }
}
