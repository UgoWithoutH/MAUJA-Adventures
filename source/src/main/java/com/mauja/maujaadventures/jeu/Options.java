package com.mauja.maujaadventures.jeu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Options {
    IntegerProperty param = new SimpleIntegerProperty();
        public int getParam() {
            return param.get();
        }
        public IntegerProperty paramProperty() {
            return param;
        }
        public void setParam(int param) {
            this.param.set(param);
        }
}
