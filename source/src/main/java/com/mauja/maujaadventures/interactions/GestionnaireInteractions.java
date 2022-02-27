package com.mauja.maujaadventures.interactions;

import java.util.LinkedList;
import java.util.Queue;

public class GestionnaireInteractions {
    private Queue<ElementInteractif> file;
    private static GestionnaireInteractions gestionnaireInteractions;

    private GestionnaireInteractions() {
        file = new LinkedList<>();
    }

    public static GestionnaireInteractions getInstance(){
        if(gestionnaireInteractions == null){
            return new GestionnaireInteractions();
        }
        else{
            return gestionnaireInteractions;
        }
    }

    public void ajouterElement(ElementInteractif elementInteractif) {
        file.add(elementInteractif);
    }

    private void traitement() {
        ElementInteractif element = file.poll();
        //traitement
    }
}
