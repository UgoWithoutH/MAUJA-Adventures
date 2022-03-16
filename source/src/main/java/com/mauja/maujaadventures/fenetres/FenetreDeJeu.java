package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.affichages.Carte2DGraphique;
import com.mauja.maujaadventures.affichages.JeuDeTuilesGraphique;
import com.mauja.maujaadventures.affichages.TuileGraphique;
import com.mauja.maujaadventures.chargeurs.ChargeurCartesGraphiques;
import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.jeu.Jeu;
import com.mauja.maujaadventures.jeu.Observateur;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.monde.Camera;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.JeuDeTuiles;
import com.mauja.maujaadventures.monde.Tuile;
import com.mauja.maujaadventures.utilitaires.DecoupeurImage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import vues.codebehind.CameraTuilesFX;
import vues.navigation.Fenetre;
import vues.navigation.Navigateur;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FenetreDeJeu implements Observateur {
    private Navigateur navigateur;
    private Jeu jeu;
    private TableauDeJeu tableauDeJeu;
    private CameraTuilesFX cameraTuilesFX;


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
        this.canvas = new Canvas(993, 767);
        this.contexteGraphique = canvas.getGraphicsContext2D();

        lesCouches = new StackPane();
        lesCouches.getChildren().add(canvas);
        scene = new Scene(lesCouches);

        tableauDeJeu = jeu.getTableauDeJeu();
        joueur = tableauDeJeu.getJoueur();
        carteCourante = tableauDeJeu.getCarteCourante();

        lesTuilesGraphiquesCourantes = new ArrayList<>();

        initialiser();
        ajoutElementParsage(GestionnaireInteractions.getInstance().getElementAAjouter());
    }

    public Scene getScene() {
        return scene;
    }

    public void miseAJourCarte() {
        carteCourante = tableauDeJeu.getCarteCourante();
        cameraTuilesFX = new CameraTuilesFX(carteCourante,new Dimension(30,25),carteGraphiqueCourante);

        //lesCartesGraphiques.remove(0);
        //Appel MAJ caméra.
    }

    public void ajoutElementParsage(List<ElementInteractif> list){
        carteCourante.ajouterElementsInteractifs(list);
    }

    public void affichage() {
        if (jeu.isPause()) {
            navigateur.naviguerVers(Fenetre.MENU_PAUSE, new MenuPause(navigateur, jeu, this));
        }
        var vision = cameraTuilesFX.getVisionGraphique();
        contexteGraphique.clearRect(0, 0, 1000, 1000);
        for (int k = 0; k < vision.length; k++) {
            for (int y = 0; y < vision[k].length; y++) {
                for (int x = 0; x < vision[k][y].length; x++) {
                    Tuile tuile = carteCourante.getTuile(x, y, k);
                        contexteGraphique.drawImage(vision[k][y][x].getImage(),
                                x * 32 - cameraTuilesFX.getDecalageRelatif().getLargeur() -
                                        cameraTuilesFX.getDecalageAbsolu().getLargeur(),
                                y * 32 - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                                cameraTuilesFX.getDecalageAbsolu().getHauteur(),
                                32, 32);
                }
            }
        }


        for (ElementInteractif elementInteractif : carteCourante.getLesElementsInteractifs()) {
            if (elementInteractif instanceof Ennemi ennemi) {
                contexteGraphique.drawImage(imageEnnemi, ennemi.getPosition().getX()
                                - cameraTuilesFX.getPosition().getX() * 32 -
                        cameraTuilesFX.getDecalageRelatif().getLargeur(),
                        ennemi.getPosition().getY() - cameraTuilesFX.getPosition().getY() * 32 -
                                cameraTuilesFX.getDecalageRelatif().getHauteur());
            }

            if (elementInteractif instanceof Destructible destructible) {
                contexteGraphique.drawImage(imageProjectile, destructible.getPosition().getX() -
                                cameraTuilesFX.getPosition().getX() * 32 -
                        cameraTuilesFX.getDecalageRelatif().getLargeur(),
                        destructible.getPosition().getY() - cameraTuilesFX.getPosition().getY() * 32 -
                        cameraTuilesFX.getDecalageRelatif().getHauteur());
            }

            if (elementInteractif instanceof Levier levier) {
                if (levier.isActive()) {
                    contexteGraphique.drawImage(imageLevierActif, levier.getPosition().getX() -
                                    cameraTuilesFX.getPosition().getX() * 32 -
                                    cameraTuilesFX.getDecalageRelatif().getLargeur(),
                            levier.getPosition().getY() - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                                    cameraTuilesFX.getPosition().getY() * 32 -
                                    cameraTuilesFX.getDecalageRelatif().getHauteur());
                }
                else{
                    contexteGraphique.drawImage(imageLevierPasActif, levier.getPosition().getX() -
                                    - cameraTuilesFX.getPosition().getX() * 32 -
                                    cameraTuilesFX.getDecalageRelatif().getLargeur(),
                            levier.getPosition().getY() - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                                    cameraTuilesFX.getPosition().getY() * 32 -
                                    cameraTuilesFX.getDecalageRelatif().getHauteur());
                }
            }
        }


        contexteGraphique.drawImage(imagePersonnage,
                joueur.getPosition().getX() - cameraTuilesFX.getDecalageRelatif().getLargeur() -
                cameraTuilesFX.getPosition().getX() * 32,
                joueur.getPosition().getY() - cameraTuilesFX.getDecalageRelatif().getHauteur() -
                cameraTuilesFX.getPosition().getY() * 32);

        if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
            contexteGraphique.drawImage(imageProjectile,
                    joueur.getAttaque().getCollision().getPosition().getX() - cameraTuilesFX.getPosition().getX() * 32,
                    joueur.getAttaque().getCollision().getPosition().getY() - cameraTuilesFX.getPosition().getY() * 32);
        }

        contexteGraphique.setFill(Color.rgb(255,255, 255, 0.5));
        contexteGraphique.fillRect(0, 0, 150,25);
        contexteGraphique.setFill(Color.RED);
        contexteGraphique.setFont(new Font(20));
        contexteGraphique.fillText("Vie : " + joueur.getPointsDeVie(), 20, 20);
    }

    private void initialiser() {
        ((GestionnaireDeTouchesFX) jeu.getGestionnaireDeTouches()).setScene(scene);
        ChargeurCartesGraphiques chargeurCartesGraphiques = new ChargeurCartesGraphiques();
        lesCartesGraphiques = chargeurCartesGraphiques.charge(tableauDeJeu.getLesCartes());
        lesTuilesGraphiquesCourantes = lesCartesGraphiques.get(0).getLesTuilesGraphiques();
        carteGraphiqueCourante = lesCartesGraphiques.get(0);
        cameraTuilesFX = new CameraTuilesFX(carteCourante,new Dimension(30,25),carteGraphiqueCourante);
        jeu.attacher(this);
        jeu.lancerJeu();

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
    public void miseAJour(long timer) {
        cameraTuilesFX.centrerSurEntite(tableauDeJeu.getJoueur());
        affichage();
        if (!jeu.getTableauDeJeu().getCarteCourante().equals(carteCourante)) {
            miseAJourCarte();
        }
    }
}
