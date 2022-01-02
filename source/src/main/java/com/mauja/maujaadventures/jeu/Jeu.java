package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.collisionneurs.CollisionneurCarte;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.entites.PersonnageJouable;
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
    private Camera camera;
    private PersonnageJouable joueur;
    private Image imagePersonnage;
    private int nombreCalques;

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
                rectangle, null, null);
        imagePersonnage = new Image(RecuperateurRessources.getRessource("/images/entites/link_epee.png", getClass()));

        deplaceur = new DeplaceurEntite(carte);
        nombreCalques = carte.getListeDeCalques().size();

    }

    /**
     * Méthode de la boucle de jeu du programme

     * @param input Correspond à une liste de touches taper par l'utilisateur
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void boucle(ArrayList<String> input){

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

                if (input.contains("RIGHT")) {
                    boolean estDeplace = deplaceur.deplace(joueur, 0, Direction.DROITE);
                    if (estDeplace && (carte.getDimension().getLargeur() * 30) - (joueur.getPosition().getX()) > 100) {
                        if (((camera.getPositionCameraX() <= carte.getDimension().getLargeur() * 20)) &&
                                (joueur.getPosition().getX() >= gc.getCanvas().getWidth() / 2)) {
                            camera.deplacementCamera(joueur.getVelocite().getX(), 0);
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
    }
}
