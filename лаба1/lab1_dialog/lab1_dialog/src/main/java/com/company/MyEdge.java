package com.company;

import org.jgrapht.graph.DefaultWeightedEdge;

public class MyEdge extends DefaultWeightedEdge {

    private int metka;

    public int getMetka() {
        return metka;
    }

    public void setMetka(int metka) {
        this.metka = metka;
    }
}
