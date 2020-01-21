package ru.bdm.tinex;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import ru.bdm.tinex.logic.*;

import java.util.HashMap;

public class ElementActor extends Group {

    Element element;
    Skin skin;
    Image icon;
    Image way;

    private static final HashMap<Class<? extends Element>, String> imageNames = new HashMap<>();
    static {
        imageNames.put(Stone.class, "stone");
        imageNames.put(Grass.class, "grass");
        imageNames.put(Cow.class, "cow");
        imageNames.put(Wolf.class, "wolf");
    }
    final static float duration = 1f;


    public ElementActor(Element element, Skin skin) {

        this.element = element;
        this.skin = skin;

        icon = new Image(skin, imageNames.get(element.getClass()));
        icon.setSize(0, 0);

        if (element.isAnimal()) {
            way = new Image(skin, "way");
            way.setSize(0, 0);
            addActor(way);
            updateWay();
        }
        addActor(icon);
    }


    public void updateSize(float size) {
        icon.addAction(createSizeToAction(size));
        if (!(way == null))
            way.addAction(createSizeToAction(size));
        addAction(createSizeToAction(size));
    }

    public void update(Pos p, float scale) {
        addAction(createMoveToAction(p, scale));
        if(element.isAnimal())
            updateWay();
    }

    private void updateWay() {
        way.addAction(createRotateToAction(element.toAnimal().getWay().getDegrees()));
    }

    public RotateToAction createRotateToAction(float way) {
        RotateToAction action = new RotateToAction(){
            @Override
            protected void update(float percent) {
                super.update(percent);
                getActor().setOrigin(Align.center);
            }
        };
        action.setDuration(duration);
        action.setUseShortestDirection(true);
        action.setInterpolation(Interpolation.pow2);
        action.setRotation(way);
        return action;
    }

    private MoveToAction createMoveToAction(Pos p, float scale) {
        MoveToAction move = new MoveToAction(){
            @Override
            protected void update(float percent) {
                super.update(percent);
                getActor().setOrigin(Align.center);
            }
        };
        move.setPosition(p.x * scale, p.y * scale);
        move.setInterpolation(Interpolation.pow2);
        move.setDuration(duration);
        return move;
    }

    private SizeToAction createSizeToAction(float size) {
        SizeToAction action = new SizeToAction(){
            @Override
            protected void update(float percent) {
                super.update(percent);
                getActor().setOrigin(Align.center);
            }
        };
        action.setDuration(duration);
        action.setInterpolation(Interpolation.pow2);
        action.setSize(size, size);
        return action;
    }
    public void removeActor(){
        updateSize(0);
        AfterAction afterAction = new AfterAction();
        RemoveActorAction removeAction = new RemoveActorAction();
        afterAction.setAction(removeAction);
        removeAction.setTarget(this);
        addAction(afterAction);
    }

}
