package ru.bdm.tinex.logic;

public enum TypeElement {
    EMPTY("Empty"),
    GRASS("grass"),
    COW("cow"),
    WOLF("wolf"),
    BLOCK("stone"),
    ;
    String name;

    TypeElement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
