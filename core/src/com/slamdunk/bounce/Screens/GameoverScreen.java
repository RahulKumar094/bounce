package com.slamdunk.bounce.Screens;

/**
 * Created by Rahul_K on 8/24/2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.slamdunk.bounce.BounceGame;

public class GameoverScreen implements Screen
{
    private BitmapFont fontGameover;
    private BitmapFont fontScore;
    private BitmapFont fontMessage;
    private GlyphLayout layoutGameOver;
    private GlyphLayout layoutScore;
    private GlyphLayout layoutHighScore;
    private GlyphLayout layoutMessage;
    private int score = 0;
    private int highScore = 0;

    private BounceGame game;

    private boolean justTouched = false;
    private float doubleTouchSpeed = 0.5f;
    private float doubleTouchTimer = 0;

    private float scoreTextscale = 1.5f;
    private float gameOverTextScale = 2.5f;
    private float messageTextScale = 1f;

    public GameoverScreen(BounceGame game , int score)
    {
        this.game = game;
        this.score = score;

        fontGameover = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        fontGameover.getData().setScale(gameOverTextScale);

        fontScore = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        fontScore.getData().setScale(scoreTextscale);

        fontMessage = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        fontMessage.getData().setScale(messageTextScale);

        layoutGameOver = new GlyphLayout(fontGameover, "GAMEOVER");
        layoutMessage = new GlyphLayout(fontMessage,"double tap to restart");

        layoutScore = new GlyphLayout();
        layoutHighScore = new GlyphLayout();
    }

    @Override
    public void show()
    {
        Preferences prefs = Gdx.app.getPreferences("bounce");
        highScore = prefs.getInteger("highscore", 0);

        if(highScore < score)
        {
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

        layoutScore.setText(fontScore, "SCORE: " + score);
        layoutHighScore.setText(fontScore, "HIGHSCORE: " + highScore);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        renderObjects();

        if(doubleTouched(delta))
            game.setScreen(new GameScreen(game));

        game.batch.end();
    }

    private void renderObjects()
    {
        fontGameover.draw(game.batch, layoutGameOver, BounceGame.WIDTH /2 - layoutGameOver.width/2, BounceGame.HEIGHT - 800);

        fontScore.draw(game.batch, layoutHighScore, BounceGame.WIDTH /2 - layoutHighScore.width/2, BounceGame.HEIGHT - 300);
        fontScore.draw(game.batch, layoutScore, BounceGame.WIDTH /2 - layoutScore.width/2, BounceGame.HEIGHT - 400);

        fontScore.draw(game.batch, layoutMessage, BounceGame.WIDTH /2 - layoutMessage.width/2, 100);
    }

    private boolean doubleTouched(float delta)
    {
        if(Gdx.input.justTouched())
        {
            if(!justTouched)
                justTouched = true;
            else if(doubleTouchTimer < doubleTouchSpeed)
                return true;
        }

        if(justTouched)
            doubleTouchTimer += delta;

        if(doubleTouchTimer > doubleTouchSpeed)
        {
            doubleTouchTimer = 0;
            justTouched = false;
        }

        return false;
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
