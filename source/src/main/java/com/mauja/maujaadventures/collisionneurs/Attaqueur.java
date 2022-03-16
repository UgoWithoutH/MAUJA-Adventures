package com.mauja.maujaadventures.collisionneurs;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.logique.Dimension;

public interface Attaqueur {
    Attaque genererAttaque(Entite entite, Direction direction, Dimension dimension, int degats, float duree);
}
