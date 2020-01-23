package tests.ru.bdm.tinex.logic;


import org.junit.jupiter.api.Test;
import ru.bdm.tinex.logic.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    @Test
    public void testElementEqual() {
        Element e1 = ElementFactory.cow(0);
        Element e2 = ElementFactory.cow(0);

        assertNotEquals(e1, e2);
    }

    @Test
    public void testMaps(){
        testMapSetAndGetElements(new MapArray());
        testMapSetAndGetElements(new MapSimple());
    }


    public void testMapSetAndGetElements(Map map) {
        map.setSize(20, 20);
        Element e = ElementFactory.grass();

        map.put(e, Pos.of(1, 1));

        assertEquals(e, map.getElement(Pos.of(1, 1)));
        assertEquals(ElementFactory.empty(), map.getElement(Pos.of(0, 0)));
        assertEquals(Pos.of(1, 1), map.getPosition(e));


        assertTrue(map.containOf(Pos.of(1,1)));
        assertFalse(map.containOf(Pos.of(0,0)));
    }

    @Test
    public void testIsAnimal(){
        Animal animal = ElementFactory.cow(0);
        Element element = ElementFactory.grass();

        assertTrue(animal.isAnimal());
        assertFalse(element.isAnimal());
    }
}
