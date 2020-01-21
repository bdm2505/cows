package ru.bdm.tinex.logic;

public class Cow extends Animal {

    public Cow(int id, int idAI) {
        super(id, idAI);
    }

    @Override
    public String toString() {
        return "Cow{}" + super.toString();
    }
}
