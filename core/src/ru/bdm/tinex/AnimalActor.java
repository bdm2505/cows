package ru.bdm.tinex;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import ru.bdm.tinex.logic.Element;
import ru.bdm.tinex.logic.Pos;

public class AnimalActor extends Group {

    Element element;
    Skin skin;
    Image icon;
    Image way;
    final static float duration = 1f;
    public static final float[] ways = new float[]{0, 90, 180, 270};

    public AnimalActor(Element element, Skin skin) {

        this.element = element;
        this.skin = skin;

        icon = new Image(skin, element.type.toString());
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
        way.addAction(createRotateToAction(ways[element.toAnimal().way]));
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
        action.setSize(size, size);
        return action;
    }

}
