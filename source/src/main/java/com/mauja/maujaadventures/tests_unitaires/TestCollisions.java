package com.mauja.maujaadventures.tests_unitaires;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCollisions {

    @BeforeEach
    public void creationCollision() {
        var collision = new Collision(new Position(0, 0), 20, 20);
    }
}
