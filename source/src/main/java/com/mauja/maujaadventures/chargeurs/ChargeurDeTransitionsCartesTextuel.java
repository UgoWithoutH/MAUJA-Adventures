package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.TransitionCarte;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.utilitaires.FormatInvalideException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChargeurDeTransitionsCartesTextuel implements ChargeurDeTransitionsCarte {
    private static final String CARACTERES_IGNORES ="#.*";
    private static final String DELIMITEUR_VISUEL = "=>";
    private static final String DELIMITEUR = " ";

    @Override
    public Map<TransitionCarte, TransitionCarte> charge(String chemin, List<Carte> lesCartes) {
        Map<TransitionCarte, TransitionCarte> graphe = null;

        try (BufferedReader tampon = new BufferedReader(new FileReader(chemin))) {
            StringBuilder chaineTotale = new StringBuilder();
            String ligne;
            while ((ligne = tampon.readLine()) != null) {
                ligne = ligne.replaceAll(CARACTERES_IGNORES, "");
                chaineTotale.append(ligne);
                chaineTotale.append("\n");
            }
            String chaine = chaineTotale.toString().replaceAll(DELIMITEUR_VISUEL, DELIMITEUR);
            chaine = chaine.replaceAll("\n", DELIMITEUR);
            chaine = chaine.replaceAll(" +", DELIMITEUR);
            graphe = creerGraphe(chaine.trim(), lesCartes);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return graphe;
    }

    private Map<TransitionCarte, TransitionCarte> creerGraphe(String donnees, List<Carte> lesCartes)
            throws FormatInvalideException {
        Map<TransitionCarte, TransitionCarte> graphe = new HashMap<>();
        String[] jetons = donnees.split(DELIMITEUR);
        int nombreJetons = jetons.length;

        if ((nombreJetons - 3) % 6 != 0) {
            throw new FormatInvalideException("Le nombre de champs spécifiés dans le fichier "
                    + "de transitions entre les cartes est incorrect. Il en manque " + (6 - ((nombreJetons - 3) % 6)));
        }

        int compteur = 3;
        TransitionCarte transition1, transition2;

        //On crée le point de départ.
        transition1 = creerTransitionCarte(lesCartes, jetons[0], jetons[1], jetons[2]);
        graphe.put(null, transition1);

        while (nombreJetons != compteur) {
            try {
                transition1 = creerTransitionCarte(lesCartes, jetons[compteur],
                        jetons[1 + compteur], jetons[2 + compteur]);
                transition2 = creerTransitionCarte(lesCartes, jetons[3 + compteur],
                        jetons[4 + compteur], jetons[5 + compteur]);
                graphe.put(transition1, transition2);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }

            compteur += 6;
        }
        return graphe;
    }

    private TransitionCarte creerTransitionCarte(List<Carte> lesCartes, String nomCarte,
            String positionX, String positionY) throws FormatInvalideException, NumberFormatException {
        Carte carte = carteExiste(nomCarte, lesCartes);
        double posX = Integer.parseInt(positionX);
        double posY = Integer.parseInt(positionY);

        Position position = new Position(
                posX * carte.getDimensionTuiles().getLargeur(),
                posY * carte.getDimensionTuiles().getHauteur());
        return new TransitionCarte(nomCarte, position, new Rectangle(position, carte.getDimensionTuiles()));
    }

    private Carte carteExiste(String nom, List<Carte> lesCartes) throws FormatInvalideException {
        for (Carte carte : lesCartes) {
            if (carte.getNom().equals(nom)) {
                return carte;
            }
        }
        throw new FormatInvalideException("La carte de nom " + nom + " n'existe pas et il est "
                + "impossible de lui appliquer des transitions.");
    }
}
