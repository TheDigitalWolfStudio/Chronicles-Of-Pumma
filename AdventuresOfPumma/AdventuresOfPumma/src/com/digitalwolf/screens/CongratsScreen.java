package com.digitalwolf.screens;

import com.badlogic.gdx.Game;
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

public class CongratsScreen extends AbstractScreen implements IScreen{

	EmptyAbstractActor testActor;
	EmptyAbstractActorLight logo;
	
	public CongratsScreen(Game game, String screenName) {
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
	}

	@Override
	public void setUpInfoPanel() {
		logo = new EmptyAbstractActorLight(2*359/3, 2*188/3, true);
		logo.setPosition(AppSettings.SCREEN_W -1.2f*logo.getWidth(), AppSettings.WORLD_HEIGHT - 1.4f*logo.getHeight());		
		logo.setTextureRegion(Assets.logo, true);
		EffectCreator.create_SC_BTN(logo, 1.0f, 1.0f, 1.0f, null, false);		
		getStage().addActor(logo);
	}

	private void setUpActors() {
		
		testActor = new EmptyAbstractActor(500*AppSettings.getWorldSizeRatio(), 400*AppSettings.getWorldSizeRatio(), true);
		testActor.setPosition(
				60 * AppSettings.getWorldPositionXRatio(),
				60 * AppSettings.getWorldPositionYRatio());
		testActor.setTextureRegion(Assets.congrats_screen, true);
		getStage().addActor(testActor);
		
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
							btnOn.setTextureRegion(Assets.button_off, true);
							GameData.setSoundEnabled(false);
						}
						else{
							btnOn.setTextureRegion(Assets.button_on, true);
							GameData.setSoundEnabled(true);
						}
					}
				});
				
	
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
		
		getStage().addActor(tableMenu);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		getGame().setScreen(new MainMenuScreen(getGame(), "MainMenu Screen"));
	}
}
