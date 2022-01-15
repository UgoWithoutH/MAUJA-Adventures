package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Camera;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.Tuile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;

public class FenetreDeJeu {
    private Carte carteCourante;
    private Camera camera;
    private PersonnageJouable joueur;
    private Rectangle attaqueJoueur;
    private int nombreCalques;

    private GraphicsContext gc;
    private Image imagePersonnage;
    private Image imageProjectile;
    private Image imageEnnemi;
    private Map<Tuile, Image> lesTuilesImagees;
    private List<Image> lesImages;

    public FenetreDeJeu(Carte carteCourante, Camera camera, PersonnageJouable joueur, int nombreCalques, GraphicsContext gc, Image imagePersonnage, Image imageProjectile, Image imageEnnemi, Map<Tuile, Image> lesTuilesImagees, List<Image> lesImages) {
        this.carteCourante = carteCourante;
        this.camera = camera;
        this.joueur = joueur;
        this.nombreCalques = nombreCalques;
        this.gc = gc;
        this.imagePersonnage = imagePersonnage;
        this.imageProjectile = imageProjectile;
        this.imageEnnemi = imageEnnemi;
        this.lesTuilesImagees = lesTuilesImagees;
        this.lesImages = lesImages;
    }

    public void affichage(Rectangle attaqueJoueur) {
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
            //System.out.println("Coucou");
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
}
