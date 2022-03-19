package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.affichages.Carte2DGraphique;
import com.mauja.maujaadventures.affichages.TuileGraphique;
import com.mauja.maujaadventures.cameras.CameraTuilesFX;
import com.mauja.maujaadventures.chargeurs.ChargeurCartesGraphiques;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.elements.Levier;
import com.mauja.maujaadventures.jeu.GestionnaireDeJeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.monde.Tuile;
import com.mauja.maujaadventures.observateurs.Observateur;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.observateurs.ObservateurElementInteractif;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import com.mauja.maujaadventures.utilitaires.Navigateur;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;


public class FenetreDeJeu implements Observateur, ObservateurElementInteractif {
    private Navigateur navigateur;
    private GestionnaireDeJeu gestionnaireDeJeu;
    private TableauDeJeu tableauDeJeu;
    private PersonnageJouable joueur;
    private Carte carteCourante;

    private List<Carte2DGraphique> lesCartesGraphiques;
    private Carte2DGraphique carteGraphiqueCourante;
    private CameraTuilesFX cameraTuilesFX;

    private Scene scene;
    private StackPane lesCouches;
    private Canvas canvas;
    private GraphicsContext contexteGraphique;

    private Image imagePersonnage;
    private Image imageProjectile;
    private Image imageAttaque;
    private Image imageEnnemi;
    private Image imageLevierPasActif;
    private Image imageLevierActif;

    private double largeurTuile;
    private double hauteurTuile;

    public FenetreDeJeu(Navigateur navigateur, GestionnaireDeJeu gestionnaireDeJeu) throws IllegalArgumentException {
        if (gestionnaireDeJeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.navigateur = navigateur;
        this.canvas = new Canvas(960 - 32, 767 - 32);
        this.contexteGraphique = canvas.getGraphicsContext2D();

        lesCouches = new StackPane();
        lesCouches.getChildren().add(canvas);
        scene = new Scene(lesCouches);

        tableauDeJeu = gestionnaireDeJeu.getTableauDeJeu();
        joueur = tableauDeJeu.getJoueur();
        carteCourante = tableauDeJeu.getCarteCourante();

        initialiser();
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void miseAJour(long timer) {
        if (!gestionnaireDeJeu.getTableauDeJeu().getCarteCourante().equals(carteCourante)) {
            miseAJourCarte();
        }
        affichage();
        if (!gestionnaireDeJeu.isLance()) {
            navigateur.naviguerVers(Fenetre.MENU_PAUSE, new MenuPause(navigateur, gestionnaireDeJeu, this));
        }
    }

    @Override
    public void miseAJour(ElementInteractif elementInteractif) {
        cameraTuilesFX.centrerSurEntite(elementInteractif);
    }

    public void affichage() {
        var vision = cameraTuilesFX.getVisionGraphique();

        contexteGraphique.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int k = 0; k < vision.length; k++) {
            for (int y = 0; y < vision[k].length; y++) {
                for (int x = 0; x < vision[k][y].length; x++) {
                    TuileGraphique tuile = vision[k][y][x];
                    if (tuile != null && !tuile.getTuile().equals(Tuile.TUILE_IGNOREE)) {
                        contexteGraphique.drawImage(tuile.getImage(),
                                (int) (x * largeurTuile - cameraTuilesFX.getDecalageRelatif().getLargeur() -
                                        cameraTuilesFX.getDecalageAbsolu().getLargeur()),
                                (int) (y * hauteurTuile -  cameraTuilesFX.getDecalageRelatif().getHauteur() -
                                        cameraTuilesFX.getDecalageAbsolu().getHauteur()),
                                largeurTuile, hauteurTuile);
                    }
                }
            }
        }


        for (ElementInteractif elementInteractif : carteCourante.getLesElementsInteractifs()) {
            if (elementInteractif instanceof Ennemi ennemi) {
                afficherElementInteractif(ennemi, imageEnnemi);
            }
            if (elementInteractif instanceof Destructible destructible) {
                afficherElementInteractif(destructible, imageProjectile);
            }

            if (elementInteractif instanceof Levier levier) {
                if (levier.isActive()) {
                    afficherElementInteractif(levier, imageLevierActif);
                }
                else {
                    afficherElementInteractif(levier, imageLevierPasActif);
                }
            }
        }
        afficherElementInteractif(joueur, imagePersonnage);

        if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
            contexteGraphique.drawImage(imageAttaque,
                    joueur.getAttaque().getCollision().getPosition().getX() - cameraTuilesFX.getDecalageRelatif().getLargeur() -
                            cameraTuilesFX.getPosition().getX() * largeurTuile,
                    joueur.getAttaque().getCollision().getPosition().getY() - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                            cameraTuilesFX.getPosition().getY() * hauteurTuile);
        }
        afficherVie();
    }

