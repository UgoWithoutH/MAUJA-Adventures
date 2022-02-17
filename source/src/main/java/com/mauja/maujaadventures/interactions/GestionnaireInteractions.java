package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.interactions.evenements.Evenement;
import com.mauja.maujaadventures.interactions.evenements.TraiteurEvenements;

import java.util.Stack;

public class GestionnaireInteractions {
    private Stack<Evenement> pileCourante;
    private Stack<Evenement> pileSauvegarde;
    private TraiteurEvenements traiteurEvenements;
    private boolean enCours;

    public GestionnaireInteractions() {
        pileCourante = new Stack<>();
        pileSauvegarde = new Stack<>();
        traiteurEvenements = new TraiteurEvenements();
        enCours = false;
    }

    public void ajouterEvenement(Evenement evenement) {
        if (!enCours) {
            pileCourante.add(evenement);
            Thread thread = new Thread(() -> traitement());
            thread.start();
        }
        else{
            pileSauvegarde.add(evenement);
        }
    }

    private void traitement(){
        enCours = true;
        while(true){
            if(pileCourante.size() != 0){
                traiteurEvenements.traiter(pileCourante.pop());
            }
            else{
                if(pileSauvegarde.size() != 0){
                    pileCourante = (Stack<Evenement>) pileSauvegarde.clone();
                    pileSauvegarde = new Stack<>();
                }
                else
                    break;
            }
        }
        enCours = false;
    }
}
