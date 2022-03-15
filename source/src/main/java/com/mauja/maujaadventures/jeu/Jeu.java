package com.mauja.maujaadventures.jeu;


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

import java.util.*;

public class Jeu extends Observable implements Observateur, ObservateurCarte {
    private TableauDeJeu tableauDeJeu;
    private GestionnaireDeTouches gestionnaireDeTouches;
    private GestionnaireInteractions gestionnaireInteractions;

    private BoucleDeJeu boucle;
    private Thread threadBoucleDeJeu;
    private List<Touche> lesTouchesAppuyees;
    private Deplaceur deplaceur;

    private boolean pause;

    private Camera camera;
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
        pause = true;
        camera = new Camera(0, 0);
    }

    public TableauDeJeu getTableauDeJeu() {
        return tableauDeJeu;
    }

    public Camera getCamera() {
        return camera;
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

        tableauDeJeu.getJoueur().setEtatAction(EtatAction.SANS_ACTION);
        if (lesTouchesAppuyees.contains(Touche.B)) {
            System.out.println("Je me protège");
        }

        if (lesTouchesAppuyees.contains(Touche.ESPACE)) {
            //System.out.println("J'attaque");
            tableauDeJeu.getJoueur().setEtatAction(EtatAction.ATTAQUE);
            GestionnaireInteractions.getInstance().ajouter(
                    new EvenementAttaque(tableauDeJeu.getCarteCourante(), tableauDeJeu.getJoueur()));
            Rectangle collisionAttaque;
            if (tableauDeJeu.getJoueur().getDirection() == Direction.DROITE) {
                collisionAttaque = new Rectangle(tableauDeJeu.getJoueur().getPosition().getX() + tableauDeJeu.getJoueur().getCollision().getPosition().getX()
                        + tableauDeJeu.getJoueur().getCollision().getDimension().getLargeur(),
                        tableauDeJeu.getJoueur().getPosition().getY() +
                                (tableauDeJeu.getJoueur().getDimension().getHauteur() - tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getHauteur()) / 2,
                        tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getLargeur(),
                        tableauDeJeu.getJoueur().getCollision().getDimension().getHauteur());
                tableauDeJeu.getJoueur().getAttaque().setCollision(collisionAttaque);
            }
            if (tableauDeJeu.getJoueur().getDirection() == Direction.GAUCHE) {
                collisionAttaque = new Rectangle(tableauDeJeu.getJoueur().getPosition().getX()
                        - tableauDeJeu.getJoueur().getCollision().getDimension().getLargeur(),
                        tableauDeJeu.getJoueur().getPosition().getY() +
                                (tableauDeJeu.getJoueur().getDimension().getHauteur() - tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getHauteur()) / 2,
                        tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getLargeur(),
                        tableauDeJeu.getJoueur().getCollision().getDimension().getHauteur());
                tableauDeJeu.getJoueur().getAttaque().setCollision(collisionAttaque);
            }

            if (tableauDeJeu.getJoueur().getDirection() == Direction.HAUT) {
                collisionAttaque = new Rectangle(tableauDeJeu.getJoueur().getPosition().getX() +
                        (tableauDeJeu.getJoueur().getDimension().getLargeur() - tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getLargeur()) / 2,
                        tableauDeJeu.getJoueur().getPosition().getY() - tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getHauteur(),
                        tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getLargeur(),
                        tableauDeJeu.getJoueur().getCollision().getDimension().getHauteur());
                tableauDeJeu.getJoueur().getAttaque().setCollision(collisionAttaque);
            }

            if (tableauDeJeu.getJoueur().getDirection() == Direction.BAS) {
                collisionAttaque = new Rectangle(tableauDeJeu.getJoueur().getPosition().getX() +
                        (tableauDeJeu.getJoueur().getDimension().getLargeur() - tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getLargeur()) / 2,
                        tableauDeJeu.getJoueur().getPosition().getY() + tableauDeJeu.getJoueur().getDimension().getHauteur(),
                        tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getLargeur(),
                        tableauDeJeu.getJoueur().getCollision().getDimension().getHauteur());
                tableauDeJeu.getJoueur().getAttaque().setCollision(collisionAttaque);
            }
            tempsAttaque = 0;
            new EvenementAttaque(tableauDeJeu.getCarteCourante(), tableauDeJeu.getJoueur());
        }
        else {
            tempsAttaque++;
            if (tempsAttaque > tableauDeJeu.getJoueur().getAttaque().getDuree() && tableauDeJeu.getJoueur().getEtatAction() != EtatAction.SE_PROTEGE) {
                tableauDeJeu.getJoueur().setEtatAction(EtatAction.SANS_ACTION);
            }
        }

        if (tableauDeJeu.getJoueur().getEtatAction() == EtatAction.SANS_ACTION) {
            if (lesTouchesAppuyees.contains(Touche.FLECHE_DROITE)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(tableauDeJeu.getJoueur(), Direction.DROITE, deplaceur));
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_GAUCHE)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(tableauDeJeu.getJoueur(), Direction.GAUCHE, deplaceur));
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_HAUT)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(tableauDeJeu.getJoueur(), Direction.HAUT, deplaceur));
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_BAS)) {
                gestionnaireInteractions.ajouter(new EvenementDeplacement(tableauDeJeu.getJoueur(), Direction.BAS, deplaceur));
            }
        }

        // Detection attaque joueur et ennemis
        /*for (ElementInteractif elementInteractif : tableauDeJeu.getCarteCourante().getLesElementsInteractifs()) {
            Rectangle collisionEntite = new Rectangle(elementInteractif.getCollision().getPosition().getX() + elementInteractif.getPosition().getX(),
                    elementInteractif.getCollision().getPosition().getY() + elementInteractif.getPosition().getY(),
                    elementInteractif.getCollision().getDimension());
            Rectangle collisionJoueur = new Rectangle(tableauDeJeu.getJoueur().getCollision().getPosition().getX() + tableauDeJeu.getJoueur().getPosition().getX(),
                    tableauDeJeu.getJoueur().getCollision().getPosition().getY() + tableauDeJeu.getJoueur().getPosition().getY(),
                    tableauDeJeu.getJoueur().getCollision().getDimension());

            if (elementInteractif instanceof Ennemi ennemi) {
                if (collisionneur.collisionne(tableauDeJeu.getJoueur().getAttaque().getCollision(), collisionEntite)
                        && tableauDeJeu.getJoueur().getEtatAction() == EtatAction.ATTAQUE) {
                    solveurCollision.resoud(ennemi,tableauDeJeu.getJoueur());
                }

                if (collisionneur.collisionne(collisionJoueur, collisionEntite)) {
                    solveurCollision.resoud(ennemi,tableauDeJeu.getJoueur());
                }
            }
        }*/

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