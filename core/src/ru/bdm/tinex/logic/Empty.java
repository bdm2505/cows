package ru.bdm.tinex.logic;

public class Empty extends Element {
    protected Empty(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Empty{} " + super.toString();
    }

    @Override
    public Element copy() {
        return this;
    }
}
