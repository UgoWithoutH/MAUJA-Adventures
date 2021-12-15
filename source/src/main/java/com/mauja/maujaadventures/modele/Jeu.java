package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.RecuperateurDeCartes;
import com.mauja.maujaadventures.modele.action.affiche.AfficheurEntite;
import com.mauja.maujaadventures.modele.action.deplace.Deplaceur;
import com.mauja.maujaadventures.modele.action.deplace.DeplaceurEntite;
import com.mauja.maujaadventures.modele.monde.*;
import com.mauja.maujaadventures.modele.personnage.Entite;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;

public class Jeu {
    public static final int NOMBRE_TUILES_SUR_LARGEUR_ECRAN = 5;
    public static final int NOMBRE_TUILES_SUR_HAUTEUR_ECRAN = 5;

    private ContexteGraphique contexteGraphique;
    private GraphicsContext gc;
    private Deplaceur deplaceur;
    private Collisionneur collisionneur;
    private Map<Tuile, Image> lesTuilesImagees;
    private List<Tuile> lesTuiles;
    private Carte carte;
    private List<Image> lesImages;
    private Camera camera;
    //private Afficheur afficheur;

    /**
     * Constructeur de Jeu
     * @param gc Contexte Graphique du Jeu
     * @throws FileNotFoundException Exception déclencher si le fichier n'est pas trouvé
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Jeu(GraphicsContext gc) throws FileNotFoundException {
        this.gc = gc;
        contexteGraphique = new Caneva(gc);
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
        File f = new File("resources/images/tilesets/hyptosis_tile-art-batch-3.png");
        String chemin = null;
        try {
            chemin = f.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        lesImages = d.decoupe(chemin,32,32);
        //images.addAll(d.decoupe("C:\\Users\\jtrem\\Downloads\\images\\hyptosis_tile-art-batch-5.png", 32, 32));
        RecuperateurDeCartes recuperateurDeCartes = new RecuperateurDeCartes();
        f = new File("resources/cartes/carteTest2.tmx");
        chemin = f.getAbsolutePath();

        //carte = recuperateurDeCartes.recupereCarte("D:\\Cours\\2021-2022\\Projet\\Repository\\mauja-adventures\\source\\resources\\cartes\\carteTest.tmx");
        carte = recuperateurDeCartes.recupereCarte(f.getAbsolutePath());
        //List<JeuDeTuiles> lesJeuxDeTuiles = recuperateurDeCartes.recupereJeuxDeTuiles("D:\\Cours\\2021-2022\\Projet\\Repository\\mauja-adventures\\source\\resources\\cartes\\carteTest.tmx");
        List<JeuDeTuiles> lesJeuxDeTuiles = recuperateurDeCartes.recupereJeuxDeTuiles(f.getAbsolutePath());
        System.out.println(lesImages.size());
        lesTuiles = new ArrayList<Tuile>();

        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            lesTuiles.addAll(jeuDeTuiles.getListeDeTuiles());
        }

        System.out.println("Tuiles : " + lesTuiles.size());
        System.out.println("Images : " + lesImages.size());

        lesTuilesImagees = new HashMap<Tuile, Image>();
        for (int i = 0 ; i < lesTuiles.size(); i++) {
            lesTuilesImagees.put(lesTuiles.get(i), lesImages.get(i));
        }
        System.out.println(lesTuilesImagees.size());
    }

    /**
     * Méthode de la boucle de jeu du programme

     * @param input Correspond à une liste de touches taper par l'utilisateur
     * @param e Entité que l'on fait bouger
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void boucle(ArrayList<String> input, Entite e){
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
                contexteGraphique.effacer(new Position(0, 0), new Dimension(1000, 1000));
                for (int k = 0; k < nombreCalques; k++) {
                    for (int i = 0; i < carte.getDimension().getLargeur(); i++) {
                        for (int j = 0; j < carte.getDimension().getHauteur(); j++) {
                            Tuile tuile = carte.getListeDeCalques().get(k).getListeDeTuiles().get(i * (int)carte.getDimension().getLargeur() + j);
                            if (tuile.getId() >= 1) {
                                contexteGraphique.dessiner(
                                        lesImages.get(tuile.getId()),
                                        new Position((j * 32) - camera.getPositionCameraX(), i * 32 - camera.getPositionCameraY()),
                                        new Dimension(32, 32));
                            }
                        }
                    }
                }
                Rectangle positionPerso;

                /*int positionY = (int) ((e.getPosition().getPositionY()
                        + e.getCollision().getPosition().getPositionY()) / 32);
                int positionX = (int) ((e.getPosition().getPositionX()
                        + e.getCollision().getPosition().getPositionX()) / 32);




                System.out.println(e.getCollision().getPosition().getPositionY());
                System.out.println(((int) (e.getPosition().getPositionY() /32)) * 32);

                caseSuivante = lesTuilesCourantes.get(positionY * 30 + positionX).getCollision();
                if (caseSuivante == null) {
                    caseSuivante2 = null;
                }
                else {
                    caseSuivante2 = new Rectangle(new Position(((int) (e.getPosition().getPositionX() / 32)) * 32 +
                            caseSuivante.getPosition().getPositionX(), ((int) (e.getPosition().getPositionX() / 32)) * 32
                            + caseSuivante.getPosition().getPositionY()), caseSuivante.getDimension());
                    System.out.println("Position perso : " + e.getPosition().getPositionY() + " " + caseSuivante2 + " Perso :" + positionPerso);
                }*/

                boolean collisionnage = false;

