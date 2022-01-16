package com.mauja.maujaadventures.jeu;


import com.mauja.maujaadventures.comportements.Comportement;
import com.mauja.maujaadventures.comportements.ComportementPoursuite;
import com.mauja.maujaadventures.comportements.ComportementOctorockTireur;
import com.mauja.maujaadventures.entites.*;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.chargeurs.RecuperateurDeCartes;
import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.monde.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Jeu extends Observable implements Observateur {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(964, 800);

    private DeplaceurEntite deplaceur;
    private List<Tuile> lesTuiles;
    private List<Carte> lesCartes;
    private Carte carteCourante;
    private Camera camera;
    private PersonnageJouable joueur;
    private int tempsAttaque = 0;
    private Boucle boucle;


    private final double decalageX = 28.2;
    private final double decalageY = 24;

    private GestionnaireDeTouches gestionnaireDeTouches;
    private List<Touche> lesTouchesAppuyees;
    private CollisionneurAABB collisionneur;

    /**
     * Constructeur de Jeu
     * @throws FileNotFoundException Exception déclencher si le fichier n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Jeu(GestionnaireDeTouches gestionnaireDeTouches)
            throws IllegalArgumentException, FileNotFoundException {
        if (gestionnaireDeTouches == null) {
            throw new IllegalArgumentException("Le gestionnaire de touches passé en paramètre ne peut pas être null.");
        }

        this.gestionnaireDeTouches = gestionnaireDeTouches;
        collisionneur = new CollisionneurAABB();

        camera = new Camera( 0, 0);
        lesTouchesAppuyees = new ArrayList<>();
        lesCartes = new ArrayList<>();

        boucle = new Boucle();
        boucle.attacher(this);
        initialiser();
    }

    public List<Carte> getLesCartes() {
        return lesCartes;
    }

    public Carte getCarteCourante() {
        return carteCourante;
    }

    public Camera getCamera() {
        return camera;
    }

    public PersonnageJouable getJoueur() {
        return joueur;
    }

    public List<Tuile> getLesTuiles() {
        return lesTuiles;
    }

    /**
     * Fonction d'initialisation du jeu
     * @throws FileNotFoundException Exception si le fichier de l'image n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void initialiser() throws FileNotFoundException {
        RecuperateurDeCartes recuperateurDeCartes = new RecuperateurDeCartes();

        List<String> lesCartesChemin = Ressources.getLesCartes();

        List<JeuDeTuiles> lesJeuxDeTuiles = null;

        Carte carte;
        for (String chemin : lesCartesChemin) {
            carte = recuperateurDeCartes.recupereCarte(chemin);
            lesCartes.add(carte);
        }

        carteCourante = lesCartes.get(0);

        for (String chemin : lesCartesChemin) {
            lesJeuxDeTuiles = recuperateurDeCartes.recupereJeuxDeTuiles(chemin);
        }

        lesTuiles = new ArrayList<>();

        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            lesTuiles.addAll(jeuDeTuiles.getListeDeTuiles());
        }

        Position position = new Position(482, 400);
        Rectangle rectangle = new Rectangle(new Position(3, 24), new Dimension(27, 23));
        joueur = new PersonnageJouable(position, new Dimension(33, 47),
                rectangle, null, new Attaque(new Rectangle(0, 0, 30, 30), 1000));


        Entite entite = new Ennemi(new Position(400, 600), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                new ComportementOctorockTireur(carteCourante), 10);

        Entite entite2 = new Ennemi(new Position(1000, 1000), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                new ComportementPoursuite(carteCourante, joueur), 10);

        carteCourante.ajouterEntite(entite);
        carteCourante.ajouterEntite(entite2);


        deplaceur = new DeplaceurEntite(carteCourante);
    }

    public void start() {
        boucle.setRunning(true);
        Thread boucleThread = new Thread(boucle);
        boucleThread.start();
    }

    @Override
    public void update(int timer) {
        lesTouchesAppuyees = gestionnaireDeTouches.detecte();

        if (lesTouchesAppuyees.contains(Touche.B) && tempsAttaque > joueur.getAttaque().getDuree()) {
            System.out.println("Je me protège");
            joueur.setEtatAction(EtatAction.SE_PROTEGE);
        }
        else {
            joueur.setEtatAction(EtatAction.SANS_ACTION);
        }

        if (lesTouchesAppuyees.contains(Touche.ESPACE)) {
            //System.out.println("J'attaque");
            joueur.setEtatAction(EtatAction.ATTAQUE);
            Rectangle collisionAttaque;
            if (joueur.getDirection() == Direction.DROITE) {
                collisionAttaque = new Rectangle(joueur.getPosition().getX() + joueur.getCollision().getPosition().getX()
                        + joueur.getCollision().getDimension().getLargeur(),
                        joueur.getPosition().getY() +
                                (joueur.getDimension().getHauteur() - joueur.getAttaque().getCollision().getDimension().getHauteur()) / 2,
                        joueur.getAttaque().getCollision().getDimension().getLargeur(),
                        joueur.getCollision().getDimension().getHauteur());
                joueur.getAttaque().setCollision(collisionAttaque);
            }
            if (joueur.getDirection() == Direction.GAUCHE) {
                collisionAttaque = new Rectangle(joueur.getPosition().getX()
                        - joueur.getCollision().getDimension().getLargeur(),
                        joueur.getPosition().getY() +
                                (joueur.getDimension().getHauteur() - joueur.getAttaque().getCollision().getDimension().getHauteur()) / 2,
                        joueur.getAttaque().getCollision().getDimension().getLargeur(),
                        joueur.getCollision().getDimension().getHauteur());
                joueur.getAttaque().setCollision(collisionAttaque);
            }

            if (joueur.getDirection() == Direction.HAUT) {
                collisionAttaque = new Rectangle(joueur.getPosition().getX() +
                        (joueur.getDimension().getLargeur() - joueur.getAttaque().getCollision().getDimension().getLargeur()) / 2,
                        joueur.getPosition().getY() - joueur.getAttaque().getCollision().getDimension().getHauteur(),
                        joueur.getAttaque().getCollision().getDimension().getLargeur(),
                        joueur.getCollision().getDimension().getHauteur());
                joueur.getAttaque().setCollision(collisionAttaque);
            }

            if (joueur.getDirection() == Direction.BAS) {
                collisionAttaque = new Rectangle(joueur.getPosition().getX() +
                        (joueur.getDimension().getLargeur() - joueur.getAttaque().getCollision().getDimension().getLargeur()) / 2,
                        joueur.getPosition().getY() + joueur.getDimension().getHauteur(),
                        joueur.getAttaque().getCollision().getDimension().getLargeur(),
                        joueur.getCollision().getDimension().getHauteur());
                joueur.getAttaque().setCollision(collisionAttaque);
            }
            tempsAttaque = 0;
        }
        else {
            tempsAttaque++;
            if (tempsAttaque > joueur.getAttaque().getDuree() && joueur.getEtatAction() != EtatAction.SE_PROTEGE) {
                joueur.setEtatAction(EtatAction.SANS_ACTION);
            }
        }

        if (joueur.getEtatAction() == EtatAction.SANS_ACTION) {
            if (lesTouchesAppuyees.contains(Touche.FLECHE_DROITE)) {
                boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.DROITE, true);

                if (carteCourante.getDimension().getLargeur() * decalageX - (joueur.getPosition().getX()) > 100) {
                    if (((camera.getPositionCameraX() <= carteCourante.getDimension().getLargeur() * decalageX)) &&
                            (joueur.getPosition().getX() >= DIMENSION_CAMERA_PAR_DEFAUT.getLargeur() / 2)) {
                        camera.deplacementCamera(joueur.getVelocite().getX(), 0);
                    }
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_GAUCHE)) {
                boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.GAUCHE, true);
                if (estDeplace && 0 + joueur.getPosition().getY() > carteCourante.getDimension().getLargeur()) {
                    if (!(camera.getPositionCameraX() <= 0) &&
                            (joueur.getPosition().getX() <= carteCourante.getDimension().getLargeur() * 32 -
                                    DIMENSION_CAMERA_PAR_DEFAUT.getLargeur() / 2)) {
                        camera.deplacementCamera(-joueur.getVelocite().getX(), 0);
                    }
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_HAUT)) {
                boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.HAUT, true);
                if (estDeplace && !(camera.getPositionCameraY() <= 0) &&
                        (joueur.getPosition().getY() <= carteCourante.getDimension().getHauteur() * decalageY +
                                DIMENSION_CAMERA_PAR_DEFAUT.getHauteur() / 2)) {
                    camera.deplacementCamera(0, -joueur.getVelocite().getY());
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_BAS)) {
                boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.BAS, true);

                if (estDeplace && (carteCourante.getDimension().getLargeur() * carteCourante.getDimension().getLargeur()) - (joueur.getPosition().getY()) > carteCourante.getDimension().getHauteur() &&
                        (camera.getPositionCameraY() <= carteCourante.getDimension().getHauteur() * decalageY &&
                                (joueur.getPosition().getY() >= DIMENSION_CAMERA_PAR_DEFAUT.getHauteur() / 2))) {
                    camera.deplacementCamera(0, joueur.getVelocite().getY());
                }
            }
        }

        // Detection attaque joueur et ennemis
        for (Entite entite : carteCourante.getLesEntites()) {
            Rectangle collisionEntite = new Rectangle(entite.getCollision().getPosition().getX() + entite.getPosition().getX(),
                    entite.getCollision().getPosition().getY() + entite.getPosition().getY(),
                    entite.getCollision().getDimension());
            Rectangle collisionJoueur = new Rectangle(joueur.getCollision().getPosition().getX() + joueur.getPosition().getX(),
                    joueur.getCollision().getPosition().getY() + joueur.getPosition().getY(),
                    joueur.getCollision().getDimension());

            if (entite instanceof Ennemi ennemi) {
                if (collisionneur.collisionne(joueur.getAttaque().getCollision(), collisionEntite)
                        && joueur.getEtatAction() == EtatAction.ATTAQUE) {
                    ennemi.setPointsDeVie(ennemi.getPointsDeVie() - joueur.getAttaque().getDegats());
                    if (ennemi.getPointsDeVie() <= 0) {
                        carteCourante.supprimerEntite(ennemi);
                    }
                }

                if (collisionneur.collisionne(collisionJoueur, collisionEntite)) {
                    joueur.setPointsDeVie(joueur.getPointsDeVie() - ennemi.getAttaque().getDegats());
                }
            }
            if (entite instanceof Projectile projectile) {
                if (collisionneur.collisionne(collisionJoueur, collisionEntite)) {
                    joueur.setPointsDeVie(joueur.getPointsDeVie() - projectile.getDegats());
                    carteCourante.supprimerEntite(projectile);
                }
            }
        }

        // MAJ ennemis
        for (Entite entite : carteCourante.getLesEntites()) {
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
}