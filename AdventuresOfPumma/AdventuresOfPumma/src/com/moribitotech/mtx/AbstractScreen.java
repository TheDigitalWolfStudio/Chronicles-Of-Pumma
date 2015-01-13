/*******************************************************************************
 * Copyright 2012-Present, MoribitoTech
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.moribitotech.mtx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.settings.MtxLogger;

public abstract class AbstractScreen implements Screen {
	//
	protected static final String logTag = "MtxScreenLog";
	public static boolean logActive = true;

	// Game reference
	private Game game;

	// Initial
	private String screenName = "Untitled Screen";
	private final Stage stage;

	// Screen second counter (1 second tick)
	private float startTime = System.nanoTime();
	public static float SECONDS_TIME = 0;

	// Animation timer (If any animation is used)
	private float stateTime = 0;

	// Custom back button
	private boolean isBackButtonActive = false;

	/**
	 * Construct the screen
	 * <p>
	 * -Gives reference to game<br>
	 * -Creates stage<br>
	 * -Centers camera of stage<br>
	 * -Sets Input processor for stage (Gdx.input.setInputProcessor(stage))<br>
	 * -Calls setUpScreenElements (Good place the set views and iniatial
	 * elements)
	 * 
	 * @param game
	 *            the main game class
	 * @param screenName
	 *            the name of the screen
	 * */
	public AbstractScreen(Game game, String screenName) {
		super();
		this.game = game;
		if (screenName.equals("")) {
			this.screenName = "Untitled Screen";
		} else {
			this.screenName = screenName;
		}

		//
		stage = new Stage(AppSettings.SCREEN_W, AppSettings.SCREEN_H, false);
		stage.getCamera().position.set(AppSettings.SCREEN_W / 2,
				AppSettings.SCREEN_H / 2, 0);

		// Receive inputs from stage
		Gdx.input.setInputProcessor(stage);

		// INFO LOG
		MtxLogger.log(logActive, true, logTag, "SCREEN CONSTRUCTED: "
				+ getScreenName());
	}

	@Override
	public void render(float delta) {
		// Update screen clock (1 second tick)
		// ############################################################
		if (System.nanoTime() - startTime >= 1000000000) {
			SECONDS_TIME++;
			startTime = System.nanoTime();
		}

		// Update animation times
		// ############################################################
		stateTime += delta;

		// Snippet (Clear screen and give red color)
		// ############################################################
		
		Gdx.gl.glClearColor(0.2f, 0.2f,1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        
		// Update stage/actors logic (update() method in previous games)
		// ############################################################
		stage.act(delta);

		// Render drawings (draw()/render() methods in previous games)
		// ############################################################
		stage.draw();

		// Custom back button
		// ############################################################
		if (isBackButtonActive) {
			if (Gdx.input.isKeyPressed(Keys.BACK)) {
				keyBackPressed();
			}
		}
	}

	/**
	 * Set stage background. Sets the image (Adds to stage as image)
	 * 
	 * @param backgroundTextureRegion
	 * 
	 * */
	public void setBackgroundTexture(TextureRegion textureBackground) {
		Drawable tBg = new TextureRegionDrawable(textureBackground);
		Image imgbg = new Image(tBg, Scaling.stretch);
		imgbg.setFillParent(true);
		stage.addActor(imgbg);
		//
		MtxLogger.log(logActive, true, logTag, "SCREEN BG IMAGE SET: "
				+ getScreenName());
	}

	/**
	 * Set the back button active for the screen. Sets
	 * "Gdx.input.setCatchBackKey(true)" and override the method
	 * "keyBackPressed" to add desired functionality to back button
	 * 
	 * @param isBackButtonActive
	 *            to use or not to use the back button
	 * @see keyBackPressed
	 * 
	 * */
	public void setBackButtonActive(boolean isBackButtonActive) {
		Gdx.input.setCatchBackKey(true);
		this.isBackButtonActive = isBackButtonActive;
		//
		MtxLogger.log(logActive, true, logTag, "SCREEN BACK BUTTON SET: "
				+ getScreenName());
	}

	/**
	 * Override this method to do some function when back button pressed
	 * */
	public void keyBackPressed() {
	}

	/**
	 * Get the game class
	 * */
	public Game getGame() {
		return game;
	}

	/**
	 * Set the game class
	 * */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Get screen name
	 * */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Set screen name
	 * */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * Get screen name
	 * */
	public float getStartTime() {
		return startTime;
	}

	/**
	 * Get seconds since this screen constructed (EX: 3345 seconds)
	 * */
	public float getSecondsTime() {
		return SECONDS_TIME;
	}

	/**
	 * Set or reset sceconds
	 * */
	public void setSecondsTime(float secondsTime) {
		SECONDS_TIME = secondsTime;
	}

	/**
	 * Get delta added state time (Generally used for animations)
	 * */
	public float getStateTime() {
		return stateTime;
	}

	/**
	 * Set state time
	 * */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	/**
	 * Get if back button active
	 * */
	public boolean isBackButtonActive() {
		return isBackButtonActive;
	}

	/**
	 * Get stage of the screen
	 * */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Get screen time from start in format of HH:MM:SS. It is calculated from
	 * "secondsTime" parameter, reset that to get resetted time.
	 * */
	public String getScreenTime() {
		int seconds = (int) (SECONDS_TIME % 60);
		int minutes = (int) ((SECONDS_TIME / 60) % 60);
		int hours = (int) ((SECONDS_TIME / 3600) % 24);
		String secondsStr = (seconds < 10 ? "0" : "") + seconds;
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return new String(hoursStr + ":" + minutesStr + ":" + secondsStr);
	}

	@Override
	public void resize(int width, int height) {
		MtxLogger.log(logActive, true, logTag, "SCREEN RESIZE: "
				+ getScreenName());
	}

	@Override
	public void show() {
		MtxLogger.log(logActive, true, logTag, "SCREEN SHOW: "
				+ getScreenName());
	}

	@Override
	public void hide() {
		MtxLogger.log(logActive, true, logTag, "SCREEN HIDE: "
				+ getScreenName());
	}

	@Override
	public void pause() {
		MtxLogger.log(logActive, true, logTag, "SCREEN PAUSE: "
				+ getScreenName());
	}

	@Override
	public void resume() {
		MtxLogger.log(logActive, true, logTag, "SCREEN RESUME: "
				+ getScreenName());
	}

	@Override
	public void dispose() {
		stage.dispose();

		// Add items here for log
		String strDisposedItems = "Stage, ";
		MtxLogger.log(logActive, true, logTag, "$$ :: SCREEN DISPOSING: "
				+ getScreenName() + " DISPOSED: " + strDisposedItems);
	}
}