                if (input.contains("LEFT")) {
                    positionPerso = new Rectangle(new Position(e.getPosition().getX() +
                            e.getCollision().getPosition().getX() - 3, e.getPosition().getY()
                            + e.getCollision().getPosition().getY() - 20), e.getCollision().getDimension());

                    for (int i = 0; i < carte.getDimension().getLargeur(); i++) {
                        for (int j = 0; j < carte.getDimension().getHauteur(); j++) {
                            Tuile tuile = carte.getListeDeCalques().get(1).getListeDeTuiles().get(i * 80 + j);
                            if (tuile.getId() >= 1 && tuile.getCollision() != null) {
                                Rectangle collision = new Rectangle(new Position(
                                        tuile.getCollision().getPosition().getX() + j * 32,
                                        tuile.getCollision().getPosition().getY() + i * 32),
                                        tuile.getCollision().getDimension());
                                if (collisionneur.collision(positionPerso, collision)) {
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + e.getPosition() + " " + e.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(e, e.getPosition().getX() - 3, e.getPosition().getY());
                        if (0 + e.getPosition().getY() > 100) {
                            deplaceur.deplace(e, e.getPosition().getX() - 3, e.getPosition().getY());
                            if (!(camera.getPositionCameraX() <= 0) &&
                                    (e.getPosition().getX() <= carte.getDimension().getLargeur() * 20 +
                                            gc.getCanvas().getWidth() / 2)) {
                                camera.deplacementCamera(-3, 0);
                            }
                        }
                    }
                }

                if (input.contains("RIGHT")) {
                    positionPerso = new Rectangle(new Position(e.getPosition().getX() +
                            e.getCollision().getPosition().getX() + 3, e.getPosition().getY()
                            + e.getCollision().getPosition().getY() - 20), e.getCollision().getDimension());

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
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + e.getPosition() + " " + e.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(e, e.getPosition().getX() + 3, e.getPosition().getY());
                        if ((carte.getDimension().getLargeur() * 30) - (e.getPosition().getX()) > 100) {
                            deplaceur.deplace(e, e.getPosition().getX() + 3, e.getPosition().getY());
                            if (((camera.getPositionCameraX() <= carte.getDimension().getLargeur() * 20)) &&
                                    (e.getPosition().getX() >= gc.getCanvas().getWidth() / 2)) {
                                camera.deplacementCamera(3, 0);
                            }
                        }
                    }
                }

                if (input.contains("UP")) {
                    positionPerso = new Rectangle(new Position(e.getPosition().getX() +
                            e.getCollision().getPosition().getX(), e.getPosition().getY()
                            + e.getCollision().getPosition().getY() - 23), e.getCollision().getDimension());
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
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + e.getPosition() + " " + e.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(e, e.getPosition().getX(), e.getPosition().getY() - 3);
                        if (!(camera.getPositionCameraY() <= 0) &&
                                (e.getPosition().getY() <= carte.getDimension().getHauteur() * 22 +
                                        gc.getCanvas().getHeight() / 2)) {
                            camera.deplacementCamera(0, -3);
                            System.out.println(camera.getPositionCameraY());
                        }
                    }
                }

                if (input.contains("DOWN")) {
                    positionPerso = new Rectangle(new Position(e.getPosition().getX() +
                            e.getCollision().getPosition().getX(), e.getPosition().getY()
                            + e.getCollision().getPosition().getY() - 16), e.getCollision().getDimension());

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
                                    System.out.println(collision + " Position perso :" + positionPerso + " " + e.getPosition() + " " + e.getCollision());

                                    collisionnage = true;
                                }
                            }
                        }
                    }
                    if (!collisionnage) {
                        System.out.println(" Position perso :" + positionPerso);
                        deplaceur.deplace(e, e.getPosition().getX(), e.getPosition().getY() + 3);
                        if ((carte.getDimension().getLargeur() * carte.getDimension().getLargeur()) - (e.getPosition().getY()) > 100 &&
                                (camera.getPositionCameraY() <= carte.getDimension().getHauteur() * 22 &&
                                        (e.getPosition().getY() >= gc.getCanvas().getHeight() / 2))) {
                            camera.deplacementCamera(0, 3);
                        }
                    }
                }

                //System.out.println(e.toString());
                AfficheurEntite ae = new AfficheurEntite();
                //camera.centrerSurEntite(e);
                ae.affiche(e, new Position(e.getPosition().getX() - camera.getPositionCameraX(),
                                e.getPosition().getY() - camera.getPositionCameraY()), contexteGraphique,
                        Jeu.this);
            }

        }.start();
    };

    public Camera getCamera(){
        return camera;
    }
    public Collisionneur getCollisionneur() {
        return collisionneur;
    }

    public Deplaceur getDeplaceur() {
        return deplaceur;
    }

    public ContexteGraphique getContexteGraphique() {
        return contexteGraphique;
    }

    public GraphicsContext getGraphicsContext(){
        return gc;
    }

    /**
     * Redéfintion du HashCode
     * @return Hachage des attributs de la classe Jeu
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 31*contexteGraphique.hashCode()+31*deplaceur.hashCode()+31*collisionneur.hashCode();
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Jeu autre = (Jeu) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param j Jeu que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Jeu j) {
        boolean resultat = (contexteGraphique.equals(j.getContexteGraphique())) && (deplaceur.equals(j.getDeplaceur())) &&
                (collisionneur.equals(j.getCollisionneur()));
        return resultat;
    }

    /**
     * Redéfinition du toString
     * @return Chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Jeu{" +
                "contexteGraphique=" + contexteGraphique.toString() +
                ", deplaceur=" + deplaceur.toString() +
                ", collisionneur=" + collisionneur.toString() +
                ", lesTuilesImagees=" + lesTuilesImagees.toString() +
                ", lesTuiles=" + lesTuiles.toString() +
                ", carte=" + carte.toString() +
                ", lesImages=" + lesImages.toString() +
                ", camera=" + camera.toString() +
                '}';
    }
}
