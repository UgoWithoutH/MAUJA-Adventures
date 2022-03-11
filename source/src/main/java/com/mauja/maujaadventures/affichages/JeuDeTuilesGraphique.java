package com.mauja.maujaadventures.affichages;

import com.mauja.maujaadventures.monde.JeuDeTuiles;

import java.util.Collections;
import java.util.List;

public class JeuDeTuilesGraphique {
    private JeuDeTuiles jeuDeTuiles;
    private List<TuileGraphique> lesTuilesGraphiques;

    public JeuDeTuilesGraphique(JeuDeTuiles jeuDeTuiles, List<TuileGraphique> lesTuilesGraphiques)
            throws IllegalArgumentException {
        if (jeuDeTuiles == null) {
            throw new IllegalArgumentException("Le jeu de tuiles passé en paramètre ne peut pas être null");
        }
        this.jeuDeTuiles = jeuDeTuiles;
        this.lesTuilesGraphiques = lesTuilesGraphiques;
    }

    public JeuDeTuiles getJeuDeTuiles() {
        return jeuDeTuiles;
    }

    public List<TuileGraphique> getLesTuilesGraphiques() {
        return Collections.unmodifiableList(lesTuilesGraphiques);
    }

    private void verificationTuiles(JeuDeTuiles jeuDeTuiles, List<TuileGraphique> lesTuiles)
            throws IllegalArgumentException {
        if (lesTuiles == null) {
            throw new IllegalArgumentException("La liste de tuiles passée en paramètre ne peut pas être nulle.");
        }
        if (jeuDeTuiles.getNombreTuiles() != lesTuiles.size()) {
            throw new IllegalArgumentException("Le nombre de tuiles du jeu de tuiles diffère du nombre de tuiles "
                    + "de la liste passée en paramètre. Jeu de tuiles : " + jeuDeTuiles.getNombreTuiles()
                    + ", liste de tuiles : " + lesTuiles.size());
        }

        for (TuileGraphique tuileGraphique : lesTuiles) {
            if (tuileGraphique == null) {
                throw new IllegalArgumentException("Une tuile passée en paramètre de la collection est nulle.");
            }
        }
    }
}
