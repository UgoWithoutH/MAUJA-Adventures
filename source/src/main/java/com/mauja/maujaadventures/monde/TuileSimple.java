package com.mauja.maujaadventures.monde;

import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Rectangle;

public class TuileSimple extends Tuile {
    /**
     * Constructeur de la classe TuileSimple, appelle sa classe mère Tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public TuileSimple(int id, String identifiantJeuDeTuile, Rectangle collision, Dimension dimension) {
        super(id, identifiantJeuDeTuile, collision, dimension);
    }
}
