package com.ilouse.geekstoreV2.enumm;

public enum City {
    NULL(0),
    CASABLANCA(1),
    RABAT(2),
    FES(3),
    MARRAKECH(4),
    TANGER(5);

    private final int index;

    City(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

