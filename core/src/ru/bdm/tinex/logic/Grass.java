package ru.bdm.tinex.logic;

public class Grass extends Element {

    public Grass(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Grass{} " + super.toString();
    }

    @Override
    public Element copy() {
        return this;
    }
}