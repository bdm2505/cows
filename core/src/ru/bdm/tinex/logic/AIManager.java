package ru.bdm.tinex.logic;

import java.util.HashMap;

public class AIManager {
    private HashMap<Integer, AI> aiMap = new HashMap<>();

    public void registration(AI ai) {
        aiMap.put(ai.id, ai);
    }

    public AI.Result nextResult(Animal animal, Element[] seeArea) {
        return aiMap.get(animal.getIdAI()).work(seeArea);
    }
}
