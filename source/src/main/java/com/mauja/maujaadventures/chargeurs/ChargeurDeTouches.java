package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.utilitaires.FormatInvalideException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public abstract class ChargeurDeTouches {
    private static final String DELIMITEUR_DONNEES = ":";
    private static final String COMMENTAIRES_IGNORES ="#.*";
    private Map<String, String> lesTouches;

    public ChargeurDeTouches() {
        lesTouches = new HashMap<>();
    }

    public Map<String, String> charge(String cheminFichier) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            StringBuilder donnees = new StringBuilder();
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                ligne = ligne.replaceAll(COMMENTAIRES_IGNORES, "");
                donnees.append(ligne).append(":");
            }
            String chaine = donnees.toString().trim().toUpperCase(Locale.ROOT);
            //chaine = chaine.replaceAll("\n", ":");
            creationTouches(chaine);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lesTouches;
    }

    private void creationTouches(String donnees) throws FormatInvalideException {
        StringTokenizer jetonneur = new StringTokenizer(donnees, DELIMITEUR_DONNEES);
        int nombreJetons = jetonneur.countTokens();

        if (nombreJetons % 2 != 0) {
            throw new FormatInvalideException("Le nombre de touches spécifiées dans le fichier"
                    + " de configuration est incorrect.");
        }

        while (jetonneur.hasMoreTokens()) {
            lesTouches.put(jetonneur.nextToken().trim(), jetonneur.nextToken().trim());
        }
    }
}
