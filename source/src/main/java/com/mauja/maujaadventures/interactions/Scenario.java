package com.mauja.maujaadventures.interactions;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private List<ElementInteractif> listeElemInteractif;


    public Scenario() {
        this.listeElemInteractif = new ArrayList<>();
    }

    public void ajouterElementInteractif(ElementInteractif elementInteractif){
        listeElemInteractif.add(elementInteractif);
    }


    @Override
    public String toString() {
        return "Scenario{" +
                "listeElemInteractif=" + listeElemInteractif +
                '}';
    }
}
