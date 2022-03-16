package com.mauja.maujaadventures.jeu;


import com.mauja.maujaadventures.collisionneurs.Attaqueur;
import com.mauja.maujaadventures.collisionneurs.AttaqueurAbsolu;
import com.mauja.maujaadventures.deplaceurs.Deplaceur;
import com.mauja.maujaadventures.deplaceurs.DeplaceurBasique;
import com.mauja.maujaadventures.entites.*;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.evenements.EvenementAttaque;
import com.mauja.maujaadventures.interactions.evenements.EvenementDeplacement;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.monde.*;
import com.mauja.maujaadventures.observateurs.Observable;
import com.mauja.maujaadventures.observateurs.Observateur;
import com.mauja.maujaadventures.observateurs.ObservateurCarte;

import java.util.*;

public class Jeu extends Observable implements Observateur, ObservateurCarte {
    private TableauDeJeu tableauDeJeu;
    private GestionnaireDeTouches gestionnaireDeTouches;
    private GestionnaireInteractions gestionnaireInteractions;

    private BoucleDeJeu boucle;
    private Thread threadBoucleDeJeu;
    private List<Touche> lesTouchesAppuyees;
    private Deplaceur deplaceur;
    private Attaqueur attaqueur;
    private boolean pause;

    private int tempsAttaque = 0;

    public Jeu(GestionnaireDeTouches gestionnaireDeTouches) throws IllegalArgumentException {
        if (gestionnaireDeTouches == null) {
            throw new IllegalArgumentException("Le gestionnaire de touches passé en paramètre ne peut pas être null.");
        }
        this.gestionnaireDeTouches = gestionnaireDeTouches;
        tableauDeJeu = new TableauDeJeu();
        tableauDeJeu.attacher(this);
        boucle = new BoucleDeJeu();
        boucle.attacher(this);
        gestionnaireInteractions = new GestionnaireInteractions(tableauDeJeu);
        lesTouchesAppuyees = new ArrayList<>();
        deplaceur = new DeplaceurBasique(tableauDeJeu.getCarteCourante());
        attaqueur = new AttaqueurAbsolu();
        pause = true;
    }

    public TableauDeJeu getTableauDeJeu() {
        return tableauDeJeu;
    }

    public GestionnaireDeTouches getGestionnaireDeTouches() {
        return gestionnaireDeTouches;
    }

    public void lancerJeu() throws IllegalStateException {
        if (threadBoucleDeJeu != null && threadBoucleDeJeu.isAlive()) {
            throw new IllegalStateException("Le thread du jeu est déjà lancé et doit d'abord être interrompu.");
        }
        boucle.setActif(true);
        pause = false;
        threadBoucleDeJeu = new Thread(boucle, "Mauja Adventures Thread");
        threadBoucleDeJeu.start();
        if (!gestionnaireInteractions.isLance()) {
            gestionnaireInteractions.lancerGestionnaire();
        }
    }

    public void arreterJeu() {
        boucle.setActif(false);
        try {
            if (isLance()) {
                threadBoucleDeJeu.join();
            }
            if (gestionnaireInteractions.isLance()) {
                gestionnaireInteractions.arreterGestionnaire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pause = true;
    }

    public boolean isLance() {
        return threadBoucleDeJeu != null && threadBoucleDeJeu.isAlive();
    }

    @Override
    public void miseAJour(long timer) {
        if (pause) {
            return;
        }
        lesTouchesAppuyees = gestionnaireDeTouches.detecte();
        if (lesTouchesAppuyees.contains(Touche.ECHAP)) {
            arreterJeu();
            lesTouchesAppuyees.clear();
        }

        PersonnageJouable joueur = tableauDeJeu.getJoueur();

        joueur.setEtatAction(EtatAction.SANS_ACTION);
        if (lesTouchesAppuyees.contains(Touche.B)) {
            System.out.println("Je me protège");
        }

        if (lesTouchesAppuyees.contains(Touche.ESPACE)) {
            joueur.setEtatAction(EtatAction.ATTAQUE);
            Attaque attaqueJoueur = joueur.getAttaque();

            Attaque attaque = attaqueur.genererAttaque(joueur, joueur.getDirection(),
                    attaqueJoueur.getCollision().getDimension(), attaqueJoueur.getDegats(),
                    attaqueJoueur.getDuree());
            if (attaque != null) {
                joueur.setAttaque(attaque);
                gestionnaireInteractions.ajouter(new EvenementAttaque(joueur, joueur.getAttaque()));
                tempsAttaque = 0;
            }
        }
        else {
            tempsAttaque++;
            if (tempsAttaque > joueur.getAttaque().getDuree() && joueur.getEtatAction() != EtatAction.SE_PROTEGE) {
                joueur.setEtatAction(EtatAction.SANS_ACTION);
            }
        }

        if (joueur.getEtatAction() == EtatAction.SANS_ACTION) {
            if (lesTouchesAppuyees.contains(Touche.FLECHE_DROITE)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(joueur, Direction.DROITE, deplaceur));
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_GAUCHE)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(joueur, Direction.GAUCHE, deplaceur));
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_HAUT)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(joueur, Direction.HAUT, deplaceur));
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_BAS)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(joueur, Direction.BAS, deplaceur));
            }
        }

        for (ElementInteractif elementInteractif : tableauDeJeu.getCarteCourante().getLesElementsInteractifs()) {
            elementInteractif.miseAJour();
        }
        notifier(timer);
    }

    @Override
    public void miseAJour(Carte carte) {
        if (carte != null) {
            deplaceur = new DeplaceurBasique(carte);
        }
    }
}