    private void initialiser() {
        ((GestionnaireDeTouchesFX) gestionnaireDeJeu.getGestionnaireDeTouches()).setScene(scene);
        gestionnaireDeJeu.attacher(this);

        if (!gestionnaireDeJeu.isLance()) {
            gestionnaireDeJeu.lancerJeu();
        }

        ChargeurCartesGraphiques chargeurCartesGraphiques = new ChargeurCartesGraphiques();
        lesCartesGraphiques = chargeurCartesGraphiques.charge(tableauDeJeu.getLesCartes());
        carteGraphiqueCourante = lesCartesGraphiques.get(0);
        cameraTuilesFX = new CameraTuilesFX(carteCourante, new Dimension(30,24), carteGraphiqueCourante);
        miseAJourCarte();
        joueur.attacher(this);
        cameraTuilesFX.centrerSurEntite(joueur);

        try {
            imagePersonnage = new Image(String.valueOf(new File("ressources/images/entites/personnage.png").toURI().toURL()));
            imageProjectile = new Image(String.valueOf(new File("ressources/images/entites/projectile.png").toURI().toURL()));
            imageAttaque = new Image(String.valueOf(new File("ressources/images/entites/attaque.png").toURI().toURL()));
            imageEnnemi = new Image(String.valueOf(new File("ressources/images/entites/ennemi.png").toURI().toURL()));
            imageLevierPasActif = new Image(String.valueOf(new File("ressources/images/entites/levierPasActif.png").toURI().toURL()));
            imageLevierActif = new Image(String.valueOf(new File("ressources/images/entites/levierActif.png").toURI().toURL()));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void miseAJourCarte() {
        carteCourante = tableauDeJeu.getCarteCourante();
        for (Carte2DGraphique carte2DGraphique : lesCartesGraphiques) {
            if (carte2DGraphique.getCarte().getNom().equals(carteCourante.getNom())) {
                carteGraphiqueCourante = carte2DGraphique;
                cameraTuilesFX = new CameraTuilesFX(carteCourante, new Dimension(30,24), carteGraphiqueCourante);
            }
        }
        largeurTuile = carteCourante.getDimensionTuiles().getLargeur();
        hauteurTuile = carteCourante.getDimensionTuiles().getHauteur();
    }

    private void afficherVie() {
        contexteGraphique.setFill(Color.rgb(255,255, 255, 0.5));
        contexteGraphique.fillRect(0, 0, 150,25);
        contexteGraphique.setFill(Color.RED);
        contexteGraphique.setFont(new Font(20));
        contexteGraphique.fillText("Vie : " + joueur.getPointsDeVie(), 20, 20);
    }

    private void afficherElementInteractif(ElementInteractif elementInteractif, Image image) {
        contexteGraphique.drawImage(image,
                (int) (elementInteractif.getPosition().getX() - cameraTuilesFX.getPosition().getX() * largeurTuile
                        - cameraTuilesFX.getDecalageRelatif().getLargeur()),
                (int) (elementInteractif.getPosition().getY() - cameraTuilesFX.getPosition().getY() * hauteurTuile
                        - cameraTuilesFX.getDecalageRelatif().getHauteur()));
    }


}
