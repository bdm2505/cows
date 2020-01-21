package ru.bdm.tinex.logic;

import java.util.Random;

public class Animal extends Element {
    private static final Random rand = new Random();
    public static int addHpForCow = 10;
    public static int addHpForWolf = 20;
    private final int idAI;
    private int hp;
    private Way way;

    public Animal(int id, int idAI) {
        super(id);
        this.hp = 20;
        this.way = new Way();
        this.idAI = idAI;
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

    public void move() {
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
}
