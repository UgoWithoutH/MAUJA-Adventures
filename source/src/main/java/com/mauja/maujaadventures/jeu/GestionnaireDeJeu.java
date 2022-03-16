package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.observateurs.Observateur;

public class GestionnaireDeJeu {
    private Jeu jeu;

    public GestionnaireDeJeu(GestionnaireDeTouches gestionnaireDeTouches) throws IllegalArgumentException {
        jeu = new Jeu(gestionnaireDeTouches);
    }

    public TableauDeJeu getTableauDeJeu() {
        return jeu.getTableauDeJeu();
    }

    public GestionnaireDeTouches getGestionnaireDeTouches() {
        return jeu.getGestionnaireDeTouches();
    }

    public void attacher(Observateur observateur) {
        jeu.attacher(observateur);
    }

    public void detacher(Observateur observateur) {
        jeu.detacher(observateur);
    }

    public boolean isAttache(Observateur observateur) {
        return jeu.isAttache(observateur);
    }

    public boolean isLance() {
        return jeu.isLance();
    }

    public void lancerJeu() throws IllegalStateException {
        jeu.lancerJeu();
    }

    public void arreterJeu() {
        jeu.arreterJeu();
    }
}
