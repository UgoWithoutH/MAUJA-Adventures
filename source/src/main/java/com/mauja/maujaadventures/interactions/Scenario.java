package com.mauja.maujaadventures.interactions;

import java.util.ArrayList;
import java.util.List;

public class Scenario implements Balise {

    private List<ElementInteractif> listeElemInteractif;


    public Scenario() {
        this.listeElemInteractif = new ArrayList<>();
    }

    public void ajouterElementInteractif(ElementInteractif elementInteractif){
        listeElemInteractif.add(elementInteractif);
    }

    public List<ElementInteractif> getListeElemInteractif() {
        return listeElemInteractif;
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "listeElemInteractif=" + listeElemInteractif +
                '}';
    }

    @Override
    public void ajouter(Balise balise) {
        ajouterElementInteractif((ElementInteractif)balise);
    }
}
