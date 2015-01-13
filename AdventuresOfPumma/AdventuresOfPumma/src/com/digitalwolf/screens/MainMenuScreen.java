/*
 * This is the Main Menu Screen. As soon as the Game class set up the game data and load the assets, it sets the screen to MainMenuScreen
 */

package com.digitalwolf.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.gamedata.GameData;
import com.moribitotech.mtx.AbstractScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.IScreen;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.effects.EffectCreator;
import com.moribitotech.mtx.models.base.EmptyAbstractActor;
import com.moribitotech.mtx.models.base.EmptyAbstractActorLight;
import com.moribitotech.mtx.models.base.TableModel;
import com.moribitotech.mtx.settings.AppSettings;
import com.mtx.scene2dactors.TestActor;

public class MainMenuScreen extends AbstractScreen implements IScreen{

	private EmptyAbstractActor testActor;
	private ButtonGame timerActor;
	private EmptyAbstractActorLight logo;
	private EmptyAbstractActorLight rateOnGooglePlay;
	
	public MainMenuScreen(Game game, String screenName) {
		super(game, screenName);
		//
		 
		setUpScreenElements();
		setUpInfoPanel();
		setUpActors();
		setUpMenu();
	}

	@Override
	public void setUpScreenElements() {
		setBackgroundTexture(Assets.bg);
		setBackButtonActive(true);
		
		//Start Playing the Music if the sound is enabled
		if (GameData.getSoundEnabled()) Assets.music.play();
	}

	@Override
	public void setUpInfoPanel() {
		// Game Logo
		logo = new EmptyAbstractActorLight(358, 188, true);
		logo.setPosition(AppSettings.SCREEN_W -1.7f*logo.getWidth(), AppSettings.WORLD_HEIGHT - 1.1f*logo.getHeight());		
		logo.setTextureRegion(Assets.logo, true);
		EffectCreator.create_SC_BTN(logo, 1.0f, 1.0f, 1.0f, null, false);		
		getStage().addActor(logo);

	}

	private void setUpActors() {
		
		testActor = new EmptyAbstractActor(50, 100, true);
		testActor.setPosition(
				240 * AppSettings.getWorldPositionXRatio()
						- testActor.getWidth() / 2.0f,
				140 * AppSettings.getWorldPositionYRatio()
						- testActor.getHeight() / 2.0f);
		testActor.setAnimation(Assets.pummaStill, true, true);
		getStage().addActor(testActor);
		
		timerActor = MenuCreator.createCustomGameButton(Assets.smallFont, Assets.tooltip, Assets.tooltip,360,60,true);		
		timerActor.setPosition(testActor.getX()+testActor.getWidth(), testActor.getY()+testActor.getHeight());
		timerActor.setTextPosXY(10, 52);
		timerActor.setText("Keep Patience.. Run!", true);
		
		getStage().addActor(timerActor);


	}

	@Override
	public void setUpMenu() {
		
		// // #######################################
		TableModel tableMenu = new TableModel(null,
				300*AppSettings.getWorldPositionXRatio(), 2*AppSettings.WORLD_HEIGHT/3);
		
		tableMenu.setPosition(AppSettings.WORLD_WIDTH + tableMenu.getWidth(),
				-AppSettings.getWorldPositionYRatio());
		
		tableMenu.addAction(Actions.moveTo(AppSettings.WORLD_WIDTH - tableMenu.getWidth(), 0, 0.5f));

		// #######################################
		ButtonGame startGameButton = MenuCreator.createCustomGameButton(null, Assets.startgame_button, Assets.startgame_button2);
		
		startGameButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				//
				getGame().setScreen(new LevelSelectScreen(getGame(), "Game Screen"));
			}
		});

		// #########################################
		ButtonGame highScoresButton = MenuCreator.createCustomGameButton(null,
				Assets.button_highscore, Assets.button_highscore2);
		
		highScoresButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				//
				getGame().setScreen(new HighScoresScreen(getGame(), "High Scores Screen"));
			}
		});
		
		// #######################################
		ButtonGame creditsButton = MenuCreator.createCustomGameButton(
				null, Assets.button_credits,
				Assets.button_credits2);
	
		creditsButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new CreditsScreen(getGame(), "Credits Screen"));
		
			}
		});

		// #########################################
		final TestActor btnOn = new TestActor(90, 90, true);
		TextureRegion temp = (GameData.getSoundEnabled())?Assets.button_on:Assets.button_off;
		btnOn.setTextureRegion(temp, true);
		
		btnOn.addListener(new ActorGestureListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchUp(event, x, y, pointer, button);
                  //
						if(GameData.getSoundEnabled()){
							if(Assets.music.isPlaying())
							Assets.music.pause();
							btnOn.setTextureRegion(Assets.button_off, true);
							GameData.setSoundEnabled(false);
						}
						else{
							if(!(Assets.music.isPlaying()))
								Assets.music.play();
							btnOn.setTextureRegion(Assets.button_on, true);
							GameData.setSoundEnabled(true);
						}
					}
				});
				
				// #########################################
		rateOnGooglePlay = new EmptyAbstractActorLight(120, 120, true);
		rateOnGooglePlay.setTextureRegion(Assets.rateOnGooglePlay, true);
		rateOnGooglePlay.setPosition(10*AppSettings.getWorldSizeRatio(), 10*AppSettings.getWorldSizeRatio());
		rateOnGooglePlay.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				//
				Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.digitalwolf.adventuresOfPumma");
			}
		});

		getStage().addActor(rateOnGooglePlay);
		
		//
		float dipRatioWidth = 1.1f* 174 * AppSettings.getWorldSizeRatio();
		float dipRatioHeight =  1.1f* 74 * AppSettings.getWorldSizeRatio();
		float padding = 1.0f * AppSettings.getWorldSizeRatio();

		// #######################################
		
		
		tableMenu.add(startGameButton).size(dipRatioWidth, dipRatioHeight).pad(padding);
		tableMenu.row();
		tableMenu.add(highScoresButton).size(dipRatioWidth, dipRatioHeight).pad(padding);
		tableMenu.row();
		tableMenu.add(creditsButton).size(dipRatioWidth, dipRatioHeight).pad(padding);
		tableMenu.row();
		tableMenu.add(btnOn).padLeft(padding*20).size(80* AppSettings.getWorldSizeRatio(), 80* AppSettings.getWorldSizeRatio());
		
		getStage().addActor(tableMenu);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		if(getSecondsTime()%2==0){
			EffectCreator.create_SC(logo, 1.0f, 1.0f, 5, getStage(), false);
			}
			else{
				EffectCreator.create_SC(logo, 0.6f, 0.6f, 5, getStage(), false);
			}
		if(getSecondsTime()%3==0){
			EffectCreator.create_SC(rateOnGooglePlay, 1.2f, 1.2f, 2, getStage(), false);
		}
		else{
			EffectCreator.create_SC(rateOnGooglePlay, 0.8f, 0.8f, 2, getStage(), false);
		}
	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		Gdx.app.exit();		
    }
}
