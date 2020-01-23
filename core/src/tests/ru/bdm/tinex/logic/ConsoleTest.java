package ru.bdm.tinex.logic;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

    public static void main(String[] args) {
        Shepherd shepherd = Shepherd.createSimple(new MapSimple(), 300,200,10000, 1000, 500);
        time(shepherd);

        shepherd = Shepherd.createSimple(new MapArray(), 3000,200,10000, 1000, 500);
        time(shepherd);

        shepherd = Shepherd.createSimple(new MapArray(), 10 ,10, 10,10,10);
        System.out.println(shepherd.getMap());
        System.out.println(shepherd.getMap().copy());
    }

    private static void time(Shepherd shepherd) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            shepherd.nextTurn();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("time=" + (double)(endTime - startTime) / 1000);
    }
}
