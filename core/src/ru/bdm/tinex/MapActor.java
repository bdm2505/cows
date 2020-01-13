package ru.bdm.tinex;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ru.bdm.tinex.logic.*;

import java.util.HashMap;

public class MapActor extends Group {
    Map map;
    Skin skin;
    float scale = 50f;
    boolean scaleChanged = true;
    final static float duration = 1f;
    HashMap<Element, AnimalActor> hash = new HashMap<>();

    public MapActor(Map map, Skin skin) {
        this.skin = skin;
        updateMap(map);
    }

    public void updateMap(Map map) {
        this.map = map;
        for (Element e : map.getElements()) {
            if (!hash.containsKey(e)) {

                AnimalActor actor = new AnimalActor(e, skin) ;
                Pos p = map.get(e);
                actor.setBounds(p.x * scale, p.y * scale, 0, 0);
                actor.updateSize(scale);
                addActor(actor);
                hash.put(e, actor);
            } else {
                AnimalActor actor = hash.get(e);
                if (scaleChanged) {
                    actor.updateSize(scale);
                }
                actor.update(map.get(e), scale);

            }
        }
        if (scaleChanged) {
            scaleChanged = false;
            changeScaled();
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
