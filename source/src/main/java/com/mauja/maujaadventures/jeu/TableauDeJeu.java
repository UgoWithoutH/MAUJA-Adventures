package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.chargeurs.*;
import com.mauja.maujaadventures.comportements.ComportementChevalier;
import com.mauja.maujaadventures.comportements.ComportementPoursuite;
import com.mauja.maujaadventures.comportements.ComportementTireur;
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

    public TableauDeJeu() {
        lesCartes = new ArrayList<>();
        this.options = new Options();
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

    public boolean changeCarte(Carte carte) {
        if (lesCartes.contains(carte)) {
            carteCourante = carte;
            return true;
        }
        return false;
    }

    public boolean changeCarte(String nomCarte) {
        Carte carte = getCarte(nomCarte);
        if (carte != null) {
            carteCourante = carte;
            return true;
        }
        return false;
    }

    public Carte getCarte(String nomCarte) {
        for (Carte carte : lesCartes) {
            if (carte.getNom().equals(nomCarte)) {
                return carte;
            }
        }
        return null;
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
        carteCourante = getCarte(transitionsEntreCartes.get(null).getNomCarte()) == null
                ? getCarte(transitionsEntreCartes.get(null).getNomCarte()) : lesCartes.get(0);

        Rectangle rectangle = new Rectangle(new Position(3, 24), new Dimension(27, 23));
        joueur = new PersonnageJouable(transitionsEntreCartes.get(null).getPosition(), new Dimension(33, 47),
                rectangle, null, new Attaque(new Rectangle(0, 0, 30, 30), 1000));

        Entite entite = new Ennemi(new Position(300, 600), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                new ComportementTireur(carteCourante), 100);

        Entite entite2 = new Ennemi(new Position(400, 600), new Dimension(30, 30),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 5), null,
                new ComportementTireur(carteCourante), 100);

        carteCourante.ajouterElementInteractif(entite);
        carteCourante.ajouterElementInteractif(entite2);
    }
}
