package com.slamdunk.bounce.Utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rahul_K on 8/23/2019.
 */

public class CircleCollider
{
    public Vector2 center;
    public float radius;
    private float tempRadius;
    private Texture blank;

    public CircleCollider(Vector2 center, float radius)
    {
        this.center = center;
        this.radius = radius;

        tempRadius = radius;
        blank = new Texture("blank.png");
    }

    public void Move(Vector2 center)
    {
        this.center = center;
    }

    public boolean CollideWith(CircleCollider col)
    {
        return Math.sqrt(Math.pow(col.center.x - center.x,2) + Math.pow(col.center.y - center.y,2)) <= col.radius + radius;
    }

    public void setActive(boolean active)
    {
        if(!active)
            radius = 0;
        else
            radius = tempRadius;
    }

    public void drawCollider(Batch batch, Color color, int lineWidth)
    {
        batch.setColor(color);

        for(int i = 0; i <= 360; i++)
        {
            float x = (float)Math.cos(i) * radius + center.x;
            float y = (float)Math.sin(i) * radius + center.y;
            batch.draw(blank, x, y, lineWidth, lineWidth);
        }

        batch.setColor(Color.WHITE);
    }

}
