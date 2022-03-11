package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.affichages.JeuDeTuilesGraphique;
import com.mauja.maujaadventures.affichages.TuileGraphique;
import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.jeu.Jeu;
import com.mauja.maujaadventures.jeu.Observateur;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Camera;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.JeuDeTuiles;
import com.mauja.maujaadventures.monde.Tuile;
import com.mauja.maujaadventures.utilitaires.DecoupeurImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FenetreDeJeu implements Observateur {
    private Jeu jeu;
    private TableauDeJeu tableauDeJeu;
    private Camera camera;
    private PersonnageJouable joueur;
    private Carte carteCourante;

    private List<TuileGraphique> lesTuilesGraphiquesCourantes;
    private List<JeuDeTuilesGraphique> lesJeuxDeTuilesGraphiques;

    private Canvas canvas;
    private GraphicsContext contexteGraphique;

    private Image imagePersonnage;
    private Image imageProjectile;
    private Image imageEnnemi;
    private Image imageLevierPasActif;
    private Image imageLevierActif;

    public FenetreDeJeu(GraphicsContext contexteGraphique, Jeu jeu) {
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        this.jeu = jeu;
        //this.canvas = new Canvas(964, 608);
        this.contexteGraphique = contexteGraphique;
        tableauDeJeu = jeu.getTableauDeJeu();
        joueur = tableauDeJeu.getJoueur();
        carteCourante = tableauDeJeu.getCarteCourante();
        camera = jeu.getCamera();

        lesTuilesGraphiquesCourantes = new ArrayList<>();
        lesJeuxDeTuilesGraphiques = new ArrayList<>();

        initialiser();
        ajoutElementParsage(GestionnaireInteractions.getInstance().getElementAAjouter());
    }

    public void ajoutElementParsage(List<ElementInteractif> list){
        carteCourante.ajouterElementsInteractifs(list);
    }

    public void affichage() {
        contexteGraphique.clearRect(0, 0, 1000, 1000);
        for (int k = 0; k < carteCourante.getLaCarte().length; k++) {
            for (int y = 0; y < carteCourante.getDimensionCarte().getHauteur(); y++) {
                for (int x = 0; x < carteCourante.getDimensionCarte().getLargeur(); x++) {
                    Tuile tuile = carteCourante.getTuile(x, y, k);
                    if (tuile.getId() >= 1) {
                        contexteGraphique.drawImage(lesTuilesGraphiquesCourantes.get(tuile.getId()).getImage(),
                                x * 32 - camera.getPositionCameraX(), y * 32 - camera.getPositionCameraY(),
                                32, 32);
                    }
                }
            }
        }

        for (ElementInteractif elementInteractif : carteCourante.getLesElementsInteractifs()) {
            if (elementInteractif instanceof Ennemi ennemi) {
                contexteGraphique.drawImage(imageEnnemi, ennemi.getPosition().getX() - camera.getPositionCameraX(),
                        ennemi.getPosition().getY() - camera.getPositionCameraY());
            }

            if (elementInteractif instanceof Projectile projectile) {
                contexteGraphique.drawImage(imageProjectile, projectile.getPosition().getX() - camera.getPositionCameraX(),
                        projectile.getPosition().getY() - camera.getPositionCameraY());
            }

            if (elementInteractif instanceof Levier levier) {
                if(levier.isActive()) {
                    contexteGraphique.drawImage(imageLevierActif, levier.getPosition().getX() - camera.getPositionCameraX(),
                            levier.getPosition().getY() - camera.getPositionCameraY());
                }else{
                    contexteGraphique.drawImage(imageLevierPasActif, levier.getPosition().getX() - camera.getPositionCameraX(),
                            levier.getPosition().getY() - camera.getPositionCameraY());
                }
            }
        }
        contexteGraphique.drawImage(imagePersonnage, joueur.getPosition().getX() - camera.getPositionCameraX(),
                joueur.getPosition().getY() - camera.getPositionCameraY());

        if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
            contexteGraphique.drawImage(imageProjectile,
                    joueur.getAttaque().getCollision().getPosition().getX() - camera.getPositionCameraX(),
                    joueur.getAttaque().getCollision().getPosition().getY() - camera.getPositionCameraY());
        }
        contexteGraphique.fillText("Vie : " + joueur.getPointsDeVie(), 20, 20);
    }

    private void initialiser() {
        jeu.attacher(this);
        DecoupeurImage decoupeur = new DecoupeurImage();
        List<JeuDeTuiles> jeuxDeTuiles = carteCourante.getLesJeuxDeTuiles();

        for (JeuDeTuiles jeuDeTuiles : jeuxDeTuiles) {
            List<TuileGraphique> lesTuilesGraphiques = new ArrayList<>();
            Image imageJeuDeTuile = new Image(jeuDeTuiles.getCheminJeuDeTuiles());
            List<Image> imagesTuiles = decoupeur.decoupe(imageJeuDeTuile, (int) jeuDeTuiles.getDimensionJeuDeTuiles().getLargeur(),
                    (int) jeuDeTuiles.getDimensionJeuDeTuiles().getHauteur());

            List<Tuile> lesTuiles = jeuDeTuiles.getListeDeTuiles();
            for (int i = 0; i < lesTuiles.size(); i++) {
                lesTuilesGraphiques.add(new TuileGraphique(lesTuiles.get(i), imagesTuiles.get(i)));
            }
            lesJeuxDeTuilesGraphiques.add(new JeuDeTuilesGraphique(jeuDeTuiles, lesTuilesGraphiques));
        }

        for (JeuDeTuilesGraphique jeuDeTuilesGraphique : lesJeuxDeTuilesGraphiques) {
            lesTuilesGraphiquesCourantes.addAll(jeuDeTuilesGraphique.getLesTuilesGraphiques());
        }

        try {
            imagePersonnage = new Image(String.valueOf(new File("ressources/images/entites/link_epee.png").toURI().toURL()));
            imageProjectile = new Image(String.valueOf(new File("ressources/images/entites/projectile.png").toURI().toURL()));
            imageEnnemi = new Image(String.valueOf(new File("ressources/images/entites/ennemi.png").toURI().toURL()));
            imageLevierPasActif = new Image(String.valueOf(new File("ressources/images/entites/levierPasActif.png").toURI().toURL()));
            imageLevierActif = new Image(String.valueOf(new File("ressources/images/entites/levierActif.png").toURI().toURL()));
            Levier.setHauteurDefaut(imageLevierActif.getHeight());
            Levier.setLargeurDefaut(imageLevierActif.getWidth());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void miseAJour(int timer) {
        affichage();
    }
}
