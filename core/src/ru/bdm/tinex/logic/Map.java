package ru.bdm.tinex.logic;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import static ru.bdm.tinex.logic.TypeElement.*;


public class Map {

    private int width;
    private int height;


    private HashMap<Animal, Pos> animals = new HashMap<>();
    private HashMap<Element, Pos> all = new HashMap<>();
    private HashMap<Pos, Element> rAll = new HashMap<>();
    private static Random rand = new Random();
    private HashMap<Integer, AI> aiMap = new HashMap<>();


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public Set<Element> getElements() {
        return all.keySet();
    }

    public Pos get(Element e) {
        return all.get(e);
    }

    public void put(Element e, Pos pos) {
        if (all.containsKey(e))
            rAll.remove(all.get(e));

        all.put(e, pos);
        rAll.put(pos, e);
        if (e.isAnimal())
            animals.put(e.toAnimal(), pos);
    }

    public void remove(Element element) {
        if (all.containsKey(element)) {
            rAll.remove(all.get(element));
            all.remove(element);
        }
        if (element.isAnimal())
            animals.remove(element.toAnimal());
    }

    public void go(Animal a, Pos pos) {
        if (a.isDepth()) {
            remove(a);
            return;
        }
        a.move();
        if (rAll.containsKey(pos)) {
            Element element = rAll.get(pos);
            if (a.type == COW) {
                if (element.type == COW || element.type == BLOCK)
                    return;
                if (element.type == GRASS) {
                    a.eat();
                    remove(element);
                    put(a, pos);
                }
                if (element.type == WOLF) {
                    element.toAnimal().eat();
                    remove(a);
                }
            } else if (a.type == WOLF) {
                if (element.type == WOLF || element.type == BLOCK)
                    return;
                if (element.type == COW) {
                    a.eat();
                }
                remove(element);
                put(a, pos);
            }
        } else {
            put(a, pos);
        }
    }

    public void addAI(AI ai) {
        aiMap.put(ai.id, ai);
    }

    public void nextTurn() {
        HashMap<Animal, Pos> goAnimals = new HashMap<>();

        for (Animal animal : animals.keySet()) {

            AI.Result result = aiMap.get(animal.ai).work(listInfoAnimal(animal));
            switch (result) {
                case ROTATE_LEFT:
                    animal.rotateLeft();
                    break;
                case ROTATE_RIGHT:
                    animal.rotateRight();
                    break;
                case GO:
                    int dx = 0, dy = 0;
                    if (animal.way == Way.UP) dy = 1;
                    if (animal.way == Way.DOWN) dy = -1;
                    if (animal.way == Way.LEFT) dx = -1;
                    if (animal.way == Way.RIGHT) dx = 1;
                    Pos current = animals.get(animal);
                    goAnimals.put(animal, new Pos(current.x + dx, current.y + dy));
            }
        }


        for (Animal a : goAnimals.keySet()) {
            remove(a);
        }

        for (Animal a : goAnimals.keySet()) {
            go(a, goAnimals.get(a));
        }
    }

    public static Map createSimple(int w, int h, int grass, int cow) {
        Map map = new Map(w, h);

        map.addAI(new CowMaskAI(0));

        for (int i = 0; i < w; i++) {
            map.put(Element.block(), new Pos(i, 0));
            map.put(Element.block(), new Pos(i, h - 1));
        }
        for (int i = 0; i < h; i++) {
            map.put(Element.block(), new Pos(0, i));
            map.put(Element.block(), new Pos(w - 1, i));
        }
        for (int i = 0; i < grass; i++) {
            map.put(Element.grass(), new Pos(rand.nextInt(w - 2) + 1, rand.nextInt(h - 2) + 1));
        }
        for (int i = 0; i < cow; i++) {
            map.put(Element.cow(), new Pos(rand.nextInt(w - 2) + 1, rand.nextInt(h - 2) + 1));
        }
        return map;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        StringBuilder bl = new StringBuilder();
        for (Element e : all.keySet())
            bl.append(e).append(" - ").append(all.get(e)).append("\n");

        return "Map{" +
                "width=" + width +
                ", height=" + height + "\n" +
                bl.toString() +

                '}';
    }

    public byte[][] listInfoAnimal(Animal animal) {
        return new byte[][]{
                listForOneType(animal, BLOCK),
                listForOneType(animal, GRASS),
                listForOneType(animal, COW),
                listForOneType(animal, WOLF)
        };
    }

    public static final HashMap<Integer, int[][]> wayMask;

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

    public byte[] listForOneType(Animal animal, TypeElement type) {
        int[][] mask = wayMask.get(animal.way);
        Pos pos = animals.get(animal);
        byte[] result = new byte[mask.length];
        for (int i = 0; i < mask.length; i++) {
            Pos curr = new Pos(pos.x + mask[i][0], pos.y + mask[i][1]);
            result[i] = (byte) (rAll.containsKey(curr) && rAll.get(curr).type == type ? 1 : 0);
        }
        return result;
    }

    public boolean contains(Element element) {
        return all.containsKey(element);
    }
}
