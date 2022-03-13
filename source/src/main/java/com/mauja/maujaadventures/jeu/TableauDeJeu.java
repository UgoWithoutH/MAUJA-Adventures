package com.mauja.maujaadventures.jeu;

import com.mauja.maujaadventures.chargeurs.*;
import com.mauja.maujaadventures.comportements.ComportementTireur;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.monde.Carte;
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

        transitionsEntreCartes = chargeurDeTransitions.charge(Ressources.getInstance().getFichierTransitions(), lesCartes);
        carteCourante = getCarte(transitionsEntreCartes.get(null).getNomCarte()) == null
                ? getCarte(transitionsEntreCartes.get(null).getNomCarte()) : lesCartes.get(0);

        Rectangle rectangle = new Rectangle(new Position(3, 24), new Dimension(27, 23));
        joueur = new PersonnageJouable(transitionsEntreCartes.get(null).getPosition(), new Dimension(33, 47),
                rectangle, null, new Attaque(new Rectangle(0, 0, 30, 30), 1000));

        carteCourante.ajouterElementInteractif(joueur);

        Entite entite = new Ennemi(new Position(400, 400), new Dimension(29, 27),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(1, 8), null,
                new ComportementTireur(carteCourante, new Velocite(2, 8)), 100);

        Entite entite2 = new Ennemi(new Position(250, 250), new Dimension(29, 27),
                new Rectangle(new Position(0, 0), 30, 30), new Velocite(5, 2), null,
                new ComportementTireur(carteCourante, new Velocite(5, 1)), 100);

        //carteCourante.ajouterElementInteractif(entite);
        carteCourante.ajouterElementInteractif(entite2);
    }
}
