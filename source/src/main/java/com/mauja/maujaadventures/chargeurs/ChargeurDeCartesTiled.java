package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.utilitaires.FormatInvalideException;

import java.io.FileNotFoundException;

public interface ChargeurDeCartesTiled {
    Carte charge(String chemin) throws FileNotFoundException, FormatInvalideException;
}
