package com.slamdunk.bounce.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.slamdunk.bounce.BounceGame;
import com.slamdunk.bounce.Utilities.CircleCollider;

/**
 * Created by Rahul_K on 8/23/2019.
 */

public class Ball
{
    private static final float VEL_DAMPER = 10f;
    private static final float FORCE = 500f;
    private static final float GRAVITY = -500f;
    private static final int SIZE = 300;
    private float x,y;
    private Vector2 center;
    private Texture tex;
    private Texture blank;
    private CircleCollider collider;
    private Vector2 velocity;
    private float rotation = 0;
    public static float gravityMultiplier;
    public static float forceMultiplier;

    private Sprite ballSprite;

    public Ball()
    {
        tex = new Texture("ball.png");
        blank = new Texture("blank.png");

        ballSprite = new Sprite(tex);
        ballSprite.setSize(SIZE, SIZE);
        ballSprite.setOrigin(ballSprite.getWidth()/2, ballSprite.getHeight()/2);

        x = BounceGame.WIDTH/2 - SIZE/2;
        y = BounceGame.HEIGHT - SIZE/2 - 200;

        center = new Vector2(0, 0);
        collider = new CircleCollider(center, SIZE/2);
        velocity = new Vector2(0, 0);

        gravityMultiplier = 1;
        forceMultiplier = 1;
    }

    public void renderAndUpdate(Batch batch, float delta)
    {
        center.x = x + SIZE/2;
        center.y = y + SIZE/2;

        drawSprite(batch);
        showCenter(batch);
        movement(delta);

        collider.Move(center);
        collider.drawCollider(batch, Color.GREEN, 3);
    }

    private void drawSprite(Batch batch)
    {
        ballSprite.setPosition(x,y);
        ballSprite.setRotation(rotation);
        ballSprite.draw(batch);
    }

    private void showCenter(Batch batch)
    {
        batch.setColor(Color.BLACK);
        batch.draw(blank, center.x, center.y, 5, 5);
        batch.setColor(Color.WHITE);
    }

    private void movement(float delta)
    {
        dampHorizontalVelocity(delta);
        x += velocity.x * delta;

        //gravity
        velocity.y += gravityMultiplier * GRAVITY * delta;
        y += velocity.y * delta;

        //wall reflection
        if(x <= 0 || x >= BounceGame.WIDTH - SIZE)
            velocity.x = -velocity.x;
    }

    public Vector2 getCenter()
    {
        return center;
    }

    public CircleCollider getCollider()
    {
        return collider;
    }

    public void addForce(Vector2 collisionPoint)
    {
        Vector2 collisionVector = new Vector2(center.x - collisionPoint.x, center.y - collisionPoint.y).nor();
        velocity.x = collisionVector.x * forceMultiplier * FORCE;
        velocity.y = Math.abs(collisionVector.y) * FORCE * 1.5f * forceMultiplier;
    }

    private void dampHorizontalVelocity(float delta)
    {
        if(Math.abs(velocity.x) > 1f)
        {
            if(velocity.x > 0)
                velocity.x -= VEL_DAMPER * delta;
            else
                velocity.x += VEL_DAMPER * delta;

            rotation -= velocity.x * delta;
        }
        else
            velocity.x = 0;
    }

    public float getSize()
    {
        return SIZE;
    }

}
