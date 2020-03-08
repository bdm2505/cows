package ru.bdm.tinex.logic;

import java.util.Random;

public abstract class Animal extends Element {
    private static final Random rand = new Random();
    private final int idAI;
    protected int hp;
    protected Way way;
    protected Animal(int id, int idAI) {
        super(id);
        this.hp = 20;
        this.way = Way.randomWay();
        this.idAI = idAI;
    }

    public int getHp() {
        return hp;
    }

    public int getIdAI() {
        return idAI;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "hp=" + hp +
                ", way=" + way +
                ", id=" + getId() +
                '}';
    }


    public void eat() {
        hp += addHpForEat();
    }

    public void updateEat() {
        hp -= 1;
    }

    public boolean isDepth() {
        return hp <= 0;
    }

    public Way getWay() {
        return way;
    }

    public void rotateLeft() {
        way.rotateLeft();
    }

    public void rotateRight() {
        way.rotateRight();
    }

    public Animal reproduction(int idAi) {
        Animal animal = ElementFactory.copy(this, idAi);
        animal.hp = hp / 2;
        hp = hp - hp / 2;
        return animal;
    }

    public boolean isReproduction(){
        return getHp() > addHpForEat();
    }

    protected abstract int addHpForEat();
}
