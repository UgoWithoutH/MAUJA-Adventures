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
        List<Tuile> lesTuiles = new ArrayList<>();
        double largeurCarte = chargeurCarte.getWidth();
        double hauteurCarte = chargeurCarte.getHeight();

        for (int j = 0; j < hauteurCarte; j++) {
            for (int k = 0; k < largeurCarte; k++) {
                //System.out.print(" (" + k + ", " + j + " " + i + ") ");
                TiledTile tuileTiled = calque.getTile(k, j);
                if (tuileTiled != null) {
                    String identifiantJeuDeTuile = tuileTiled.getTileset().getName();
                    int identifiant = tuileTiled.getID();
                    for (JeuDeTuiles jeuDeTuile : lesJeuxDeTuiles) {
                        if (jeuDeTuile.getIdentifiant().equals(identifiantJeuDeTuile)) {
                            Tuile tuile = jeuDeTuile.getListeDeTuiles().get(identifiant);
                            if (tuile != null) {
                                lesTuiles.add(tuile);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return lesTuiles;
    }
}
