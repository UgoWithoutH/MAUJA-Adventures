package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.ContexteGraphique;
import com.mauja.maujaadventures.modele.Position;

public interface Afficheur {

    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique);
}