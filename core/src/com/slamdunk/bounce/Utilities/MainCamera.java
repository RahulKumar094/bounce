package com.slamdunk.bounce.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Rahul_K on 8/23/2019.
 */

public class MainCamera
{
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private int width,height;

    public MainCamera(int width, int height)
    {
        this.width = width;
        this.height = height;
        camera = new OrthographicCamera(width, height);
        viewport = new StretchViewport(width, height, camera);
        viewport.apply(true);
    }

    public void resize(int width, int height)
    {
        viewport.update(width,height);
    }

    public Matrix4 combined()
    {
        return camera.combined;
    }

    public Vector3 getWorldPoints()
    {
        Vector3 screenPoints = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        return camera.unproject(screenPoints);
    }
}
