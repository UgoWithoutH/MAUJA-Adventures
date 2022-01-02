package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.monde.Carte;

interface ChargeurDeCartes {
    Carte charge(ChargeurEnnemis chargeurEnnemis, ChargeurDeCalques chargeurCalques);
}
