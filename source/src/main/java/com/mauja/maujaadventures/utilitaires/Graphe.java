package com.mauja.maujaadventures.utilitaires;

import java.util.*;
import java.util.function.Consumer;

public class Graphe<T> implements Iterable<T> {
    private Map<T, List<T>> lesSommets;

    public Graphe() {
        lesSommets = new HashMap<>();
    }

    public int getNombreSommets() {
        return lesSommets.size();
    }

    public int getNombreArretes() {
        int nombre = 0;
        for (Map.Entry<T, List<T>> sommet : lesSommets.entrySet()) {
            nombre += sommet.getValue().size();
        }
        return nombre / 2;
    }

    public boolean isSommet(T sommet) {
        return lesSommets.containsKey(sommet);
    }

    public boolean isArrete(T sommetSource, T sommetDestination) {
        List<T> transitions =  lesSommets.get(sommetSource);
        if (transitions == null)
            return false;
        return transitions.contains(sommetDestination);
    }

    public void ajouterSommet(T sommet) {
        lesSommets.put(sommet, new LinkedList<>());
    }

    public void ajouterArrete(T sommetSource, T sommetDestination, boolean bidirectionnel) {
        if (!lesSommets.containsKey(sommetSource)) {
            ajouterSommet(sommetSource);
        }

        if (!lesSommets.containsKey(sommetDestination)) {
            ajouterSommet(sommetDestination);
        }
        lesSommets.get(sommetSource).add(sommetDestination);

        if (bidirectionnel) {
            lesSommets.get(sommetDestination).add(sommetSource);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return lesSommets.keySet().iterator();
    }

    public boolean equals(Graphe<T> graphe) {
        if ((graphe.getNombreSommets() != getNombreSommets())
                || (graphe.getNombreArretes() != getNombreArretes())) {
            return false;
        }

        for (Map.Entry<T, List<T>> sommets : lesSommets.entrySet()) {
            if (!graphe.isSommet(sommets.getKey())) {
                return false;
            }
            for (T sommet : sommets.getValue()) {
                if (!graphe.isArrete(sommets.getKey(), sommet)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graphe<?> graphe = (Graphe<?>) o;
        return equals(graphe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesSommets);
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder("[");

        for (T sommetSource : lesSommets.keySet()) {
            chaine.append(sommetSource.toString());
            chaine.append(" => ");
            chaine.append("[");
            for (T sommetDestination : lesSommets.get(sommetSource)) {
                chaine.append(sommetDestination.toString());
                chaine.append(", ");
            }
            chaine.append("], ");
        }
        chaine.append("]");

        return (chaine.toString());
    }
}
