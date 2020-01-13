package ru.bdm.tinex.logic;

public class Animal extends Element {
    public int hp;
    public int way;
    public final int ai;

    public Animal(TypeElement type, int hp, int way, int ai) {
        super(type);
        this.hp = hp;
        this.way = way;
        this.ai = ai;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "hp=" + hp +
                ", way=" + way +
                ", id=" + id +
                ", type=" + type +
                '}';
    }

    public void rotateLeft() {
        way -= 1;
        if (way < 0) way = 3;
    }

    public void rotateRight() {
        way += 1;
        if (way > 3) way = 0;
    }
}
