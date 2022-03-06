package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.TransitionCarte;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.utilitaires.FormatInvalideException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChargeurDeTransitionsCartesTextuel implements ChargeurDeTransitionsCarte {
    private static final String CARACTERES_IGNORES ="#.*";
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
            String chaine = chaineTotale.toString().trim().toLowerCase();
            chaine = chaine.replaceAll("\n", DELIMITEUR);
            chaine = chaine.replaceAll("  ", DELIMITEUR);
            graphe = creerGraphe(chaine);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return graphe;
    }

    private Map<TransitionCarte, TransitionCarte> creerGraphe(String donnees) throws FormatInvalideException {
        Map<TransitionCarte, TransitionCarte> graphe = new HashMap<>();
        String[] jetons = donnees.split(DELIMITEUR);
        int nombreJetons = jetons.length;

        if (nombreJetons % 6 != 0) {
            throw new FormatInvalideException("Le nombre de champs spécifiés dans le fichier "
                    + "de transitions entre les cartes est incorrect. Il en manque " + (6 - (nombreJetons % 6)));
        }

        int compteur = 0;
        TransitionCarte transition1, transition2;
        while (nombreJetons != compteur) {
            try {
                transition1 = new TransitionCarte(jetons[0], Integer.parseInt(jetons[1]), Integer.parseInt(jetons[2]));
                transition2 = new TransitionCarte(jetons[3], Integer.parseInt(jetons[4]), Integer.parseInt(jetons[5]));
                graphe.put(transition1, transition2);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }

            compteur = compteur + 6;
        }
        return graphe;
    }
}
