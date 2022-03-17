package com.mauja.maujaadventures.collisionneurs;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.logique.Dimension;

/**
 * Interface Attaqueur, avec une méthode de générer une attaque
 */
public interface Attaqueur {
    Attaque genererAttaque(Entite entite, Direction direction, Dimension dimension, int degats, float duree);
}
