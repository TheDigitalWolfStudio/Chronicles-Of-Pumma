package com.digitalwolf.screenhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.screens.GameScreen;
import com.digitalwolf.screens.MainMenuScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.models.base.TableModel;
import com.moribitotech.mtx.settings.AppSettings;

public class GameScreenGamePauseMenu extends GameScreenAbstractMenu{

	private TableModel pauseMenuTable;
	private ButtonGame mainMenuButton,resumeButton, quitButton;
	
	@Override
	public void setUpMenu(final GameScreen gameScreen) {
		pauseMenuTable = new TableModel(Assets.transparent,
				AppSettings.WORLD_WIDTH , AppSettings.WORLD_HEIGHT);
		pauseMenuTable.setPosition(0 , AppSettings.WORLD_HEIGHT + pauseMenuTable.getHeight());
			
		//MAIN MENU BUTTON ON THE RIGHT SIDE
		mainMenuButton = MenuCreator.createCustomGameButton(null,
				Assets.button_mainmenu, Assets.button_mainmenu2);
		

		mainMenuButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				sendAwayMenu(gameScreen);
				gameScreen.getGame().setScreen(new MainMenuScreen(gameScreen.getGame(), "MainMenuScreen"));
			}
		});
		
		resumeButton = MenuCreator.createCustomGameButton(null,
				Assets.button_continue, Assets.button_continue2);
		
		
		resumeButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				sendAwayMenu(gameScreen);			
				GameScreen.state = GameScreen.GAME_RUNNING;
			}
		});
		
		
		quitButton = MenuCreator.createCustomGameButton(null,
				Assets.button_exit, Assets.button_exit2);
		
		quitButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				Gdx.app.exit();
			}
		});
		
		float dipRatioWidth = 174 * AppSettings.getWorldSizeRatio();
		float dipRatioHeight = 74* AppSettings.getWorldSizeRatio();
		float dipPadding = 5.0f * AppSettings.getWorldSizeRatio();

		// #######################################
		//tableMenu.add(shootingActor).size(dipRatioWidth, dipRatioHeight).pad(dipPadding);
		pauseMenuTable.add(mainMenuButton).size(dipRatioWidth, dipRatioHeight).pad(dipPadding);
		pauseMenuTable.row();
		pauseMenuTable.add(resumeButton).size(dipRatioWidth, dipRatioHeight).pad(dipPadding);
		pauseMenuTable.row();
		pauseMenuTable.add(quitButton).size(dipRatioWidth, dipRatioHeight).pad(dipPadding);
		
	}

	@Override
	public void sendInMenu(GameScreen gameScreen) {
		gameScreen.getStage().addActor(pauseMenuTable);
    	pauseMenuTable.addAction(Actions.moveTo(0, AppSettings.WORLD_HEIGHT- pauseMenuTable.getHeight(), 0.5f));
	}

	@Override
	public void sendAwayMenu(GameScreen gameScreen) {
		pauseMenuTable.addAction(Actions.moveTo(0 , AppSettings.WORLD_HEIGHT + pauseMenuTable.getHeight(), 0.5f));
	}

}
