package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;

public class CreateurDElementInteractif implements CreateurDeBalise{
    @Override

    public Balise creation(Attributes attributes) {

        ElementInteractif baliseCourante = null;
        Map<Condition, List<Action>> map = new HashMap<>();
        try {
            if (attributes.getValue("type").equalsIgnoreCase("com.mauja.maujaadventures.entites.Ennemi")) {
                Constructor[] constructors;

                constructors = Class.forName(attributes.getValue("type")).getConstructors();

                baliseCourante = (ElementInteractif) constructors[0].newInstance(new Position(
                                Double.parseDouble(attributes.getValue("x")),
                                Double.parseDouble(attributes.getValue("y"))),
                        new Dimension(Double.parseDouble(attributes.getValue("largeur")),
                                Double.parseDouble(attributes.getValue("hauteur"))),
                        new Rectangle(10, 10, 10, 10), null, null, null,
                        Integer.parseInt(attributes.getValue("ptsVie")));
            }
            if (attributes.getValue("type").equalsIgnoreCase("com.mauja.maujaadventures.interactions.Levier")) {
                Constructor[] constructors = Class.forName(attributes.getValue("type")).getConstructors();
                baliseCourante = (ElementInteractif) constructors[0].newInstance(
                        Double.parseDouble(attributes.getValue("x")),
                        Double.parseDouble(attributes.getValue("y")));
            }
            assert baliseCourante != null;
            baliseCourante.setMapConditionAction(map);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return baliseCourante;
    }
}
