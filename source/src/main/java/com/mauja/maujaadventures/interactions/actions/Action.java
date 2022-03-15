package com.mauja.maujaadventures.interactions.actions;

import com.mauja.maujaadventures.interactions.elements.Balise;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.ArrayList;
import java.util.List;

public abstract class Action extends Balise {
    protected List<ElementInteractif> lesElementsInteractifs;

    public Action() {
        lesElementsInteractifs = new ArrayList<>();
    }

    public abstract void agit(ElementInteractif elementInteractif, TableauDeJeu tableauDeJeu);

    @Override
    public void ajouter(Balise balise) {
        ajouterElementInteractif((ElementInteractif) balise);
    }

    private void ajouterElementInteractif(ElementInteractif elementInteractif) {
        lesElementsInteractifs.add(elementInteractif);
    }

    @Override
    public String toString() {
        return "Action{" +
                "listeElementInteractif=" + lesElementsInteractifs +
                '}';
    }
}
