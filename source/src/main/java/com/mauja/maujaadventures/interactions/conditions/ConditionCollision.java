package com.mauja.maujaadventures.interactions.conditions;

import com.mauja.maujaadventures.interactions.elements.Balise;
import com.mauja.maujaadventures.interactions.TypeCollision;

public class ConditionCollision extends Condition {
    private TypeCollision typeCollision;

    @Override
    public boolean verificationCondition() {
        return false;
    }

    @Override
    public void ajouter(Balise balise) {
        getBaliseParente().ajouter(balise);
    }
}
