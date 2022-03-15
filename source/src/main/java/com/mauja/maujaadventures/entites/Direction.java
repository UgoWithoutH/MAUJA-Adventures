package com.mauja.maujaadventures.entites;

/**
 * Ennumération des directions possibles pour le personnage
 */
public enum Direction {
    DROITE ((byte)1),
    GAUCHE ((byte)2),
    HAUT ((byte)3),
    BAS ((byte)4);

    private byte val;

    Direction(byte val) {
        this.val = val;
    }

    public byte getVal(){
        return this.val;
    }

    public static Direction valeurDe(byte val)
    {
        for(Direction valD : Direction.values()) {
            if (valD.getVal() == val) {
                return valD;
            }
        }
        throw new RuntimeException("Valeur incorrect valeur direction: " + val );
    }



}
