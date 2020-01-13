package ru.bdm.tinex.logic;

import java.util.Random;

public abstract class AI {

    public final int id;

    protected AI(int id) {
        this.id = id;
    }

    enum Result {
        ROTATE_LEFT,
        ROTATE_RIGHT,
        GO
    }



    abstract Result work(Map map);
}
