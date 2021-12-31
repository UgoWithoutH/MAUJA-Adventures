package com.mauja.maujaadventures.modele.jeu;

import com.mauja.maujaadventures.modele.chargeurs.Ressources;
import com.mauja.maujaadventures.modele.entites.PersonnageJouable;
import com.mauja.maujaadventures.modele.logique.Dimension;
import com.mauja.maujaadventures.modele.logique.Position;
import com.mauja.maujaadventures.modele.logique.Rectangle;
import com.mauja.maujaadventures.modele.chargeurs.RecuperateurDeCartes;
import com.mauja.maujaadventures.modele.deplaceurs.Deplaceur;
import com.mauja.maujaadventures.modele.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.modele.collisionneurs.Collisionneur;
import com.mauja.maujaadventures.modele.monde.*;
import com.mauja.maujaadventures.modele.utilitaires.Decoupeur;
import com.mauja.maujaadventures.modele.utilitaires.RecuperateurRessources;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.*;

public class Jeu {
    public static final int NOMBRE_TUILES_SUR_LARGEUR_ECRAN = 5;
    public static final int NOMBRE_TUILES_SUR_HAUTEUR_ECRAN = 5;

    private GraphicsContext gc;
    private Deplaceur deplaceur;
    private Collisionneur collisionneur;
    private Map<Tuile, Image> lesTuilesImagees;
    private List<Tuile> lesTuiles;
    private Carte carte;
    private List<Image> lesImages;
    private Camera camera;
    private PersonnageJouable joueur;
    private Image imagePersonnage;
    //private Afficheur afficheur;

