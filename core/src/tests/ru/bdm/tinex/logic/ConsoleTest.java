package ru.bdm.tinex.logic;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

    public static void main(String[] args) {
        int NUMBER = 30;
        double simple = 0;
        double array = 0;

        for (int i = 0; i < NUMBER; i++) {


            Shepherd shepherd = Shepherd.createSimple(new MapSimple(), 100, 100, 5000, 1000, 500);
            simple += time(shepherd);

            shepherd = Shepherd.createSimple(new MapArray(), 100, 100, 5000, 1000, 500);
            array += time(shepherd);

        }

        System.out.println("simple = " + simple / NUMBER + "  array = " + array / NUMBER);
//        shepherd = Shepherd.createSimple(new MapArray(), 10 ,10, 10,10,10);
//        System.out.println(shepherd.getMap());
//        System.out.println(shepherd.getMap().copy());
    }

    private static double time(Shepherd shepherd) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            shepherd.nextTurn();
        }
        long endTime = System.currentTimeMillis();

        return  (double)(endTime - startTime) / 1000;
    }
}
