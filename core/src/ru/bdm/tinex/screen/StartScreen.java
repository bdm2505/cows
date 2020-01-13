package ru.bdm.tinex.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.bdm.tinex.GameScreenManager;
import ru.bdm.tinex.StartGame;


/**
 * @author bdm
 * экран начала игры
 */
public class StartScreen extends MainScreen {

    Image bg;


    public StartScreen(final Skin skin, final GameScreenManager gsm) {
        super(skin, gsm);



        bg = new Image(skin.get("fon.png", Texture.class));
        bg.setBounds(0 ,0, StartGame.WIDTH, StartGame.HEIGHT);


        stage.addActor(bg);

        Button button = new Button(skin);
        button.setBounds(StartGame.WIDTH * 0.66f, StartGame.HEIGHT * 0.4f, 100, 40);
        stage.addActor(button);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.push(new MapScreen(skin, gsm));
            }
        });

    }
}
