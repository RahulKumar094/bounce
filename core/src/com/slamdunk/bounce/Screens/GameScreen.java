package com.slamdunk.bounce.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.slamdunk.bounce.BounceGame;
import com.slamdunk.bounce.Entities.Ball;
import com.slamdunk.bounce.Utilities.CircleCollider;

/**
 * Created by Rahul_K on 8/23/2019.
 */

public class GameScreen implements Screen
{
    private BounceGame game;
    public GameScreen(BounceGame game)
    {
        this.game = game;
    }
    private int gameLevel;
    private float timer;

    private Ball ball;
    private Vector2 touchPoint = Vector2.Zero;
    private CircleCollider touchCollider;

    private BitmapFont font;
    private GlyphLayout gLayput;

    private static int score;

    @Override
    public void show()
    {
        ball = new Ball();
        score = 0;
        timer = 0;
        gameLevel = 0;

        touchCollider = new CircleCollider(touchPoint, 3);
        touchCollider.setActive(false);

        font = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        font.getData().setScale(2);
        gLayput = new GlyphLayout(font,"0", Color.WHITE, 200,1,false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.3f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        timer += delta;
        myGame();

        if(ball != null)
        {
            ball.renderAndUpdate(game.batch, delta);

            if(isBallJustTouched())
            {
                score++;
                ball.addForce(touchCollider.center);
            }
        }

        if(ball.getCenter().y < - ball.getSize())
        {
            game.setScreen(new GameoverScreen(game, score));
        }

        System.out.println("Level: " + gameLevel + " grav " + ball.gravityMultiplier + " force " + ball.forceMultiplier);

        updateScoreText();
        game.batch.end();
    }

    private void updateScoreText()
    {
        gLayput.setText(font, "" + score);
        font.draw(game.batch, gLayput, BounceGame.WIDTH/2 - gLayput.width/2, BounceGame.HEIGHT - 200);
    }

    private boolean isBallJustTouched()
    {
        if(Gdx.input.justTouched())
        {
            touchCollider.setActive(true);
            touchCollider.center = new Vector2(BounceGame.camera.getWorldPoints().x, BounceGame.camera.getWorldPoints().y);
            touchCollider.drawCollider(game.batch, Color.WHITE, 3);

            if(ball.getCollider().CollideWith(touchCollider))
                return true;

            return false;
        }
        else
        {
            touchCollider.setActive(false);
            return false;
        }
    }

    private void myGame()
    {
        if(timer >= 20)
        {
            gameLevel++;
            timer = 0;
            ball.gravityMultiplier += 0.5f;
            ball.forceMultiplier += 0.3f;
        }
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
