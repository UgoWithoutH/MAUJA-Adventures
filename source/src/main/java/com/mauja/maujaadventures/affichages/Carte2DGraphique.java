package com.mauja.maujaadventures.affichages;

import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.monde.Carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carte2DGraphique {
    private Carte carte;
    private List<TuileGraphique> lesTuilesGraphiques;
    private List<JeuDeTuilesGraphique> lesJeuxDeTuilesGraphiques;

    public Carte2DGraphique(Carte carte, List<JeuDeTuilesGraphique> lesJeuxDeTuilesGraphiques)
            throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre ne peut pas être nulle.");
        }
        verificationJeuxDeTuiles(lesJeuxDeTuilesGraphiques, carte);
        lesTuilesGraphiques = new ArrayList<>();
        initilisationTuiles(lesJeuxDeTuilesGraphiques);
        this.lesJeuxDeTuilesGraphiques = lesJeuxDeTuilesGraphiques;
        this.carte = carte;
    }

    public Carte getCarte() {
        return carte;
    }

    public List<TuileGraphique> getLesTuilesGraphiques() {
        return Collections.unmodifiableList(lesTuilesGraphiques);
    }

    public List<JeuDeTuilesGraphique> getLesJeuxDeTuilesGraphiques() {
        return Collections.unmodifiableList(lesJeuxDeTuilesGraphiques);
    }

    private void verificationJeuxDeTuiles(List<JeuDeTuilesGraphique> lesJeuxDeTuilesGraphiques, Carte carte)
            throws IllegalArgumentException {
        if (lesJeuxDeTuilesGraphiques == null || lesJeuxDeTuilesGraphiques.isEmpty()) {
            throw new IllegalArgumentException("Les jeux de tuiles passés en paramètre ne peut pas être null ou vides.");
        }

        Dimension dimensionJeuDeTuile = lesJeuxDeTuilesGraphiques.get(0).getJeuDeTuiles().getDimensionJeuDeTuiles();

        var jeuxDeTuilesCarte = carte.getLesJeuxDeTuiles();
        if (lesJeuxDeTuilesGraphiques.size() != jeuxDeTuilesCarte.size()) {
            throw new IllegalArgumentException("Le nombre de jeux de tuiles de la carte (" + jeuxDeTuilesCarte.size()
                    + ") n'est pas le même que ceux donné pour le jeu de tuiles graphiques ("
                    + lesJeuxDeTuilesGraphiques.size() + ").");
        }

        for (JeuDeTuilesGraphique jeuDeTuilesGraphique : lesJeuxDeTuilesGraphiques) {
            if (jeuDeTuilesGraphique == null) {
                throw new IllegalArgumentException("Un des jeu de tuiles graphiques passé en paramètre est null.");
            }
        }
    }

    private void initilisationTuiles(List<JeuDeTuilesGraphique> lesJeuxDeTuilesGraphiques) {
        for (JeuDeTuilesGraphique jeuDeTuilesGraphique : lesJeuxDeTuilesGraphiques) {
            lesTuilesGraphiques.addAll(jeuDeTuilesGraphique.getLesTuilesGraphiques());
        }
    }
}
