package com.mauja.maujaadventures.jeu;


import com.mauja.maujaadventures.comportements.Comportement;
import com.mauja.maujaadventures.comportements.ComportementNull;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.chargeurs.RecuperateurDeCartes;
import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.monde.*;
import com.mauja.maujaadventures.utilitaires.DecoupeurImage;
import com.mauja.maujaadventures.utilitaires.RecuperateurRessources;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.*;

public class Jeu {
    public static final int NOMBRE_TUILES_SUR_LARGEUR_ECRAN = 5;
    public static final int NOMBRE_TUILES_SUR_HAUTEUR_ECRAN = 5;

    private GraphicsContext gc;
    private DeplaceurEntite deplaceur;
    private CollisionneurAABB collisionneur;
    private Map<Tuile, Image> lesTuilesImagees;
    private List<Tuile> lesTuiles;
    private Carte carte;
    private List<Image> lesImages;
    private List<Entite> lesEntites;
    private Camera camera;
    private PersonnageJouable joueur;
    private Rectangle attaqueJoueur;
    private Image imagePersonnage;
    private Image imageProjectile;
    private Image imageEnnemi;
    private int nombreCalques;
    private int tempsAttaque = 0, tempsDefense = 0;

    /**
     * Constructeur de Jeu
     * @param gc Contexte Graphique du Jeu
     * @throws FileNotFoundException Exception déclencher si le fichier n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Jeu(GraphicsContext gc) throws FileNotFoundException {
        this.gc = gc;
        collisionneur = new CollisionneurAABB();
        camera = new Camera( 0, 0);
        lesEntites = new ArrayList<>();
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
            carte = recuperateurDeCartes.recupereCarte(chemin);
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
        imagePersonnage = new Image(RecuperateurRessources.getRessource("/images/entites/link_epee.png", getClass()));
        imageProjectile = new Image(RecuperateurRessources.getRessource("/images/entites/projectile.png", getClass()));
        imageEnnemi = new Image(RecuperateurRessources.getRessource("/images/entites/ennemi.png", getClass()));

        lesEntites.add(new Ennemi(new Position(500, 600), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), null, null, new ComportementNull(), 10));

        deplaceur = new DeplaceurEntite(carte);
        nombreCalques = carte.getListeDeCalques().size();
    }

    /**
     * Méthode de la boucle de jeu du programme

     * @param input Correspond à une liste de touches taper par l'utilisateur
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void boucle(ArrayList<String> input) {

        new AnimationTimer() {
            /**
             * Lorsque l'on appuie sur une touche cette méthode est appelé et on le, rajoute dans la liste
             *
             * @param currentNanoTime Correspond au temps passé
             * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
             */
            public void handle(long currentNanoTime) {
                if (input.contains("B") && tempsAttaque > joueur.getAttaque().getDuree()) {
                    System.out.println("Je me protège");
                    joueur.setEtatAction(EtatAction.SE_PROTEGE);
                }
                else {
                    joueur.setEtatAction(EtatAction.SANS_ACTION);
                }

                if (input.contains("SPACE")) {
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
                    if (input.contains("RIGHT")) {
                        boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.DROITE);
                        if (estDeplace && (carte.getDimension().getLargeur() * 30) - (joueur.getPosition().getX()) > 100) {
                            if (((camera.getPositionCameraX() <= carte.getDimension().getLargeur() * 20)) &&
                                    (joueur.getPosition().getX() >= gc.getCanvas().getWidth() / 2)) {
                                camera.deplacementCamera(joueur.getVelocite().getX(), 0);
                            }
                        }
                    }

                    if (input.contains("LEFT")) {
                        boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.GAUCHE);
                        if (estDeplace && 0 + joueur.getPosition().getY() > 100) {
                            if (!(camera.getPositionCameraX() <= 0) &&
                                    (joueur.getPosition().getX() <= carte.getDimension().getLargeur() * 20 +
                                            gc.getCanvas().getWidth() / 2)) {
                                camera.deplacementCamera(-joueur.getVelocite().getX(), 0);
                            }
                        }
                    }

                    if (input.contains("UP")) {
                        boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.HAUT);
                        if (estDeplace && !(camera.getPositionCameraY() <= 0) &&
                                (joueur.getPosition().getY() <= carte.getDimension().getHauteur() * 22 +
                                        gc.getCanvas().getHeight() / 2)) {
                            camera.deplacementCamera(0, -joueur.getVelocite().getY());
                        }
                    }

                    if (input.contains("DOWN")) {
                        boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.BAS);
                        if (estDeplace && (carte.getDimension().getLargeur() * carte.getDimension().getLargeur()) - (joueur.getPosition().getY()) > 100 &&
                                (camera.getPositionCameraY() <= carte.getDimension().getHauteur() * 22 &&
                                        (joueur.getPosition().getY() >= gc.getCanvas().getHeight() / 2))) {
                            camera.deplacementCamera(0, joueur.getVelocite().getY());
                        }
                    }
                }

                // Detection attaque joueur et ennemis
                if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
                    for (Entite entite : lesEntites) {
                        if (entite instanceof Ennemi ennemi) {
                            Rectangle collision = new Rectangle(ennemi.getCollision().getPosition().getX() + ennemi.getPosition().getX(),
                                    ennemi.getCollision().getPosition().getY() + ennemi.getPosition().getY(),
                                    ennemi.getCollision().getDimension());
                            if (CollisionneurAABB.collision(attaqueJoueur, collision)) {
                                ennemi.setPointsDeVie(ennemi.getPointsDeVie() - joueur.getAttaque().getDegats());
                                if (ennemi.getPointsDeVie() <= 0) {
                                    lesEntites.remove(ennemi);
                                }
                            }
                            Rectangle collisionJoueur = new Rectangle(joueur.getCollision().getPosition().getX() + joueur.getPosition().getX(),
                                    joueur.getCollision().getPosition().getY() + joueur.getPosition().getY(),
                                    joueur.getCollision().getDimension());
                            System.out.println(collisionJoueur + " : " + collision);
                            if (CollisionneurAABB.collision(collisionJoueur, collision)) {
                                joueur.setPointsDeVie(joueur.getPointsDeVie() - ennemi.getAttaque().getDegats());
                            }
                        }
                    }
                }

                // MAJ ennemis
                for (Entite entite : lesEntites) {
                    if (entite instanceof Ennemi ennemi) {
                        Comportement comportement = ennemi.getComportement();
                        if (comportement != null) {
                            comportement.agit(ennemi, 0);
                        }
                    }
                }

                affichage();
            }
        }.start();
    };

    public void affichage() {
        gc.clearRect(0, 0, 1000, 1000);
        for (int k = 0; k < nombreCalques; k++) {
            for (int i = 0; i < carte.getDimension().getLargeur(); i++) {
                for (int j = 0; j < carte.getDimension().getHauteur(); j++) {
                    Tuile tuile = carte.getListeDeCalques().get(k).getListeDeTuiles().get(i * (int)carte.getDimension().getLargeur() + j);
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

        for (Entite entite : lesEntites) {
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
}
