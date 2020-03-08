package ru.bdm.tinex.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class AIManager {
    private HashMap<Integer, AI> aiMap = new HashMap<>();

    private static Random rand = new Random();
    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    };

    public void registration(AI ai) {
        aiMap.put(ai.id, ai);
    }

    public AI.Result nextResult(Animal animal, Element[] seeArea) {
        return aiMap.get(animal.getIdAI()).work(seeArea);
    }

    public void removeDepthAI(Collection<Animal> animals){
        HashMap<Integer, AI> newAiMap = new HashMap<>();
        for(Animal animal: animals){
            AI ai = aiMap.get(animal.getIdAI());
            newAiMap.put(ai.id, ai);
        }
        aiMap = newAiMap;
    }

    public int createRandomMastAi(){
        AI ai = MaskAI.createRandom(getNextId());
        registration(ai);
        return ai.id;
    }

    public int mutation(int idAi) {
        return aiMap.get(idAi).mutable(this);
    }

    public int getNumberAI() {
        return aiMap.size();
    }
}
