package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.Levier;
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
    private Image imageLevierPasActif;
    private Image imageLevierActif;
    private List<Image> lesImages;
    private Map<Tuile, Image> lesTuilesImagees;
    private int test= 0 ;

    public FenetreDeJeu(GraphicsContext gc, Jeu jeu) {
        this.gc = gc;
        this.jeu = jeu;
        jeu.attacher(this);
        initialiser();
        ajoutElementParsage(GestionnaireInteractions.getInstance().getElementAAjouter());
    }

    public void ajoutElementParsage(List<ElementInteractif> list){
        carteCourante.getLesElementsInteractif().addAll(list);
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

        for (ElementInteractif elementInteractif : carteCourante.getLesElementsInteractif()) {
            if (elementInteractif instanceof Ennemi ennemi) {
                gc.drawImage(imageEnnemi, ennemi.getPosition().getX() - camera.getPositionCameraX(),
                        ennemi.getPosition().getY() - camera.getPositionCameraY());
            }

            if (elementInteractif instanceof Projectile projectile) {
                gc.drawImage(imageProjectile, projectile.getPosition().getX() - camera.getPositionCameraX(),
                        projectile.getPosition().getY() - camera.getPositionCameraY());
            }

            if (elementInteractif instanceof Levier levier) {
                if(test == 0){
                    test++;
                }
                if(levier.isActive()) {
                    gc.drawImage(imageLevierActif, levier.getPosition().getX() - camera.getPositionCameraX(),
                            levier.getPosition().getY() - camera.getPositionCameraY());
                }else{
                    gc.drawImage(imageLevierPasActif, levier.getPosition().getX() - camera.getPositionCameraX(),
                            levier.getPosition().getY() - camera.getPositionCameraY());
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
        gc.fillText("Vie : " + joueur.getPointsDeVie(), 20, 20);
    }

    private void initialiser() {
        carteCourante = jeu.getTableauDeJeu().getCarteCourante();
        nombreCalques = carteCourante.getListeDeCalques().size();
        lesTuiles = jeu.getTableauDeJeu().getLesTuiles();
        camera = jeu.getCamera();
        joueur = jeu.getTableauDeJeu().getJoueur();

        List<String> lesImagesJeuxDeTuilesChemin = Ressources.getLesImagesJeuxDeTuiles();

        for (String chemin : lesImagesJeuxDeTuilesChemin) {
            lesImages = DecoupeurImage.decoupe(chemin,32,32);
        }

        lesTuilesImagees = new HashMap<>();
        for (int i = 0 ; i < lesTuiles.size(); i++) {
            lesTuilesImagees.put(lesTuiles.get(i), lesImages.get(i));
        }

        try {
            imagePersonnage = new Image(String.valueOf(new File("ressources/images/entites/link_epee.png").toURI().toURL()));
            imageProjectile = new Image(String.valueOf(new File("ressources/images/entites/projectile.png").toURI().toURL()));
            imageEnnemi = new Image(String.valueOf(new File("ressources/images/entites/ennemi.png").toURI().toURL()));
            imageLevierPasActif = new Image(String.valueOf(new File("ressources/images/entites/levierPasActif.png").toURI().toURL()));
            imageLevierActif = new Image(String.valueOf(new File("ressources/images/entites/levierActif.png").toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int timer) {
        affichage();
    }
}
