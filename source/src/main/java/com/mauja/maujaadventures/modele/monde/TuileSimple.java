package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.Collision;

public class TuileSimple extends Tuile{
    /**
     * Constructeur de la classe TuileSimple, appelle sa classe mère Tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public TuileSimple(int id, String identifiantJeuDeTuile, Collision collision, String chemin) {
        super(id, identifiantJeuDeTuile, collision, chemin);
    }
}
