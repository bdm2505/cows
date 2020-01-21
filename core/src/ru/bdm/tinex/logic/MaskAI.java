package ru.bdm.tinex.logic;

import java.util.Random;

public class MaskAI extends AI {

    public MaskAI(int id, int[][] maskUp, int[][] maskRotateLeft, int[][] maskRotateRight) {
        super(id);
        this.maskUp = maskUp;
        this.maskRotateLeft = maskRotateLeft;
        this.maskRotateRight = maskRotateRight;
    }

    int [][] maskUp;
    int [][] maskRotateLeft;
    int [][] maskRotateRight;

    public MaskAI(int id) {
        super(id);
    }
    @Override
    Result work(byte[][] data) {
        int resultUp = 0, resultLeft = 0, resultRight = 0;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                resultUp += data[i][j] * maskUp[i][j];
                resultLeft += data[i][j] * maskRotateLeft[i][j];
                resultRight += data[i][j] * maskRotateRight[i][j];
            }
        }
        int max = Math.max(resultLeft, Math.max(resultRight, resultUp));

        if (max == resultUp)
            return Result.GO;
        if (max == resultLeft)
            return Result.ROTATE_LEFT;
        return Result.ROTATE_RIGHT;
    }
    private static final Random random = new Random();
    public static MaskAI createRandom(int id){
        return new MaskAI(id, randomArray(10),randomArray(10),randomArray(10));
    }

    private static int [][] randomArray(int max){
        int [][] arr = new int[4][14];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(max);
            }
        }
        return arr;
    }


}