    /**
     * Constructeur de Jeu
     * @param gc Contexte Graphique du Jeu
     * @throws FileNotFoundException Exception déclencher si le fichier n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Jeu(GraphicsContext gc) throws FileNotFoundException {
        this.gc = gc;
        deplaceur = new DeplaceurEntite();
        collisionneur = new Collisionneur();
        camera = new Camera( 0, 0);
        initialiser();
    }

    /**
     * Fonction d'initialisation du jeu
     * @throws FileNotFoundException Exception si le fichier de l'image n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void initialiser() throws FileNotFoundException {
        Decoupeur d = new Decoupeur();
        RecuperateurDeCartes recuperateurDeCartes = new RecuperateurDeCartes();

        List<String> lesImagesJeuxDeTuilesChemin = Ressources.getLesImagesJeuxDeTuiles();
        List<String> lesJeuxDeTuilesChemin = Ressources.getLesJeuxDeTuiles();
        List<String> lesCartesChemin = Ressources.getLesCartes();

        List<JeuDeTuiles> lesJeuxDeTuiles = null;

        for (String chemin : lesImagesJeuxDeTuilesChemin) {
            lesImages = d.decoupe(chemin,32,32);
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

        System.out.println("Tuiles : " + lesTuiles.size());
        System.out.println("Images : " + lesImages.size());

        lesTuilesImagees = new HashMap<>();
        for (int i = 0 ; i < lesTuiles.size(); i++) {
            lesTuilesImagees.put(lesTuiles.get(i), lesImages.get(i));
        }

        for (Tuile tuile : lesTuiles) {
            System.out.print(tuile.getId() + " ");
        }

        System.out.println("TUILES : " + Tuile.getNombreTuiles());

        Position position = new Position(482, 400);
        Rectangle rectangle = new Rectangle(new Position(3, 46), new Dimension(27, 15));
        joueur = new PersonnageJouable(position, new Dimension(33, 47),
                rectangle, null, null);
        imagePersonnage = new Image(RecuperateurRessources.getRessource("/images/entites/link_epee.png", getClass()));
        System.out.println(lesTuilesImagees.size());
    }

    /**
     * Méthode de la boucle de jeu du programme

     * @param input Correspond à une liste de touches taper par l'utilisateur
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void boucle(ArrayList<String> input){
        //final long startNanoTime = System.nanoTime();
        int nombreCalques = carte.getListeDeCalques().size();

        List<Tuile> lesTuilesCourantes = carte.getListeDeCalques().get(0).getListeDeTuiles();
        double largeurCarte = carte.getDimension().getLargeur();
        double hauteurCarte = carte.getDimension().getHauteur();


        System.out.println(lesTuilesCourantes.size());
        //exit(0);

        new AnimationTimer()
        {
            /**
             * Lorsque l'on appuie sur une touche cette méthode est appelé et on le, rajoute dans la liste
             *
             * @param currentNanoTime Correspond au temps passé
             * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
             */
            public void handle(long currentNanoTime)
            {
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
                Rectangle positionPerso;

                boolean collisionnage = false;

                if (input.contains("LEFT")) {
                    positionPerso = new Rectangle(new Position(joueur.getPosition().getX() +
                            joueur.getCollision().getPosition().getX() - 3, joueur.getPosition().getY()
                            + joueur.getCollision().getPosition().getY() - 20), joueur.getCollision().getDimension());

                    for (int i = 0; i < carte.getDimension().getLargeur(); i++) {
                        for (int j = 0; j < carte.getDimension().getHauteur(); j++) {
                            Tuile tuile = carte.getListeDeCalques().get(1).getListeDeTuiles().get(i * 80 + j);
                            if (tuile.getId() >= 1 && tuile.getCollision() != null) {
                                Rectangle collision = new Rectangle(new Position(
                                        tuile.getCollision().getPosition().getX() + j * 32,
                                        tuile.getCollision().getPosition().getY() + i * 32),
                                        tuile.getCollision().getDimension());
                                if (collisionneur.collision(positionPerso, collision)) {
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + joueur.getPosition() + " " + joueur.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(joueur, joueur.getPosition().getX() - 3, joueur.getPosition().getY());
                        if (0 + joueur.getPosition().getY() > 100) {
                            deplaceur.deplace(joueur, joueur.getPosition().getX() - 3, joueur.getPosition().getY());
                            if (!(camera.getPositionCameraX() <= 0) &&
                                    (joueur.getPosition().getX() <= carte.getDimension().getLargeur() * 20 +
                                            gc.getCanvas().getWidth() / 2)) {
                                camera.deplacementCamera(-3, 0);
                            }
                        }
                    }
                }

                if (input.contains("RIGHT")) {
                    positionPerso = new Rectangle(new Position(joueur.getPosition().getX() +
                            joueur.getCollision().getPosition().getX() + 3, joueur.getPosition().getY()
                            + joueur.getCollision().getPosition().getY() - 20), joueur.getCollision().getDimension());

                    for (int i = 0; i < carte.getDimension().getLargeur(); i++) {
                        for (int j = 0; j < carte.getDimension().getHauteur(); j++) {
                            Tuile tuile = carte.getListeDeCalques().get(1).getListeDeTuiles().get(i * 80 + j);
                            if (tuile.getId() >= 1 && tuile.getCollision() != null) {
                                Rectangle collision = new Rectangle(new Position(
                                        tuile.getCollision().getPosition().getX() + j * 32,
                                        tuile.getCollision().getPosition().getY() + i * 32),
                                        tuile.getCollision().getDimension());
                                //System.out.println(collision + " " + e.getPosition());
                                if (collisionneur.collision(positionPerso, collision)) {
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + joueur.getPosition() + " " + joueur.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(joueur, joueur.getPosition().getX() + 3, joueur.getPosition().getY());
                        if ((carte.getDimension().getLargeur() * 30) - (joueur.getPosition().getX()) > 100) {
                            deplaceur.deplace(joueur, joueur.getPosition().getX() + 3, joueur.getPosition().getY());
                            if (((camera.getPositionCameraX() <= carte.getDimension().getLargeur() * 20)) &&
                                    (joueur.getPosition().getX() >= gc.getCanvas().getWidth() / 2)) {
                                camera.deplacementCamera(3, 0);
                            }
                        }
                    }
                }

                if (input.contains("UP")) {
                    positionPerso = new Rectangle(new Position(joueur.getPosition().getX() +
                            joueur.getCollision().getPosition().getX(), joueur.getPosition().getY()
                            + joueur.getCollision().getPosition().getY() - 23), joueur.getCollision().getDimension());
                    for (int i = 0; i < carte.getDimension().getLargeur(); i++) {
                        for (int j = 0; j < carte.getDimension().getHauteur(); j++) {
                            Tuile tuile = carte.getListeDeCalques().get(1).getListeDeTuiles().get(i * 80 + j);
                            if (tuile.getId() >= 1 && tuile.getCollision() != null) {
                                Rectangle collision = new Rectangle(new Position(
                                        tuile.getCollision().getPosition().getX() + j * 32,
                                        tuile.getCollision().getPosition().getY() + i * 32),
                                        tuile.getCollision().getDimension());
                                //System.out.println(collision + " " + e.getPosition());
                                if (collisionneur.collision(positionPerso, collision)) {
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + joueur.getPosition() + " " + joueur.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(joueur, joueur.getPosition().getX(), joueur.getPosition().getY() - 3);
                        if (!(camera.getPositionCameraY() <= 0) &&
                                (joueur.getPosition().getY() <= carte.getDimension().getHauteur() * 22 +
                                        gc.getCanvas().getHeight() / 2)) {
                            camera.deplacementCamera(0, -3);
                            System.out.println(camera.getPositionCameraY());
                        }
                    }
                }

                if (input.contains("DOWN")) {
                    positionPerso = new Rectangle(new Position(joueur.getPosition().getX() +
                            joueur.getCollision().getPosition().getX(), joueur.getPosition().getY()
                            + joueur.getCollision().getPosition().getY() - 16), joueur.getCollision().getDimension());

                    for (int i = 0; i < carte.getDimension().getLargeur(); i++) {
                        for (int j = 0; j < carte.getDimension().getHauteur(); j++) {
                            Tuile tuile = carte.getListeDeCalques().get(1).getListeDeTuiles().get(i * 80 + j);
                            if (tuile.getId() >= 1 && tuile.getCollision() != null) {
                                Rectangle collision = new Rectangle(new Position(
                                        tuile.getCollision().getPosition().getX() + j * 32,
                                        tuile.getCollision().getPosition().getY() + i * 32),
                                        tuile.getCollision().getDimension());
                                //System.out.println(collision + " " + e.getPosition());
                                if (collisionneur.collision(positionPerso, collision)) {
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + joueur.getPosition() + " " + joueur.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(joueur, joueur.getPosition().getX(), joueur.getPosition().getY() + 3);
                        if ((carte.getDimension().getLargeur() * carte.getDimension().getLargeur()) - (joueur.getPosition().getY()) > 100 &&
                                (camera.getPositionCameraY() <= carte.getDimension().getHauteur() * 22 &&
                                        (joueur.getPosition().getY() >= gc.getCanvas().getHeight() / 2))) {
                            camera.deplacementCamera(0, 3);
                        }
                    }
                }

                gc.drawImage(imagePersonnage, joueur.getPosition().getX() - camera.getPositionCameraX(),
                        joueur.getPosition().getY() - camera.getPositionCameraY());
            }

        }.start();
    };

    public Camera getCamera(){
        return camera;
    }
}
