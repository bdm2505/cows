package ru.bdm.tinex.logic;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MapSimple extends Map {

    private HashMap<Element, Pos> elements = new HashMap<>();
    private HashMap<Pos, Element> positions = new HashMap<>();
    private Set<Animal> animals = new HashSet<>();

    @Override
    public Element getElement(Pos pos) {
        if (positions.containsKey(pos))
            return positions.get(pos);
        else
            return ElementFactory.empty();
    }

    @Override
    public Pos getPosition(Element element) {
        return elements.get(element);
    }

    @Override
    public Collection<Animal> getAnimals() {
        return animals;
    }

    /**
     * put element in position
     * if map contain element, then move it
     * if there is someone, then delete it
     */
    @Override
    public void put(Element element, Pos pos) {

        remove(element);
        remove(pos);

        elements.put(element, pos);
        positions.put(pos, element);
        if(element.isAnimal())
            animals.add(element.toAnimal());
    }

    @Override
    public void remove(Element element) {
        if(elements.containsKey(element)){
            Pos pos = elements.get(element);
            remove(element, pos);
        }
    }

    @Override
    public void remove(Pos pos) {
        if(positions.containsKey(pos)){
            Element element = positions.get(pos);
            remove(element, pos);
        }
    }

    @Override
    public boolean containOf(Pos pos) {
        return positions.containsKey(pos);
    }

    private void remove(Element element, Pos pos) {
        positions.remove(pos);
        elements.remove(element);
        if(element.isAnimal())
            animals.remove(element.toAnimal());
    }
}
