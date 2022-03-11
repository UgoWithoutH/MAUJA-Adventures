package com.mauja.maujaadventures.interactions.createurs;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.interactions.Balise;
import org.xml.sax.Attributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class   CreateurDeBalise {

    public abstract Balise creation(Attributes attributes);

    protected Object[] trierAttributs(Attributes attributes, Annotation[][] annotations, int nbParam) {
        Object[] listeAttributs =  new Object[nbParam];
        Param param;
        for (int i=0; i<nbParam ;i++) {
            param = (Param) annotations[i][0];
            try {
                if (param.estPrimitif) {
                    Constructor<?> constructor = param.classe().getConstructor(String.class);
                    listeAttributs[i] = constructor.newInstance(attributes.getValue(param.nom()));
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return listeAttributs;
    }
}
