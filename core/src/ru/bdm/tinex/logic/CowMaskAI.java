package ru.bdm.tinex.logic;

public class CowMaskAI extends MaskAI {
    final static int [][] myMaskUp = new int[][]{
            {       0, 0, 0, 0, 0,  //block
                    0, 0,-9, 0, 0,
                    0, 0,    0, 0},
            {       1, 3, 9, 3, 1,  //grass
                    2, 4,10, 4, 2,
                    0,-1,   -1, 0},
            {       0, 0, 0, 0, 0,  //cow
                    0, 0,-9, 0, 0,
                    0, 0,    0, 0},
            {       0, 0,-1, 0, 0,  //wolf
                    0,-1,-9,-1, 0,
                    0, 2,    2, 0},
    };
    final static int [][] myMaskRotateLeft = new int[][]{
            {       0, 0, 2, 0, 0,  //block
                    0, 0, 9, 0, 0,
                    0, 0,    0, 0},
            {       0, 0, 0, 0, 0,  //grass
                    0, 0,-1, 0, 0,
                    4, 7,   -1, 0},
            {       0, 0, 1, 0, 0,  //cow
                    0, 0, 9, 0, 0,
                    0, 0,    0, 0},
            {       0, 0, 1, 0, 0,  //wolf
                    0,-1, 9, 1, 0,
                   -1,-5,    2, 0},
    };
    final static int [][] myMaskRotateRight = new int[][]{
            {       0, 0, 2, 0, 0,  //block
                    0, 0, 8, 0, 0,
                    0, 0,    0, 0},
            {       0, 0, 0, 0, 0,  //grass
                    0, 0,-1, 0, 0,
                    0,-1,    7, 4},
            {       0, 0, 1, 0, 0,  //cow
                    0, 0, 8, 0, 0,
                    0, 0,    0, 0},
            {       0, 0, 1, 0, 0,  //wolf
                    0, 1, 9,-1, 0,
                    0, 2,   -5,-1},
    };

    public CowMaskAI(int id) {
        super(id, myMaskUp, myMaskRotateLeft, myMaskRotateRight);
    }
}
