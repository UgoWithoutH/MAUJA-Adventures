package com.mauja.maujaadventures.interactions;

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
