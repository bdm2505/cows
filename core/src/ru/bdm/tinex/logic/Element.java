package ru.bdm.tinex.logic;

import java.util.Objects;
import java.util.Random;

public class Element {
    private static int nextId = 0;
    private static Random rand = new Random();
    public final int id = nextId++;
    public final TypeElement type;

    public Element(TypeElement type) {
        this.type = type;
    }

    public static Element grass() {
        return new Element(TypeElement.GRASS);
    }

    public static Element block() {
        return new Element(TypeElement.BLOCK);
    }

    public static Animal cow() {
        return new Animal(TypeElement.COW, rand.nextInt(10) + 1, rand.nextInt(4), 0);
    }

    public static Animal wolf() {
        return new Animal(TypeElement.WOLF, rand.nextInt(10) + 1, rand.nextInt(4), 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id &&
                type == element.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }

    public boolean isAnimal (){
        return getClass() == Animal.class;
    }

    public Animal toAnimal (){
        return (Animal) this;
    }
}
