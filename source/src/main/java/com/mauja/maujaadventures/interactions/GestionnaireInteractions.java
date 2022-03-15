package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.interactions.actions.Action;
import com.mauja.maujaadventures.interactions.conditions.Condition;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.evenements.Evenement;
import com.mauja.maujaadventures.interactions.parseurs.ParseurInteraction;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GestionnaireInteractions implements Runnable {
    private static GestionnaireInteractions gestionnaireInteractions;
    private ParseurInteraction parseurInteraction;
    private Thread thread;

    private ConcurrentLinkedQueue<Evenement> fileCourante;
    private List<Scenario> lesScenarios;
    private TableauDeJeu tableauDeJeu;
    private boolean enCours;

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

        initialisation();
    }

    public static GestionnaireInteractions getInstance() {
        if (gestionnaireInteractions == null) {
            throw new IllegalArgumentException("Le gestionnaire d'interactions n'a pas encore été initialisé.");
        }
        return gestionnaireInteractions;
    }

    public void lancerGestionnaire() throws IllegalStateException {
        if (thread != null && thread.isAlive()) {
            throw new IllegalStateException("Impossible de lancer le Thread du gestionnaire d'interaction car "
                    + "il est déjà en cours.");
        }
        thread = new Thread(this, "Mauja Adventures Interaction Handler");
        thread.start();
    }

    public void arreterGestionnaire() {
        enCours = false;
        try {
            synchronized (this) {
                notify();
            }
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isLance() {
        return thread != null && thread.isAlive();
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
        lesScenarios = parseurInteraction.getScenarios();
        for (Scenario scenario : lesScenarios) {
            tableauDeJeu.getCarteCourante().ajouterElementsInteractifs(scenario.getListeElemInteractif());
        }
    }

    private void verificationScenarios(List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        for (Scenario scenario : scenarios) {
            for (ElementInteractif element : scenario.getListeElemInteractif()) {
                for (Map.Entry<Condition, List<Action>> map : element.getMapConditionAction().entrySet()) {
                    if (map.getKey().verificationCondition(element, tableauDeJeu)) {
                        for (Action action : map.getValue()) {
                            action.agit(element, tableauDeJeu);
                        }
                    }
                }
            }
        }
    }
}
