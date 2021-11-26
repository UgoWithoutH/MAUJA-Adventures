package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.monde.Tuile;
import org.tiledreader.*;

import java.util.ArrayList;
import java.util.List;

public class RecuperateurDeTuiles {

    private List<JeuDeTuiles> lesJeuxDeTuiles;

    public RecuperateurDeTuiles(List<JeuDeTuiles> lesJeuxDeTuiles) {
        this.lesJeuxDeTuiles = lesJeuxDeTuiles;
    }

    public List<Tuile> recupere(TiledMap chargeurCarte, TiledTileLayer calque) {
        int compte = 0;
        List<Tuile> lesTuiles = new ArrayList<>();
        double largeurCarte = chargeurCarte.getWidth();
        double hauteurCarte = chargeurCarte.getHeight();

        for (int j = 0; j < hauteurCarte; j++) {
            for (int k = 0; k < largeurCarte; k++) {
                //System.out.print(" (" + k + ", " + j + " " + i + ") ");
                TiledTile tuileTiled = calque.getTile(k, j);
                if (tuileTiled == null) {
                    Tuile tuile = new Tuile(0, null, null, null);
                    lesTuiles.add(tuile);
                }
                else {
                    String identifiantJeuDeTuile = tuileTiled.getTileset().getName();
                    int identifiant = tuileTiled.getID();
                    for (JeuDeTuiles jeuDeTuile : lesJeuxDeTuiles) {
                        System.out.println("Tuile numéro " + compte);
                        compte++;
                        if (jeuDeTuile.getIdentifiant().equals(identifiantJeuDeTuile)) {
                            Tuile tuile = jeuDeTuile.getListeDeTuiles().get(identifiant);
                            lesTuiles.add(tuile);
                            break;
                        }
                    }
                }
            }
        }
        return lesTuiles;
    }
}
