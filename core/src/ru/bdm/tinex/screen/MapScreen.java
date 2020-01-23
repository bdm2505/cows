package ru.bdm.tinex.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.bdm.tinex.GameScreenManager;
import ru.bdm.tinex.MapActor;
import ru.bdm.tinex.StartGame;
import ru.bdm.tinex.ThreadManager;
import ru.bdm.tinex.logic.*;

public class MapScreen extends MainScreen {

    int speed = 1;
    MapActor mapActor;
    ThreadManager manager;

    public MapScreen(Skin skin, GameScreenManager gsm) {
        super(skin, gsm);
        final Shepherd shepherd = Shepherd.createSimple(new MapArray(), 150,100,2000, 500, 250);

        mapActor = new MapActor(shepherd.getMap().copy(), skin);
        manager = new ThreadManager(shepherd, mapActor);


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
                speed = 2;
//                if (speed == 1)
//                    speed = 2;
//                else
//                    speed = 1;

            }
        });

        TextButton addSpeedX10 = new TextButton("x10", skin);
        addSpeedX10.setBounds(StartGame.WIDTH - 120,StartGame.HEIGHT - 210, 100, 40);
        stage.addActor(addSpeedX10);
        addSpeedX10.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (speed == 1)
                    speed = 10;
                else
                    speed = 1;

            }
        });
        TextButton addSpeedX100 = new TextButton("x100", skin);
        addSpeedX100.setBounds(StartGame.WIDTH - 120,StartGame.HEIGHT - 260, 100, 40);
        stage.addActor(addSpeedX100);
        addSpeedX100.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (speed == 1)
                    speed = 100;
                else
                    speed = 1;

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

        manager.put(speed);
    }
}
