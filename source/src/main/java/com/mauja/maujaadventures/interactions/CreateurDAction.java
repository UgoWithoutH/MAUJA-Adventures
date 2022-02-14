package com.mauja.maujaadventures.interactions;

import org.xml.sax.Attributes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateurDAction implements CreateurDeBalise{
    @Override
    public Balise creation(Attributes attributes) {
        Action baliseCourante = null;
        Constructor[] constructors = new Constructor[0];
        try {
            constructors = Class.forName(attributes.getValue("type")).getConstructors();
            baliseCourante = (Action)constructors[0].newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return baliseCourante;
    }
}