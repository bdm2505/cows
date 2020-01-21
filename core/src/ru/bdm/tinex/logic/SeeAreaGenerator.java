package ru.bdm.tinex.logic;

import java.util.HashMap;

public class SeeAreaGenerator {
    public static final HashMap<Way, int[][]> wayMask;
    static {
        wayMask = new HashMap<>();
        wayMask.put(Way.UP, new int[][]{
                {-2, 2}, {-1, 2}, {0, 2}, {1, 2}, {2, 2},
                {-2, 1}, {-1, 1}, {0, 1}, {1, 1}, {2, 1},
                {-2, 0}, {-1, 0}, {1, 0}, {2, 0}
        });
        wayMask.put(Way.DOWN, new int[][]{
                {2, -2}, {1, -2}, {0, -2}, {-1, -2}, {-2, -2},
                {2, -1}, {1, -1}, {0, -1}, {-1, -1}, {-2, -1},
                {2, 0}, {1, 0}, {-1, 0}, {-2, 0}
        });
        wayMask.put(Way.LEFT, new int[][]{
                {-2, -2}, {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2},
                {-1, -2}, {-1, -1}, {-1, 0}, {-1, 1}, {-1, 2},
                {0, -2}, {0, -1}, {0, 1}, {0, 2}
        });
        wayMask.put(Way.RIGHT, new int[][]{
                {2, 2}, {2, 1}, {2, 0}, {2, -1}, {2, -2},
                {1, 2}, {1, 1}, {1, 0}, {1, -1}, {1, -2},
                {0, 2}, {0, 1}, {0, -1}, {0, -2}
        });
    }

    public static Pos[] generate(Way way, Pos pos){
        int[][] mask = wayMask.get(way);
        Pos [] result = new Pos[mask.length];
        for (int i = 0; i < mask.length; i++) {
            result[i] = new Pos(pos.x + mask[i][0], pos.y + mask[i][1]);
        }
        return result;
    }

}
