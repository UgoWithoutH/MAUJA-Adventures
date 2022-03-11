package com.mauja.maujaadventures.interactions.createurs;

import com.mauja.maujaadventures.annotations.ConstructeurXml;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mauja.maujaadventures.interactions.Action;
import com.mauja.maujaadventures.interactions.Balise;
import com.mauja.maujaadventures.interactions.Condition;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.createurs.CreateurDeBalise;
import org.xml.sax.Attributes;

public class CreateurDElementInteractif extends CreateurDeBalise {
    @Override

    public Balise creation(Attributes attributes) {

        ElementInteractif baliseCourante = null;
        Map<Condition, List<Action>> map = new HashMap<>();
        try {
            Constructor<?>[] constructors = Class.forName(attributes.getValue("type")).getConstructors();
            Constructor<?> constructeur = null;
            for (Constructor<?> constructor: constructors) {
                if (constructor.isAnnotationPresent(ConstructeurXml.class)){
                    constructeur = constructor;
                }
            }
            assert constructeur != null;
            Annotation[][] annotations = constructeur.getParameterAnnotations();
            Object[] listeAttributs =  trierAttributs(attributes,annotations,constructeur.getParameterCount());
            baliseCourante = (ElementInteractif) constructeur.newInstance(listeAttributs);

            baliseCourante.setMapConditionAction(map);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return baliseCourante;
    }

}
