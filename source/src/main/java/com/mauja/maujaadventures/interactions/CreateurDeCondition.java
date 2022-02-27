package com.mauja.maujaadventures.interactions;

import org.xml.sax.Attributes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateurDeCondition implements CreateurDeBalise{
    @Override
    public Balise creation(Attributes attributes) {
        Condition baliseCourante = null;
        Constructor[] constructors;
        try {
            constructors = Class.forName(attributes.getValue("type")).getConstructors();
            baliseCourante = (Condition)constructors[0].newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return baliseCourante;
    }
}
