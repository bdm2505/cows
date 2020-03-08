package ru.bdm.tinex.logic;

import java.util.HashMap;
import java.util.Random;

public class MaskAI extends AI {


    public MaskAI(int id, HashMap<Mask, Result> masks) {
        super(id);
        this.masks = masks;
    }

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

    @Override
    int mutable(AIManager manager) {
        HashMap<Mask, AI.Result> newMasks = new HashMap<>();
        for(Mask mask:masks.keySet()){
            newMasks.put(mask.mutation(), masks.get(mask));
        }
        MaskAI ai = new MaskAI(AIManager.getNextId(), newMasks);
        manager.registration(ai);
        return ai.id;
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
