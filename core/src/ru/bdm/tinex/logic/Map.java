package ru.bdm.tinex.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Map {

    private int width;
    private int height;

    private Element [][] elements;
    private HashMap<Animal, Pos> animals = new HashMap<>();
    private HashMap<Element, Pos> m = new HashMap<>();
    private static Random rand = new Random();
    private HashMap<Integer, AI> aiMap = new HashMap<>();


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        elements = new Element[width][height];
    }




    public Set<Element> getElements() {
        return m.keySet();
    }

    public Pos get(Element e) {
        return m.get(e);
    }

    public void put(Element e, Pos pos) {
        if (pos.x >= width || pos.y >= height || pos.x < 0 || pos.y < 0)
            return;
        if (elements[pos.x][pos.y] != null) {
            m.remove(elements[pos.x][pos.y]);
        }
        if (m.containsKey(e)){
            Pos p = m.get(e);
            elements[p.x][p.y] = null;
        }

        elements[pos.x][pos.y] = e;
        m.put(e, pos);
        if (e.isAnimal())
            animals.put(e.toAnimal(), pos);

    }

    public void addAI(AI ai){
        aiMap.put(ai.id, ai);
    }

    public void nextTurn(){

        for(Animal animal :animals.keySet()){
            AI.Result result = aiMap.get(animal.ai).work(this);
            switch (result){
                case ROTATE_LEFT:
                    animal.rotateLeft(); break;
                case ROTATE_RIGHT:
                    animal.rotateRight(); break;
                case GO:
                    int dx=0,dy=0;
                    if (animal.way == Way.UP) dy = 1;
                    if (animal.way == Way.DOWN) dy = -1;
                    if (animal.way == Way.LEFT) dx = -1;
                    if (animal.way == Way.RIGHT) dx = 1;
                    Pos current = animals.get(animal);
                    put(animal, new Pos(current.x + dx, current.y + dy));
            }
        }
    }

    public static Map createSimple(int w, int h, int grass, int cow) {
        Map map = new Map(w, h);

        map.addAI(new RandomAI(0));

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
        for (Element e: m.keySet())
            bl.append(e).append(" - ").append(m.get(e)).append("\n");

        return "Map{" +
                "width=" + width +
                ", height=" + height + "\n" +
                bl.toString() +

                '}';
    }
}
