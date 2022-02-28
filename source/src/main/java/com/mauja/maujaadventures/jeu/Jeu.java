package com.mauja.maujaadventures.jeu;


import com.mauja.maujaadventures.comportements.Comportement;
import com.mauja.maujaadventures.comportements.ComportementChevalier;
import com.mauja.maujaadventures.comportements.ComportementOctorockTireur;
import com.mauja.maujaadventures.entites.*;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.chargeurs.RecuperateurDeCartes;
import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.monde.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Jeu extends Observable implements Observateur {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(964, 608);

    private TableauDeJeu tableauDeJeu;
    private DeplaceurEntite deplaceur;
    private CollisionneurAABB collisionneur;
    private GestionnaireDeTouches gestionnaireDeTouches;

    private Camera camera;
    private int tempsAttaque = 0;
    private Boucle boucle;
    private int v;
    private final double decalageX = 28.2;
    private final double decalageY = 24;
    private boolean paramOuvert = false;
    private List<Touche> lesTouchesAppuyees;

    /**
     * Constructeur de Jeu
     * @throws FileNotFoundException Exception déclencher si le fichier n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Jeu(Options options) throws FileNotFoundException, FileNotFoundException {
        collisionneur = new CollisionneurAABB();
        tableauDeJeu = new TableauDeJeu(options);

        camera = new Camera( 0, 0);
        lesTouchesAppuyees = new ArrayList<>();

        boucle = new Boucle();
        boucle.attacher(this);
        initialiser();
    }

    public void setGestionnaireDeTouches(GestionnaireDeTouches gestionnaireDeTouches){
        if (gestionnaireDeTouches == null) {
            throw new IllegalArgumentException("Le gestionnaire de touches passé en paramètre ne peut pas être null.");
        }
        this.gestionnaireDeTouches = gestionnaireDeTouches;
    }

    public static Dimension getDimensionCameraParDefaut() {
        return DIMENSION_CAMERA_PAR_DEFAUT;
    }

    public TableauDeJeu getTableauDeJeu() {
        return tableauDeJeu;
    }

    public void setParamOuvert(boolean value) {
        paramOuvert = value;
    }
    public boolean isParamOuvert() {
        return paramOuvert;
    }

    public Camera getCamera() {
        return camera;
    }

    public GestionnaireDeTouches getGestionnaireDeTouches() {
        return gestionnaireDeTouches;
    }

    public Boucle getBoucle() {
        return boucle;
    }

    public void start() {
        boucle.setRunning(true);
        Thread boucleThread = new Thread(boucle);
        boucleThread.start();
    }

    public void stop(){
        boucle.setRunning(false);
    }

    @Override
    public void update(int timer) {
        lesTouchesAppuyees = gestionnaireDeTouches.detecte();

        if (lesTouchesAppuyees.contains(Touche.B) /*&& tempsAttaque > joueur.getAttaque().getDuree()*/) {
            System.out.println("Je me protège");
        }
        else {
            tableauDeJeu.getJoueur().setEtatAction(EtatAction.SANS_ACTION);
        }

        if (lesTouchesAppuyees.contains(Touche.ESPACE)) {
            //System.out.println("J'attaque");
            tableauDeJeu.getJoueur().setEtatAction(EtatAction.ATTAQUE);
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
            GestionnaireInteractions.getInstance().ajouterElement(tableauDeJeu.getJoueur());
        }
        else {
            tempsAttaque++;
            if (tempsAttaque > tableauDeJeu.getJoueur().getAttaque().getDuree() && tableauDeJeu.getJoueur().getEtatAction() != EtatAction.SE_PROTEGE) {
                tableauDeJeu.getJoueur().setEtatAction(EtatAction.SANS_ACTION);
            }
        }

        if (tableauDeJeu.getJoueur().getEtatAction() == EtatAction.SANS_ACTION) {
            if (lesTouchesAppuyees.contains(Touche.FLECHE_DROITE)) {
                boolean estDeplace = deplaceur.deplace(tableauDeJeu.getJoueur(), 0, Direction.DROITE, true);

                if (estDeplace && tableauDeJeu.getCarteCourante().getDimension().getLargeur() * decalageX - (tableauDeJeu.getJoueur().getPosition().getX()) > tableauDeJeu.getCarteCourante().getDimension().getLargeur()) {
                    if (((camera.getPositionCameraX() <= tableauDeJeu.getCarteCourante().getDimension().getLargeur() * decalageX)) &&
                            (tableauDeJeu.getJoueur().getPosition().getX() >= DIMENSION_CAMERA_PAR_DEFAUT.getLargeur() / 2)) {
                        camera.deplacementCamera(tableauDeJeu.getJoueur().getVelocite().getX(), 0);
                    }
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_GAUCHE)) {
                boolean estDeplace = deplaceur.deplace(tableauDeJeu.getJoueur(), 0, Direction.GAUCHE, true);
                if (estDeplace && 0 + tableauDeJeu.getJoueur().getPosition().getY() > tableauDeJeu.getCarteCourante().getDimension().getLargeur()) {
                    if (!(camera.getPositionCameraX() <= 0) &&
                            (tableauDeJeu.getJoueur().getPosition().getX() <= tableauDeJeu.getCarteCourante().getDimension().getLargeur() * 32 -
                                    DIMENSION_CAMERA_PAR_DEFAUT.getLargeur() / 2)) {
                        camera.deplacementCamera(-tableauDeJeu.getJoueur().getVelocite().getX(), 0);
                    }
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_HAUT)) {
                boolean estDeplace = deplaceur.deplace(tableauDeJeu.getJoueur(), 0, Direction.HAUT, true);
                if (estDeplace && !(camera.getPositionCameraY() <= 0) &&
                        (tableauDeJeu.getJoueur().getPosition().getY() <= tableauDeJeu.getCarteCourante().getDimension().getHauteur() * decalageY +
                                DIMENSION_CAMERA_PAR_DEFAUT.getHauteur() / 2)) {
                    camera.deplacementCamera(0, -tableauDeJeu.getJoueur().getVelocite().getY());
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_BAS)) {
                boolean estDeplace = deplaceur.deplace(tableauDeJeu.getJoueur(), 0, Direction.BAS, true);

                if (estDeplace && (tableauDeJeu.getCarteCourante().getDimension().getLargeur() * tableauDeJeu.getCarteCourante().getDimension().getLargeur()) - (tableauDeJeu.getJoueur().getPosition().getY()) > tableauDeJeu.getCarteCourante().getDimension().getHauteur() &&
                        (camera.getPositionCameraY() <= tableauDeJeu.getCarteCourante().getDimension().getHauteur() * decalageY &&
                                (tableauDeJeu.getJoueur().getPosition().getY() >= DIMENSION_CAMERA_PAR_DEFAUT.getHauteur() / 2))) {
                    camera.deplacementCamera(0, tableauDeJeu.getJoueur().getVelocite().getY());
                }
            }
        }

        // Detection attaque joueur et ennemis
        for (Entite entite : tableauDeJeu.getCarteCourante().getLesEntites()) {
            Rectangle collisionEntite = new Rectangle(entite.getCollision().getPosition().getX() + entite.getPosition().getX(),
                    entite.getCollision().getPosition().getY() + entite.getPosition().getY(),
                    entite.getCollision().getDimension());
            Rectangle collisionJoueur = new Rectangle(tableauDeJeu.getJoueur().getCollision().getPosition().getX() + tableauDeJeu.getJoueur().getPosition().getX(),
                    tableauDeJeu.getJoueur().getCollision().getPosition().getY() + tableauDeJeu.getJoueur().getPosition().getY(),
                    tableauDeJeu.getJoueur().getCollision().getDimension());

            if (entite instanceof Ennemi ennemi) {
                if (collisionneur.collisionne(tableauDeJeu.getJoueur().getAttaque().getCollision(), collisionEntite)
                        && tableauDeJeu.getJoueur().getEtatAction() == EtatAction.ATTAQUE) {
                    ennemi.setPointsDeVie(ennemi.getPointsDeVie() - tableauDeJeu.getJoueur().getAttaque().getDegats());
                    if (ennemi.getPointsDeVie() <= 0) {
                        tableauDeJeu.getCarteCourante().supprimerEntite(ennemi);
                    }
                }

                if (collisionneur.collisionne(collisionJoueur, collisionEntite)) {
                    tableauDeJeu.getJoueur().setPointsDeVie(tableauDeJeu.getJoueur().getPointsDeVie() - ennemi.getAttaque().getDegats());
                }
            }
            if (entite instanceof Projectile projectile) {

                if (collisionneur.collisionne(collisionJoueur, collisionEntite) && tableauDeJeu.getJoueur().getEtatAction() != EtatAction.SE_PROTEGE) {
                    tableauDeJeu.getJoueur().setPointsDeVie(tableauDeJeu.getJoueur().getPointsDeVie() - projectile.getDegats());
                    tableauDeJeu.getCarteCourante().supprimerEntite(projectile);
                } else if (collisionneur.collisionne(collisionJoueur, collisionEntite) &&
                        tableauDeJeu.getJoueur().getEtatAction() == EtatAction.SE_PROTEGE &&
                        (tableauDeJeu.getJoueur().getDirection().getVal() == (v = projectile.getDirection().getVal() + 1) ||
                                (tableauDeJeu.getJoueur().getDirection().getVal() == (v = projectile.getDirection().getVal() - 1)))) {
                    projectile.setDirection(Direction.valeurDe((byte) v));
                } else if (collisionneur.collisionne(collisionJoueur, collisionEntite) && tableauDeJeu.getJoueur().getEtatAction() == EtatAction.SE_PROTEGE) {
                    tableauDeJeu.getCarteCourante().supprimerEntite(projectile);
                    tableauDeJeu.getJoueur().setPointsDeVie(tableauDeJeu.getJoueur().getPointsDeVie() - projectile.getDegats());
                }
            }
        }

        // MAJ ennemis
        for (Entite entite : tableauDeJeu.getCarteCourante().getLesEntites()) {
            if (entite instanceof Ennemi ennemi) {
                Comportement comportement = ennemi.getComportement();
                if (comportement != null) {
                    comportement.agit(ennemi, 0);
                }
            }
            if (entite instanceof Projectile projectile) {
                deplaceur.deplace(projectile, 0, projectile.getDirection(), true);
            }
        }
        notifier(timer);
    }

    /**
     * Fonction d'initialisation du jeu
     * @throws FileNotFoundException Exception si le fichier de l'image n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void initialiser() throws FileNotFoundException {
        deplaceur = new DeplaceurEntite(tableauDeJeu.getCarteCourante());
    }
}