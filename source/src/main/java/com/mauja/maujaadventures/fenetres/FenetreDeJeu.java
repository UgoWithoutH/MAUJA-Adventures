package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.jeu.Jeu;
import com.mauja.maujaadventures.jeu.Observateur;
import com.mauja.maujaadventures.monde.Camera;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.Tuile;
import com.mauja.maujaadventures.utilitaires.DecoupeurImage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FenetreDeJeu implements Observateur {
    private Jeu jeu;

    private Carte carteCourante;
    private Camera camera;

    private List<Tuile> lesTuiles;
    private int nombreCalques;
    private PersonnageJouable joueur;

    private GraphicsContext gc;
    private Image imagePersonnage;
    private Image imageProjectile;
    private Image imageEnnemi;
    private List<Image> lesImages;
    private Map<Tuile, Image> lesTuilesImagees;

    public FenetreDeJeu(GraphicsContext gc, Jeu jeu) {
        this.gc = gc;
        this.jeu = jeu;
        jeu.attacher(this);
        initialiser();
    }

    public void affichage() {
        gc.clearRect(0, 0, 1000, 1000);
        for (int k = 0; k < nombreCalques; k++) {
            for (int y = 0; y < carteCourante.getDimensionCarte().getLargeur(); y++) {
                for (int x = 0; x < carteCourante.getDimensionCarte().getHauteur(); x++) {
                    Tuile tuile = carteCourante.getTuile(x, y, k);
                    if (tuile.getId() >= 1) {
                        gc.drawImage(lesImages.get(tuile.getId()),
                                x * 32 - camera.getPositionCameraX(), y * 32 - camera.getPositionCameraY(),
                                32, 32);
                    }
                }
            }
        }
        gc.drawImage(imagePersonnage, joueur.getPosition().getX() - camera.getPositionCameraX(),
                joueur.getPosition().getY() - camera.getPositionCameraY());

        if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
            gc.drawImage(imageProjectile,
                    joueur.getAttaque().getCollision().getPosition().getX() - camera.getPositionCameraX(),
                    joueur.getAttaque().getCollision().getPosition().getY() - camera.getPositionCameraY());
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

    private void initialiser() {
        carteCourante = jeu.getTableauDeJeu().getCarteCourante();
        nombreCalques = carteCourante.getLaCarte().length;
        lesTuiles = jeu.getTableauDeJeu().getCarteCourante().getLesTuiles();
        camera = jeu.getCamera();
        joueur = jeu.getTableauDeJeu().getJoueur();

        List<String> lesImagesJeuxDeTuilesChemin = Ressources.getLesImagesJeuxDeTuiles();

        for (String chemin : lesImagesJeuxDeTuilesChemin) {
            lesImages = DecoupeurImage.decoupe(chemin,32,32);
        }

        System.out.println(lesTuiles.size());
        System.out.println(carteCourante);

        lesTuilesImagees = new HashMap<>();
        for (int i = 0 ; i < lesTuiles.size(); i++) {
            lesTuilesImagees.put(lesTuiles.get(i), lesImages.get(i));
        }

        try {
            imagePersonnage = new Image(String.valueOf(new File("ressources/images/entites/link_epee.png").toURI().toURL()));
            imageProjectile = new Image(String.valueOf(new File("ressources/images/entites/projectile.png").toURI().toURL()));
            imageEnnemi = new Image(String.valueOf(new File("ressources/images/entites/ennemi.png").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int timer) {
        affichage();
    }
}
