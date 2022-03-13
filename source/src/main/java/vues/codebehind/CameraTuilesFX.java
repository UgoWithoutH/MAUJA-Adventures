package vues.codebehind;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.Tuile;

import java.util.List;

public class CameraTuilesFX extends CameraTuiles {
    private List<Tuile> lesTuilesGraphiques;
    private Tuile[][] visionGraphique;

    public CameraTuilesFX(Carte carte, Dimension zoneVisuelle, List<Tuile> lesTuilesGraphiques)
            throws IllegalArgumentException {
        super(carte, zoneVisuelle);
        if (lesTuilesGraphiques == null) {
            throw new IllegalArgumentException("La liste de tuiles graphique doit être non nulle pour la création de la caméra.");
        }
        this.lesTuilesGraphiques = lesTuilesGraphiques;
        visionGraphique = new Tuile[(int) zoneVisuelle.getHauteur()][(int) zoneVisuelle.getLargeur()];
        //visionGraphique = new TuileFX[(int) zoneVisuelle.getLargeur()][(int) zoneVisuelle.getHauteur()];
    }

    public Tuile getTuileGraphique(int x, int y) {
        return visionGraphique[x][y];
    }

    @Override
    public void centrerSurEntite(Entite entite) {
        super.centrerSurEntite(entite);
        miseAJour();
    }

    private void miseAJour() {
        double largeurCamera = zoneObservable.getLargeur();
        double hauteurCamera = zoneObservable.getHauteur();

        for (int x = 0; x < largeurCamera; x++) {
            for (int y = 0; y < hauteurCamera; y++) {
                visionGraphique[x][y] = lesTuilesGraphiques.get(zoneVisible[x][y].getId());
            }
        }
    }
}
