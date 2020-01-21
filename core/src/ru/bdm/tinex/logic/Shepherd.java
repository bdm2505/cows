package ru.bdm.tinex.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;



public class Shepherd {


    private static Random rand = new Random();
    private static int nextIdAI = 1;


    public int grass = 0;
    public int maxGrass = 20;

    private Map map;
    private HashMap<Integer, AI> aiMap = new HashMap<>();

    public Shepherd(Map map) {
        this.map = map;
    }

    public static Shepherd createSimple(int w, int h, int grass, int cow, int wolf) {
        Map map = new MapSimple();
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
        for (int i = 0; i < grass; i++) {
            map.randomEmptyPut(ElementFactory.grass());
        }
        for (int i = 0; i < cow; i++) {
            AI ai = MaskAI.createRandom(nextIdAI++);
            shepherd.aiMap.put(ai.id, ai);
            map.randomEmptyPut(ElementFactory.cow(0));
        }
        for (int i = 0; i < wolf; i++) {
            AI ai = MaskAI.createRandom(nextIdAI++);
            shepherd.aiMap.put(ai.id, ai);
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
        aiMap.put(ai.id, ai);
    }

    public void nextTurn() {
        HashMap<Animal, Pos> goAnimals = new HashMap<>();

        for (Animal animal : map.getAnimals()) {

            AI.Result result = aiMap.get(animal.getIdAI()).work(listInfoAnimal(animal));
            switch (result) {
                case ROTATE_LEFT:
                    animal.rotateLeft();
                    break;
                case ROTATE_RIGHT:
                    animal.rotateRight();
                    break;
                case GO:
                    int dx = 0, dy = 0;
                    if (animal.getWay().isUp()) dy = 1;
                    if (animal.getWay().isDown()) dy = -1;
                    if (animal.getWay().isLeft()) dx = -1;
                    if (animal.getWay().isRight()) dx = 1;
                    Pos current = map.getPosition(animal);
                    goAnimals.put(animal, new Pos(current.x + dx, current.y + dy));
            }
        }


        for (Animal a : goAnimals.keySet()) {
            map.remove(a);
        }

        for (Animal a : goAnimals.keySet()) {
            go(a, goAnimals.get(a));
        }
        Set<Animal> depth = new HashSet<>();

        for (Animal a : map.getAnimals()) {
            if (a.isDepth()) {
                depth.add(a);
            }
            a.move();
        }
        for (Animal a : depth) {
            map.remove(a);
        }

        while (grass < maxGrass) {
            map.randomEmptyPut(ElementFactory.grass());
        }
    }

    
    public byte[][] listInfoAnimal(Animal animal) {
        return new byte[][]{
                listForOneType(animal, Stone.class),
                listForOneType(animal, Grass.class),
                listForOneType(animal, Cow.class),
                listForOneType(animal, Wolf.class)
        };
    }

    public byte[] listForOneType(Animal animal, Class<? extends Element> type) {
        Element [] elements = map.getSeeArea(animal);

        byte[] result = new byte[elements.length];
        for (int i = 0; i < elements.length; i++) {
            result[i] = (byte) (elements[i].isType(type) ? 1 : 0);
        }
        return result;
    }

}
