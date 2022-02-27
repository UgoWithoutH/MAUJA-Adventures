package com.mauja.maujaadventures.interactions;

public abstract class Condition extends Balise {
    public abstract boolean verificationCondition();


    @Override
    public String toString() {
        return "Condition{}";
    }
}
