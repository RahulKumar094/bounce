package com.slamdunk.bounce;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.slamdunk.bounce.Screens.GameScreen;
import com.slamdunk.bounce.Utilities.MainCamera;

public class BounceGame extends Game
{
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 1920;

	public static MainCamera camera;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new MainCamera(WIDTH, HEIGHT);

		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined());
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		camera.resize(width, height);
		super.resize(width,height);
	}
}
