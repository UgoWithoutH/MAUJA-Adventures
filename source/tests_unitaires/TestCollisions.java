import com.mauja.maujaadventures.modele.Collisionneur;
import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.Rectangle;
import com.mauja.maujaadventures.modele.Position;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class TestCollisions {

    Collisionneur collisionneur;
    Rectangle rectangle1, rectangle2, rectangle3, rectangle4;

    @BeforeEach
    public void creationCollision() {
        rectangle1 = new Rectangle(new Position(0, 0),
                new Dimension(20, 20));
        rectangle2 = new Rectangle(new Position(-10, -10),
                new Dimension(20, 20));
        rectangle3 = new Rectangle(new Position(-15, -10),
                new Dimension(40, 5));
        rectangle4 = new Rectangle(new Position(-10, -10),
                new Dimension(10, 10));
        collisionneur = new Collisionneur();
    }

    @Test
    @DisplayName("Il devrait y avoir collision sur ces deux carrés.")
    public void testCollisions() {
        assertAll("Résultat collision",
                () -> assertEquals(true, collisionneur.collision(rectangle1, rectangle2)),
                () -> assertEquals(false, collisionneur.collision(rectangle1, rectangle3)),
                () -> assertEquals(false, collisionneur.collision(rectangle1, rectangle4)),
                () -> assertEquals(true, collisionneur.collision(rectangle2, rectangle3)),
                () -> assertEquals(true, collisionneur.collision(rectangle2, rectangle4)),
                () -> assertEquals(true, collisionneur.collision(rectangle3, rectangle4))
        );
    }
}


