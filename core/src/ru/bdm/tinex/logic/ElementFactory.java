package ru.bdm.tinex.logic;


public class ElementFactory {

    private static int nextId = 0;
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

    public static Animal copy(Animal animal, int idAi) {
        if(animal.isWolf())
            return wolf(idAi);
        else
            return cow(idAi);
    }
}