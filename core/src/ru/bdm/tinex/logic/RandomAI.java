package ru.bdm.tinex.logic;

import java.util.Random;

import static ru.bdm.tinex.logic.AI.Result.*;

public class RandomAI extends AI {

    private static final Random random = new Random();

    protected RandomAI(int id) {
        super(id);
    }

    public Result createRandom() {
        switch (random.nextInt(4)) {
            case 0:
                return GO;
            case 1:
                return ROTATE_LEFT;
            case 2:
                return ROTATE_RIGHT;
            case 3:
            default:
                return REPRODUCTION;
        }
    }

    @Override
    Result work(Element[] data) {
        return createRandom();
    }

    @Override
    int mutable(AIManager manager) {
        return id;
    }


}
