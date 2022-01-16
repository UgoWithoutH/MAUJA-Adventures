package com.mauja.maujaadventures.jeu;


import com.mauja.maujaadventures.comportements.Comportement;
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
import com.mauja.maujaadventures.utilitaires.DecoupeurImage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;

public class Jeu implements Observateur {
    private DeplaceurEntite deplaceur;
    private List<Tuile> lesTuiles;
    private List<Carte> lesCartes;
    private Carte carteCourante;
    private Camera camera;
    private PersonnageJouable joueur;
    private Rectangle attaqueJoueur;
    private int nombreCalques;
    private int tempsAttaque = 0, tempsDefense = 0;
    private Boucle boucle;

    private GestionnaireDeTouches gestionnaireDeTouches;
    private List<Touche> lesTouchesAppuyees;

    private GraphicsContext gc;
    private Image imagePersonnage;
    private Image imageProjectile;
    private Image imageEnnemi;
    private Map<Tuile, Image> lesTuilesImagees;
    private List<Image> lesImages;

    private CollisionneurAABB collisionneur;

    /**
     * Constructeur de Jeu
     * @param gc Contexte Graphique du Jeu
     * @throws FileNotFoundException Exception déclencher si le fichier n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Jeu(GraphicsContext gc, GestionnaireDeTouches gestionnaireDeTouches)
            throws IllegalArgumentException, FileNotFoundException {
        if (gestionnaireDeTouches == null) {
            throw new IllegalArgumentException("Le gestionnaire de touches passé en paramètre ne peut pas être null.");
        }

        this.gestionnaireDeTouches = gestionnaireDeTouches;
        this.gc = gc;

        collisionneur = new CollisionneurAABB();
        lesTouchesAppuyees = new ArrayList<>();
        camera = new Camera( 0, 0);
        boucle = new Boucle();
        boucle.subscribe(this);
        initialiser();
    }

    /**
     * Fonction d'initialisation du jeu
     * @throws FileNotFoundException Exception si le fichier de l'image n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void initialiser() throws FileNotFoundException {
        RecuperateurDeCartes recuperateurDeCartes = new RecuperateurDeCartes();

        List<String> lesImagesJeuxDeTuilesChemin = Ressources.getLesImagesJeuxDeTuiles();
        List<String> lesCartesChemin = Ressources.getLesCartes();

        List<JeuDeTuiles> lesJeuxDeTuiles = null;

        for (String chemin : lesImagesJeuxDeTuilesChemin) {
            lesImages = DecoupeurImage.decoupe(chemin,32,32);
        }

        for (String chemin : lesCartesChemin) {
            carteCourante = recuperateurDeCartes.recupereCarte(chemin);
        }

        for (String chemin : lesCartesChemin) {
            lesJeuxDeTuiles = recuperateurDeCartes.recupereJeuxDeTuiles(chemin);
        }

        System.out.println(lesImages.size());
        lesTuiles = new ArrayList<>();

        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            lesTuiles.addAll(jeuDeTuiles.getListeDeTuiles());
        }

        lesTuilesImagees = new HashMap<>();
        for (int i = 0 ; i < lesTuiles.size(); i++) {
            lesTuilesImagees.put(lesTuiles.get(i), lesImages.get(i));
        }

        for (Tuile tuile : lesTuiles) {
            System.out.print(tuile.getId() + " ");
        }

        Position position = new Position(482, 400);
        Rectangle rectangle = new Rectangle(new Position(3, 24), new Dimension(27, 23));
        joueur = new PersonnageJouable(position, new Dimension(33, 47),
                rectangle, null, new Attaque(new Rectangle(0, 0, 30, 30), 1000));

        try {
            imagePersonnage = new Image(String.valueOf(new File("ressources/images/entites/link_epee.png").toURI().toURL()));
            imageProjectile = new Image(String.valueOf(new File("ressources/images/entites/projectile.png").toURI().toURL()));
            imageEnnemi = new Image(String.valueOf(new File("ressources/images/entites/ennemi.png").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Entite entite = new Ennemi(new Position(500, 600), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                new ComportementOctorockTireur(carteCourante), 10);

        carteCourante.ajouterEntite(entite);

        deplaceur = new DeplaceurEntite(carteCourante);
        nombreCalques = carteCourante.getListeDeCalques().size();
    }

    public void start() {
        boucle.setRunning(true);
        Thread boucleThread = new Thread(boucle);
        boucleThread.start();
    }

    public void affichage() {
        gc.clearRect(0, 0, 1000, 1000);
        for (int k = 0; k < nombreCalques; k++) {
            for (int i = 0; i < carteCourante.getDimension().getLargeur(); i++) {
                for (int j = 0; j < carteCourante.getDimension().getHauteur(); j++) {
                    Tuile tuile = carteCourante.getListeDeCalques().get(k).getListeDeTuiles().get(i * (int) carteCourante.getDimension().getLargeur() + j);
                    if (tuile.getId() >= 1) {
                        gc.drawImage(lesImages.get(tuile.getId()),
                                j * 32 - camera.getPositionCameraX(), i * 32 - camera.getPositionCameraY(),
                                32, 32);
                    }
                }
            }
        }
        gc.drawImage(imagePersonnage, joueur.getPosition().getX() - camera.getPositionCameraX(),
                joueur.getPosition().getY() - camera.getPositionCameraY());

        if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
            gc.drawImage(imageProjectile, attaqueJoueur.getPosition().getX() - camera.getPositionCameraX(),
                    attaqueJoueur.getPosition().getY() - camera.getPositionCameraY());
        }

        for (Entite entite : carteCourante.getLesEntites()) {
            if (entite instanceof Ennemi) {
                gc.drawImage(imageEnnemi, entite.getPosition().getX() - camera.getPositionCameraX(),
                        entite.getPosition().getY() - camera.getPositionCameraY());
            }

            if (entite instanceof Projectile) {
                gc.drawImage(imageProjectile, entite.getPosition().getX() - camera.getPositionCameraX(),
                        entite.getPosition().getY() - camera.getPositionCameraY());
            }
        }
        gc.fillText("Vie : " + joueur.getPointsDeVie(), 20, 20);
    }

    @Override
    public void update(int timer) {
        lesTouchesAppuyees = gestionnaireDeTouches.detecte();
        System.out.println(lesTouchesAppuyees);

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
                //joueur.getAttaque().setCollision(collisionAttaque);
                attaqueJoueur = collisionAttaque;
            }
            if (joueur.getDirection() == Direction.GAUCHE) {
                collisionAttaque = new Rectangle(joueur.getPosition().getX()
                        - joueur.getCollision().getDimension().getLargeur(),
                        joueur.getPosition().getY() +
                                (joueur.getDimension().getHauteur() - joueur.getAttaque().getCollision().getDimension().getHauteur()) / 2,
                        joueur.getAttaque().getCollision().getDimension().getLargeur(),
                        joueur.getCollision().getDimension().getHauteur());
                //joueur.getAttaque().setCollision(collisionAttaque);
                attaqueJoueur = collisionAttaque;

            }
            if (joueur.getDirection() == Direction.HAUT) {
                collisionAttaque = new Rectangle(joueur.getPosition().getX() +
                        (joueur.getDimension().getLargeur() - joueur.getAttaque().getCollision().getDimension().getLargeur()) / 2,
                        joueur.getPosition().getY() - joueur.getAttaque().getCollision().getDimension().getHauteur(),
                        joueur.getAttaque().getCollision().getDimension().getLargeur(),
                        joueur.getCollision().getDimension().getHauteur());
                //joueur.getAttaque().setCollision(collisionAttaque);
                attaqueJoueur = collisionAttaque;

            }
            if (joueur.getDirection() == Direction.BAS) {
                collisionAttaque = new Rectangle(joueur.getPosition().getX() +
                        (joueur.getDimension().getLargeur() - joueur.getAttaque().getCollision().getDimension().getLargeur()) / 2,
                        joueur.getPosition().getY() + joueur.getDimension().getHauteur(),
                        joueur.getAttaque().getCollision().getDimension().getLargeur(),
                        joueur.getCollision().getDimension().getHauteur());
                //joueur.getAttaque().setCollision(collisionAttaque);
                attaqueJoueur = collisionAttaque;

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
                if (carteCourante.getDimension().getLargeur() * 30 - (joueur.getPosition().getX()) > 100) {
                    if (((camera.getPositionCameraX() <= carteCourante.getDimension().getLargeur() * 20)) &&
                            (joueur.getPosition().getX() >= gc.getCanvas().getWidth() / 2)) {
                        camera.deplacementCamera(joueur.getVelocite().getX(), 0);
                    }
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_GAUCHE)) {
                boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.GAUCHE, true);
                if (estDeplace && 0 + joueur.getPosition().getY() > 100) {
                    if (!(camera.getPositionCameraX() <= 0) &&
                            (joueur.getPosition().getX() <= carteCourante.getDimension().getLargeur() * 20 +
                                    gc.getCanvas().getWidth() / 2)) {
                        camera.deplacementCamera(-joueur.getVelocite().getX(), 0);
                    }
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_HAUT)) {
                boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.HAUT, true);
                if (estDeplace && !(camera.getPositionCameraY() <= 0) &&
                        (joueur.getPosition().getY() <= carteCourante.getDimension().getHauteur() * 22 +
                                gc.getCanvas().getHeight() / 2)) {
                    camera.deplacementCamera(0, -joueur.getVelocite().getY());
                }
            }

            if (lesTouchesAppuyees.contains(Touche.FLECHE_BAS)) {
                boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.BAS, true);
                if (estDeplace && (carteCourante.getDimension().getLargeur() * carteCourante.getDimension().getLargeur()) - (joueur.getPosition().getY()) > 100 &&
                        (camera.getPositionCameraY() <= carteCourante.getDimension().getHauteur() * 22 &&
                                (joueur.getPosition().getY() >= gc.getCanvas().getHeight() / 2))) {
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
                if (collisionneur.collisionne(attaqueJoueur, collisionEntite)
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

        affichage();
    }
}