package com.mauja.maujaadventures.interactions.conditions;

import com.mauja.maujaadventures.interactions.elements.Balise;

public abstract class Condition extends Balise {
    public abstract boolean verificationCondition();

    @Override
    public String toString() {
        return "Condition{}";
    }
}
