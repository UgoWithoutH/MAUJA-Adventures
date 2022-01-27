package com.mauja.maujaadventures;

import com.mauja.maujaadventures.jeu.Options;

public class Manager {

    private final Options options;

    public Manager() {
        options = new Options();
    }

    public Options getOptions() {
        return options;
    }
}
