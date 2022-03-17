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
import com.mauja.maujaadventures.observateurs.Observateur;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Carte;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import com.mauja.maujaadventures.utilitaires.Navigateur;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class FenetreDeJeu implements Observateur {
    private Navigateur navigateur;
    private GestionnaireDeJeu gestionnaireDeJeu;
    private TableauDeJeu tableauDeJeu;
    private PersonnageJouable joueur;
    private Carte carteCourante;

    private List<Carte2DGraphique> lesCartesGraphiques;
    private Carte2DGraphique carteGraphiqueCourante;
    private List<TuileGraphique> lesTuilesGraphiquesCourantes;

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
    private CameraTuilesFX cameraTuilesFX;
    private double largeurTuile = 32;
    private double hauteurTuile = 32;

    public FenetreDeJeu(Navigateur navigateur, GestionnaireDeJeu gestionnaireDeJeu) throws IllegalArgumentException {
        if (gestionnaireDeJeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.navigateur = navigateur;
        this.canvas = new Canvas(993, 767);
        this.contexteGraphique = canvas.getGraphicsContext2D();

        lesCouches = new StackPane();
        lesCouches.getChildren().add(canvas);
        scene = new Scene(lesCouches);

        tableauDeJeu = gestionnaireDeJeu.getTableauDeJeu();
        joueur = tableauDeJeu.getJoueur();
        carteCourante = tableauDeJeu.getCarteCourante();

        lesTuilesGraphiquesCourantes = new ArrayList<>();

        initialiser();
    }

    public Scene getScene() {
        return scene;
    }

    public void miseAJourCarte() {
        carteCourante = tableauDeJeu.getCarteCourante();
        for (Carte2DGraphique carte2DGraphique : lesCartesGraphiques) {
            if (carte2DGraphique.getCarte().getNom().equals(carteCourante.getNom())){
                lesTuilesGraphiquesCourantes = carte2DGraphique.getLesTuilesGraphiques();
            }
        }
        cameraTuilesFX = new CameraTuilesFX(carteCourante,new Dimension(30,26),carteGraphiqueCourante);
    }


    public void ajoutElementParsage(List<ElementInteractif> list){
        carteCourante.ajouterElementsInteractifs(list);
    }

    public void affichage() {
        var vision = cameraTuilesFX.getVisionGraphique();

        contexteGraphique.clearRect(0, 0, 1000, 1000);
        for (int k = 0; k < vision.length; k++) {
            for (int y = 0; y < vision[k].length; y++) {
                for (int x = 0; x < vision[k][y].length; x++) {
                        contexteGraphique.drawImage(vision[k][y][x].getImage(),
                                x * largeurTuile
                                        - cameraTuilesFX.getDecalageRelatif().getLargeur() -
                                        cameraTuilesFX.getDecalageAbsolu().getLargeur(),
                                y * hauteurTuile - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                                cameraTuilesFX.getDecalageAbsolu().getHauteur(),
                                largeurTuile, hauteurTuile);
                }
            }
        }


        for (ElementInteractif elementInteractif : carteCourante.getLesElementsInteractifs()) {
            if (elementInteractif instanceof Ennemi ennemi) {

                contexteGraphique.drawImage(imageEnnemi, ennemi.getPosition().getX()
                                - cameraTuilesFX.getPosition().getX() * largeurTuile -
                        cameraTuilesFX.getDecalageRelatif().getLargeur(),
                        ennemi.getPosition().getY() - cameraTuilesFX.getPosition().getY() * hauteurTuile -
                                cameraTuilesFX.getDecalageRelatif().getHauteur());
            }
            if (elementInteractif instanceof Destructible destructible) {
                contexteGraphique.drawImage(imageProjectile, destructible.getPosition().getX() -
                                cameraTuilesFX.getPosition().getX() * largeurTuile -
                        cameraTuilesFX.getDecalageRelatif().getLargeur(),
                        destructible.getPosition().getY() - cameraTuilesFX.getPosition().getY() * hauteurTuile -
                        cameraTuilesFX.getDecalageRelatif().getHauteur());
            }

            if (elementInteractif instanceof Levier levier) {
                if (levier.isActive()) {

                    contexteGraphique.drawImage(imageLevierActif, levier.getPosition().getX() -
                                    cameraTuilesFX.getPosition().getX() * largeurTuile -
                                    cameraTuilesFX.getDecalageRelatif().getLargeur(),
                            levier.getPosition().getY() - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                                    cameraTuilesFX.getPosition().getY() * hauteurTuile -
                                    cameraTuilesFX.getDecalageRelatif().getHauteur());
                }
                else{
                    contexteGraphique.drawImage(imageLevierPasActif, levier.getPosition().getX() -
                                    cameraTuilesFX.getPosition().getX() * largeurTuile -
                                    cameraTuilesFX.getDecalageRelatif().getLargeur(),
                            levier.getPosition().getY() - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                                    cameraTuilesFX.getPosition().getY() * hauteurTuile -
                                    cameraTuilesFX.getDecalageRelatif().getHauteur());
                }
            }
        }


        contexteGraphique.drawImage(imagePersonnage,
                joueur.getPosition().getX() - cameraTuilesFX.getDecalageRelatif().getLargeur() -
                cameraTuilesFX.getPosition().getX() * largeurTuile,
                joueur.getPosition().getY() - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                cameraTuilesFX.getPosition().getY() * hauteurTuile);

        if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
            contexteGraphique.drawImage(imageAttaque,
                    joueur.getAttaque().getCollision().getPosition().getX() - cameraTuilesFX.getPosition().getX() *
                            largeurTuile - cameraTuilesFX.getDecalageRelatif().getLargeur(),
                    joueur.getAttaque().getCollision().getPosition().getY() - cameraTuilesFX.getPosition().getY() *
                            hauteurTuile - cameraTuilesFX.getDecalageRelatif().getHauteur());
        }

        contexteGraphique.setFill(Color.rgb(255,255, 255, 0.5));
        contexteGraphique.fillRect(0, 0, 150,25);
        contexteGraphique.setFill(Color.RED);
        contexteGraphique.setFont(new Font(20));
        contexteGraphique.fillText("Vie : " + joueur.getPointsDeVie(), 20, 20);
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
        cameraTuilesFX = new CameraTuilesFX(carteCourante,new Dimension(30,25),carteGraphiqueCourante);
        miseAJourCarte();

        try {
            imagePersonnage = new Image(String.valueOf(new File("ressources/images/entites/link_epee.png").toURI().toURL()));
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

    @Override
    public void miseAJour(long timer) {
        cameraTuilesFX.centrerSurEntite(tableauDeJeu.getJoueur());
        affichage();
        if (!gestionnaireDeJeu.getTableauDeJeu().getCarteCourante().equals(carteCourante)) {
            miseAJourCarte();
        }
        if (!gestionnaireDeJeu.isLance()) {
            navigateur.naviguerVers(Fenetre.MENU_PAUSE, new MenuPause(navigateur, gestionnaireDeJeu, this));
        }
    }
}
