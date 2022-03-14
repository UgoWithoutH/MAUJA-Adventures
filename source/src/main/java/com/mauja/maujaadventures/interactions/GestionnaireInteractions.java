package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.interactions.evenements.Evenement;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Camera;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GestionnaireInteractions implements Runnable {
    private static GestionnaireInteractions gestionnaireInteractions;
    private ConcurrentLinkedQueue<Evenement> fileCourante;
    private ParseurInteraction parseurInteraction;
    private Thread thread;
    private boolean enCours;
    private TableauDeJeu tableauDeJeu;

    public GestionnaireInteractions(TableauDeJeu tableauDeJeu) throws IllegalArgumentException, IllegalStateException {
        if (gestionnaireInteractions != null) {
            throw new IllegalStateException("Il ne peut y avoir qu'un seul gestionnaire d'interactions, "
                    + "et il a déjà été initialisé.");
        }
        if (tableauDeJeu == null) {
            throw new IllegalArgumentException("Le tableau de jeu ne peut pas être null.");
        }
        this.tableauDeJeu = tableauDeJeu;
        gestionnaireInteractions = this;

        fileCourante = new ConcurrentLinkedQueue<>();
        parseurInteraction = new ParseurInteraction();

        enCours = false;
        initialisation();

        thread = new Thread(this, "Mauja Adventures Interaction Handler");
        thread.start();
    }

    public static GestionnaireInteractions getInstance() {
        if (gestionnaireInteractions == null) {
            throw new IllegalArgumentException("Le gestionnaire d'interactions n'a pas encore été initialisé.");
        }
        return gestionnaireInteractions;
    }

    public List<ElementInteractif> getElementAAjouter() {
        return parseurInteraction.getElementAAjouter();
    }

    public void ajouter(Evenement evenement) {
        if (evenement != null) {
            fileCourante.add(evenement);
            synchronized (this) {
                notify();
            }
        }
    }

    @Override
    public void run() {
        enCours = true;
        while (enCours) {
            if (!fileCourante.isEmpty()) {
                Evenement evenement = fileCourante.poll();
                evenement.traitement(parseurInteraction.getScenarios(), tableauDeJeu);
                verificationScenarios(parseurInteraction.getScenarios(), tableauDeJeu);
            }
            else {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void initialisation() {
        parseurInteraction.creerActionInteraction(Ressources.getInstance().getLesScripts().get(0));
    }

    private void verificationScenarios(List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        List<Condition> listConditionsASupprimer = new ArrayList<>();

        for (Scenario scenario : scenarios) {
            for (ElementInteractif element : scenario.getListeElemInteractif()) {
                Iterator<Map.Entry<Condition, List<Action>>> it = element.getMapConditionAction().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Condition, List<Action>> map = it.next();
                    for (Action action : map.getValue()) {
                        if(map.getKey().verificationCondition(element, tableauDeJeu)) {
                            action.agit(element, tableauDeJeu);
                            //listConditionsASupprimer.add(map.getKey());
                        }
                    }
                }
            }
        }
        //suppressionConditions(scenarios, listConditionsASupprimer);
    }

    /*private void suppressionConditions(List<Scenario> scenarios, List<Condition> listConditions){
        for(Scenario scenario : scenarios){
            for(ElementInteractif elementInteractif : scenario.getListeElemInteractif()){
                for(Condition condition : listConditions){
                    elementInteractif.getMapConditionAction().remove(condition);
                }
            }
        }
    }*/
}
