package ru.bdm.tinex.logic;

import java.util.Collection;
import java.util.Random;

public abstract class Map {
    private static Random rand = new Random();
    protected int width, height;


    public abstract Element getElement(Pos pos);

    public abstract Pos getPosition(Element element);

    public abstract Collection<Animal> getAnimals();

    /**
     * put element in position
     * if map contain element, then move it
     * if there is someone, then delete it
     */
    public abstract void put(Element element, Pos pos);

    public abstract void remove(Element element);

    public abstract void remove(Pos pos);
    public abstract boolean containOf(Pos pos);

    public Element[] getSeeArea(Animal animal) {
        Pos pos = getPosition(animal);
        Pos[] positions = SeeAreaGenerator.generate(animal.getWay(), pos);
        Element[] result = new Element[positions.length];
        for (int i = 0; i < positions.length; i++) {
            result[i] = getElement(positions[i]);
        }
        return result;
    }

    public void randomEmptyPut(Element e) {
        Pos pos;
        int number = 100;
        do {
            pos = new Pos(rand.nextInt(width - 2) + 1, rand.nextInt(height - 2) + 1);
        } while (!getElement(pos).isEmpty() && number-- > 0);
        if (number > 0) {
            put(e, pos);
        }
    }

    public void setSize(int w, int h) {
        width = w;
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
