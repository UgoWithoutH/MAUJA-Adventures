package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.affichages.Carte2DGraphique;
import com.mauja.maujaadventures.affichages.TuileGraphique;
import com.mauja.maujaadventures.chargeurs.ChargeurCartesGraphiques;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.elements.Levier;
import com.mauja.maujaadventures.jeu.Jeu;
import com.mauja.maujaadventures.jeu.Observateur;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Camera;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.Tuile;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import vues.navigation.Fenetre;
import vues.navigation.Navigateur;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class FenetreDeJeu implements Observateur {
    private Navigateur navigateur;
    private Jeu jeu;
    private TableauDeJeu tableauDeJeu;
    private Camera camera;

    private PersonnageJouable joueur;
    private Carte carteCourante;

    private List<Carte2DGraphique> lesCartesGraphiques;
    private List<TuileGraphique> lesTuilesGraphiquesCourantes;

    private Scene scene;
    private StackPane lesCouches;
    private Canvas canvas;
    private GraphicsContext contexteGraphique;

    private Image imagePersonnage;
    private Image imageProjectile;
    private Image imageEnnemi;
    private Image imageLevierPasActif;
    private Image imageLevierActif;

    public FenetreDeJeu(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        this.jeu = jeu;
        this.navigateur = navigateur;
        this.canvas = new Canvas(964, 608);
        this.contexteGraphique = canvas.getGraphicsContext2D();

        lesCouches = new StackPane();
        lesCouches.getChildren().add(canvas);
        scene = new Scene(lesCouches);

        tableauDeJeu = jeu.getTableauDeJeu();
        joueur = tableauDeJeu.getJoueur();
        carteCourante = tableauDeJeu.getCarteCourante();
        camera = jeu.getCamera();

        lesTuilesGraphiquesCourantes = new ArrayList<>();

        initialiser();
    }

    public Scene getScene() {
        return scene;
    }

    public void miseAJourCarte() {
        carteCourante = tableauDeJeu.getCarteCourante();
        //Appel MAJ caméra.
    }

    public void affichage() {
        if (jeu.isPause()) {
            navigateur.naviguerVers(Fenetre.MENU_PAUSE, new MenuPause(navigateur, jeu, this));
        }

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

            if (elementInteractif instanceof Destructible destructible) {
                contexteGraphique.drawImage(imageProjectile, destructible.getPosition().getX() - camera.getPositionCameraX(),
                        destructible.getPosition().getY() - camera.getPositionCameraY());
            }

            if (elementInteractif instanceof Levier levier) {
                if (levier.isActive()) {
                    contexteGraphique.drawImage(imageLevierActif, levier.getPosition().getX() - camera.getPositionCameraX(),
                            levier.getPosition().getY() - camera.getPositionCameraY());
                }
                else{
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

        contexteGraphique.setFill(Color.rgb(255,255, 255, 0.5));
        contexteGraphique.fillRect(0, 0, 150,25);
        contexteGraphique.setFill(Color.RED);
        contexteGraphique.setFont(new Font(20));
        contexteGraphique.fillText("Vie : " + joueur.getPointsDeVie(), 20, 20);
    }

    private void initialiser() {
        ((GestionnaireDeTouchesFX) jeu.getGestionnaireDeTouches()).setScene(scene);
        jeu.attacher(this);
        jeu.lancerJeu();

        ChargeurCartesGraphiques chargeurCartesGraphiques = new ChargeurCartesGraphiques();
        lesCartesGraphiques = chargeurCartesGraphiques.charge(tableauDeJeu.getLesCartes());
        lesTuilesGraphiquesCourantes = lesCartesGraphiques.get(0).getLesTuilesGraphiques();

        try {
            imagePersonnage = new Image(String.valueOf(new File("ressources/images/entites/link_epee.png").toURI().toURL()));
            imageProjectile = new Image(String.valueOf(new File("ressources/images/entites/projectile.png").toURI().toURL()));
            imageEnnemi = new Image(String.valueOf(new File("ressources/images/entites/ennemi.png").toURI().toURL()));
            imageLevierPasActif = new Image(String.valueOf(new File("ressources/images/entites/levierPasActif.png").toURI().toURL()));
            imageLevierActif = new Image(String.valueOf(new File("ressources/images/entites/levierActif.png").toURI().toURL()));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        affichage();
    }

    @Override
    public void miseAJour(long timer) {
        affichage();
        if (!jeu.getTableauDeJeu().getCarteCourante().equals(carteCourante)) {
            miseAJourCarte();
        }
    }
}
