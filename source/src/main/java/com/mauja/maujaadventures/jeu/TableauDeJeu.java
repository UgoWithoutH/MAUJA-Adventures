package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.chargeurs.*;
import com.mauja.maujaadventures.comportements.ComportementPoursuite;
import com.mauja.maujaadventures.comportements.ComportementTireur;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.observateurs.ObservableCarte;
import com.mauja.maujaadventures.utilitaires.FormatInvalideException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TableauDeJeu extends ObservableCarte {
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

    public boolean changerCarte(Carte carte) {
        if (lesCartes.contains(carte)) {
            carteCourante = carte;
            return true;
        }
        return false;
    }

    public boolean changerCarte(String nomCarte) {
        Carte carte = getCarte(nomCarte);
        if (carte != null) {
            carteCourante.supprimerElementInteractif(joueur);
            carteCourante = carte;
            carteCourante.ajouterElementInteractif(joueur);
            notifier(carteCourante);
            return true;
        }
        return false;
    }

    public void changerElementInteractifDeCarte(ElementInteractif elementInteractif, String nomCarte) {
        Carte carte = getCarte(nomCarte);
        if (carte != null) {
            carteCourante.supprimerElementInteractif(elementInteractif);
            carte.ajouterElementInteractif(elementInteractif);
        }
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

        transitionsEntreCartes = chargeurDeTransitions.charge(Ressources.getInstance().getCheminTransitions(), lesCartes);
        carteCourante = (getCarte(transitionsEntreCartes.get(null).getNomCarte()) == null)
                ? lesCartes.get(0) : getCarte(transitionsEntreCartes.get(null).getNomCarte());

        Rectangle rectangle = new Rectangle(new Position(2, 32), new Dimension(27, 24));
        joueur = new PersonnageJouable(transitionsEntreCartes.get(null).getPosition(), new Dimension(34, 56),
                rectangle, null, new Attaque(new Rectangle(0, 0, 44, 20), 1000),
                1000);

        Entite entite = new Ennemi(new Position(800, 800), new Dimension(29, 27),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(4, 4), null,
                new ComportementPoursuite(carteCourante, joueur), 10);

        Entite entite2 = new Ennemi(new Position(420, 700), new Dimension(29, 27),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(0.5, 0.5), null,
                new ComportementPoursuite(carteCourante, joueur), 10);

        Entite entite3 = new Ennemi(new Position(250, 250), new Dimension(29, 27),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(2, 2), null,
                new ComportementTireur(carteCourante, new Velocite(15, 2), 16), 10);

        Entite entite4 = new Ennemi(new Position(350, 500), new Dimension(29, 27),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(4, 4), null,
                new ComportementTireur(carteCourante, new Velocite(5, 15), 8), 10);

        carteCourante.ajouterElementInteractif(entite);
        carteCourante.ajouterElementInteractif(entite2);
        carteCourante.ajouterElementInteractif(entite3);
        carteCourante.ajouterElementInteractif(entite4);
        carteCourante.ajouterElementInteractif(joueur);
    }
}
