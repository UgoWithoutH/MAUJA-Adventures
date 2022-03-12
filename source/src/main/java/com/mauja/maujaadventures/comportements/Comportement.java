package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.entites.Vivant;

public interface Comportement {
    void agit(Vivant vivant, float temps);
}
