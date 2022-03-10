package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.chargeurs.*;
import com.mauja.maujaadventures.comportements.ComportementChevalier;
import com.mauja.maujaadventures.comportements.ComportementOctorockTireur;
import com.mauja.maujaadventures.comportements.ComportementPoursuite;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.utilitaires.FormatInvalideException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TableauDeJeu {
    private List<Carte> lesCartes;
    private Carte carteCourante;
    private PersonnageJouable joueur;
    private Options options;
    private Map<TransitionCarte, TransitionCarte> transitionsEntreCartes;

    public TableauDeJeu(Options options) {
        lesCartes = new ArrayList<>();
        this.options = options;
        initialiser();
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

    public Map<TransitionCarte, TransitionCarte> getTransitionsEntreCartes() {
        return Collections.unmodifiableMap(transitionsEntreCartes);
    }

    private void initialiser() {
        ChargeurDeCarteTiled chargeurDeCartes = new ChargeurDeCarteTiledReader();
        ChargeurDeTransitionsCarte chargeurDeTransitions = new ChargeurDeTransitionsCartesTextuel();

        List<String> lesCartesChemin = Ressources.getInstance().getLesCartes();

        Carte carte = null;
        for (String chemin : lesCartesChemin) {
            try {
                carte = chargeurDeCartes.charge(chemin);
            } catch (FormatInvalideException | FileNotFoundException e) {
                e.printStackTrace();
            }
            lesCartes.add(carte);
        }
        transitionsEntreCartes = chargeurDeTransitions.charge(Ressources.getInstance().getFichierTransitions(), lesCartes);

        carteCourante = lesCartes.get(0);

        Position position = new Position(482, 400);
        Rectangle rectangle = new Rectangle(new Position(3, 24), new Dimension(27, 23));
        joueur = new PersonnageJouable(position, new Dimension(33, 47),
                rectangle, null, new Attaque(new Rectangle(0, 0, 30, 30), 1000));

        carteCourante.ajouterElementInteractif(joueur);

        Entite entite = new Ennemi(new Position(300, 600), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                new ComportementOctorockTireur(carteCourante), 10);

        Entite entite2 = new Ennemi(new Position(400, 600), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                new ComportementChevalier(carteCourante, joueur), 10);

        carteCourante.ajouterElementInteractif(entite);
        carteCourante.ajouterElementInteractif(entite2);

        /*for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                entite = new Ennemi(new Position(588 + j * 32, 1772 + i * 32), new Dimension(30, 30),
                        new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                        new ComportementPoursuite(carteCourante, joueur), 10);
                carteCourante.ajouterElementInteractif(entite);
            }
        }*/
    }
}
