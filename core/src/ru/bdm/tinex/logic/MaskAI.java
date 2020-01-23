package ru.bdm.tinex.logic;

import java.util.HashMap;
import java.util.Random;

public class MaskAI extends AI {



    private HashMap<Mask, AI.Result> masks = new HashMap<>();
    private HashMap<Mask, Integer> results = new HashMap<>();


    public MaskAI(int id) {
        super(id);
    }

    public static MaskAI createRandom(int id) {
        MaskAI maskAI = new MaskAI(id);
        maskAI.addMask(Mask.random(), Result.ROTATE_LEFT);
        maskAI.addMask(Mask.random(), Result.ROTATE_RIGHT);
        maskAI.addMask(Mask.random(), Result.GO);
        maskAI.addMask(Mask.random(), Result.REPRODUCTION);
        return maskAI;
    }

    public void addMask(Mask mask, AI.Result result){
        masks.put(mask, result);
    }

    @Override
    Result work(Element[] elements) {
        results.clear();
        for (Mask mask : masks.keySet())
            results.put(mask, mask.getResult(elements));

        Mask max = getMaxMask();

        return masks.get(max);
    }

    private Mask getMaxMask() {
        Mask maxMask = masks.keySet().iterator().next();
        int max = results.get(maxMask);
        for (Mask mask : results.keySet()) {
            int current = results.get(mask);
            if (current > max) {
                max = current;
                maxMask = mask;
            }
        }
        return maxMask;
    }


}
