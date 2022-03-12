package com.mauja.maujaadventures.monde;


import com.mauja.maujaadventures.logique.Dimension;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JeuDeTuiles {
    private final String identifiant;
    private int nombreTuiles;
    private String cheminJeuDeTuiles;
    private Dimension dimensionJeuDeTuiles;
    private Dimension dimensionTuiles;
    private List<Tuile> listeDeTuiles;

    /**
     * Constructeur du jeu de tuiles
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public JeuDeTuiles(Dimension dimensionJeuDeTuiles, String identifiant, List<Tuile> lesTuiles, String cheminJeuDeTuiles)
            throws IllegalArgumentException {
        if (lesTuiles == null || lesTuiles.isEmpty()) {
            throw new IllegalArgumentException("Le jeu de tuile ne peut pas être vide.");
        }
        if (identifiant == null || identifiant.trim().isEmpty()) {
            throw new IllegalArgumentException("L'identifiant passé en paramètre ne peut pas être null.");
        }
        if (cheminJeuDeTuiles == null) {
            throw new IllegalArgumentException("L'URL du tileset passé en paramètre est nulle.");
        }
        verificationTuiles(lesTuiles);
        this.dimensionJeuDeTuiles = dimensionJeuDeTuiles;
        verificationDimensionTuiles(lesTuiles);
        this.cheminJeuDeTuiles = cheminJeuDeTuiles;
        this.identifiant = identifiant;
        listeDeTuiles = lesTuiles;
        nombreTuiles = listeDeTuiles.size();
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public int getNombreTuiles() {
        return nombreTuiles;
    }

    public String getCheminJeuDeTuiles() {
        return cheminJeuDeTuiles;
    }

    public Dimension getDimensionJeuDeTuiles() {
        return dimensionJeuDeTuiles;
    }

    public Dimension getDimensionTuiles() {
        return dimensionTuiles;
    }

    public List<Tuile> getListeDeTuiles() {
        return Collections.unmodifiableList(listeDeTuiles);
    }

    /**
     * Redéfinition du HashCode
     * @return Valeurs de l'Hachage des attributs de jeu de tuiles
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return identifiant.hashCode();
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        JeuDeTuiles autre = (JeuDeTuiles) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param jt Jeu de Tuile que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(JeuDeTuiles jt) {
        return jt != null && jt.getIdentifiant().equals(identifiant);
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();
        for (Tuile tuile : listeDeTuiles) {
            chaine.append(tuile.getId()).append(" ");
        }
        return chaine.toString();
    }

    private void verificationTuiles(List<Tuile> lesTuiles) throws IllegalArgumentException {
        for (Tuile tuile : lesTuiles) {
            if (tuile == null) {
                throw new IllegalArgumentException("Une tuile passée en paramètre du jeu de tuile est nulle.");
            }
        }
    }

    private void verificationDimensionTuiles(List<Tuile> lesTuiles) throws IllegalArgumentException {
        dimensionTuiles = lesTuiles.get(0).getDimension();

        if (dimensionJeuDeTuiles == null
                || dimensionJeuDeTuiles.getHauteur() * dimensionJeuDeTuiles.getLargeur() != lesTuiles.size()) {
            throw new IllegalArgumentException("Les dimensions du jeu de tuiles passé en paramètre "
                    + "sont nulles ou incorrectes au nombre de tuiles. Donné : " + dimensionJeuDeTuiles
                    + ", Nombre de tuiles dans le jeu : " + lesTuiles.size());
        }

        for (Tuile tuile : lesTuiles) {
            if (!tuile.getDimension().equals(dimensionTuiles)) {
                throw new IllegalArgumentException("Les tuiles doivent toute faire la même dimension. "
                        + "Donné : " + dimensionTuiles + ", trouvé : " + tuile);
            }
        }
    }
}
