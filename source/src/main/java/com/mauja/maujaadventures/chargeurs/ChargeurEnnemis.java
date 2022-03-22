package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.entites.Ennemi;

import java.util.List;

public interface ChargeurEnnemis {
    List<Ennemi> charge(String chemin);
}
