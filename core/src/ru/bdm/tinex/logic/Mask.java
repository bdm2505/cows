package ru.bdm.tinex.logic;

import java.util.HashMap;
import java.util.Random;

public class Mask {

    public static final int MAX = 10;
    private static final Random random = new Random();
    private HashMap<Class<?>, int[]> types;

    public Mask(int[] stone, int[] grass, int[] cow, int[] wolf, int[] empty) {

        types = new HashMap<>(4);
        types.put(Stone.class, stone);
        types.put(Grass.class, grass);
        types.put(Cow.class, cow);
        types.put(Wolf.class, wolf);
        types.put(Empty.class, empty);

    }

    public static Mask random() {
        return new Mask(randomArray(), randomArray(), randomArray(), randomArray(), randomArray());
    }

    private static int[] randomArray() {
        int[] arr = new int[14];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(MAX);
        }
        return arr;
    }

    public int getResult(Element[] elements) {
        int result = 0;
        for (int i = 0; i < elements.length; i++) {

            result += types.get(elements[i].getClass())[i];
        }
        return result;
    }

}
