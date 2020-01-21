package ru.bdm.tinex.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.bdm.tinex.GameScreenManager;
import ru.bdm.tinex.MapActor;
import ru.bdm.tinex.StartGame;
import ru.bdm.tinex.logic.*;

public class MapScreen extends MainScreen {

    Shepherd map;
    MapActor mapActor;

    public MapScreen(Skin skin, GameScreenManager gsm) {
        super(skin, gsm);
        map = Shepherd.createSimple(30,20,50, 10, 5);

        mapActor = new MapActor(map, skin);

        final ScrollPane pane = new ScrollPane(mapActor);
        pane.setSize(StartGame.WIDTH, StartGame.HEIGHT);
        stage.addActor(pane);

        TextButton plus = new TextButton("+", skin);
        plus.setBounds(StartGame.WIDTH - 120,StartGame.HEIGHT - 60, 100, 40);
        stage.addActor(plus);
        plus.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mapActor.scaleTo(1.1f);
            }
        });

        TextButton minus = new TextButton("-", skin);
        minus.setBounds(StartGame.WIDTH - 120,StartGame.HEIGHT - 110, 100, 40);
        stage.addActor(minus);
        minus.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mapActor.scaleTo(0.9f);
            }
        });

        TextButton addSpeed = new TextButton("x2", skin);
        addSpeed.setBounds(StartGame.WIDTH - 120,StartGame.HEIGHT - 160, 100, 40);
        stage.addActor(addSpeed);
        addSpeed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (duration >= 1f)
                    duration = 0.5f;
                else
                    duration = 1f;

                MapActor.duration = duration;
            }
        });

    }
    float timer = 0f;
    float duration = 1f;

    @Override
    public void render(float delta) {
        super.render(delta);
        timer += delta;
        if (timer > duration){
            timer -= duration;
            timerActive();
        }
    }

    private void timerActive() {
        map.nextTurn();
        mapActor.updateMap(map);
    }
}
