package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.affichages.Carte2DGraphique;
import com.mauja.maujaadventures.affichages.JeuDeTuilesGraphique;
import com.mauja.maujaadventures.affichages.TuileGraphique;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.JeuDeTuiles;
import com.mauja.maujaadventures.monde.Tuile;
import com.mauja.maujaadventures.utilitaires.DecoupeurImage;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ChargeurCartesGraphiques {

    public List<Carte2DGraphique> charge(List<Carte> lesCartes) throws IllegalArgumentException {
        List<Carte2DGraphique> lesCartesGraphiques = new ArrayList<>();

        for (Carte carte : lesCartes) {
            lesCartesGraphiques.add(chargeCarte(carte));
        }

        return lesCartesGraphiques;
    }

    private Carte2DGraphique chargeCarte(Carte carte) throws IllegalArgumentException {
        List<JeuDeTuiles> jeuxDeTuiles = carte.getLesJeuxDeTuiles();
        List<JeuDeTuilesGraphique> lesJeuxDeTuilesGraphiques = new ArrayList<>();
        DecoupeurImage decoupeur = new DecoupeurImage();

        for (JeuDeTuiles jeuDeTuiles : jeuxDeTuiles) {
            List<TuileGraphique> lesTuilesGraphiques = new ArrayList<>();
            Image imageJeuDeTuile = new Image(String.valueOf(jeuDeTuiles.getCheminJeuDeTuiles().toURI()));
            List<Image> imagesTuiles = decoupeur.decoupe(imageJeuDeTuile,
                    (int) jeuDeTuiles.getDimensionTuiles().getLargeur(),
                    (int) jeuDeTuiles.getDimensionTuiles().getHauteur());

            List<Tuile> lesTuiles = jeuDeTuiles.getListeDeTuiles();
            for (int i = 0; i < lesTuiles.size(); i++) {
                lesTuilesGraphiques.add(new TuileGraphique(lesTuiles.get(i), imagesTuiles.get(i)));
            }
            lesJeuxDeTuilesGraphiques.add(new JeuDeTuilesGraphique(jeuDeTuiles, lesTuilesGraphiques));
        }
        return new Carte2DGraphique(carte, lesJeuxDeTuilesGraphiques);
    }
}
