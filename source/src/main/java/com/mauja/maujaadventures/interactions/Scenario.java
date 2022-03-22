package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.interactions.elements.Balise;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scenario extends Balise {
    private List<ElementInteractif> listeElemInteractif;

    public Scenario() {
        this.listeElemInteractif = new ArrayList<>();
    }

    public List<ElementInteractif> getListeElemInteractif() {
        return Collections.unmodifiableList(listeElemInteractif);
    }

    public void ajouterElementInteractif(ElementInteractif elementInteractif) {
        listeElemInteractif.add(elementInteractif);
    }

    @Override
    public void ajouter(Balise balise) {
        ajouterElementInteractif((ElementInteractif)balise);
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "listeElemInteractif=" + listeElemInteractif +
                '}';
    }
}
