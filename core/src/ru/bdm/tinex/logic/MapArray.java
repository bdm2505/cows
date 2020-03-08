package ru.bdm.tinex.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MapArray extends Map {

    Element [][] elements = new Element[0][0];
    private int grass = 0;

    HashMap<Element, Pos> poss = new HashMap<>();

    @Override
    public void setSize(int w, int h) {
        super.setSize(w, h);
        elements = new Element[w][h];
        fillMap();
    }

    private void fillMap(){
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                elements[i][j] = ElementFactory.empty();
            }
        }
    }

    @Override
    public Element getElement(Pos pos) {
        if(outSize(pos))
            return ElementFactory.empty();
        return elements[pos.x][pos.y];
    }
    private boolean outSize(Pos pos){
        return pos.x < 0 || pos.x >= width || pos.y < 0 || pos.y >= height;
    }

    @Override
    public Pos getPosition(Element element) {
        return poss.get(element);
    }

    @Override
    public Collection<Animal> getAnimals() {
        ArrayList<Animal> result = new ArrayList<>();
        for(Element element: getAllElements()){
            if(element.isAnimal())
                result.add(element.toAnimal());
        }
        return result;
    }

    @Override
    public Collection<Element> getAllElements() {
        return poss.keySet();
    }

    @Override
    public void put(Element element, Pos pos) {
        if(outSize(pos))
            return;
        remove(element);
        remove(pos);
        poss.put(element, pos);
        elements[pos.x][pos.y] = element;
        if(element.isGrass())
            grass++;
    }

    @Override
    public void remove(Element element) {
        if (poss.containsKey(element)) {
            Pos pos = poss.get(element);
            remove(element, pos);
        }
    }

    @Override
    public void remove(Pos pos) {
        if(outSize(pos))
            return;
        if(!elements[pos.x][pos.y].isEmpty()){
            remove(elements[pos.x][pos.y], pos);
        }
    }

    private void remove(Element element, Pos pos){
        elements[pos.x][pos.y] = ElementFactory.empty();
        poss.remove(element);

        if(element.isGrass())
            grass--;
    }

    @Override
    public boolean containOf(Pos pos) {
        if(outSize(pos))
            return false;
        return !elements[pos.x][pos.y].isEmpty();
    }

    @Override
    public boolean containOf(Element element) {
        return poss.containsKey(element);
    }

    @Override
    public int getNumberGrass() {
        return grass;
    }

    @Override
    public Map copy() {
        MapArray map = new MapArray();
        return fillCopyMap(map);
    }
}
