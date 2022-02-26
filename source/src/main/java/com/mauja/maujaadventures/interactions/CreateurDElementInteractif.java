package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.annotations.ConstructDef;
import com.mauja.maujaadventures.annotations.Param;


import java.lang.annotation.Annotation;
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
            Constructor[] constructors = Class.forName(attributes.getValue("type")).getConstructors();
            Constructor constructeur = null;
            for (Constructor constructor: constructors) {
                if (constructor.isAnnotationPresent(ConstructDef.class)){
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

    private Object[] trierAttributs(Attributes attributes, Annotation[][] annotations, int nbParam) {
        Object[] listeAttributs =  new Object[nbParam];
        Param param;
        for (int i=0; i<nbParam ;i++) {
            param = (Param) annotations[i][0];
            try {
                Constructor constructor = param.classe().getConstructor(String.class);
                listeAttributs[i]= constructor.newInstance(attributes.getValue(param.nom()));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return listeAttributs;
    }
}
