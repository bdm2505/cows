package ru.bdm.tinex.logic;

import java.util.Objects;
import java.util.Random;

public class Element {

    private int id;

    protected Element(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                '}';
    }

    public boolean isAnimal (){
        return isType(Animal.class);
    }

    public boolean isStone() {
        return isType(Stone.class);
    }
    public boolean isGrass() {
        return isType(Grass.class);
    }
    public boolean isCow(){
        return isType(Cow.class);
    }
    public boolean isWolf(){
        return isType(Wolf.class);
    }

    public Animal toAnimal (){
        return (Animal) this;
    }

    public boolean isType(Class<? extends Element> type){
        return getClass() == type;
    }

    public boolean isEmpty() {
        return isType(Element.class);
    }

    public boolean nonEmpty() {
        return !isEmpty();
    }
}
