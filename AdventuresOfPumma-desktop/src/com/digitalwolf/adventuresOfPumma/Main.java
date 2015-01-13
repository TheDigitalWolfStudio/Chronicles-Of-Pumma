package com.digitalwolf.adventuresOfPumma;

/*
 * Chronicles of Pumma is a 2D Open Source game written in LibGDX by Digital Wolf Studio. This game is meant for the
 * purpose of learning. You may use the source code to create your own games.
 */
/*
 * 
 */
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.digitalwolf.adventuresOfPumma.AdventuresOfPumma;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ChroniclesOfPumma";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new AdventuresOfPumma(), cfg);
	}
}
