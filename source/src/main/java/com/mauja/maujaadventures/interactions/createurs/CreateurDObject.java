package com.mauja.maujaadventures.interactions.createurs;

import com.mauja.maujaadventures.annotations.ConstructeurXml;
import com.mauja.maujaadventures.annotations.Param;
import org.xml.sax.Attributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CreateurDObject {

    public Object creation(Attributes attributes, Map<String, Object> autreAttributs) {
        Object baliseCourante = null;
        try {
            Constructor<?>[] constructors = Class.forName(attributes.getValue("type")).getConstructors();
            Constructor<?> constructeur = null;
            for (Constructor<?> constructor: constructors) {
                if (constructor.isAnnotationPresent(ConstructeurXml.class)){
                    constructeur = constructor;
                }
            }
            if (constructeur != null) {
                Annotation[][] annotations = constructeur.getParameterAnnotations();
                Object[] listeAttributs = trierAttributs(attributes, autreAttributs, annotations, constructeur.getParameterCount());
                baliseCourante = constructeur.newInstance(listeAttributs);
            }
            else {
                baliseCourante = constructors[0].newInstance();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return baliseCourante;
    }

    protected Object[] trierAttributs(Attributes attributes, Map<String, Object> autreAttributs,
                                      Annotation[][] annotations, int nbParam) {
        Object[] listeAttributs =  new Object[nbParam];
        Param param;
        for (int i=0; i<nbParam ;i++) {
            param = (Param) annotations[i][0];
            try {
                if (param.estPrimitif()) {
                    Constructor<?> constructor = param.classe().getConstructor(String.class);
                    listeAttributs[i] = constructor.newInstance(attributes.getValue(param.nom()));
                }
                else {
                    listeAttributs[i] = autreAttributs.get(param.nom());
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return listeAttributs;
    }
}
