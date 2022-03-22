package com.mauja.maujaadventures.observateurs;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;

public interface ObservateurEvenementiel {
    void miseAJour(ElementInteractif elementInteractif, Boolean resultat, Object... parametres);
}
