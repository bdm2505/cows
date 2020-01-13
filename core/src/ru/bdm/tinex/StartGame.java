package ru.bdm.tinex;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ru.bdm.tinex.screen.StartScreen;

public class StartGame extends Game {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public GameScreenManager gsm;
	public AssetManager assets;
	public Skin skin;

	@Override
	public void create () {
		gsm = new GameScreenManager(this);
		assets = new AssetManager();
		assets.load("data/load_atlas.json", Skin.class);
		assets.load("data/fon.png", Texture.class);
		assets.finishLoading();
		skin = assets.get("data/load_atlas.json", Skin.class);
		skin.add("fon.png",assets.get("data/fon.png", Texture.class));
		gsm.push(new StartScreen(skin,gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
