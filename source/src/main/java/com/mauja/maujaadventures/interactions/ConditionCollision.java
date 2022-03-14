package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.solveurs.collision.SolveurCollision;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

public class ConditionCollision extends Condition {
    private TypeCollision typeCollision;

    @Override
    public boolean verificationCondition(ElementInteractif elementInteractif, TableauDeJeu tableauDeJeu) {

        CollisionneurAABB collisionneur = new CollisionneurAABB();
        Position posElem = elementInteractif.getPosition();
        Dimension dimElem = elementInteractif.getCollision().getDimension();

        Rectangle rectangleElement = new Rectangle(posElem.getX(), posElem.getY(),
                dimElem.getLargeur(), dimElem.getHauteur());

        for(ElementInteractif elementInter : tableauDeJeu.getCarteCourante().getLesElementsInteractifs()){
            if(elementInter instanceof PersonnageJouable personnageJouable){
                Dimension dimPerso = personnageJouable.getAttaque().getCollision().getDimension();
                Position posPerso = personnageJouable.getAttaque().getCollision().getPosition();

                Rectangle rectangleAttaque = new Rectangle(posPerso.getX(), posPerso.getY(), dimPerso.getLargeur(), dimPerso.getHauteur());

                if(collisionneur.collisionne(rectangleElement, rectangleAttaque)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void ajouter(Balise balise) {
        getBaliseParente().ajouter(balise);
    }
}
