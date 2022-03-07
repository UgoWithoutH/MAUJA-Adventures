package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.Dimension;
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
            System.out.println(chaine);
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

        if (nombreJetons % 6 != 0) {
            throw new FormatInvalideException("Le nombre de champs spécifiés dans le fichier "
                    + "de transitions entre les cartes est incorrect. Il en manque " + (6 - (nombreJetons % 6)));
        }

        int compteur = 0;

        TransitionCarte transition1, transition2;
        while (nombreJetons != compteur) {
            System.out.println(compteur);
            Carte depart = carteExiste(jetons[compteur], lesCartes);
            Carte arrivee = carteExiste(jetons[3 + compteur], lesCartes);

            try {
                double positionX1 = Integer.parseInt(jetons[1 + compteur]), positionY1 = Integer.parseInt(jetons[2 + compteur]),
                        positionX2 = Integer.parseInt(jetons[4 + compteur]), positionY2 = Integer.parseInt(jetons[5 + compteur]);
                Position position1 = new Position(
                        positionX1 * depart.getDimensionTuiles().getLargeur(),
                        positionY1 * depart.getDimensionTuiles().getHauteur()
                );
                Position position2 = new Position(
                        positionX2 * arrivee.getDimensionTuiles().getLargeur(),
                        positionY2 * arrivee.getDimensionTuiles().getHauteur()
                );
                transition1 = new TransitionCarte(jetons[compteur], new Position(positionX1, positionY1),
                        new Rectangle(position1, depart.getDimensionTuiles()));
                transition2 = new TransitionCarte(jetons[3 + compteur], new Position(positionX2, positionY2),
                        new Rectangle(position2, arrivee.getDimensionTuiles()));
                graphe.put(transition1, transition2);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }

            compteur += 6;
        }
        System.out.println(graphe);
        return graphe;
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
