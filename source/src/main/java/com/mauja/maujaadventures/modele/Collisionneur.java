package com.mauja.maujaadventures.modele;

public class Collisionneur {
    /**
     * Méthode colission permet de voir si une collision est visible
     * @param collision1 Rectangle de la première collision
     * @param collision2 Rectangle de la seconde collision
     * @return true si il y a une collision sinon false
     * @throws IllegalArgumentException
     */
    boolean collisionne(Collision collision1, Collision collision2) throws IllegalArgumentException {
        double gauche, droite, haut, bas;
        verificationCollision(collision1);
        verificationCollision(collision2);
        Position position1 = collision1.getPosition();
        Position position2 = collision2.getPosition();

        verificationPosition(position1);
        verificationPosition(position2);

        gauche = Math.max(position1.getPositionX(), position2.getPositionX());
        droite = Math.min(position1.getPositionX() + collision1.getLargeur(),
                position2.getPositionX() + collision2.getLargeur());
        haut = Math.max(position1.getPositionY(), position2.getPositionY());
        bas = Math.min(position1.getPositionY() + collision1.getHauteur(),
                position2.getPositionY() + collision2.getHauteur());

        return (gauche < droite) && (haut < bas);
    }

    /**
     * Méthode permettant la vérification si une collision est présente
     * @param collision Collision à tester
     * @throws IllegalArgumentException Si la collision est nulle
     */
    private void verificationCollision(Collision collision) throws IllegalArgumentException {
        if (collision == null) {
            throw new IllegalArgumentException("La collision donnée en paramètre est nulle");
        }
    }

    /**
     * Méthode permettant de savoir si la position est nulle
     * @param position Position à tester
     * @throws IllegalArgumentException levé si la position est nulle
     */
    private void verificationPosition(Position position) throws IllegalArgumentException {
        if (position == null) {
            throw new IllegalArgumentException("La collision donnée en paramètre est nulle");
        }
    }
}
