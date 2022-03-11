package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.chargeurs.Ressources;
import com.mauja.maujaadventures.interactions.evenements.Evenement;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Camera;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GestionnaireInteractions {
    private static GestionnaireInteractions gestionnaireInteractions;
    private Queue<Evenement> fileCourante;
    private Queue<Evenement> fileSauvegarde;
    private ParseurInteraction parseurInteraction;
    private Thread thread;
    private boolean enCours;
    private TableauDeJeu tableauDeJeu;
    private Camera camera;

    private GestionnaireInteractions() {
        fileCourante = new LinkedList<>();
        fileSauvegarde = new LinkedList<>();
        parseurInteraction = new ParseurInteraction();
        enCours = false;
        initialisation();
    }

    public static GestionnaireInteractions getInstance() {
        if(gestionnaireInteractions == null) {
            gestionnaireInteractions = new GestionnaireInteractions();
        }
        return gestionnaireInteractions;
    }

    public List<ElementInteractif> getElementAAjouter() {
        return parseurInteraction.getElementAAjouter();
    }

    public void initialisationBoucleEvenementielle(TableauDeJeu tableauDeJeu, Camera camera){
        this.tableauDeJeu = tableauDeJeu;
        this.camera = camera;
    }

    private void initialisation() {
        parseurInteraction.creerActionInteraction(Ressources.getInstance().getLesScripts().get(0));
    }

    public void ajouter(Evenement evenement) {
        if (!enCours) {
            fileCourante.add(evenement);
            if(thread != null) {
                synchronized (this) {
                    notify();
                }
            }
            else {
                thread = new Thread(this::traitement, "Thread Traitement évènement");
                thread.start();
            }
        }
        else{
            fileSauvegarde.add(evenement);
        }
    }

    private void traitement() {
        enCours = true;
        while(true) {
            if(fileCourante.size() != 0){
                Evenement evenement = fileCourante.poll();
                if(evenement != null) {
                    evenement.traitement(parseurInteraction.getScenarios(), tableauDeJeu, camera);
                }
            }
            else{
                if(fileSauvegarde.size() != 0){
                    System.out.println("file courante : " + fileCourante.size());
                    System.out.println("file sauvegarde : " + fileSauvegarde.size());
                    fileCourante = new LinkedList<>(fileSauvegarde);
                    fileSauvegarde = new LinkedList<>();
                }
                else {
                    enCours = false;
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
    }
}
