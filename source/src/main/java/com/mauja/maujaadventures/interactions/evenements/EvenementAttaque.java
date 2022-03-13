package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.solveurs.attaque.SolveurAttaque;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Carte;

import java.util.List;

public class EvenementAttaque extends Evenement {
    private Carte carte;

    public EvenementAttaque(Carte carteCourante, ElementInteractif elementInteractif)
            throws IllegalArgumentException {
        super(elementInteractif);
        if (carteCourante == null) {
            throw new IllegalArgumentException("La carte passée en paramètre ne peut pas être nulle.");
        }
    }

    @Override
    public void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        SolveurAttaque solveurAttaque = new SolveurAttaque(tableauDeJeu.getCarteCourante());

        for (ElementInteractif elementInter : tableauDeJeu.getCarteCourante().getLesElementsInteractifs()){
            if(elementInteractif != elementInter) {
                solveurAttaque.resoud(elementInteractif, elementInter);
            }
        }
        /*
        if(elementInteractif instanceof PersonnageJouable personnage){
            for(Scenario scenario :  scenarios){
                for(ElementInteractif e  : scenario.getListeElemInteractif()){
                    if(e instanceof Levier levier) {
                        Iterator<Map.Entry<Condition, List<Action>>> it = e.getMapConditionAction().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Condition, List<Action>> a = it.next();
                            if (a.getKey() instanceof ConditionCollision) {
                                if (!levier.isActive()) {
                                    Dimension dim = personnage.getAttaque().getCollision().getDimension();
                                    Position pos = personnage.getAttaque().getCollision().getPosition();
                                    Rectangle rectangleAttaque = new Rectangle((int) pos.getX(), (int) pos.getY(), (int) dim.getLargeur(), (int) dim.getHauteur());
                                    Rectangle rectangleLevier = new Rectangle((int) levier.getPosition().getX(), (int) levier.getPosition().getY(), (int) Levier.getLargeurDefaut(), (int) Levier.getHauteurDefaut());
                                    if (rectangleAttaque.intersects(rectangleLevier)) {
                                        levier.setActive(true);
                                        for (Action action : a.getValue()) {
                                            action.agit(tableauDeJeu);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        */
    }
}
