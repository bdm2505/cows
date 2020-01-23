package ru.bdm.tinex.logic;

import java.util.Objects;
import java.util.Random;

public class Way {
    public static final Way UP = new Way(0);
    public static final Way LEFT = new Way(1);
    public static final Way DOWN = new Way(2);
    public static final Way RIGHT = new Way(3);

    private static final Random rand = new Random();
    private static final float[] ways = new float[]{0, 90, 180, 270};
    private int value;

    public static Way randomWay() {
        return new Way(rand.nextInt(4));
    }

    private Way(int value) {
        this.value = value;
    }

    public void rotateLeft() {
        value -= 1;
        if (value < 0) value = 3;
    }

    public void rotateRight() {
        value += 1;
        if (value > 3) value = 0;
    }

    public float getDegrees(){
        return ways[value];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Way way = (Way) o;
        return value == way.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isUp(){
        return equals(UP);
    }

    public boolean isLeft(){
        return equals(LEFT);
    }

    public boolean isRight(){
        return equals(RIGHT);
    }
    public boolean isDown(){
        return equals(DOWN);
    }

    @Override
    public String toString() {
        return "Way{" +
                 + getDegrees() +
                '}';
    }

    public Way copy() {
        return new Way(value);
    }
}
