package ru.bdm.tinex.logic;

import java.util.Random;

import static ru.bdm.tinex.logic.AI.Result.*;

public class RandomAI extends AI {

    private static final Random random = new Random();
    public Result createRandom(){
        int i = random.nextInt(3);
        return i != 0 ? i != 1 ? GO : ROTATE_LEFT: ROTATE_RIGHT;
    }

    protected RandomAI(int id) {
        super(id);
    }

    @Override
    public Result work(byte[][] arr) {
        return createRandom();
    }
}
