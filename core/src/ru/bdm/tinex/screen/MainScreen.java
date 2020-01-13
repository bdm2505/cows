package ru.bdm.tinex.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.bdm.tinex.GameScreenManager;
import ru.bdm.tinex.StartGame;


/**
 * @author bdm
 */
public class MainScreen implements Screen {
    protected Stage stage;
    protected Skin skin;
    protected GameScreenManager gsm;

    public MainScreen(Skin skin, GameScreenManager gsm) {
        this.skin = skin;
        this.gsm = gsm;
        stage = new Stage(new StretchViewport(StartGame.WIDTH, StartGame.HEIGHT));
    }

    @Override
    public void show() {
        System.out.println("setInputProcessor");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize");
        stage.getViewport().setScreenSize(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
