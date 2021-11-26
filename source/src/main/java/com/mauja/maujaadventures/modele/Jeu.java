package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.RecuperateurDeCartes;
import com.mauja.maujaadventures.modele.action.affiche.AfficheurEntite;
import com.mauja.maujaadventures.modele.action.deplace.Deplaceur;
import com.mauja.maujaadventures.modele.action.deplace.DeplaceurEntite;
import com.mauja.maujaadventures.modele.monde.Carte;
import com.mauja.maujaadventures.modele.monde.Decoupeur;
import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.monde.Tuile;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jeu {
    public static final int NOMBRE_TUILES_SUR_LARGEUR_ECRAN = 5;
    public static final int NOMBRE_TUILES_SUR_HAUTEUR_ECRAN = 5;

    private ContexteGraphique contexteGraphique;
    private Deplaceur deplaceur;
    private Collisionneur collisionneur;
    private Map<Tuile, Image> lesTuilesImagees;
    private List<Tuile> lesTuiles;
    private Carte carte;
    private List<Image> lesImages;
    //private Afficheur afficheur;

    /**
     * Constructeur de la classe Jeu
     * @param gc Contexte graphique à afficher
     */
    public Jeu(GraphicsContext gc) throws FileNotFoundException {
        contexteGraphique = new Caneva(gc);
        deplaceur = new DeplaceurEntite();
        collisionneur = new Collisionneur();
        initialiser();
    }

    public void initialiser() throws FileNotFoundException {
        Decoupeur d = new Decoupeur();
        lesImages = d.decoupe("C:\\Users\\jtrem\\Downloads\\images\\hyptosis_tile-art-batch-3.png",32,32);
        //images.addAll(d.decoupe("C:\\Users\\jtrem\\Downloads\\images\\hyptosis_tile-art-batch-5.png", 32, 32));
        RecuperateurDeCartes recuperateurDeCartes = new RecuperateurDeCartes();
        carte = recuperateurDeCartes.recupereCarte("D:\\Cours\\2021-2022\\Projet\\Repository\\mauja-adventures\\source\\src\\main\\resources\\com\\mauja\\maujaadventures\\carteTest.tmx");
        List<JeuDeTuiles> lesJeuxDeTuiles = recuperateurDeCartes.recupereJeuxDeTuiles("D:\\Cours\\2021-2022\\Projet\\Repository\\mauja-adventures\\source\\src\\main\\resources\\com\\mauja\\maujaadventures\\carteTest.tmx");
        System.out.println(lesImages.size());
        lesTuiles = new ArrayList<Tuile>();

        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            lesTuiles.addAll(jeuDeTuiles.getListeDeTuiles());
        }

        lesTuilesImagees = new HashMap<Tuile, Image>();
        for (int i = 0 ; i < lesTuiles.size(); i++) {
            lesTuilesImagees.put(lesTuiles.get(i), lesImages.get(i));
        }
        System.out.println(lesTuilesImagees.size());
    }

    /**
     * Méthode de la boucle de jeu du programme

     * @param input Correspond à une liste detouche taper par l'utilisateur
     * @param e Entité que l'on fait bouger
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void boucle(ArrayList<String> input, Entite e){
        //final long startNanoTime = System.nanoTime();
        int nombreCalques = carte.getListeDeCalques().size();
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
                    for (int i = 0; i < 30; i++) {
                        for (int j = 0; j < 30; j++) {
                            Tuile tuile = carte.getListeDeCalques().get(k).getListeDeTuiles().get(i * 30 + j);
                            if (tuile.getId() > 1) {
                                contexteGraphique.dessiner(
                                        new ProprietesImage(lesImages.get(tuile.getId())),
                                        new Position(j * 32, i * 32),
                                        new Dimension(32, 32));
                        }
                    }
                }
            }
                if (input.contains("LEFT") &&
                        carte.getListeDeCalques().get(2).getListeDeTuiles().get((int) ((e.getPosition().getPositionY() / 32) * 30 + (e.getPosition().getPositionX() % 32))).getId() != 258) {
                    System.out.println(carte.getListeDeCalques().get(1).getListeDeTuiles().get((int) ((e.getPosition().getPositionX() / 32) * 30 + (e.getPosition().getPositionX() % 32 - 1))).getId());
                    deplaceur.deplace(e,e.getPosition().getPositionX() - 3, e.getPosition().getPositionY());
                }

                if (input.contains("RIGHT") &&
                        carte.getListeDeCalques().get(2).getListeDeTuiles().get((int) ((e.getPosition().getPositionY() / 32) * 30 + (e.getPosition().getPositionX() % 32 + 1))).getId() != 258)
                    deplaceur.deplace(e, e.getPosition().getPositionX() + 3, e.getPosition().getPositionY());
                if (input.contains("UP") &&
                        carte.getListeDeCalques().get(2).getListeDeTuiles().get((int) ((e.getPosition().getPositionY() / 32 - 1) * 30 + (e.getPosition().getPositionX() % 32))).getId() != 258)
                    deplaceur.deplace(e, e.getPosition().getPositionX(),e.getPosition().getPositionY() - 3);
                if (input.contains("DOWN") &&
                        carte.getListeDeCalques().get(2).getListeDeTuiles().get((int) ((e.getPosition().getPositionY() / 32 + 1) * 30 + (e.getPosition().getPositionX() % 32))).getId() != 258)
                    deplaceur.deplace(e, e.getPosition().getPositionX(), e.getPosition().getPositionY() + 3);
                //System.out.println(e.toString());
                AfficheurEntite ae = new AfficheurEntite();
                ae.affiche(e, e.getPosition(), contexteGraphique);
            }

        }.start();
    };
    public Collisionneur getCollisionneur() {
        return collisionneur;
    }

    public void setCollisionneur(Collisionneur collisionneur) {
        this.collisionneur = collisionneur;
    }

    public Deplaceur getDeplaceur() {
        return deplaceur;
    }

    public void setDeplaceur(Deplaceur deplaceur) {
        this.deplaceur = deplaceur;
    }
    public ContexteGraphique getContexteGraphique() {
        return contexteGraphique;
    }

    public void setContexteGraphique(ContexteGraphique contexteGraphique) {
        this.contexteGraphique = contexteGraphique;
    }
    @Override
    public int hashCode() {
        return 31*contexteGraphique.hashCode()+31*deplaceur.hashCode()+31*collisionneur.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Jeu autre = (Jeu) obj;
        return equals(autre);
    }

    public boolean equals(Jeu j) {
        boolean resultat = (contexteGraphique.equals(j.getContexteGraphique())) && (deplaceur.equals(j.getDeplaceur())) &&
                (collisionneur.equals(j.getCollisionneur()));
        return resultat;
    }
}
