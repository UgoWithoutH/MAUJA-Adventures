package com.mauja.maujaadventures.interactions.createurs;

import com.mauja.maujaadventures.interactions.Balise;
import com.mauja.maujaadventures.interactions.Condition;
import com.mauja.maujaadventures.interactions.createurs.CreateurDeBalise;
import org.xml.sax.Attributes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateurDeCondition extends CreateurDeBalise {
    @Override
    public Balise creation(Attributes attributes) {
        Condition baliseCourante = null;
        Constructor<?>[] constructors;
        try {
            constructors = Class.forName(attributes.getValue("type")).getConstructors();
            baliseCourante = (Condition)constructors[0].newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return baliseCourante;
    }
}
