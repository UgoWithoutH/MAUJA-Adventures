package com.mauja.maujaadventures.logique;

public class MementoPosition {
    private Position position;

    /**
     * Constructeur du mémento
     * @param position ancienne position du personnage
     * @throws IllegalArgumentException Argument levée si position est nulle
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public MementoPosition(Position position) throws IllegalArgumentException {
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre ne peut pas être nulle.");
        }
        this.position = position;
    }

    /**
     * Récupération de la position
     * @return position actuelle du mémento
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() {
        return position;
    }
}
