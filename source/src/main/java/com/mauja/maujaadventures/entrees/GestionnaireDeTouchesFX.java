package com.mauja.maujaadventures.entrees;

import com.mauja.maujaadventures.chargeurs.ChargeurDeTouchesFX;
import com.mauja.maujaadventures.chargeurs.Ressources;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GestionnaireDeTouchesFX extends GestionnaireDeTouches {
    private Scene scene;
    private Map<KeyCode, Touche> dicoTouchesFX;

    public GestionnaireDeTouchesFX(Scene scene) throws IllegalArgumentException {
        setScene(scene);
        ChargeurDeTouchesFX chargeur = new ChargeurDeTouchesFX();
        dicoTouchesFX = chargeur.recupereTouches(Ressources.getInstance().getCheminTouches());
    }

    public Map<KeyCode, Touche> getDicoTouchesFX() {
        return Collections.unmodifiableMap(dicoTouchesFX);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        if (scene != null) {
            initialisation();
        }
    }

    public void ajouterToucheFX(KeyCode toucheFX, Touche touche) {
        dicoTouchesFX.put(toucheFX, touche);
    }

    public void supprimerToucheFX(KeyCode toucheFX) {
        dicoTouchesFX.remove(toucheFX);
    }

    @Override
    public List<Touche> detecte() {
        return super.getLesTouchesAppuyees();
    }

    public void initialisation() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (touche) -> {
            KeyCode codeTouche = touche.getCode();
            if (dicoTouchesFX.containsKey(codeTouche)){
                if(!lesTouchesAppuyees.contains(dicoTouchesFX.get(codeTouche))) {
                    lesTouchesAppuyees.add(dicoTouchesFX.get(codeTouche));
                }
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (touche) -> {
            KeyCode codeTouche = touche.getCode();
                lesTouchesAppuyees.remove(dicoTouchesFX.get(codeTouche));
        });
    }
}
