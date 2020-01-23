package ru.bdm.tinex.logic;

public class Wolf extends Animal {
    public Wolf(int id, int idAI) {
        super(id, idAI);
    }

    @Override
    public String toString() {
        return "Wolf{} " + super.toString();
    }

    @Override
    public Element copy() {
        Wolf wolf = new Wolf(getId(), getIdAI());
        wolf.hp = hp;
        wolf.way = way.copy();
        return wolf;
    }
}
