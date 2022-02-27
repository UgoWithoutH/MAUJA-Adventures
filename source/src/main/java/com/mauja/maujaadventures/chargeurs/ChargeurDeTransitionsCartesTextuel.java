package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.TransitionCarte;
import com.mauja.maujaadventures.utilitaires.FormatInvalideException;
import com.mauja.maujaadventures.utilitaires.Graphe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

public class ChargeurDeTransitionsCartesTextuel implements ChargeurTransitionCarte {
    private static final String CARACTERES_IGNORES ="#.*";
    private static final String DELIMITEUR = "=>";

    @Override
    public Graphe<TransitionCarte> charge(String chemin) {
        Graphe<TransitionCarte> graphe = null;

        try (BufferedReader tampon = new BufferedReader(new FileReader(chemin))) {
            StringBuilder chaineTotale = new StringBuilder();
            String ligne;
            while ((ligne = tampon.readLine()) != null) {
                ligne = ligne.replaceAll(CARACTERES_IGNORES, "");
                chaineTotale.append(ligne);
                chaineTotale.append("\n");
            }
            String chaine = chaineTotale.toString().trim().toUpperCase(Locale.ROOT);
            chaine = chaine.replaceAll("\n", ":");
            graphe = creerGraphe(chaine);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return graphe;
    }

    private Graphe<TransitionCarte> creerGraphe(String donnees) throws FormatInvalideException {
        Graphe<TransitionCarte> graphe = new Graphe<>();
        StringTokenizer delimiteur = new StringTokenizer(donnees, DELIMITEUR);
        int nombreJetons = delimiteur.countTokens();

        if (nombreJetons % 6 != 0) {
            throw new FormatInvalideException("Le nombre de champs spécifiés dans le fichier "
                    + "de transitions entre les cartes est incorrect. Il en manque " + (6 - (nombreJetons % 6)));
        }

        while (delimiteur.hasMoreTokens()) {
            //graphe.ajouterArrete(, false);
        }
        return graphe;
    }
}
