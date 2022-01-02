package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.entites.Ennemi;

import java.util.ArrayList;
import java.util.List;

public class ChargeurEnnemisStub implements ChargeurEnnemis {
    @Override
    public List<Ennemi> charge(String chemin) {
        List<Ennemi> lesEnnemis = new ArrayList<>();
        return lesEnnemis;
    }
}
