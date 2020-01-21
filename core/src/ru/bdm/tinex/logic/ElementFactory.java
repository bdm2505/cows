package ru.bdm.tinex.logic;

import java.util.Random;

public class ElementFactory {

    private static int nextId = 0;
    private static Random rand = new Random();
    private static Empty emptySingle = new Empty(getNextId());

    private static int getNextId(){
        return nextId++;
    }


    public static Element grass() {
        return new Grass(getNextId());
    }

    public static Element stone() {
        return new Stone(getNextId());
    }

    public static Animal cow(int idAI) {
        return new Cow(getNextId(), idAI);
    }

    public static Animal wolf(int idAI) {
        return new Wolf(getNextId(), idAI);
    }

    public static Element empty() {
        return emptySingle;
    }

}