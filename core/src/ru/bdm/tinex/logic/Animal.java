package ru.bdm.tinex.logic;

import java.util.Random;

public abstract class Animal extends Element {
    private static final Random rand = new Random();
    public static int addHpForCow = 10;
    public static int addHpForWolf = 20;
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
        if (isCow())
            hp += addHpForWolf;
        else if (isWolf())
            hp += addHpForCow;
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

    public Animal reproduction() {
        Animal animal = ElementFactory.copy(this);
        animal.hp = hp / 2;
        hp = hp - hp / 2;
        return animal;
    }
}
