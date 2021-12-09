package com.mauja.maujaadventures.modele;

public class Collisionneur {
    /**
     * Méthode colission permet de voir si une collision est visible
     * @param rectangle1 Rectangle de la première collision
     * @param rectangle2 Rectangle de la seconde collision
     * @return true si il y a une collision sinon false
     * @throws IllegalArgumentException
     */
    boolean collision(Rectangle rectangle1, Rectangle rectangle2) throws IllegalArgumentException {
        double gauche, droite, haut, bas;
        verificationRectangle(rectangle1);
        verificationRectangle(rectangle2);
        Position position1 = rectangle1.getPosition();
        Position position2 = rectangle2.getPosition();

        verificationPosition(position1);
        verificationPosition(position2);

        gauche = Math.max(position1.getPositionX(), position2.getPositionX());
        droite = Math.min(position1.getPositionX() + rectangle1.getDimension().getLargeur(),
                position2.getPositionX() + rectangle2.getDimension().getLargeur());
        haut = Math.max(position1.getPositionY(), position2.getPositionY());
        bas = Math.min(position1.getPositionY() + rectangle1.getDimension().getHauteur(),
                position2.getPositionY() + rectangle2.getDimension().getHauteur());

        return (gauche < droite) && (haut < bas);
    }

    /**
     * Méthode permettant la vérification si une collision est présente
     * @param rectangle Collision à tester
     * @throws IllegalArgumentException Si la collision est nulle
     */
    private void verificationRectangle(Rectangle rectangle) throws IllegalArgumentException {
        if (rectangle == null) {
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
