package com.slamdunk.bounce.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.slamdunk.bounce.BounceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BounceGame.WIDTH/3;
		config.height = BounceGame.HEIGHT/3;
		new LwjglApplication(new BounceGame(), config);
	}
}
