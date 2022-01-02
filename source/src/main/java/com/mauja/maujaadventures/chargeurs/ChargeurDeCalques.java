package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.monde.Tuile;

import java.util.List;

public interface ChargeurDeCalques {
    List<List<Tuile>> charge(String chemin);
}
