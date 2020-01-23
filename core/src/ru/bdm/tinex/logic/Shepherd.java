package ru.bdm.tinex.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Shepherd {


    private static Random rand = new Random();
    private static int nextIdAI = 1;


    public int maxGrass;

    private Map map;
    private AIManager manager = new AIManager();

    private HashMap<Animal, Pos> goAnimals = new HashMap<>();
    private ArrayList<Animal> depth = new ArrayList<>();


    public Shepherd(Map map) {
        this.map = map;
    }

    public static Shepherd createSimple(Map map, int w, int h, int grass, int cow, int wolf) {
        map.setSize(w, h);
        Shepherd shepherd = new Shepherd(map);
        shepherd.maxGrass = grass;

        shepherd.addAI(new CowMaskAI(0));

        for (int i = 0; i < w; i++) {
            map.put(ElementFactory.stone(), new Pos(i, 0));
            map.put(ElementFactory.stone(), new Pos(i, h - 1));
        }
        for (int i = 0; i < h; i++) {
            map.put(ElementFactory.stone(), new Pos(0, i));
            map.put(ElementFactory.stone(), new Pos(w - 1, i));
        }
        map.randomPutGrass(grass);
        for (int i = 0; i < cow; i++) {
            AI ai = MaskAI.createRandom(nextIdAI++);
            shepherd.addAI(ai);
            map.randomEmptyPut(ElementFactory.cow(ai.id));
        }
        for (int i = 0; i < wolf; i++) {
            AI ai = MaskAI.createRandom(nextIdAI++);
            shepherd.addAI(ai);
            map.randomEmptyPut(ElementFactory.wolf(ai.id));
        }
        return shepherd;
    }


    public void go(Animal a, Pos pos) {

        if (map.containOf(pos)) {
            Element element = map.getElement(pos);
            if (a.isCow()) {
                if (element.isCow() || element.isStone())
                    return;
                if (element.isGrass()) {
                    a.eat();
                    map.remove(element);
                    map.put(a, pos);
                }
                if (element.isWolf()) {
                    element.toAnimal().eat();
                    map.remove(a);
                }
            } else if (a.isWolf()) {
                if (element.isWolf() || element.isStone())
                    return;
                if (element.isCow()) {
                    a.eat();
                }
                map.remove(element);
                map.put(a, pos);
            }
        } else {
            map.put(a, pos);
        }
    }

    public void addAI(AI ai) {
        manager.registration(ai);
    }

    public void nextTurn() {
        goAnimals.clear();

        calculateNextPositionAnimals();
        goAnimals();
        deleteDepthAnimal();
        updateEatAnimals();
        addTheMissingGrass();
    }

    private void addTheMissingGrass() {
        if (maxGrass - map.getNumberGrass() > 0)
            map.randomPutGrass(maxGrass - map.getNumberGrass());

    }

    private void updateEatAnimals() {
        for (Animal animal : map.getAnimals()) {
            animal.updateEat();
        }
    }

    private void deleteDepthAnimal() {
        depth.clear();

        for (Animal a : map.getAnimals()) {
            if (a.isDepth()) {
                depth.add(a);
            }
        }
        for (Animal a : depth) {
            map.remove(a);
        }
    }

    private void goAnimals() {
        for (Animal a : goAnimals.keySet()) {
            map.remove(a);
        }
        for (Animal a : goAnimals.keySet()) {
            go(a, goAnimals.get(a));
        }
    }

    private void calculateNextPositionAnimals() {
        for (Animal animal : map.getAnimals()) {
            nextAction(animal);
        }
    }

    private void nextAction(Animal animal) {
        AI.Result result = manager.nextResult(animal, map.getSeeArea(animal));
        switch (result) {
            case ROTATE_LEFT:
                animal.rotateLeft();
                break;
            case ROTATE_RIGHT:
                animal.rotateRight();
                break;
            case GO:
                Pos nextPos = nextPositionAnimal(animal.getWay(), map.getPosition(animal));
                goAnimals.put(animal, nextPos);
                break;
            case REPRODUCTION:
                if (animal.getHp() > 20) {
                    animalReproduction(animal);
                }
        }
    }

    private void animalReproduction(Animal animal) {
        Animal repAnimal = animal.reproduction();
        Pos current = map.getPosition(animal);
        Pos posNewAnimal = Pos.of(current.x + rand.nextInt(3) - 1, current.y + rand.nextInt(3) - 1);
        goAnimals.put(repAnimal, posNewAnimal);
    }

    private Pos nextPositionAnimal(Way way, Pos current) {
        int dx = 0, dy = 0;
        if (way.isUp()) dy = 1;
        if (way.isDown()) dy = -1;
        if (way.isLeft()) dx = -1;
        if (way.isRight()) dx = 1;
        return new Pos(current.x + dx, current.y + dy);
    }


    public Map getMap() {
        return map;
    }
}
