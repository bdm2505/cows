package ru.bdm.tinex;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ru.bdm.tinex.logic.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MapActor extends Group {
    Map map;
    Skin skin;
    float scale = 50f;
    boolean scaleChanged = true;
    public static float duration = 1f;
    HashMap<Element, ElementActor> hash = new HashMap<>();

    public MapActor(Map map, Skin skin) {
        this.skin = skin;
        scale = (float) StartGame.HEIGHT / map.getHeight();
        System.out.println("scale=" + scale);
        updateMap(map);
    }

    public void updateMap(Map map) {
        this.map = map;
        System.out.println(map.getAnimals().size());
        for (Element e : map.getAllElements()) {
            if (!hash.containsKey(e)) {

                ElementActor actor = new ElementActor(e, skin) ;
                Pos p = map.getPosition(e);
                actor.setBounds(p.x * scale, p.y * scale, 0, 0);
                actor.updateSize(scale);
                addActor(actor);
                hash.put(e, actor);
            } else {
                ElementActor actor = hash.get(e);
                if (scaleChanged) {
                    actor.updateSize(scale);
                }
                actor.update(e, map.getPosition(e), scale);

            }
        }
        if (scaleChanged) {
            scaleChanged = false;
            changeScaled();
        }
        cleanElements();
    }

    private void cleanElements(){
        Set<Element> removeSet = new HashSet<>();
        for(Element element: hash.keySet()){
            if (!map.containOf(element)){
                hash.get(element).removeActor();
                removeSet.add(element);
            }
        }
        for(Element element: removeSet){
            hash.remove(element);
        }
    }



    public void changeScaled() {
        SizeToAction action = new SizeToAction() {
            @Override
            protected void update(float percent) {
                super.update(percent);
                if (getParent().getClass() == ScrollPane.class) {
                    ScrollPane pane = (ScrollPane) getParent();
                    float x = pane.getScrollPercentX();
                    float y = pane.getScrollPercentY();
                    pane.layout();
                    pane.setScrollPercentX(x);
                    pane.setScrollPercentY(y);
                }
            }
        };
        action.setSize(map.getWidth() * scale, map.getHeight() * scale);
        action.setDuration(duration);
        addAction(action);
    }


    public void scaleTo(float addScale) {
        this.scale *= addScale;
        scaleChanged = true;
        updateMap(map);
    }
}
