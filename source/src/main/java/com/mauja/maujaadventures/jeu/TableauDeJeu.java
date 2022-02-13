package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.chargeurs.RecuperateurDeCartes;
import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.comportements.ComportementPoursuite;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.JeuDeTuiles;
import com.mauja.maujaadventures.monde.Tuile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TableauDeJeu {
    private List<Tuile> lesTuiles;
    private List<Carte> lesCartes;
    private Carte carteCourante;
    private PersonnageJouable joueur;
    private Options options;

    public TableauDeJeu(Options options) {
        lesCartes = new ArrayList<>();
        this.options = options;
        initialiser();
    }

    public List<Tuile> getLesTuiles() {
        return lesTuiles;
    }

    public List<Carte> getLesCartes() {
        return lesCartes;
    }

    public Carte getCarteCourante() {
        return carteCourante;
    }

    public PersonnageJouable getJoueur() {
        return joueur;
    }

    public Options getOptions() {
        return options;
    }

    private void initialiser() {
        RecuperateurDeCartes recuperateurDeCartes = new RecuperateurDeCartes();
        List<String> lesCartesChemin = Ressources.getLesCartes();

        List<JeuDeTuiles> lesJeuxDeTuiles = null;

        Carte carte = null;
        for (String chemin : lesCartesChemin) {
            try {
                carte = recuperateurDeCartes.recupereCarte(chemin);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            lesCartes.add(carte);
        }

        carteCourante = lesCartes.get(0);

        for (String chemin : lesCartesChemin) {
            try {
                lesJeuxDeTuiles = recuperateurDeCartes.recupereJeuxDeTuiles(chemin);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        lesTuiles = new ArrayList<>();

        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            lesTuiles.addAll(jeuDeTuiles.getListeDeTuiles());
        }

        Position position = new Position(482, 400);
        Rectangle rectangle = new Rectangle(new Position(3, 24), new Dimension(27, 23));
        joueur = new PersonnageJouable(position, new Dimension(33, 47),
                rectangle, null, new Attaque(new Rectangle(0, 0, 30, 30), 1000));


        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Entite entite = new Ennemi(new Position(i*16, j*16), new Dimension(30, 30),
                        new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                        new ComportementPoursuite(carteCourante, joueur), 10);

                carteCourante.ajouterEntite(entite);
            }
        }
    }
}
